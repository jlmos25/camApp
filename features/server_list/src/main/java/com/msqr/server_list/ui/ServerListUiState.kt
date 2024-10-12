package com.msqr.server_list.ui

import com.msqr.domain.model.Server

data class ServerListUiState(
    val servesList: List<Server> = listOf()
)