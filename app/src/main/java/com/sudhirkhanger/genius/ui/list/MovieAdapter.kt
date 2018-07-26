/*
 * Copyright 2018 Sudhir Khanger
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sudhirkhanger.genius.ui.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.sudhirkhanger.genius.R
import com.sudhirkhanger.genius.data.database.MovieEntry

class MovieAdapter(
        private val onMovieClick: OnMovieClickListener) :
        RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieEntries: MutableList<MovieEntry?> = mutableListOf()

    interface OnMovieClickListener {
        fun onMovieClick(movieEntry: MovieEntry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view, onMovieClick)
    }

    override fun getItemCount() = movieEntries.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movieEntries[position]!!)
    }

    class MovieViewHolder(view: View, private val onMovieClickListener: OnMovieClickListener) :
            RecyclerView.ViewHolder(view) {
        private val movieImage = view.findViewById<ImageView>(R.id.movie_image_view)

        fun bindMovie(movieEntry: MovieEntry) {
            with(movieEntry) {
                Picasso.with(itemView.context)
                        .load("https://image.tmdb.org/t/p/w185/${movieEntry.posterPath}")
                        .into(movieImage)
                itemView.setOnClickListener { onMovieClickListener.onMovieClick(this) }
            }
        }
    }

    fun setMovieData(movieEntries: MutableList<MovieEntry?>) {
        this.movieEntries = movieEntries
        notifyDataSetChanged()
    }
}