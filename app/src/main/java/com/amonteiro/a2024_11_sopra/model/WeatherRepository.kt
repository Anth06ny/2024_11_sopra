package com.amonteiro.a2024_11_sopra.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

//Utilisation
fun main() {
//    //Lance la requête et met le corps de la réponse dans html
//    var html : String = WeatherRepository.sendGet("https://www.google.fr")
//    //Affiche l'HTML
//    println("html=$html")

    //WeatherRepository.loadWeathers("Nice")

//    var user = WeatherRepository.loadRandomUser()
//    println("""
//        Il s'appelle ${user.name} pour le contacter :
//        Phone : ${user.coord?.phone ?: "-"}
//        Mail : ${user.coord?.mail ?: "-"}
//    """.trimIndent())
//
//
//    var users = WeatherRepository.loadRandomUsers()
//    for(u in users) {
//        println(
//            """
//        Il s'appelle ${u.name} pour le contacter :
//        Phone : ${u.coord?.phone ?: "-"}
//        Mail : ${u.coord?.mail ?: "-"}
//    """.trimIndent()
//        )
//    }


    val weatherList = WeatherRepository.loadWeathers("Nice")

    for(city in weatherList){
        println("""
            Il fait ${city.main.temp}° à ${city.name} (id=${city.id}) avec un vent de ${city.wind.speed} m/s
            -Description : ${city.weather.firstOrNull()?.description ?: "-"}
            -Icône : ${city.weather.firstOrNull()?.icon ?: "-"}
        """.trimIndent())
        println("------------------")
    }
}

object WeatherRepository {
    val client = OkHttpClient()
    val gson = Gson()

    private const val URL_API = "https://api.openweathermap.org/data/2.5/find?cnt=5&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr&q="

    fun loadWeathers(cityName : String): List<WeatherBean> {
        //Verification
        //Requete
        val json : String = sendGet(URL_API + cityName)
        Thread.sleep(5000)
        //Parsing
        val list = gson.fromJson(json, WeatherResultBean::class.java).list
        //Traitement, je remplace le nom de l'icone par l'url
        list.forEach {
            it.weather.forEach {
                it.icon = "https://openweathermap.org/img/wn/${it.icon}@4x.png"
            }
        }

        //Verification
        return list
    }

    fun loadRandomUser(): UserBean {
        val json : String = sendGet("https://www.amonteiro.fr/api/randomuser")
        println(json)
        return gson.fromJson(json, UserBean::class.java)
    }

    fun loadRandomUsers(): List<UserBean> {
        val json : String = sendGet("https://www.amonteiro.fr/api/randomusers")
        println(json)
        return gson.fromJson(json, Array<UserBean>::class.java).toList()
    }

    //Méthode qui prend en entrée une url, execute la requête
    //Retourne le code HTML/JSON reçu
    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
        //Execution de la requête
        return client.newCall(request).execute().use { //it:Response
            //use permet de fermer la réponse qu'il y ait ou non une exception
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}\n${it.body.string()}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}

/* -------------------------------- */
// Weather
/* -------------------------------- */
data class WeatherResultBean(var list: List<WeatherBean>)
data class WeatherBean(var id: Int, var name:String, var main: TempBean, var wind : WindBean, var weather : List<DescriptionBean>  )
data class TempBean(var temp:Double )
data class WindBean(var speed:Double )
data class DescriptionBean(var description:String, var icon:String )

/* -------------------------------- */
// USER
/* -------------------------------- */

data class UserBean(var name:String, var age:Int, var coord:CoordBean?)
data class CoordBean(var phone:String?, var mail:String?)