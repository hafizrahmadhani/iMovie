package com.hafizrahmadhani.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hafizrahmadhani.movie.R
import com.hafizrahmadhani.movie.data.response.MovieResponse
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val movie: List<MovieResponse>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    companion object{
        private const val imageSize = "https://image.tmdb.org/t/p/w500/"
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(movies : MovieResponse){
            with(itemView){
                Glide.with(context)
                    .load(imageSize + movies.poster_path)
                    .apply(RequestOptions().override(370, 420))
                    .into(mov_poster)

                mov_title.text = movies.title
                mov_desc.text = movies.overview
                rating.text = movies.voteAverage.toString()

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
        fun onItemClicked(data: MovieResponse)
    }
}