package com.msqr.server_list.view.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msqr.domain.model.Server
import com.msqr.domain.repository.server.ServerRepository
import com.msqr.server_list.ui.ServerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShowServersViewModel(private val serverRepository: ServerRepository) :ViewModel() {

    private val _serverUIStateFlow = MutableStateFlow(ServerUiState())
    val serverUiState = _serverUIStateFlow.asStateFlow()
    private val _serverList = MutableStateFlow<List<Server>>(mutableListOf())
    val serverList : StateFlow<List<Server>> = _serverList.asStateFlow()

    fun loadServers(){
        viewModelScope.launch {
            serverRepository.findAllServers().collectLatest {
                _serverList.value = it
            }
        }
    }

    fun addNewServer(serverName:String, ipAddress:String){
        val server = Server(ipAddress,serverName)
        val tmpList = _serverList.value.toMutableList()
        tmpList.add(server)
        _serverList.value = tmpList
        viewModelScope.launch {
            serverRepository.addServer(server)
        }

    }

    fun updateShowDialog(showDialog: Boolean){
        _serverUIStateFlow.update { it.copy(showDialog = showDialog) }
    }

}