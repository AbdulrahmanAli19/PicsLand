package abdulrahman.ali19.intrazero.data.db

import abdulrahman.ali19.intrazero.data.model.Page
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATABASE_NAME = "INTRAZERO_DB"

@Database(entities = [Page::class], version = 1, exportSchema = false)
abstract class IntrazeroDatabase : RoomDatabase() {

    abstract fun deletePage(page: Page): Int

    abstract fun insertPage(page: Page): Int

    abstract suspend fun getAllPages(): List<Page>

    abstract fun deleteAllPages(): Int

    companion object {

        @Volatile
        private var INCTANCE: IntrazeroDatabase? = null

        fun init(context: Context) = INCTANCE ?: synchronized(this) {

            val instance = Room.databaseBuilder(
                context.applicationContext,
                IntrazeroDatabase::class.java,
                DATABASE_NAME
            ).build()

            INCTANCE = instance
            instance
        }

    }
}