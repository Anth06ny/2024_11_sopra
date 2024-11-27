package com.amonteiro.a2024_11_sopra.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amonteiro.a2024_11_sopra.model.PictureBean
import com.amonteiro.a2024_11_sopra.model.WeatherBean
import com.amonteiro.a2024_11_sopra.model.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

fun main() {

    val viewModel = MainViewModel()

    viewModel.loadWeathers("Nice")

    while(viewModel.runInProgress.value) {
        Thread.sleep(500)
    }

    println("List : ${viewModel.dataList.value}")

}

class MainViewModel : ViewModel() {
    //MutableStateFlow est une donnée observable
    val dataList = MutableStateFlow(emptyList<PictureBean>())
    var runInProgress = MutableStateFlow(false)

    fun loadWeathers(cityName: String) {

        runInProgress.value = true

        viewModelScope.launch(Dispatchers.IO) {
            val list: List<WeatherBean> = WeatherRepository.loadWeathers(cityName)

            dataList.value = list.map { city ->
                PictureBean(
                    id = city.id,
                    url = city.weather.firstOrNull()?.icon ?: "",
                    title = city.name,
                    longText = """
                     Il fait ${city.main.temp}° à ${city.name} (id=${city.id}) avec un vent de ${city.wind.speed} m/s
            -Description : ${city.weather.firstOrNull()?.description ?: "-"}
            -Icône : ${city.weather.firstOrNull()?.icon ?: "-"}
                """.trimIndent()

                )
            }
            runInProgress.value = false
        }
    }
}