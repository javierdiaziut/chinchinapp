package cl.blackmind.app_api.repository

import cl.blackmind.app_domain.model.Task
import cl.blackmind.app_domain.respository.TaskRepository

class TaskRepository : TaskRepository {

    override suspend fun getAllTasks(): List<Task> {
        TODO("not implemented")
    }
}