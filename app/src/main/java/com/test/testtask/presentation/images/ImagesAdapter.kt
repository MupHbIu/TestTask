package com.test.testtask.presentation.images

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.testtask.R
import com.test.testtask.domain.entities.Image

class ImagesAdapter: RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {
    companion object {
        const val TYPE_ITEM = 0
        const val TYPE_LOAD = 1
    }

    var listener: ImagesAdapterListener? = null
    private var data = mutableListOf<Image>()
    private var isLoaderVisible = false

    interface ImagesAdapterListener {
        fun loadNextPage()
    }

    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
    class LoadViewHolder(view: View): ViewHolder(view) {}
    class ImageViewHolder(view: View): ViewHolder(view) {
        private val image = view.findViewById<ImageView>(R.id.itemImage_imgae)
        private val author = view.findViewById<TextView>(R.id.itemImage_textView)

        fun bindItems(data: Image) {
            // TODO: - Set resource to ImageView
            Picasso.get()
                    .load(data.download_url)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
                    .fit()
                    .centerInside()
                    .into(image)
            author.text = data.author
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(isLoaderVisible && isLastItem(position))
            return TYPE_LOAD
        return TYPE_ITEM
    }

    private fun isLastItem(position: Int): Boolean = position == data.size - 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType) {
            TYPE_LOAD -> LoadViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image_load, parent, false))
            else -> ImageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder) {
            is ImageViewHolder -> { holder.bindItems(data[position]) }
            is LoadViewHolder -> { listener?.loadNextPage() }
        }
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    fun setData(data: List<Image>) {
        this.data = data.toMutableList()
        notifyDataSetChanged()
    }

    fun addData(data: List<Image>) {
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun addLoading() {
        if(!isLoaderVisible) {
            isLoaderVisible = true
            data.add(Image("1", "1"))
            notifyItemInserted(data.size - 1)
        }
    }

    fun removeLoading() {
        if(isLoaderVisible) {
            isLoaderVisible = false
            val position: Int = data.size - 1
            val item: Image = data[position]
            data.removeAt(position)
            notifyItemRemoved(position)
        }
    }

}