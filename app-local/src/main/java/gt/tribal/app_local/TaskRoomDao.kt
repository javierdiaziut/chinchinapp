package cl.blackmind.app_local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import cl.blackmind.app_local.entity.TaskEntity

@Dao
interface TaskRoomDao {

    @Query("SELECT * from tasks")
    fun getAll(): List<TaskEntity>

    @Insert(onConflict = REPLACE)
    fun insert(weatherData: TaskEntity)

    @Query("DELETE from tasks")
    fun deleteAll()
}