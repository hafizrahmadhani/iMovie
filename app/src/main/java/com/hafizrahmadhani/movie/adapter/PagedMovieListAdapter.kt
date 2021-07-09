package com.hafizrahmadhani.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hafizrahmadhani.movie.data.entity.MovieEntity
import com.hafizrahmadhani.movie.databinding.ItemMovieBinding

class PagedMovieListAdapter(private val click: (movie : MovieEntity) -> Unit) : PagedListAdapter<MovieEntity, PagedMovieListAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK : DiffUtil.ItemCallback<MovieEntity> =
            object : DiffUtil.ItemCallback<MovieEntity>() {
                override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                    return oldItem.title == newItem.title && oldItem.description == newItem.description
                }

                override fun areContentsTheSame(
                    oldItem: MovieEntity,
                    newItem: MovieEntity
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        private val imageSize = "https://image.tmdb.org/t/p/w500/"

        fun bind(list : MovieEntity){
            with(binding) {
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
        holder.bind(getItem(position) as MovieEntity)
    }
}