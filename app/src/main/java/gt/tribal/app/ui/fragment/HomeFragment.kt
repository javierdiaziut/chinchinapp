package gt.tribal.app.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem
import gt.tribal.app.R
import gt.tribal.app.databinding.FragmentHomeBinding
import gt.tribal.app.domain.response.PhotoItemHome
import gt.tribal.app.ui.PhotoEntity
import gt.tribal.app.ui.activity.ProfileActivity
import gt.tribal.app.ui.activity.USERNAME
import gt.tribal.app.ui.adapter.PhotoGridAdapter
import gt.tribal.app.viewmodel.HomeViewModel
import gt.tribal.core.coroutines.Completable
import gt.tribal.core.coroutines.Result
import gt.tribal.core.extension.observe
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(), PhotoGridAdapter.ItemUserListener {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding
    private var items = ArrayList<PhotoItemHome>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        with(viewModel) {
            observe(homeLiveData, ::homeDataobserver)
            observe(saveFavoriteLiveData, ::savePhotoDataobserver)
        }
        viewModel.getHomePhotos()

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun homeDataobserver(result: Result<List<PhotoItemHome>>?){
        when (result) {
            is Result.OnLoading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is Result.OnSuccess -> {
                binding.progressBar.visibility = View.GONE
                items = ArrayList(result.value)
                setupAdapter(items)
            }
            is Result.OnError -> {
                binding.progressBar.visibility = View.GONE

            }
            else -> {

            }
        }
    }

    private fun setupAdapter(itemsPhoto: ArrayList<PhotoItemHome>){
        // Choose your own preferred column width
        listView.hasFixedSize()
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, 1)
        var adapter = PhotoGridAdapter(context!!, itemsPhoto)
        adapter.setClickListener(this)

        // initialize your items array
        listView.layoutManager = staggeredGridLayoutManager
        listView.adapter = adapter
    }

    private fun savePhotoDataobserver(result: Completable?){
        when (result) {
            is Completable.OnComplete -> {
                Toast.makeText(context, getString(R.string.fav_success),Toast.LENGTH_LONG).show()
            }
            is Completable.OnLoading -> {
            }
            is Completable.OnError -> {
                Toast.makeText(context, getString(R.string.fav_error),Toast.LENGTH_LONG).show()
            }
            is Completable.OnCancel -> {
            }
            else -> {

            }
        }
    }

    override fun onSaveUser(position: Int) {
        viewModel.savePhoto(PhotoEntity(items[position].id, items[position].urls.full, true,
            items[position].user.username, items[position].likes, items[position].user.profile_image.medium))

        items[position].isFavorite = true
    }

    override fun onSelected(position: Int) {
        val intent = Intent(activity, ProfileActivity::class.java)
        intent.putExtra(USERNAME,items[position].user.username)
        startActivity(intent)
    }
}
