package kenji.pilotprojects.madormz.ui.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kenji.pilotprojects.madormz.ui.data.dao.RoomDao
import kenji.pilotprojects.madormz.ui.data.entity.RoomEntity

@Database(entities = [RoomEntity::class], version = 0, exportSchema = true)
abstract class DormDatabase : RoomDatabase() {

    abstract fun roomDao(): RoomDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDatabase? = null

        fun getDatabase(context: Context): RoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDatabase::class.java,
                    "room_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}