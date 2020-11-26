package shafiee.mr.interviewtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Sw(
    @Expose
    @SerializedName("lat")
    var lat: Double?,
    @Expose
    @SerializedName("lng")
    var lng: Double?
)