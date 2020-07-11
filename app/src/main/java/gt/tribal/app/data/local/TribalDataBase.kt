package gt.tribal.app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gt.tribal.app.data.database.PhotoDao
import gt.tribal.app.ui.PhotoEntity


@Database(entities = [PhotoEntity::class], version = 1)
abstract class TribalDataBase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao


    companion object {
        private var INSTANCE: TribalDataBase? = null

        fun getInstance(context: Context): TribalDataBase? {
            if (INSTANCE == null) {
                synchronized(TribalDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TribalDataBase::class.java, "photos.db"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}