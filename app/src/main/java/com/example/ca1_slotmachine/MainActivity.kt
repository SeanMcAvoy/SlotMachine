package com.example.ca1_slotmachine

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        val slotTreeSpin = slot.spin()

        //finding the 3 Image views
        val slotImage1: ImageView = findViewById(R.id.ImageView)
        val slotImage2: ImageView = findViewById(R.id.imageView2)
        val slotImage3: ImageView = findViewById(R.id.imageView3)

        //TODO timeout for the spinning images - youtube
        //TODO make function void to set images - so pass in the number and set that image
        val drawableResourceSlotOne = when (slotOneSpin){
            1 -> R.drawable.grapes1
            2 -> R.drawable.lemon1
            3 -> R.drawable.watermellon1
            else -> R.drawable.number7e
        }
        val drawableResourceSlotTwo = when (slotTwoSpin){
            1 -> R.drawable.grapes1
            2 -> R.drawable.lemon1
            3 -> R.drawable.watermellon1
            else -> R.drawable.number7e
        }
        val drawableResourceSlotThree = when (slotTreeSpin){
            1 -> R.drawable.grapes1
            2 -> R.drawable.lemon1
            3 -> R.drawable.watermellon1
            else -> R.drawable.number7e
        }

        //updating the images
        slotImage1.setImageResource(drawableResourceSlotOne)
        slotImage2.setImageResource(drawableResourceSlotTwo)
        slotImage3.setImageResource(drawableResourceSlotThree)

        val resultMessage : TextView = findViewById(R.id.resultMessage)
        if(slotOneSpin == slotTwoSpin && slotTreeSpin == slotOneSpin)
        {
            resultMessage.text = "Well done You Won"

            //updating win stats
            totalWins += 1
            val totalWinsText: TextView = findViewById(R.id.totalWinsResult)
            totalWinsText.text = totalWins.toString()
            playsSinceLastWin = 0
            playsSinceLastWinText.text = playsSinceLastWin.toString()
        }else{
            resultMessage.text = "No Luck Try Again"
        }
        //win/PlayRatio stats
        //https://code.luasoftware.com/tutorials/kotlin/kotlin-round-double-to-2-decimal-point/ -- helped (ref)
        winPlayRatio = totalWins / totalPlays.toDouble()
        var roundedWinPlayRatio = "%.3f".format(winPlayRatio).toDouble()
        val winPlayRatioText: TextView = findViewById(R.id.winPlayRatioResult)
        winPlayRatioText.text = roundedWinPlayRatio.toString()
    }
}

class Slot {

    fun spin(): Int
    {
        return (1..4).random()
    }
}