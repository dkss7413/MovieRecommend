package com.example.movierecommender.view.main.fragment.community

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movierecommender.R
import com.example.movierecommender.adapter.CommunityListAdapter
import com.example.movierecommender.model.BoardDTO
import com.example.movierecommender.network.Service
import com.example.movierecommender.util.SaveSharedPreference
import com.example.movierecommender.util.replaceFragment
import com.example.movierecommender.util.showToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.community_item.view.*
import kotlinx.android.synthetic.main.fragment_community.view.*

class CommunityFragment : Fragment() {
    companion object {
        fun newInstance(): CommunityFragment {
            return CommunityFragment()
        }
    }

    lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_community, container, false)

        (activity as AppCompatActivity).setSupportActionBar(root.community_toolbar)
        setHasOptionsMenu(true)

        val boardList: ArrayList<BoardDTO?>? = null
        setCommunityListAdapter(boardList)

        Service.create().boardGet()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                setCommunityListAdapter(it)
            }, { })

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_community, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_create -> {
                if (SaveSharedPreference.getUserId(context)?.length != 0) {
                    BoardAddFragment.newInstance().replaceFragment(activity)
                } else
                    context?.showToast("로그인을 해주세요.", Toast.LENGTH_SHORT)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setCommunityListAdapter(boardList: ArrayList<BoardDTO?>?) {
        val communityListAdapter = CommunityListAdapter(boardList)
        root.communityRecyclerView.adapter = communityListAdapter
        root.communityRecyclerView.layoutManager = LinearLayoutManager(activity)
    }
}