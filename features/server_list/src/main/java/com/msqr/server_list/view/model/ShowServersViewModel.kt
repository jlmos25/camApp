package com.msqr.server_list.view.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShowServersViewModel() :ViewModel() {

    private val _serverList = MutableStateFlow<List<String>>(mutableListOf())
    val serverList : StateFlow<List<String>> = _serverList.asStateFlow()

    fun loadServers(){
        val servers = mutableListOf<String>()
        servers.add("s1")
        servers.add("s2")
        servers.add("s3")
        servers.add("s4")
        _serverList.value = servers
    }

}