package gt.tribal.app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gt.tribal.app.ui.PhotoEntity


@Database(entities = [PhotoEntity::class], version = 1)
abstract class TaskDataBase : RoomDatabase() {


    companion object {
        private var INSTANCE: TaskDataBase? = null

        fun getInstance(context: Context): TaskDataBase? {
            if (INSTANCE == null) {
                synchronized(TaskDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TaskDataBase::class.java, "photos.db"
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