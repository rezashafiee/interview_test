package shafiee.mr.interviewtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BestPhoto(
    @Expose
    @SerializedName("visibility")
    var visibility: String?,
    @Expose
    @SerializedName("height")
    var height: String?,

    @Expose
    @SerializedName("width")
    var width: String?,

    @Expose
    @SerializedName("suffix")
    var suffix: String?,

    @Expose
    @SerializedName("prefix")
    var prefix: String?,

    /*@Expose
    @SerializedName("source")
    var source: Source?,*/

    @Expose
    @SerializedName("createdAt")
    var createdat: String?,

    @Expose
    @SerializedName("id")
    var id: String? = null
)