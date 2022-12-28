package com.example.fragments.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import com.example.fragments.data.Menudata

class Fragment2ViewModel(val app:Application) : AndroidViewModel(app) {
    val selectedMenu = MutableLiveData<Menudata>()
    val activityTitle = MutableLiveData<String>()

    init {
        updateActivityTitle()
    }


    fun updateActivityTitle() {
        val signature =
            PreferenceManager.getDefaultSharedPreferences(app)
                .getString("signature", "Menu",)
        activityTitle.value = "Stickers for $signature"
    }

}