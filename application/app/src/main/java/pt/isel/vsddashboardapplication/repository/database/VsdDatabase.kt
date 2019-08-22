package pt.isel.vsddashboardapplication.repository.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Database
import androidx.room.Room
import pt.isel.vsddashboardapplication.model.*
import pt.isel.vsddashboardapplication.model.converters.BootstapStatusConverter
import pt.isel.vsddashboardapplication.model.statistics.DpiProbestats
import pt.isel.vsddashboardapplication.model.statistics.Sysmon
import pt.isel.vsddashboardapplication.repository.dao.*


@TypeConverters(BootstapStatusConverter::class)
@Database(entities = [NSGateway::class,
    NSPort::class,
    Alarm::class,
    Enterprise::class,
    DpiProbestats::class,
    APM::class,
    NSGInfo::class,
    VRS::class,
    VSP::class,
    VSC::class,
    VPort::class,
    PerformanceMonitor::class,
    Sysmon::class
], version = 16, exportSchema = false)
abstract class VsdDatabase : RoomDatabase() {

    // --- DAO ---
    abstract fun nsgDao(): NSGatewayDao
    abstract fun nsportDao(): NSPortDao
    abstract fun nsAlarmDao() : AlarmDao
    abstract fun enterpriseDao() : EnterpriseDao
    abstract fun dpiProbestatsDao() : DpiProbestatsDao
    abstract fun apmDao(): ApmDao
    abstract fun nsgInfoDao(): NSGInfoDao
    abstract fun vrsDao(): VrsDao
    abstract fun vspDao(): VspDao
    abstract fun vscDao(): VscDao
    abstract fun vportDao() : VPortDao
    abstract fun performanceMonitorDao() : PerformanceMonitorDao
    abstract fun sysmonDao(): SysmonDao

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