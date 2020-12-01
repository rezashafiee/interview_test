package shafiee.mr.interviewtest.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class PlaceDetailsResponse(

    @PrimaryKey
    var id: Int,

    @Expose
    @SerializedName("response")
    var data: Data?
)