package com.msqr.server_list.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.msqr.domain.model.Server
import com.msqr.server_list.view.model.ServerStateViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun serverComponentCard(server: Server,serverStateViewModel: ServerStateViewModel = koinViewModel()) {

    val serverUiState by serverStateViewModel.serverUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit){
        serverStateViewModel.checkServerState(server)
    }

    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = if (serverUiState.connected) {
               Color.Green
            } else {
                Color.Red
            },
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = server.serverName,
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
        )
    }
}