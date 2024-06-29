package vn.dungnt.medpro.presentation.viewmodels.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import vn.dungnt.medpro.domain.entities.UiState

open class BaseViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.None)
    var uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun setUiState(state: UiState) {
        _uiState.value = state
    }
}