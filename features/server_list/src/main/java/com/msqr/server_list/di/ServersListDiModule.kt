package com.msqr.server_list.di

import com.msqr.server_list.view.model.ShowServersViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val serversModule = module {
    viewModelOf(::ShowServersViewModel)
}

