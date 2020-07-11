package gt.tribal.app.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import gt.tribal.app.ui.PhotoEntity

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photo WHERE isFavorite = :isFavorite")
    suspend fun get(isFavorite: Boolean = true): List<PhotoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg photo: PhotoEntity)

    @Query("DELETE FROM photo WHERE id = :id")
    suspend fun deletePhoto(id: String)

}