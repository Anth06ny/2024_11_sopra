package com.amonteiro.a2024_11_sopra.model

import java.util.Random

fun main() {

//    val car = CarBean("Seat", "Leon")
//    car.color = "Grise"
//
//    println("Une ${car.marque} ${car.model} ${car.color}")
//
//    var house = HouseBean("Bleu", 10, 5)
//    house.print()


    var t = ThermometerBean.getCelsiusThermometer()

    var r = RandomName()
    var r2 = RandomName()
    r.add("Toto")
    r.addAll("Bobby", "Gustave")

    repeat(10){
        print(r.next() + " ")
    }

}

data class PictureBean(val id:Int, val url: String, val title: String, val longText: String)



class RandomName {
    private val list = arrayListOf("Toto", "Titi", "Bob")
    private var oldValue = ""

    fun add(name:String?) {
        if(!name.isNullOrBlank() && name !in list) {
            list.add(name)
        }
    }

    fun next() = list.random()

    fun nextDiff(): String {
        var newValue = next()
        while(newValue == oldValue) {
            newValue = next()
        }

        oldValue = newValue
        return oldValue
    }

    fun nextDiff2(): String {
        oldValue =  list.filter { it != oldValue  }.random()
        return oldValue
    }

    fun nextDiff3() = list.filter { it != oldValue  }.random().also { oldValue = it }

    fun addAll(vararg names:String){
        for(name in names){
            add(name)
        }
    }
}


class ThermometerBean(var min : Int, var max : Int, value : Int) {
    var value : Int = value.coerceIn(min, max)
        set(newValue) {
            field = newValue.coerceIn(min, max)
        }

    companion object {

        fun getCelsiusThermometer() = ThermometerBean(-0, 50, 0)
        fun getFahrenheitThermometer() = ThermometerBean(20, 120,32)

    }

}

class PrintRandomIntBean(var max : Int){
    private val random : Random = Random()

    init {
        repeat(3) {
            println(random.nextInt(max))
        }
    }

    constructor() : this(100) {
        println(random.nextInt(max))

    }

}

class HouseBean(var color: String, length: Int, width: Int) {
    var area = length * width

    fun print() = println("La maison $color fait ${area}m²")
}

data class CarBean(var marque: String = "", var model: String) {
    var color = ""
}
