package com.armandoalvear.mathvein

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SwitchCompat
import android.widget.Button

class WorkActivity : AppCompatActivity() {

    lateinit var submitBtn: Button
    lateinit var switch_1: SwitchCompat
    lateinit var switch_2: SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work)
    }
}
