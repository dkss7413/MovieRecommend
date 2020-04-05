package com.example.movierecommender.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecommender.R
import com.example.movierecommender.model.BoardDTO
import kotlinx.android.synthetic.main.community_item.view.*

class CommunityListAdapter(val boardList: ArrayList<BoardDTO?>?): RecyclerView.Adapter<CommunityListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.community_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return boardList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(boardList?.get(position))
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: BoardDTO?){
            with(itemView) {
                titleText.text = item?.boardTitle
                statusText.text = "${item?.boardDate?.subSequence(0, 4)}.${item?.boardDate?.subSequence(4,6)}.${item?.boardDate?.subSequence(6,8)}"
                community_contentText.text = item?.boardContent
            }
        }
    }
}