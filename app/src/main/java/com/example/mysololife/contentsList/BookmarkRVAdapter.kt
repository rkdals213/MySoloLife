package com.example.mysololife.contentsList

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mysololife.R

class BookmarkRVAdapter(
    private val context: Context,
    private val items: List<ContentModel>,
    private val itemKeyList: List<String>,
    private val bookmarkIdList: MutableList<String>
) : RecyclerView.Adapter<BookmarkRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.content_rv_item, parent, false)

        Log.d("BookmarkRVAdapter", itemKeyList.toString())
        Log.d("BookmarkRVAdapter", bookmarkIdList.toString())
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: BookmarkRVAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position], itemKeyList[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(item: ContentModel, itemKey: String) {
            itemView.setOnClickListener {
                Toast.makeText(context, item.title, Toast.LENGTH_LONG).show()
                val intent = Intent(context, ContentShowActivity::class.java)
                intent.putExtra("url", item.webUrl)
                itemView.context.startActivity(intent)
            }

            val contentTitle = itemView.findViewById<TextView>(R.id.textArea)
            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)
            val bookmarkArea = itemView.findViewById<ImageView>(R.id.bookmarkArea)

            if (bookmarkIdList.contains(itemKey)) {
                bookmarkArea.setImageResource(R.drawable.bookmark_color)
            } else {
                bookmarkArea.setImageResource(R.drawable.bookmark_white)
            }

            contentTitle.text = item.title

            Glide.with(context)
                .load(item.imageUrl)
                .into(imageViewArea)
        }
    }
}