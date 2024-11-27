package com.amonteiro.a2024_11_sopra.exo

import com.amonteiro.a2024_11_sopra.PRICE_BAGUETTE
import com.amonteiro.a2024_11_sopra.PRICE_CROISSANT
import com.amonteiro.a2024_11_sopra.PRICE_SANDWITCH


fun main() {

    println(scanNumber("Donnez un chiffre : "))


}

fun scanNumber(question:String): Int {
    print(question)
    return readlnOrNull()?.toIntOrNull() ?: 0
}


fun scanText(question:String): String {
    print(question)
    return readlnOrNull() ?: "-"
}


fun pair(c:Int) = c%2 == 0
fun myPrint(text:String) = println("#$text#")
fun boulangerie(nbC : Int = 0, nbB : Int = 0, nbS : Int = 0) =  nbC * PRICE_CROISSANT + nbB * PRICE_BAGUETTE + nbS * PRICE_SANDWITCH

