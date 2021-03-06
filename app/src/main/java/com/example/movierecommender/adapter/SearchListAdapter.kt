package com.example.movierecommender.adapter

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movierecommender.R
import com.example.movierecommender.model.MovieDTO
import com.example.movierecommender.model.MovieItemDTO
import kotlinx.android.synthetic.main.search_item.view.*

class SearchListAdapter(val searchMovieList: MovieDTO?): RecyclerView.Adapter<SearchListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return searchMovieList?.items?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(searchMovieList?.items?.get(position)) {
            holder.bind(this)
            holder.clickListener(this)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: MovieItemDTO?) {
            with(itemView) {
                textTitle.text = item?.title?.htmlToString()
                textSubTitle.text = item?.subtitle?.htmlToString()
                textPubDate.text = item?.pubDate?.htmlToString()
                textDirector.text = item?.director?.htmlToString()
                textActor.text = item?.actor?.htmlToString()
                search_ratingBar.rating =
                    item?.userRating?.toDouble()?.div(2)?.toFloat() ?: 0.toFloat()

                Glide.with(imageView.context)
                    .load(item?.image)
                    .error(R.drawable.error_image)
                    .into(imageView)
            }
        }

        fun clickListener(itemDTO: MovieItemDTO?){
            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(itemDTO?.link))
                startActivity(it.context, intent, null)
            }
        }


        fun String.htmlToString(): String {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                return Html.fromHtml(this).toString()
            }
        }
    }
}