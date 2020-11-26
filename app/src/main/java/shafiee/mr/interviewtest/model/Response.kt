package shafiee.mr.interviewtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response(
    @Expose
    @SerializedName("response")
    var data: Data?
)
