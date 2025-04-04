package dev.luboganev.kspnumberofloadedfilesissue

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.luboganev.kspnumberofloadedfilesissue.remotetime.LondonTimeProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val helloWorldProvider: HelloWorldProvider,
    private val londonTimeProvider: LondonTimeProvider,
) : ViewModel() {

    private val _helloWorldMessageState = MutableStateFlow("")

    val helloWorldMessageState: StateFlow<String> = _helloWorldMessageState.asStateFlow()

    init {
        viewModelScope.launch {
            _helloWorldMessageState.value = "Fetching..."
            val londonTimeMessage = try {
                withContext(Dispatchers.IO) {
                    londonTimeProvider.getLondonTime()
                }
            } catch (e: Exception) {
                "Failed to fetch London"
            }

            val localMessage = helloWorldProvider.getMessage()
            _helloWorldMessageState.value = "$localMessage\n$londonTimeMessage"
        }
    }
}
