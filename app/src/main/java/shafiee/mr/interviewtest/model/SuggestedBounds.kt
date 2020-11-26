package shafiee.mr.interviewtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SuggestedBounds(
    @Expose
    @SerializedName("ne")
    var ne: Ne?,
    @Expose
    @SerializedName("sw")
    var sw: Sw?
)