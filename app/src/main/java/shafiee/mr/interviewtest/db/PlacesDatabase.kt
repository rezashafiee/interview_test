package shafiee.mr.interviewtest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import shafiee.mr.interviewtest.db.type_converters.PlacesListDataTypeConverter
import shafiee.mr.interviewtest.model.Response

@Database(entities = [Response::class], version = 3, exportSchema = false)
@TypeConverters(PlacesListDataTypeConverter::class)
abstract class PlacesDatabase : RoomDatabase() {
    abstract fun placesListDao(): PlacesListDao
}