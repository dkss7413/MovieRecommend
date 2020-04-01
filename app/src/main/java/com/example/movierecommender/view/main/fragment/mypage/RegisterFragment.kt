package com.example.movierecommender.view.main.fragment.mypage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.movierecommender.R
import com.example.movierecommender.network.Service
import com.example.movierecommender.util.SaveSharedPreference
import com.example.movierecommender.util.replaceFragment
import com.example.movierecommender.util.showToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_register.*
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
            val userId = root.reg_idText.text.toString()
            val userPassword = root.reg_passwordText.text.toString()
            val userPassword2 = root.reg_passwordText2.text.toString()
            val nickname = root.reg_nicknameText.text.toString()

            if(userId == "" || userPassword == "" || userPassword2 == "" || nickname == ""){
                context?.showToast("입력 안된 사항이 있습니다.", Toast.LENGTH_SHORT)
            }
            else if(userPassword != userPassword2) {
                context?.showToast("비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT)
                reg_passwordText.requestFocus()
            }
            else{
                Service.create().userRegister(userId.toString(), userPassword.toString(), nickname.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({

                        when(it.get("result").asString){
                            "Id error!" -> {
                                context?.showToast("존재하는 아이디입니다.", Toast.LENGTH_SHORT)
                                root.reg_idText.requestFocus()
                            }
                            "Nickname error!" -> {
                                context?.showToast("존재하는 닉네임입니다.", Toast.LENGTH_SHORT)
                                root.reg_nicknameText.requestFocus()
                            }
                            "true" -> {
                                context?.showToast("회원가입 성공", Toast.LENGTH_SHORT)

                                SaveSharedPreference.setUserName(context, userId)
                                MypageFrament.newInstance().replaceFragment(activity)
                            }
                        }

                    }, { Log.d("회원가입 오류", it.localizedMessage) })
            }
        }

        root.reg_cancelButton.setOnClickListener {
            LoginFragment.newInstance().replaceFragment(activity)
        }

        return root
    }
}