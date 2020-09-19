package com.soni.movietest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soni.movietest.R
import com.soni.movietest.extensions.clicklistener.forItemClickListener
import com.soni.movietest.extensions.glide.loadImg
import com.soni.movietest.extensions.view.setOnClickListenerCooldown
import com.soni.movietest.webservices.ApiClient
import com.soni.movietest.webservices.responsebean.Result
import kotlinx.android.synthetic.main.item_movies_list.view.*

/**
 * Created By Tejas Soni
 * tejashsoni51331@gmail.com
 */
class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MyViewHolder>() {
    private var onListItemClickListener : forItemClickListener<Result>?=null
    private var movieList: ArrayList<Result> = arrayListOf()

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(result: Result) {
            itemView.apply {
                tvMovieName.text = result.title
                loadImg(ApiClient.MOVIE_URL + result.posterPath, ivMovie)
                setOnClickListenerCooldown { onListItemClickListener?.forItem(result) }
            }
        }

    }

    fun onItemClick(listener: forItemClickListener<Result>){
        onListItemClickListener =listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movies_list, parent, false)
        )
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    fun addData(results: ArrayList<Result>?) {
        movieList = results!!
        this.notifyDataSetChanged()
    }
}