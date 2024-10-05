package com.msqr.server_list.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.msqr.server_list.view.model.ShowServersViewModel
import org.koin.androidx.compose.koinViewModel
import java.security.AllPermission


@Composable
fun show_servers(showServersViewModel: ShowServersViewModel = koinViewModel()){


    LaunchedEffect(Unit){
        showServersViewModel.loadServers()
    }

    val serverList by showServersViewModel.serverList.collectAsStateWithLifecycle()
    val serverUiState by showServersViewModel.serverUiState.collectAsStateWithLifecycle()

    addDialog(serverUiState, showServersViewModel)

    Scaffold(floatingActionButton = { actionButton(showServersViewModel) }) {
        Column(Modifier.padding(it)) {
            Text(text = "Servidores disponibles")
            LazyColumn(){
                items(serverList) {
                    OutlinedCard(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                        ),
                        border = BorderStroke(1.dp, Color.Black),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = it.serverName,
                            modifier = Modifier
                                .padding(16.dp),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
    }


    }
}

@Composable
fun actionButton(showServersViewModel: ShowServersViewModel) {
    FloatingActionButton(onClick =
    {showServersViewModel.updateShowDialog(true)}){
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}

@Composable
fun addDialog(serverUiState:ServerUiState,serversViewModel: ShowServersViewModel){
    if (serverUiState.showDialog) {
        var serverName by remember { mutableStateOf("") }
        var serverIp by remember { mutableStateOf("") }
        Dialog(onDismissRequest = {serversViewModel.updateShowDialog(false)}){
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White
            ){
                Box(contentAlignment = Alignment.Center){
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier =
                    Modifier.padding(top = 5.dp)) {
                        TextField(
                            value = serverName,
                            onValueChange = { serverName = it },
                            label = { Text("Add Server Name") }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        TextField(
                            value = serverIp,
                            onValueChange = { serverIp = it },
                            label = { Text("Add Server Ip") }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(onClick = {
                            serversViewModel.updateShowDialog(false)
                            serversViewModel.addNewServer(serverName,serverIp)
                        }) {
                            Text(text = "Add Server")
                        }
                    }
                }
            }
        }
    }
}