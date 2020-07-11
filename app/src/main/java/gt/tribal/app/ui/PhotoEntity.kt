package gt.tribal.app.ui

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo")
data class PhotoEntity(@PrimaryKey val id: String, val urlPhoto: String, val isFavorite: Boolean,
                       val nameUser: String, val likes: Int, val userPhoto: String)