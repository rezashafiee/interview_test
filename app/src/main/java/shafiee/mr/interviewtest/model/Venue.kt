package shafiee.mr.interviewtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Venue(

    @Expose
    @SerializedName("id")
    var id: String,
    @Expose
    @SerializedName("name")
    var name: String?,
    @Expose
    @SerializedName("location")
    var location: Location?,
    @Expose
    @SerializedName("categories")
    var categories: List<Category>?,
    @Expose
    @SerializedName("popularityByGeo")
    var popularityByGeo: Double?,
    @Expose
    @SerializedName("venuePage")
    var venuePage: VenuePage?,
    @Expose
    @SerializedName("likes")
    var likesInfo: LikesInfo?,
    @Expose
    @SerializedName("stats")
    var statsInfo: StatsInfo?,
    @Expose
    var rating: String?,
    @Expose
    var ratingSignals: String?,
    @Expose
    var description: String?,
    @Expose
    var bestPhoto: BestPhoto?
) {
    @Expose
    var ratingColor: String? = null
        get() = "#$field"
}