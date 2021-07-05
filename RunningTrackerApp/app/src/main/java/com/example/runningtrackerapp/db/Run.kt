package com.example.runningtrackerapp.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "running_table")
data class Run(
    var img: Bitmap? = null,
    var timeStamp : Long = 0L, // when was the run
    var aveSpeedInKMH : Float = 0f,
    var distanceInMeters : Int = 0,
    var timeInMills : Long = 0L, // How long a run takes
    var caloriesBurned : Int = 0
) {
    //No need to init in constructor
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}