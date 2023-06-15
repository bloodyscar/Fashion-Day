package com.example.fashionday

import android.view.View
import com.example.fashionday.data.response.DataItem

interface RvClickListener {
    fun onItemClicked(view: View, data: DataItem)
}