package com.example.runningtrackerapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)

    @Query("Select * FROM running_table ORDER BY timeStamp DESC")
    fun getAllRunsSortedByDate(): LiveData<List<Run>>

    @Query("Select * FROM running_table ORDER BY timeInMills DESC")
    fun getAllRunsSortedByTimeinMillis(): LiveData<List<Run>>

    @Query("Select * FROM running_table ORDER BY caloriesBurned DESC")
    fun getAllRunsSortedByCaloriesBurned(): LiveData<List<Run>>

    @Query("Select * FROM running_table ORDER BY aveSpeedInKMH DESC")
    fun getAllRunsSortedByAvgSpeed(): LiveData<List<Run>>

    @Query("Select * FROM running_table ORDER BY distanceInMeters DESC")
    fun getAllRunsSortedByDistance(): LiveData<List<Run>>

    @Query("SELECT SUM(timeInMills) FROM running_table")
    fun getTotalTimeInMillis() : LiveData<Long>

    @Query("SELECT SUM(caloriesBurned) FROM running_table" )
    fun getTotalCaloriesBurned() : LiveData<Int>

    @Query("SELECT SUM(distanceInMeters) FROM running_table")
    fun getTotalDistance() : LiveData<Int>

    @Query("SELECT AVG(aveSpeedInKMH) FROM running_table")
    fun getTotalAverageSpeed() : LiveData<Float>

}