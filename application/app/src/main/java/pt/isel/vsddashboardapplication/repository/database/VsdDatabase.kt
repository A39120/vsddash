package pt.isel.vsddashboardapplication.repository.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Database
import androidx.room.Room
import pt.isel.vsddashboardapplication.repository.dao.NSAlarmDao
import pt.isel.vsddashboardapplication.repository.dao.NSGatewayDao
import pt.isel.vsddashboardapplication.repository.dao.NSPortDao
import pt.isel.vsddashboardapplication.model.Alarm
import pt.isel.vsddashboardapplication.model.Enterprise
import pt.isel.vsddashboardapplication.model.NSGateway
import pt.isel.vsddashboardapplication.model.converters.BootstapStatusConverter
import pt.isel.vsddashboardapplication.model.NSPort
import pt.isel.vsddashboardapplication.repository.dao.EnterpriseDao


//@TypeConverters(DateConverter::class)
@TypeConverters(BootstapStatusConverter::class)
@Database(entities = [NSGateway::class,
    NSPort::class,
    Alarm::class,
    Enterprise::class
], version = 4, exportSchema = false)
abstract class VsdDatabase : RoomDatabase() {

    // --- DAO ---
    abstract fun nsgDao(): NSGatewayDao
    abstract fun nsportDao(): NSPortDao
    abstract fun nsAlarmDao() : NSAlarmDao
    abstract fun enterpriseDao() : EnterpriseDao

    companion object {
        private const val DB_NAME = "vsddatabase"
        private var INSTANCE: VsdDatabase? = null
        fun getInstance(context: Context? = null) : VsdDatabase {
            if(INSTANCE == null && context != null)
                INSTANCE =
                    Room
                        .databaseBuilder( context, VsdDatabase::class.java, DB_NAME )
                        .fallbackToDestructiveMigration()
                        .build()
            return INSTANCE!!
        }
    }
}