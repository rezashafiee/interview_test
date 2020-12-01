package shafiee.mr.interviewtest.ui.places.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import shafiee.mr.interviewtest.model.PlaceDetailsResponse
import shafiee.mr.interviewtest.network.Resource
import shafiee.mr.interviewtest.repository.PlaceDetailsRepository
import javax.inject.Inject

class PlaceDetailsViewModel @Inject constructor(
    var placeDetailsRepository: PlaceDetailsRepository
) : ViewModel() {

    fun loadPlaceDetails(id: String): LiveData<Resource<PlaceDetailsResponse>> {
        return placeDetailsRepository.loadPlaceDetails(id)
    }
}