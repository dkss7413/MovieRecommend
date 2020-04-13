package com.example.movierecommender.view.main.fragment.community

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.movierecommender.R
import com.example.movierecommender.util.SaveSharedPreference
import com.example.movierecommender.util.ShowFragment
import com.example.movierecommender.util.showToast
import com.example.movierecommender.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_board_add.view.*
import kotlinx.android.synthetic.main.fragment_board_add.view.titleText
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class BoardAddFragment: Fragment(), BoardAddContract.View {

    lateinit var root: View

    var presenter = BoardAddPresenter().apply {
        view = this@BoardAddFragment
    }

    companion object: BaseFragment{
        override fun newInstance(): BoardAddFragment{
            return BoardAddFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_board_add, container, false)

        (activity as AppCompatActivity).setSupportActionBar(root.board_add_toolbar)
        setHasOptionsMenu(true)

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_board_add, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_cancel -> {
                moveFragment("community")
                return true
            }
            R.id.menu_complete -> {
                if(root.titleText.text.toString() == "" || root.board_add_contentText.text.toString() == ""){
                    showToast("입력 안된 사항이 있습니다.")
                }
                else {
                    presenter.addBoard(getBoardData())

                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showToast(text: String){
        context?.showToast(text, Toast.LENGTH_SHORT)
    }

    override fun moveFragment(nextFragmentName: String){
        ShowFragment.move("boardAdd", nextFragmentName, activity!!)
    }

    fun getBoardData(): HashMap<String, String?>{
        val map = HashMap<String, String?>()
        map["userId"] = SaveSharedPreference.getUserId(context)
        map["nickname"] = SaveSharedPreference.getNickname(context)
        map["boardTitle"] = root.titleText.text.toString()
        map["boardData"] =
            SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(Date()).toString()
        map["boardContent"] = root.board_add_contentText.text.toString()

        return map
    }
}