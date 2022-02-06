package com.linuxh2o.retrofitwithkotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.linuxh2o.retrofitwithkotlin.R
import com.linuxh2o.retrofitwithkotlin.models.Post

class PostAdapter(val posts: List<Post>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val id = itemView.findViewById<TextView>(R.id.tvId)
        val title = itemView.findViewById<TextView>(R.id.tvTitle)
        val postBody = itemView.findViewById<TextView>(R.id.tvBlogBody)

        fun bind(post: Post){
            id.text = "Id: ${post.id}"
            title.text = post.title
            postBody.text = post.body
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_item_view, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}