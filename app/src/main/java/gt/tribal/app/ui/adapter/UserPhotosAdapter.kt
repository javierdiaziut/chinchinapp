package gt.tribal.app.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import gt.tribal.app.R
import gt.tribal.app.domain.response.PhotoItemHome
import kotlinx.android.synthetic.main.item_photo.view.*

class UserPhotosAdapter(val context: Context, var data: ArrayList<PhotoItemHome>): RecyclerView.Adapter<UserPhotosAdapter.ViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_user_photo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        var width = 0
        var height = 0
        if(position % 2 == 0){
            width = 300
            height = 400
        }else{
            width = 300
            height = 300
        }
        Glide.with(context)
            .load(data[position].urls.full).override(width, height)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.itemView.progressBar.visibility = View.GONE
                    return false
                }
            })
            .into(holder.itemView.imageviewWidget)

    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)

}