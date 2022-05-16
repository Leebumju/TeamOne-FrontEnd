package com.team1.teamone.board.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.team1.teamone.R
import com.team1.teamone.board.model.BoardApi
import com.team1.teamone.board.model.BoardResponse
import com.team1.teamone.board.model.FreeBoardRequest
import com.team1.teamone.databinding.ActivityCreateFreeBoardBinding
import com.team1.teamone.home.view.HomeActivity
import com.team1.teamone.util.network.*
import kotlinx.android.synthetic.main.activity_create_free_board.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateFreeBoardActivity : AppCompatActivity() {
    private val api = RetrofitClient.create(BoardApi::class.java, RetrofitClient.getAuth())
    private lateinit var freeBoardBinding: ActivityCreateFreeBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_free_board)
        var isContentBlank = false

        freeBoardBinding = DataBindingUtil.setContentView(this, R.layout.activity_create_free_board)
        freeBoardBinding.btnWriteFreeBoard.setOnClickListener{
            val title = et_title.text.toString()
            val content = et_content.text.toString()
            val request = FreeBoardRequest(title, content)
            createFreeBoard(request)
        }
    }

    private fun createFreeBoard(request: FreeBoardRequest) {
        api.postFreeBoard(request).enqueue(object : Callback<BoardResponse> {
            override fun onResponse(call: Call<BoardResponse>, response: Response<BoardResponse>) {
                Log.d("auth", RetrofitClient.getAuth())
                if (response.body() == null) {
                    Log.d("log", "blank")
                    return
                } else {
                    val intent = Intent(applicationContext, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                    Log.d("log", "success")
                }
            }

            override fun onFailure(call: Call<BoardResponse>, t: Throwable) {
                // 실패
                Log.d("log", "fail")
            }
        })
    }
}
