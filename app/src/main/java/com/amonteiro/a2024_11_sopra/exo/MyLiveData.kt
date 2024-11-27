package com.amonteiro.a2024_11_sopra.exo

import com.amonteiro.a2024_11_sopra.model.CarBean

fun main() {
    var data  = MyLiveData(CarBean("Seat", "Leon"))

    data.action = {
        println(it)
    }

    data.value = data.value.copy(marque = "Nissan")

}

class MyLiveData<T>(value : T){

     var value = value
         set(newValue) {
             field = newValue
             action.invoke(newValue)
         }

     var action : (T)->Unit = {}

}