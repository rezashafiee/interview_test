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
        private const val KEY_LAST_PAGE = "last_page"

        fun getInstance(context: Context): PreferencesManager? {
            if (instance == null)
                instance = PreferencesManager(context)
            return instance
        }
    }

    fun saveLocationLat(latitude: String?) {
        sharedPreferences?.edit()?.putString(KEY_LATITUDE, latitude!!)?.apply()
    }

    fun getLastLocationLat(): Double? {
        return sharedPreferences?.getString(KEY_LATITUDE, "0")?.toDouble()
    }

    fun saveLocationLng(longitude: String?) {
        sharedPreferences?.edit()?.putString(KEY_LONGITUDE, longitude!!)?.apply()
    }

    fun getLastLocationLng(): Double? {
        return sharedPreferences?.getString(KEY_LONGITUDE, "0")?.toDouble()
    }

    fun savePageNumber(pageNumber: Int) {
        sharedPreferences?.edit()?.putInt(KEY_LAST_PAGE, pageNumber)?.apply()
    }

    fun getLastPage(): Int? {
        return sharedPreferences?.getInt(KEY_LAST_PAGE, 1)
    }

    fun removeLastPage() {
        sharedPreferences?.edit()?.remove(KEY_LAST_PAGE)?.apply()
    }
}