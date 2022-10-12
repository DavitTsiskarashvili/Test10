package com.example.test10_final.ui.home

import androidx.lifecycle.ViewModel
import com.example.test10_final.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun items() = repository.getItems()

}