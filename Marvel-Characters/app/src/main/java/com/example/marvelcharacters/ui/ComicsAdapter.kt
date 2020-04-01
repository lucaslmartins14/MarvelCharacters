package com.example.marvelcharacters.ui

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
import com.example.marvelcharacters.R
import com.example.marvelcharacters.model.Results
import kotlinx.android.synthetic.main.item_card.view.*

class ComicsAdapter(private val context: Context) :
    RecyclerView.Adapter<ComicsAdapter.ComicsViewHolder>() {
    private var itemList: List<Results> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false)
        return ComicsViewHolder(view)
    }

    fun setItems(itemList: List<Results>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        holder.bindView(itemList.get(position))
    }

    class ComicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTitle = itemView.textView
        val ivThumb = itemView.imageView
        fun bindView(item: Results) {
            tvTitle.text = item.title
            Glide.with(itemView)
                .load(item.thumbnail.path + "/portrait_uncanny.${item.thumbnail.extension}")
                .listener(object :
                    RequestListener<Drawable> {
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.progressBar2.visibility = View.GONE
                        return false
                    }

                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: com.bumptech.glide.request.target.Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        itemView.progressBar2.visibility = View.GONE
                        return false
                    }
                }).into(ivThumb)
        }
    }
}