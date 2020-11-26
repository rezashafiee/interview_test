package shafiee.mr.interviewtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LabeledLatLng(
    @Expose
    @SerializedName("label")
    var label: String?,
    @Expose
    @SerializedName("lat")
    var lat: Double?,
    @Expose
    @SerializedName("lng")
    var lng: Double?
)