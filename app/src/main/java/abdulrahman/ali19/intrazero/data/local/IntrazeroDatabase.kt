package abdulrahman.ali19.intrazero.data.local

import abdulrahman.ali19.intrazero.data.local.dao.PageDao
import abdulrahman.ali19.intrazero.data.local.dao.RemoteKeysDao
import abdulrahman.ali19.intrazero.domain.model.Page
import abdulrahman.ali19.intrazero.domain.model.RemoteKeys
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATABASE_NAME = "INTRAZERO_DB"

@Database(entities = [Page::class, RemoteKeys::class], version = 1, exportSchema = false)
abstract class IntrazeroDatabase : RoomDatabase() {

    abstract fun pageDao(): PageDao

    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {

        @Volatile
        private var INSTANCE: IntrazeroDatabase? = null

        fun init(context: Context) = INSTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                IntrazeroDatabase::class.java,
                DATABASE_NAME
            ).build()

            INSTANCE = instance
            instance
        }

    }
}