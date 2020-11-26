package shafiee.mr.interviewtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Warning(
    @Expose
    @SerializedName("text")
    var text: String?
)