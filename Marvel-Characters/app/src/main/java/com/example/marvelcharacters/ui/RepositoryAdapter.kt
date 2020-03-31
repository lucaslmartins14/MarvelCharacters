package com.example.marvelcharacters.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvelcharacters.R
import com.example.marvelcharacters.model.Results
import kotlinx.android.synthetic.main.item_card.view.*

class RepositoryAdapter(private val context: Context) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {
    private var itemList: List<Results> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false)
        return RepositoryViewHolder(view)
    }

    fun setItems(itemList: List<Results>){
        this.itemList = itemList
        notifyDataSetChanged()
    }
    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bindView(itemList.get(position))
    }

    class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.textView
        val ivThumb = itemView.imageView
        fun bindView(item: Results) {
            tvTitle.text = item.title
            Glide.with(itemView).load(item.thumbnail.path).into(ivThumb)
        }
    }
}