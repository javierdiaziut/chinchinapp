package cl.blackmind.app_domain.usecase

import cl.blackmind.app_domain.respository.TaskRepository

class GetAllTasks(
    private val taskRepository: TaskRepository
) {

}