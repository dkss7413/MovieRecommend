package com.example.movierecommender.view.main.fragments.mypage

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.movierecommender.R
import com.example.movierecommender.network.Service
import com.example.movierecommender.view.main.replaceFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_register.view.*

class RegisterFragment : Fragment(){
    companion object{
        fun newInstance(): RegisterFragment{
            return RegisterFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register, container, false)

        root.reg_registerButton.setOnClickListener {
            val userId: String = root.reg_idText.text.toString()
            val userPassword = root.reg_passwordText.text.toString()
            val userPassword2 = root.reg_passwordText2.text.toString()
            val nickname = root.reg_nicknameText.text.toString()

            if(userPassword == userPassword2) {
                Service.create().register(userId, userPassword, nickname)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        val result = it.get("result").asString
                        if(result == "Id error!"){
                            context?.showToast("존재하는 아이디입니다.", Toast.LENGTH_SHORT)
                            root.reg_idText.text = null
                        }
                        if(result == "Nickname error!"){
                            context?.showToast("존재하는 닉네임입니다.", Toast.LENGTH_SHORT)
                            root.reg_nicknameText.text = null
                        }
                        if(result == "true"){
                            context?.showToast("회원가입 성공", Toast.LENGTH_SHORT)
                        }
                    }, {
                        Log.d("회원가입 오류", it.localizedMessage)
                    })
            }
            else{
                context?.showToast("비밀번호를 확인해주세요.", Toast.LENGTH_SHORT)
                root.reg_passwordText.text = null
                root.reg_passwordText2.text = null
            }

        }

        root.reg_cancelButton.setOnClickListener {
            LoginFragment.newInstance().replaceFragment(activity)
        }

        return root
    }
}

fun Context.showToast(text: String, duration: Int){
    Toast.makeText(this, text, duration).show()
}