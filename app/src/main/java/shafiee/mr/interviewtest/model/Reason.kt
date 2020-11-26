package shafiee.mr.interviewtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Reason(
    @Expose
    @SerializedName("count")
    var count: Int?,
    @Expose
    @SerializedName("items")
    var items: List<ItemX>?
)
