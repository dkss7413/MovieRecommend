package com.example.movierecommender.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movierecommender.R
import com.example.movierecommender.model.MovieItemDTO
import kotlinx.android.synthetic.main.home_item.view.*

class HomeListAdapter(val root:View, val homeMovieMap: HashMap<Int, MovieItemDTO>?): RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {
    lateinit var parent: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        this.parent = parent
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(homeMovieMap == null) return 0
        return 9
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(homeMovieMap?.get(position))
        holder.clickListener(homeMovieMap?.get(position))

        //랭킹 숫자
        holder.itemView.rankingNumber.text = position.plus(1).toString()

        //영화 포스터 크기 설정
        with(holder.itemView) {
            val viewWidth = root.width
            val viewHeight = root.height

            layoutParams.width = viewWidth.div(3)
            layoutParams.height = viewHeight.div(3)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: MovieItemDTO?){
            Glide.with(itemView.homeMovieView.context)
                .load(item?.image)
                .error(R.drawable.error_image)
                .into(itemView.homeMovieView)
        }

        fun clickListener(item: MovieItemDTO?){
            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item?.link))
                ContextCompat.startActivity(it.context, intent, null)
            }
        }

    }
}