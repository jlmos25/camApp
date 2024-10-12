package com.msqr.server_list.view.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msqr.domain.model.Response
import com.msqr.domain.model.Server
import com.msqr.domain.repository.server.ServerRepository
import com.msqr.server_list.ui.ServerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ServerStateViewModel(private val serverRepository: ServerRepository) : ViewModel()  {

    private val _serverUiState = MutableStateFlow(ServerUiState())
    val serverUiState = _serverUiState.asStateFlow()

    fun checkServerState(server: Server){
        viewModelScope.launch {
            serverRepository.checkServerConnection(server).collectLatest {response ->
                when(response){
                    is Response.Success -> _serverUiState.update { it.copy(connected = response.data) }
                    is Response.Error -> _serverUiState.update { it.copy(connected = false) }
                }
            }
        }

    }
}