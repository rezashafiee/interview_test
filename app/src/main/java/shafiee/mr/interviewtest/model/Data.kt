package shafiee.mr.interviewtest.model

import com.google.gson.annotations.Expose

data class Data(
    @Expose
    var warning: Warning?,
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
    @Expose
    var suggestedBounds: SuggestedBounds?,
    @Expose
    var groups: List<Group>?,
    @Expose
    var venue: Venue?
)

