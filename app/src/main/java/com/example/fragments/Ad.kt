package com.example.fragments

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.NumberFormat

@BindingAdapter("image")
fun loadImage(view: ImageView, image: Int) {
   Glide.with(view.context)
        .load(image)
       .into(view)}

@BindingAdapter("price")
fun itemPrice(view: TextView, value: Int) {
   val formatter = NumberFormat.getCurrencyInstance()
    val text = "${formatter.format(value)} / L.E"
    view.text = text }