package shafiee.mr.interviewtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Item(
    @Expose
    @SerializedName("reasons")
    var reason: Reason?,
    @Expose
    @SerializedName("venue")
    var venue: Venue?
)
