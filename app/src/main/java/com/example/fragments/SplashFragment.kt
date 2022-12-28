package com.example.fragments


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.navigation.fragment.findNavController


class SplashFragment : Fragment() {
    private lateinit var topanim: Animation
    private lateinit var downanim: Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_splash, container, false)
        topanim = AnimationUtils.loadAnimation(context, R.anim.top)
        downanim = AnimationUtils.loadAnimation(context, R.anim.down)
        val im = view.findViewById<ImageView>(R.id.imageView)
        val ima = view.findViewById<ImageView>(R.id.imageView3)
        im.animation = (topanim)
        ima.animation = (downanim)
        Handler(Looper.myLooper()!!).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_fragment1)
        }, 3000)
        return view
    }
}