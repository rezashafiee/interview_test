package shafiee.mr.interviewtest.utils

import android.location.Location
import com.google.android.gms.common.api.ResolvableApiException

interface LocationSupportView {

    fun showLocationSettingDialog(exception: ResolvableApiException)

    fun showPermissionRequestDialog()

    fun onLocationProvided(lastLocation: Location?)
}