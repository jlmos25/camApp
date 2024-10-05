package com.msqr.server_list.events

/**
 * Class for represent all view states
 */
sealed class ServersViewEvent {
    data object LOADING: ServersViewEvent()
    data object SHOW_DIALOG: ServersViewEvent()
    data object SHOW_EMPTY_SERVER_MESSAGE : ServersViewEvent()
    data object SHOW_SERVER_LIST : ServersViewEvent()
    data class SHOW_SERVER_LIST_WITH_MESSAGE(val message: String) : ServersViewEvent()
}
