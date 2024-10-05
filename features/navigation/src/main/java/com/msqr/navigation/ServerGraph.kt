package com.msqr.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.msqr.server_list.ui.show_servers


fun NavGraphBuilder.serverNavigationGraph(navController: NavController){
    navigation(route = Graph.SERVERS,
    startDestination = ServersScreen.ListServers.route ){
        composable (route = ServersScreen.ListServers.route){
            show_servers()
        }

    }
}

sealed class ServersScreen(val route: String){
    data object ListServers : ServersScreen( route = "SERVERS_LIST")
}