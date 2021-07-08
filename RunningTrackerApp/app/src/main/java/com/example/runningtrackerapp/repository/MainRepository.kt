package com.example.runningtrackerapp.repository

import com.example.runningtrackerapp.db.Run
import com.example.runningtrackerapp.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDao : RunDAO
){
    suspend fun insertRun(run : Run) = runDao.insertRun(run)
    suspend fun deleteRun(run:Run) = runDao.deleteRun(run)
    fun getAllRunsSortedByDate() = runDao.getAllRunsSortedByDate()
    fun getAllRunsSortedByDistance() = runDao.getAllRunsSortedByDistance()
    fun getAllRunsSortedByTimeInMillis() = runDao.getAllRunsSortedByTimeinMillis()
    fun getAllRunsSortedAvgSpeed() = runDao.getAllRunsSortedByAvgSpeed()
    fun getAllRunsSortedByCaloriesBurned() = runDao.getAllRunsSortedByCaloriesBurned()
    fun getTotalAvgSpeed() = runDao.getTotalAverageSpeed()

}