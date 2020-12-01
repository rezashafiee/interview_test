package shafiee.mr.interviewtest.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class PlaceDetailsResponse(
    @Expose
    @SerializedName("response")
    var data: Data?
) {
    @PrimaryKey
    var id: String = ""
        get() = data?.venue?.id!!
        set(value) {
            value.let { field = it }
        }
}