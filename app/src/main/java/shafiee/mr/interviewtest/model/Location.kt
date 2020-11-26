package shafiee.mr.interviewtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Location(
    @Expose
    @SerializedName("address")
    var address: String?,
    @Expose
    @SerializedName("crossStreet")
    var crossStreet: String?,
    @Expose
    @SerializedName("lat")
    var lat: Double?,
    @Expose
    @SerializedName("lng")
    var lng: Double?,
    @Expose
    @SerializedName("labeledLatLngs")
    var labeledLatLngs: List<LabeledLatLng>?,
    @Expose
    @SerializedName("distance")
    var distance: Int?,
    @Expose
    @SerializedName("postalCode")
    var postalCode: String?,
    @Expose
    @SerializedName("cc")
    var cc: String?,
    @Expose
    @SerializedName("city")
    var city: String?,
    @Expose
    @SerializedName("state")
    var state: String?,
    @Expose
    @SerializedName("country")
    var country: String?,
    @Expose
    @SerializedName("formattedAddress")
    var formattedAddress: List<String>?
)