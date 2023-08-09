package app.futured.academyproject.domain

import app.futured.academyproject.data.model.local.Place
import app.futured.academyproject.data.store.PlacesStore
import app.futured.arkitekt.crusecases.UseCase
import javax.inject.Inject

class GetPlaceUseCase @Inject constructor(private val placesStore: PlacesStore) : UseCase<Int, Place>() {

    override suspend fun build(args: Int): Place {
        return placesStore.getPlace(args) ?: throw IllegalArgumentException("id ${args} not found")
    }

    data class Args(
        val placeId: Int
    )
}
