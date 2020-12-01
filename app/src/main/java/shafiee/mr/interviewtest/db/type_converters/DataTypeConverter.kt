package shafiee.mr.interviewtest.db.type_converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import shafiee.mr.interviewtest.model.Data

class DataTypeConverter {

    @TypeConverter
    fun fromJson(json: String): Data {
        return Gson().fromJson(json, Data::class.java)
    }

    @TypeConverter
    fun toJson(data: Data): String {
        return Gson().toJson(data)
    }

}