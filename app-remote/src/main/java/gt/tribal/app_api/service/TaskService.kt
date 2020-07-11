package cl.blackmind.app_api.service

//import cl.blackmind.app.data.local.TaskEntity
import retrofit2.http.POST
import retrofit2.http.Path

interface TaskService {

  //@GET("tasks/all")
  //fun getAllTasksAsync(): Deferred<List<TaskEntity>>

  @POST("tasks/add")
  fun addTask()

  @POST("tasks/{id}/delete")
  fun deleteTask(@Path("id") id: String)
}