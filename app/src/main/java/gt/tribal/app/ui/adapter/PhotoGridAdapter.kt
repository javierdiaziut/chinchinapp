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

class PhotoGridAdapter(val context: Context, var data: ArrayList<PhotoItemHome>): RecyclerView.Adapter<PhotoGridAdapter.ViewHolder>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var itemUserListener: ItemUserListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.item_photo, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        holder.itemView.nameUserItem.text = data[position].user.name
        holder.itemView.likesNumber.text = String.format(
            context.resources.getString(R.string.likes_number),
            data[position].likes
        )

        Glide.with(context)
            .load(data[position].user.profile_image.medium)
            .into(holder.itemView.userAvatar)

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
            .load(data[position].urls.full).
                override(width, height).listener(object : RequestListener<Drawable> {
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

        if(data[position].isFavorite){
            holder.itemView.imageFav.setImageDrawable(context.getDrawable(R.drawable.ic_favorite))
        }
        holder.itemView.imageFav.setOnClickListener{
            itemUserListener!!.onSaveUser(position)
            holder.itemView.imageFav.setImageDrawable(context.getDrawable(R.drawable.ic_favorite))
        }

        holder.itemView.userAvatar.setOnClickListener {
            itemUserListener!!.onSelected(position)
        }
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface ItemUserListener {
        fun onSaveUser(position: Int)
        fun onSelected(position: Int)
    }
    // allows clicks events to be caught
    internal fun setClickListener(itemUserListener: PhotoGridAdapter.ItemUserListener) {
        this.itemUserListener = itemUserListener
    }
}