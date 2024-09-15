package com.msqr.server_list.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.msqr.server_list.view.model.ShowServersViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun show_servers(showServersViewModel: ShowServersViewModel = koinViewModel()){


    LaunchedEffect(Unit){
        showServersViewModel.loadServers()
    }

    val serverList by showServersViewModel.serverList.collectAsStateWithLifecycle()

    LazyColumn(){
        items(serverList) {
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier.fillMaxWidth().fillMaxHeight()
            ) {
                Text(
                    text = it,
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }

}