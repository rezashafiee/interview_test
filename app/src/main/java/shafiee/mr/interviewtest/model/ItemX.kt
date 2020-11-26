package shafiee.mr.interviewtest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ItemX(
    @Expose
    @SerializedName("summary")
    var summary: String?,
    @Expose
    @SerializedName("type")
    var type: String?,
    @Expose
    @SerializedName("reasonName")
    var reasonName: String?
)
