package com.blackmind.app.data.remote

import com.blackmind.app.domain.TaskRepository

class TaskRetrofit(private val taskService: TaskService):
    TaskRepository {

//  override fun getAllTasks(): LiveData<List<Task>> {
//    //return taskService.getAllTasksAsync().await()
//  }
//
//  override fun addTask() {
//    taskService.addTask()
//  }
//
//  override fun deleteTask() {
//    taskService.deleteTask("1")
//  }
}