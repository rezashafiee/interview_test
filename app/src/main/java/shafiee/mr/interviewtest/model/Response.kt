package shafiee.mr.interviewtest.model

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(
    primaryKeys = ["id"]
)
data class Response(
    var id: Int,

    @Expose
    @SerializedName("response")
    var placesListData: PlacesListData?
)
