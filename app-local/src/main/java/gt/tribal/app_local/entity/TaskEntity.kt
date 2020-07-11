package cl.blackmind.app_local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import cl.blackmind.app_domain.model.Task

@Entity(tableName = "tasks")
data class TaskEntity(
  @PrimaryKey(autoGenerate = true) var id: Long,
  @ColumnInfo(name = "title") var title: String,
  @ColumnInfo(name = "description") var description: String
)

fun TaskEntity.toTask() = Task(id = id, title = title, description = description)