package gt.tribal.app.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import gt.tribal.app.R
import gt.tribal.app.databinding.FragmentFavoritesBinding
import gt.tribal.app.domain.response.PhotoItemHome
import gt.tribal.app.domain.response.ProfileImageData
import gt.tribal.app.domain.response.UrlData
import gt.tribal.app.domain.response.UserData
import gt.tribal.app.ui.PhotoEntity
import gt.tribal.app.ui.activity.ProfileActivity
import gt.tribal.app.ui.activity.USERNAME
import gt.tribal.app.ui.adapter.FavoritesAdapter
import gt.tribal.app.ui.adapter.PhotoGridAdapter
import gt.tribal.app.viewmodel.PhotoViewModel
import gt.tribal.core.coroutines.Completable
import gt.tribal.core.extension.observe
import org.koin.androidx.viewmodel.ext.android.viewModel
import gt.tribal.core.coroutines.Result
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment(), FavoritesAdapter.DeleteListener {

    private val viewModel: PhotoViewModel by viewModel()
    private lateinit var binding: FragmentFavoritesBinding
    private var items = ArrayList<PhotoEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        with(viewModel) {
            observe(getPhotoLiveData, ::getPhotosDataobserver)
            observe(deletePhotoLiveData, ::deletePhotoDataObserver)
        }
        viewModel.getPhotos()

        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }


    private fun getPhotosDataobserver(result: Result<List<PhotoEntity>>?){
        when (result) {
            is Result.OnLoading -> {
            }
            is Result.OnSuccess -> {
                items = ArrayList(result.value)
                if(items.isEmpty()){
                    showEmptyList()
                }else{
                    setupAdapter(items)
                }

            }
            is Result.OnError -> {
                showEmptyList()
                Log.i("OnError","OnError")
            }
            else -> {

            }
        }
    }

    private fun setupAdapter(itemsPhoto: ArrayList<PhotoEntity>){

        var itemsFavorites = ArrayList<PhotoItemHome>()

        itemsPhoto.map {
            var urlData = UrlData("",it.urlPhoto,"","","")
            var profileImageData =ProfileImageData("",it.userPhoto,"")
            var userData = UserData("","",it.nameUser,profileImageData,it.likes,0)
            var  photoItemHome = PhotoItemHome(it.id, "","","",
                "","", "","","", urlData,
                it.likes, userData)
            itemsFavorites.add(photoItemHome)
        }
        // Choose your own preferred column width
        listView.hasFixedSize()
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, 1)
        var adapter = FavoritesAdapter(context!!, itemsFavorites)
        adapter.setClickListener(this)

        // initialize your items array
        listView.layoutManager = staggeredGridLayoutManager
        listView.adapter = adapter
    }

    override fun onDeletePhoto(position: Int) {
        viewModel.deletePhoto(items[position].id)
    }

    private fun deletePhotoDataObserver(result: Completable?){
        when (result) {
            is Completable.OnComplete -> {
                Toast.makeText(context, getString(R.string.delete_success), Toast.LENGTH_LONG).show()
                viewModel.getPhotos()
            }
            is Completable.OnLoading -> {
            }
            is Completable.OnError -> {
                Toast.makeText(context, getString(R.string.delete_error), Toast.LENGTH_LONG).show()
            }
            is Completable.OnCancel -> {
            }
            else -> {

            }
        }
    }

    private fun showEmptyList(){
        binding.imageEmpty.visibility = View.VISIBLE
        binding.textEmpty.visibility = View.VISIBLE
        binding.listView.visibility = View.GONE

    }

    override fun onSelected(position: Int) {
        val intent = Intent(activity, ProfileActivity::class.java)
        intent.putExtra(USERNAME,items[position].nameUser)
        startActivity(intent)
    }
}
