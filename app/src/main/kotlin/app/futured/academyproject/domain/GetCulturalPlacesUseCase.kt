package app.futured.academyproject.domain

import app.futured.academyproject.data.model.api.CulturalPlaces
import app.futured.academyproject.data.model.local.Place
import app.futured.academyproject.data.remote.ApiManager
import app.futured.arkitekt.crusecases.UseCase
import javax.inject.Inject

class GetCulturalPlacesUseCase @Inject constructor(
    private val apiManager: ApiManager,
) : UseCase<Unit, List<Place>>() {

    // TODO Krok 6:
    //  Implementuj metódu build, ktorá vráti zoznam miest.
    //  Využi ApiManager a namapuj CulturalPlaces na List<Place>.
    //  Mali by stačiť iba nasledujúce properties: id, longitude, latitude, name, type, note,
    override suspend fun build(args: Unit): List<Place> {
        return apiManager.getCulturalPlaces().features.map {
            Place(id = it.properties.ogcFid, name = it.properties.name, type = it.properties.type, image1Url = it.properties.image1Url,
            latitude = it.geometry?.coordinates?.get(0) ?: 0.0, longitude = it.geometry?.coordinates?.get(1) ?: 0.0, note = it.properties.note)
        }
    }
}
