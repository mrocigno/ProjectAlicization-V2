package br.com.mrocigno.alicization.data.repository.home

import kotlinx.coroutines.flow.flow

class HomeRepository(
    private val localHomeData: HomeData,
    private val remoteHomeData: HomeData
) {

    fun getHome() = flow {
        emit(remoteHomeData.getHomeItems())
    }
}