package com.example.movierecommender.view.main.fragment.community

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.movierecommender.R
import com.example.movierecommender.util.showToast
import kotlinx.android.synthetic.main.fragment_community.view.*

class CommunityFragment:Fragment(){
    companion object{
        fun newInstance(): CommunityFragment{
            return CommunityFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_community, container, false)

        (activity as AppCompatActivity).setSupportActionBar(root.community_toolbar)
        setHasOptionsMenu(true)

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_community, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_create -> {
                context?.showToast("세팅", Toast.LENGTH_SHORT)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}