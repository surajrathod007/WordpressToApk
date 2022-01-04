package com.surajrathod.surajtutz

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class HomeRecyclerAdapter(val context: Context, val itemList: ArrayList<Post>) :
    RecyclerView.Adapter<HomeRecyclerAdapter.HomeViewHolder>() {

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val cardView: View = view.findViewById(R.id.cardViewHome)
        val imgPost: ImageView = view.findViewById(R.id.imgPost)
        val txtPostTitle: TextView = view.findViewById(R.id.txtPostTitle)
        val cardView: View = view.findViewById(R.id.cardViewHome)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        //this is where we will decide how many items should be display in our list like 5,10 etc.


        //here will create the view of list
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_home_single_row, parent, false)


        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        val post = itemList[position]


        holder.txtPostTitle.text = post.PostTitle

        //now set the image
        Picasso.get().load(post.postImage).error(R.drawable.ic_launcher_background)
            .into(holder.imgPost);

        //onlick

        holder.cardView.setOnClickListener {
            //open new activity

            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(
                "title",
                post.PostTitle
            ) //this will send to the post request to the server
            intent.putExtra("content", post.postContent)
            intent.putExtra("jetpack_featured_media_url", post.postImage)

            context.startActivity(intent) //start the activity, in adapter we have to use context
        }


    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}