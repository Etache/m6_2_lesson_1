package com.example.m6_2_lesson_1

data class TaskModel(
    val title: String,
    var isDone: Boolean = false
) {
    fun toggleDone() {
        isDone = !isDone
    }
}