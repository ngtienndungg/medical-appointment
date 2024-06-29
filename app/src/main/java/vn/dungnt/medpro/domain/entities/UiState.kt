package vn.dungnt.medpro.domain.entities

sealed class UiState {
    data object None : UiState()
    data object Loading : UiState()
    data class Error(val errorMessage: String?) : UiState()
}