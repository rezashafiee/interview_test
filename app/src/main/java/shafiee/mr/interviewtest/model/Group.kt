package shafiee.mr.interviewtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Group(
    @Expose
    @SerializedName("type")
    var type: String?,
    @Expose
    @SerializedName("name")
    var name: String?,
    @Expose
    @SerializedName("items")
    var items: List<Item>?
)
