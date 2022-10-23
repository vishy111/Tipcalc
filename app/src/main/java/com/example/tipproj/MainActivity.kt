package com.example.tipproj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
private const val INIT_TIP_PERCENT = 15
private const val TAG ="MainActivity"

//fdi
class MainActivity : AppCompatActivity() {
    private lateinit var Pretipamount: EditText
    private lateinit var Tbar: SeekBar
    private lateinit var tipper: TextView
    private lateinit var tipAmount: TextView
    private lateinit var Total: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Pretipamount = findViewById(R.id.Pretipamount)
        Tbar = findViewById(R.id.Tbar)
        tipper = findViewById(R.id.tipper)
        tipAmount = findViewById(R.id.TipAmount)
        Total = findViewById(R.id.Total)

        //seekbar being moved will help change tip %
        Tbar.progress = INIT_TIP_PERCENT
        tipper.text = "$INIT_TIP_PERCENT%"

        Tbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.i(TAG, "onProgressChanged $progress")
                tipper.text = "$progress%"
                computeTT()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        Pretipamount.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                Log.i(TAG, "afterTextChanged $s")
                computeTT()
            }

        })
    }

    //logic for computing tip and the total
    private fun computeTT() {
        val Pretamount = Pretipamount.text.toString().toDouble()
        val tPercent = Tbar.progress

        val TAmount = Pretamount * tPercent / 100
        val TotalAmount = Pretamount + TAmount

        tipAmount.text = TAmount.toString()
        Total.text = TotalAmount.toString()


    }

}