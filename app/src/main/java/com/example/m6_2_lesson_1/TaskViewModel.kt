package com.example.m6_2_lesson_1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel: ViewModel() {

    val taskList: MutableLiveData<List<TaskModel>?> = MutableLiveData()

    init {
        taskList.value = listOf()
    }

    fun addTask(title: String){
        val currentList = taskList.value?.toMutableList() ?: mutableListOf()
        currentList.add(TaskModel(title))
        taskList.value = currentList
    }

    fun markTaskAsDone(position: Int){
        val currentList = taskList.value?.toMutableList()
        currentList?.get(position)?.isDone = true
        taskList.value = currentList
    }

    fun deleteTask(position: Int){
        val tasks = taskList.value?.toMutableList()
        tasks?.removeAt(position)
        taskList.value = tasks
    }
}