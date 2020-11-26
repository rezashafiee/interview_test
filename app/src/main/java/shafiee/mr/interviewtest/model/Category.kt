package shafiee.mr.interviewtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category(
    @Expose
    @SerializedName("id")
    var id: String?,
    @Expose
    @SerializedName("name")
    var name: String?,
    @Expose
    @SerializedName("pluralName")
    var pluralName: String?,
    @Expose
    @SerializedName("shortName")
    var shortName: String?,
    @Expose
    @SerializedName("icon")
    var icon: Icon?,
    @Expose
    @SerializedName("primary")
    var primary: Boolean?
)