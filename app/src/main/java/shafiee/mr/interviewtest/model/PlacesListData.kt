package shafiee.mr.interviewtest.model

import com.google.gson.annotations.Expose

data class PlacesListData(
    /*@Expose
    @SerializedName("warning")
    var warning: Warning?,*/
    @Expose
    var suggestedRadius: Int?,
    @Expose
    var headerLocation: String?,
    @Expose
    var headerFullLocation: String?,
    @Expose
    var headerLocationGranularity: String?,
    @Expose
    var totalResults: Int?,
    /*@Expose
    @SerializedName("suggestedBounds")
    var suggestedBounds: SuggestedBounds?,
    @Expose
    @SerializedName("groups")
    var groups: List<Group>?*/
)

