package shafiee.mr.interviewtest.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import shafiee.mr.interviewtest.model.Response

@Dao
interface PlacesListDao {
    @Query("SELECT * FROM response")
    fun getAll(): LiveData<Response>

    /*@Query("SELECT * FROM user WHERE uid IN (:venueIds)")
    fun loadAllByIds(venueIds: IntArray): List<Venue>*/

    /*@Query("SELECT * FROM items WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Venue*/

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(placesListData: PlacesListData)

    @Delete
    fun delete(venue: Venue)*/


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(response: Response)
}