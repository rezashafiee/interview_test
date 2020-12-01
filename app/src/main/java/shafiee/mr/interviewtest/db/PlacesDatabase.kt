package shafiee.mr.interviewtest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import shafiee.mr.interviewtest.db.type_converters.DataTypeConverter
import shafiee.mr.interviewtest.model.PlaceDetailsResponse
import shafiee.mr.interviewtest.model.PlaceListResponse

@Database(
    entities = [PlaceListResponse::class, PlaceDetailsResponse::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(DataTypeConverter::class)
abstract class PlacesDatabase : RoomDatabase() {
    abstract fun placesListDao(): PlacesListDao
    abstract fun placeDetailsDao(): PlaceDetailsDao
}