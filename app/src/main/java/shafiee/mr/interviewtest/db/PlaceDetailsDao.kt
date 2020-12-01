package shafiee.mr.interviewtest.db

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import shafiee.mr.interviewtest.model.PlaceDetailsResponse

interface PlaceDetailsDao {

    @Query("SELECT * FROM placedetailsresponse WHERE id = (:id)")
    fun getById(id: Int): LiveData<PlaceDetailsResponse>

    /*@Query("SELECT * FROM user WHERE uid IN (:venueIds)")
    fun loadAllByIds(venueIds: IntArray): List<Venue>*/

    /*@Query("SELECT * FROM items WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Venue*/

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(placesListData: PlacesListData)

    @Delete
    fun delete(venue: Venue)*/

    @Query("DELETE FROM placedetailsresponse")
    fun removeAll()

    @Insert
    fun insert(placeDetailsResponse: PlaceDetailsResponse)
}