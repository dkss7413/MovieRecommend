package com.example.movierecommender.view.main.fragment.community

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.movierecommender.R
import com.example.movierecommender.network.Service
import com.example.movierecommender.util.SaveSharedPreference
import com.example.movierecommender.util.replaceFragment
import com.example.movierecommender.util.showToast
import com.example.movierecommender.view.BaseFragment
import com.example.movierecommender.view.main.fragment.mypage.MypageFrament
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.community_item.view.*
import kotlinx.android.synthetic.main.fragment_board_add.view.*
import kotlinx.android.synthetic.main.fragment_board_add.view.titleText
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

class BoardAddFragment: Fragment() {
    companion object: BaseFragment{
        override fun newInstance(): BoardAddFragment{
            return BoardAddFragment()
        }
    }

    lateinit var root: View

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
                CommunityFragment.newInstance().replaceFragment(activity)
                return true
            }
            R.id.menu_complete -> {
                if(root.titleText.text.toString() == "" || root.board_add_contentText.text.toString() == ""){
                    context?.showToast("입력 안된 사항이 있습니다.", Toast.LENGTH_SHORT)
                }
                else {
                    val map = HashMap<String, String?>()
                    map["userId"] = SaveSharedPreference.getUserId(context)
                    map["nickname"] = SaveSharedPreference.getNickname(context)
                    map["boardTitle"] = root.titleText.text.toString()
                    map["boardData"] =
                        SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA).format(Date()).toString()
                    map["boardContent"] = root.board_add_contentText.text.toString()

                    Service.create().boardRegister(map)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            if(it.get("success").asString == "true") {
                                context?.showToast("글 생성", Toast.LENGTH_SHORT)
                                CommunityFragment.newInstance().replaceFragment(activity)
                            }
                            else
                                context?.showToast("글 생성 실패", Toast.LENGTH_SHORT)
                        }, { Log.d("글 생성 오류", it.localizedMessage) })

                    return true
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}