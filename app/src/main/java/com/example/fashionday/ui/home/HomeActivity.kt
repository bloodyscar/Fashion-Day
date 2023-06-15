package com.example.fashionday.ui.home

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.fashionday.R
import com.example.fashionday.RvClickListener
import com.example.fashionday.data.Result
import com.example.fashionday.data.ViewModelFactory
import com.example.fashionday.data.response.DataItem
import com.example.fashionday.databinding.ActivityHomeBinding
import com.example.fashionday.ui.BestTodayAdapter
import com.example.fashionday.ui.upload.UploadFileActivity


class HomeActivity : AppCompatActivity(), RvClickListener {
    private lateinit var binding: ActivityHomeBinding
    private val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
    private val viewModel: HomeViewModel by viewModels {
        factory
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeLottieAnimationView.visibility = View.GONE

        binding.rvBest.setHasFixedSize(true)

        binding.btnCamera.setOnClickListener {
            val intent = Intent(this, UploadFileActivity::class.java)
            startActivity(intent)
        }

        showBestToday()
    }


    private fun showBestToday() {
        viewModel.getListBestToday().observe(this) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding.homeLottieAnimationView.visibility = View.VISIBLE
                        Log.d("L0Ading", "Loading....")
                    }

                    is Result.Success -> {
                        binding.homeLottieAnimationView.visibility = View.GONE
                        val data = result.data
                        showRecyclerList(data)
                    }

                    is Result.Error -> {
                        Toast.makeText(
                            this@HomeActivity,
                            "Error: ${result.error}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            }
        }
    }

    private fun showRecyclerList(list: List<DataItem>) {
        binding.rvBest.layoutManager = GridLayoutManager(this, 2)
        val listBest = BestTodayAdapter(list)
        listBest.listener = this
        binding.rvBest.adapter = listBest
    }

    override fun onItemClicked(view: View, data: DataItem) {
        val builder = AlertDialog.Builder(this@HomeActivity)

        val inflater = LayoutInflater.from(this)
        val dialoglayout = inflater.inflate(R.layout.custom_dialog, null)
        var imageView = dialoglayout.findViewById<ImageView>(R.id.ivCustom)
        Glide.with(this).load(data.photo).into(imageView)
        builder.setView(dialoglayout);
        builder.show();
    }




}