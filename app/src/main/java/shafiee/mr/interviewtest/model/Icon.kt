package shafiee.mr.interviewtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Icon(
    @Expose
    @SerializedName("prefix")
    var prefix: String?,
    @Expose
    @SerializedName("suffix")
    var suffix: String?
)