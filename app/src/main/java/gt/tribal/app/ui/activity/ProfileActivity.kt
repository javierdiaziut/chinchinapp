package gt.tribal.app.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import gt.tribal.app.R
import gt.tribal.app.databinding.ActivityProfileBinding
import gt.tribal.app.domain.response.PhotoItemHome
import gt.tribal.app.ui.PhotoEntity
import gt.tribal.app.ui.adapter.UserPhotosAdapter
import gt.tribal.app.viewmodel.ProfileUserViewModel
import gt.tribal.core.coroutines.Result
import gt.tribal.core.extension.observe
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.item_photo.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

const val USERNAME = "username"

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileUserViewModel by viewModel()
    private var items = ArrayList<PhotoItemHome>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username=intent.getStringExtra(USERNAME)

        with(viewModel) {
            observe(getUserPhotosLiveData, ::userPhotosDataObserver)
        }

        viewModel.getUserPhotos(username)
    }

    private fun userPhotosDataObserver(result: Result<List<PhotoItemHome>>?){
        when (result) {
            is Result.OnLoading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is Result.OnSuccess -> {
                binding.progressBar.visibility = View.GONE
                items = ArrayList(result.value)
                setupAdapter(items)

                Glide.with(this)
                    .load(items[0].user.profile_image.large)
                    .into(binding.userAvatar)
                binding.userTotalPhotos.text = items[0].user.total_photos.toString()
                binding.userTotalCollections.text = items[0].user.total_photos.toString()
                binding.userTotalLikes.text = items[0].user.total_likes.toString()

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
        listView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, 1)
        var adapter = UserPhotosAdapter(this, itemsPhoto)

        // initialize your items array
        listView.layoutManager = staggeredGridLayoutManager
        listView.adapter = adapter
    }
}
