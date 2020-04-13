package com.example.movierecommender.view.main.fragment.community

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movierecommender.R
import com.example.movierecommender.adapter.CommunityListAdapter
import com.example.movierecommender.model.BoardDTO
import com.example.movierecommender.util.SaveSharedPreference
import com.example.movierecommender.util.ShowFragment
import com.example.movierecommender.util.showToast
import com.example.movierecommender.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_community.view.*

class CommunityFragment : Fragment(), CommunityContract.View {
    companion object: BaseFragment {
        override fun newInstance(): CommunityFragment {
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

        val presenter = CommunityPresenter().apply {
            view = this@CommunityFragment
        }

        presenter.setList()

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
                    ShowFragment.move("community", "boardAdd", activity!!)
                } else
                    context?.showToast("로그인을 해주세요.", Toast.LENGTH_SHORT)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setCommunityListAdapter(boardList: ArrayList<BoardDTO?>?) {
        val communityListAdapter = CommunityListAdapter(boardList)
        root.communityRecyclerView.adapter = communityListAdapter
        root.communityRecyclerView.layoutManager = LinearLayoutManager(activity)
    }
}