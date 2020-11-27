package shafiee.mr.interviewtest.db.type_converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import shafiee.mr.interviewtest.model.PlacesListData

class PlacesListDataTypeConverter {

    @TypeConverter
    fun fromJson(json: String): PlacesListData {
        return Gson().fromJson(json, PlacesListData::class.java)
    }

    @TypeConverter
    fun toJson(placesListData: PlacesListData): String {
        return Gson().toJson(placesListData)
    }

}