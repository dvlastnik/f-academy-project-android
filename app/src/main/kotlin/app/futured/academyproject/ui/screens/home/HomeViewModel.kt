package app.futured.academyproject.ui.screens.home

import app.futured.academyproject.data.model.local.Place
import app.futured.academyproject.domain.GetPlacesUseCase
import app.futured.academyproject.tools.arch.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    override val viewState: HomeViewState,
    private val getPlacesUseCase: GetPlacesUseCase
) : BaseViewModel<HomeViewState>(), Home.Actions {

    init {
        loadCulturalPlaces()
    }

    private fun loadCulturalPlaces() {
        //TODO load somehow data by usecase and pass them to viewstate so they will be shown in UI
        getPlacesUseCase.execute(

        ) {
            onSuccess {
                viewState.places = it!!.toPersistentList()
            }
            onError {
                println("Error")
            }
        }
    }

    // this is action from UI so it overrides method from Home.Actions interface
    override fun navigateToDetailScreen(placeId: Int) {
        sendEvent(NavigateToDetailEvent(placeId))
    }
}
