package com.hafizrahmadhani.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hafizrahmadhani.movie.data.entity.MovieEntity
import com.hafizrahmadhani.movie.databinding.ItemMovieBinding

class MovieAdapter(private val click : (movie : MovieEntity) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var listMovie = listOf<MovieEntity>()

    companion object{
        private const val imageSize = "https://image.tmdb.org/t/p/w500/"
    }

    fun setMovie(movie : List<MovieEntity>){
        this.listMovie = movie
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(list : MovieEntity){
            with(binding){
                Glide.with(itemView)
                        .load(imageSize + list.poster as String)
                        .apply(RequestOptions().override(370, 420))
                        .into(movPoster)
                movTitle.text = list.title
                movDesc.text = list.description
                rating.text = list.vote.toString()
            }
            itemView.setOnClickListener{click(list)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size

}