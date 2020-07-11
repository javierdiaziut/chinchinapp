package cl.blackmind.app_domain.respository

import cl.blackmind.app_domain.model.Task

interface TaskRepository {

    suspend fun getAllTasks(): List<Task>
//
//  fun addTask()
//
//  fun deleteTask()
}