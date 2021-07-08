package com.example.runningtrackerapp.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.runningtrackerapp.repository.MainRepository


class StatisticsViewModel @ViewModelInject  constructor(
    val mainRepository: MainRepository
) :ViewModel() {

}