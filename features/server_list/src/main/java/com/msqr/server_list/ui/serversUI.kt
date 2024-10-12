package com.msqr.server_list.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.msqr.server_list.events.ServersViewEvent
import com.msqr.server_list.view.model.ShowServersViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun show_servers(showServersViewModel: ShowServersViewModel = koinViewModel()){

    val event by showServersViewModel.serverViewEvent.collectAsStateWithLifecycle()

    LaunchedEffect(Unit){
        showServersViewModel.loadServers()
    }

    Scaffold(floatingActionButton = { actionButton(showServersViewModel) }) {
        when(event){
            is ServersViewEvent.LOADING -> {
                show_loading()
            }
            is ServersViewEvent.SHOW_SERVER_LIST -> {
                load_servers(
                    showServersViewModel, it, {}, {})
            }

            is ServersViewEvent.SHOW_DIALOG -> load_servers(
                showServersViewModel,
                it,
                { showDialog(showServersViewModel) },
                {}
            )

            is ServersViewEvent.SHOW_EMPTY_SERVER_MESSAGE -> empty_servers(it)
            is ServersViewEvent.SHOW_SERVER_LIST_WITH_MESSAGE -> {
                load_servers(showServersViewModel, it,{} , {
                    showMessageNotification(message =
                    (event as ServersViewEvent.SHOW_SERVER_LIST_WITH_MESSAGE).message)
                })

            }
        }
    }
}

@Composable
fun showMessageNotification(message:String){
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        Toast.makeText(
            context,message,
            Toast.LENGTH_SHORT
        ).show()}
}
@Composable
fun empty_servers(paddingValues: PaddingValues) {
    Column(Modifier.padding(paddingValues)) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "No hay servidores, desea añadir uno",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun show_loading() {
    Column( modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
            Row(
                modifier = Modifier.padding(30.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center,
                    text = "Cargando Servidores"
                )
            }
        }
    }
}

@Composable
fun load_servers(
    showServersViewModel: ShowServersViewModel,
    paddingValues: PaddingValues,
    showDialogPass: @Composable (ShowServersViewModel) -> Unit,
    showToast: @Composable () -> Unit,
){
    val serverUiState by showServersViewModel.serverUiState.collectAsStateWithLifecycle()

    showDialogPass(showServersViewModel)
    showToast()

        Column(Modifier.padding(paddingValues)) {
            Text(text = "Servidores disponibles")
            LazyColumn(){
                items(serverUiState.servesList) {
                    serverComponentCard(it)
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
fun showDialog(serversViewModel: ShowServersViewModel){
        var serverName by rememberSaveable { mutableStateOf("") }
        var serverIp by rememberSaveable { mutableStateOf("") }
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