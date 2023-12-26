package br.com.mrocigno.alicization.data.repository

class HomeRepository(
    private val localHomeData: HomeData,
    private val remoteHomeData: HomeData
) {

    fun getHome(): String {
        return "${localHomeData.getHomeItems().first().name}  ${remoteHomeData.getHomeItems().first().name}"
    }
}