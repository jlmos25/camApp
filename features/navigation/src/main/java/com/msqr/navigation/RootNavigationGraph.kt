package com.msqr.navigation;

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun RootNavigationGraph(navController: NavHostController){
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.SERVERS
    ){
        serverNavigationGraph(navController)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val SERVERS  = "servers_graph"
}