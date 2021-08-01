package com.example.todolist

import android.graphics.Color
import android.view.View
import androidx.databinding.BindingAdapter
import kotlin.random.Random

@BindingAdapter("setCardColor")
fun setRandomColor(view: View,show: Boolean){

    if(show) {
        val random= Random.nextInt(0,6)

        when(random){
            0 -> view.setBackgroundColor(Color.BLUE)
            1 -> view.setBackgroundColor(Color.MAGENTA)
            2 -> view.setBackgroundColor(Color.GRAY)
            3 -> view.setBackgroundColor(Color.YELLOW)
            4 -> view.setBackgroundColor(Color.CYAN)
            5 -> view.setBackgroundColor(Color.DKGRAY)
            6 -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
    else{
        view.setBackgroundColor(Color.BLUE)
    }

}
