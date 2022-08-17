package abdulrahman.ali19.intrazero.data.local

import abdulrahman.ali19.intrazero.data.local.dao.PageDao
import abdulrahman.ali19.intrazero.data.local.dao.RemoteKeysDao
import abdulrahman.ali19.intrazero.domain.model.Page
import abdulrahman.ali19.intrazero.domain.model.RemoteKeys
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Page::class, RemoteKeys::class], version = 1, exportSchema = false)
abstract class IntrazeroDatabase : RoomDatabase() {

    abstract fun pageDao(): PageDao

    abstract fun remoteKeysDao(): RemoteKeysDao

}