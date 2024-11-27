package com.amonteiro.a2024_11_sopra.exo

fun main() {
//    exo1()
    exo3()
}

fun exo1() {

    arrayListOf("", "").joinToString()

    //Déclaration
    val lower: (String) -> Unit = { it: String -> println(it.lowercase()) }
    val lower2 = { it: String -> println(it.lowercase()) }
    val lower3: (String) -> Unit = { it -> println(it.lowercase()) }
    val lower4: (String) -> Unit = { println(it.lowercase()) }

    val hour: (Int) -> Int = { it / 60 }
    val max = { a: Int, b: Int -> Math.max(a, b) }
    val reverse: (String) -> String = { it.reversed() }

    //Appel
    lower("Coucou")
    println(hour(125))
    println(max(125, 200))
    println(reverse("coucou"))


    var minToMinHour: ((Int?) -> Pair<Int, Int>?)? = { if(it==null) null else  Pair(it / 60, it % 60) }

    val min = minToMinHour?.invoke(null)
    println(min)
    minToMinHour = null
    println(min)
}

data class PersonBean(var name:String, var note:Int)

fun exo3(){
    val list = arrayListOf(PersonBean ("toto", 16),
        PersonBean ("Tata", 20),
        PersonBean ("Toto", 8),
        PersonBean ("Charles", 14))

    println("Afficher la sous liste de personne ayant 10 et +")
    //println(list.filter { it.note >=10 })
    //Pour un affichage de notre choix
    println(list.filter { it.note >=10 }.joinToString("\n") { "-${it.name} : ${it.note}"})

    val isToto : (PersonBean)->Boolean = {it.name.equals("toto", true)}

    //TODO
    print("\n\nAfficher combien il y a de Toto dans la classe ? : ")
    println(list.count(isToto))

    print("\n\nAfficher combien de Toto ayant la moyenne (10 et +) : ")
    println(list.count { isToto(it) && it.note >= 10 })

    println("\n\nAfficher combien de Toto ont plus que la moyenne de la classe")
    val average = list.map{it.note}.average()
    println(list.count { it.name.equals("toto", true) && it.note >= average })

    println("\n\nAfficher la list triée par nom sans doublon")
    println(list.distinctBy { it.name.lowercase() }.sortedBy { it.name }.joinToString("\n") { "-${it.name} : ${it.note}"})

    println("\n\nAjouter un point a ceux n’ayant pas la moyenne (<10)")
    list.filter { it.note <10 }.forEach { it.note++  }

    println("\n\nAjouter un point à tous les Toto")
    list.filter { it.name.equals("toto, true") }.forEach { it.note++  }

    println("\n\nRetirer de la liste ceux ayant la note la plus petite. (Il peut y en avoir plusieurs)")
    val minNote = list.minOf { it.note }
    list.removeIf { it.note == minNote }

    println("\n\nAfficher les noms de ceux ayant la moyenne(10et+) par ordre alphabétique")
    println(list.filter{it.note >= 10}.sortedBy { it.name.lowercase() }.joinToString("\n") { "-${it.name} : ${it.note}"})


    //TODO Créer une variable isToto contenant une lambda qui teste si un PersonBean s'appelle Toto

    println("\n\nDupliquer la liste ainsi que tous les utilisateurs (nouvelle instance) qu'elle contient")
    println("\n\nAfficher par notes croissantes les élèves ayant eu cette note comme sur l'exemple")
}