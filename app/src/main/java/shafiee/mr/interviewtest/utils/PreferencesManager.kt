package shafiee.mr.interviewtest.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager private constructor(context: Context) {

    private var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = context.getSharedPreferences("ShredPref", Context.MODE_PRIVATE)
    }

    companion object {
        private var instance: PreferencesManager? = null

        private const val KEY_LATITUDE = "latitude"
        private const val KEY_LONGITUDE = "longitude"

        fun getInstance(context: Context): PreferencesManager? {
            if (instance == null)
                instance = PreferencesManager(context)
            return instance
        }
    }

    fun saveCurrentLocationLat(latitude: String?) {
        sharedPreferences?.edit()?.putString(KEY_LATITUDE, latitude!!)?.apply()
    }

    fun getLastLocationLat(): Double? {
        return sharedPreferences?.getString(KEY_LATITUDE, "0")?.toDouble()
    }

    fun saveCurrentLocationLng(longitude: String?) {
        sharedPreferences?.edit()?.putString(KEY_LONGITUDE, longitude!!)?.apply()
    }

    fun getLastLocationLng(): Double? {
        return sharedPreferences?.getString(KEY_LONGITUDE, "0")?.toDouble()
    }
}