package cl.blackmind.app_local

import cl.blackmind.app_domain.model.Task
import cl.blackmind.app_domain.respository.TaskRepository
import cl.blackmind.app_local.entity.toTask

class TaskRoom(private val taskRoomDao: TaskRoomDao):
    TaskRepository {

//  @WorkerThread
//  suspend fun insert(task: Task) {
//    taskRoomDao.insert(task)
//  }
//
  override suspend fun getAllTasks(): List<Task> {
    return taskRoomDao.getAll().map { it.toTask() }
  }
}