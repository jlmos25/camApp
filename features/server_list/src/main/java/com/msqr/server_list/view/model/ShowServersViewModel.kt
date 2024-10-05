package com.msqr.server_list.view.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msqr.domain.model.Response
import com.msqr.domain.model.Server
import com.msqr.domain.repository.server.ServerRepository
import com.msqr.server_list.events.ServersViewEvent
import com.msqr.server_list.ui.ServerUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShowServersViewModel(private val serverRepository: ServerRepository) :ViewModel() {

    private val _serverUIStateFlow = MutableStateFlow(ServerUiState())
    val serverUiState = _serverUIStateFlow.asStateFlow()

    private val _serverViewEvent = MutableStateFlow<ServersViewEvent>(ServersViewEvent.LOADING)
    val serverViewEvent = _serverViewEvent.asStateFlow()

    fun loadServers(){
        viewModelScope.launch {
            serverRepository.findAllServers().collectLatest { serverList ->

                if (serverList.isEmpty()){
                    _serverViewEvent.value = ServersViewEvent.SHOW_EMPTY_SERVER_MESSAGE
                }else {
                    _serverUIStateFlow.update { it.copy(servesList = serverList) }
                    _serverViewEvent.value = ServersViewEvent.SHOW_SERVER_LIST
                }
            }
        }
    }

    fun addNewServer(serverName:String, ipAddress:String){
        val server = Server(ipAddress,serverName)

        viewModelScope.launch {
            serverRepository.addServer(server).collectLatest{response ->
                when(response){
                    is Response.Sucess -> {
                        if(response.data){
                            val tmpList = _serverUIStateFlow.value.servesList.toMutableList()
                            tmpList.add(server)
                            _serverUIStateFlow.update { it.copy(servesList = tmpList) }
                            _serverViewEvent.value = ServersViewEvent.SHOW_SERVER_LIST
                        }else {
                            _serverViewEvent.value = ServersViewEvent
                                .SHOW_SERVER_LIST_WITH_MESSAGE("El Servidor Ya Existe")
                        }
                    }
                    else -> { _serverViewEvent.value = ServersViewEvent
                        .SHOW_SERVER_LIST_WITH_MESSAGE("Ha Ocurrido un problema")}
                }
            }
        }

    }

    fun updateShowDialog(showDialog: Boolean){
        if (showDialog) {
            _serverViewEvent.value = ServersViewEvent.SHOW_DIALOG
        }else {
            _serverViewEvent.value = ServersViewEvent.SHOW_SERVER_LIST
        }

    }

}