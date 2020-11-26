package shafiee.mr.interviewtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class PlacesListData(
    @Expose
    @SerializedName("warning")
    var warning: Warning?,
    @Expose
    @SerializedName("suggestedRadius")
    var suggestedRadius: Int?,
    @Expose
    @SerializedName("headerLocation")
    var headerLocation: String?,
    @Expose
    @SerializedName("headerFullLocation")
    var headerFullLocation: String?,
    @Expose
    @SerializedName("headerLocationGranularity")
    var headerLocationGranularity: String?,
    @Expose
    @SerializedName("totalResults")
    var totalResults: Int?,
    @Expose
    @SerializedName("suggestedBounds")
    var suggestedBounds: SuggestedBounds?,
    @Expose
    @SerializedName("groups")
    var groups: List<Group>?
)

