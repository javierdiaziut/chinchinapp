package gt.tribal.app.ui

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(@PrimaryKey val id: String, val urlPhoto: String, val isFavorite: Boolean,
                      val nameUser: String, val likes: String, val userPhoto: String)