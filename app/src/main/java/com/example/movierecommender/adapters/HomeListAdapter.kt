package com.example.movierecommender.adapters

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movierecommender.R
import com.example.movierecommender.models.NaverMovie
import com.example.movierecommender.models.NaverMovieItem
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.home_list.view.*
import kotlinx.android.synthetic.main.search_item.view.*

class HomeListAdapter(val root:View, val homeMovieList: ArrayList<NaverMovieItem>?): RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {
    lateinit var parent: ViewGroup

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_list, parent, false)
        this.parent = parent
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return homeMovieList?.size ?:0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(homeMovieList?.get(position))
        holder.clickListener(homeMovieList?.get(position))

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
        fun bind(item: NaverMovieItem?){
            Glide.with(itemView.homeMovieView.context)
                .load(item?.image)
                .error(R.drawable.error_image)
                .into(itemView.homeMovieView)
        }

        fun clickListener(item: NaverMovieItem?){
            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item?.link))
                ContextCompat.startActivity(it.context, intent, null)
            }
        }

    }
}