package pt.isel.vsddashboardapplication.repository.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Database
import androidx.room.Room
import pt.isel.vsddashboardapplication.repository.dao.NSGatewayDao
import pt.isel.vsddashboardapplication.repository.dao.NSPortDao
import pt.isel.vsddashboardapplication.repository.pojo.NSGateway
import pt.isel.vsddashboardapplication.repository.pojo.converters.BootstapStatusConverter
import pt.isel.vsddashboardapplication.repository.pojo.NSPort


//@TypeConverters(DateConverter::class)
@TypeConverters(BootstapStatusConverter::class)
@Database(entities = [NSGateway::class, NSPort::class], version = 2)
abstract class VsdDatabase : RoomDatabase() {

    // --- DAO ---
    abstract fun nsgDao(): NSGatewayDao
    abstract fun nsportDao(): NSPortDao

    companion object {
        // --- SINGLETON ---
        private const val DB_NAME = "vsddatabase"
        private var INSTANCE: VsdDatabase? = null
        fun getInstance(context: Context? = null) : VsdDatabase? {
            if(INSTANCE == null && context != null)
                INSTANCE =
                    Room.databaseBuilder( context, VsdDatabase::class.java, DB_NAME )
                    .fallbackToDestructiveMigration()
                    .build()
            return INSTANCE
        }
    }
}