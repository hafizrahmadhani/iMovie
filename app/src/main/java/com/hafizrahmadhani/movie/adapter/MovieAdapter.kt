package com.hafizrahmadhani.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hafizrahmadhani.movie.R
import com.hafizrahmadhani.movie.datamodel.DataModelMovieDetail
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val movie: ArrayList<DataModelMovieDetail>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(movies : DataModelMovieDetail){
            with(itemView){
                Glide.with(itemView.context)
                    .load(movies.poster)
                    .apply(RequestOptions().override(0, 400))
                    .into(mov_poster)

                mov_title.text = movies.title
                mov_desc.text = movies.description
                mov_genre.text = movies.genre

                itemView.setOnClickListener{
                    onItemClickCallback?.onItemClicked(movies)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movie[position])
    }

    override fun getItemCount(): Int = movie.size

    interface OnItemClickCallback {
        fun onItemClicked(data: DataModelMovieDetail)
    }
}