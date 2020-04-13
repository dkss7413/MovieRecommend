package com.example.movierecommender.view.main.fragment.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.movierecommender.R
import com.example.movierecommender.util.SaveSharedPreference
import com.example.movierecommender.util.ShowFragment
import com.example.movierecommender.util.showToast
import com.example.movierecommender.view.BaseFragment
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment : Fragment(), RegisterContract.View{
    lateinit var root: View
    val presenter = RegisterPresenter().apply {
        view = this@RegisterFragment
    }

    companion object: BaseFragment{
        override fun newInstance(): RegisterFragment{
            return RegisterFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_register, container, false)

        root.reg_registerButton.setOnClickListener {
            val userId = root.reg_idText.text.toString()
            val userPassword = root.reg_passwordText.text.toString()
            val userPassword2 = root.reg_passwordText2.text.toString()
            val nickname = root.reg_nicknameText.text.toString()

            if(userId == "" || userPassword == "" || userPassword2 == "" || nickname == ""){
                showToast("입력 안된 사항이 있습니다.")
            }
            else if(userPassword != userPassword2) {
                showToast("비밀번호가 일치하지 않습니다.")
                reg_passwordText.requestFocus()
            }
            else{
                presenter.registerUser(userId, userPassword, nickname)
            }
        }

        root.reg_cancelButton.setOnClickListener {
            moveFragment("login")
        }

        return root
    }

    override fun showToast(text: String){
        context?.showToast(text, Toast.LENGTH_SHORT)
    }

    override fun moveFragment(nextFragmentName: String){
        ShowFragment.move("register", nextFragmentName, activity!!)
    }

    override fun setUser(userId: String, nickname: String){
        SaveSharedPreference.setUser(context, userId, nickname)
    }

    override  fun focusOnIdtext(){
        root.reg_idText.requestFocus()
    }

    override fun focusOnNicknameText(){
        root.reg_nicknameText.requestFocus()
    }
}