package com.example.ca1_slotmachine

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity()
{
    //setting up stats
    private var playsSinceLastWin = 0
    private var totalPlays = 0
    private var totalWins = 0
    private var winPlayRatio = 0.0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        Log.d("Message","onCreate() - Called")
        setContentView(R.layout.activity_main)

        // Find the Button in the layout
        val spinButton: Button = findViewById(R.id.button)
        spinButton.setOnClickListener{ playSlots()}

        // start with images on app
        val titleImage: ImageView = findViewById(R.id.titleImageView)
        val slotImage1: ImageView = findViewById(R.id.ImageView)
        val slotImage2: ImageView = findViewById(R.id.imageView2)
        val slotImage3: ImageView = findViewById(R.id.imageView3)
        titleImage.setImageResource(R.drawable.title)
        slotImage1.setImageResource(R.drawable.watermellon1)
        slotImage2.setImageResource(R.drawable.grapes1)
        slotImage3.setImageResource(R.drawable.lemon1)
    }

    @SuppressLint("SetTextI18n")
    private fun playSlots()
    {
        //updating plays since last win
        playsSinceLastWin += 1
        val playsSinceLastWinText: TextView = findViewById(R.id.numPlaysSinceLastWinTextResult)
        playsSinceLastWinText.text = playsSinceLastWin.toString()

        //updating total plays
        totalPlays += 1
        val totalPlaysText: TextView = findViewById(R.id.totalPlaysResult)
        totalPlaysText.text = totalPlays.toString()

        //creating a slot and spinning it 3 times
        val slot = Slot()
        val slotOneSpin = slot.spin()
        val slotTwoSpin = slot.spin()
        val slotThreeSpin = slot.spin()
        Log.d("Random Number1", "Number Generated was: $slotOneSpin")
        Log.d("Random Number2", "Number Generated was: $slotTwoSpin")
        Log.d("Random Number3", "Number Generated was: $slotThreeSpin")

        //finding the 3 Image views
        val slotImage1: ImageView = findViewById(R.id.ImageView)
        val slotImage2: ImageView = findViewById(R.id.imageView2)
        val slotImage3: ImageView = findViewById(R.id.imageView3)

        //TODO timeout for the spinning images - youtube
        //Changing the Images
        selectImage(slotOneSpin,slotImage1)
        selectImage(slotTwoSpin,slotImage2)
        selectImage(slotThreeSpin,slotImage3)

        //find image for spin result
        val resultImage: ImageView = findViewById(R.id.imageOfResult)
        if(slotOneSpin == slotTwoSpin && slotThreeSpin == slotOneSpin)
        {
            //updating image of result
            resultImage.setImageResource(R.drawable.winner)

            //updating win stats
            totalWins += 1
            val totalWinsText: TextView = findViewById(R.id.totalWinsResult)
            totalWinsText.text = totalWins.toString()
            playsSinceLastWin = 0
            playsSinceLastWinText.text = playsSinceLastWin.toString()
        }else{
            resultImage.setImageResource(R.drawable.unlucky)
        }
        //win/PlayRatio stats
        //https://code.luasoftware.com/tutorials/kotlin/kotlin-round-double-to-2-decimal-point/ -- helped (referenced)
        winPlayRatio = totalWins / totalPlays.toDouble()
        var roundedWinPlayRatio = "%.3f".format(winPlayRatio).toDouble()
        val winPlayRatioText: TextView = findViewById(R.id.winPlayRatioResult)
        winPlayRatioText.text = roundedWinPlayRatio.toString()
    }
    private fun selectImage(randNum: Int,slotImage: ImageView)
    {
        val drawableResource = when (randNum){
            1 -> R.drawable.grapes1
            2 -> R.drawable.lemon1
            3 -> R.drawable.watermellon1
            else -> R.drawable.number7e
        }
        slotImage.setImageResource(drawableResource)
    }
}

class Slot {

    fun spin(): Int
    {
        return (1..4).random()
    }
}