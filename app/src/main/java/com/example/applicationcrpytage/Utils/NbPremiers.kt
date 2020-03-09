package com.example.applicationcrpytage.Utils

import timber.log.Timber

fun CalculDeterminant(matrice: Array<IntArray>): Int {
    if (matrice.count() == 1) {
        Timber.i("Valeur de matrice[0][0] = ${matrice[0][0]}")
        return matrice[0][0]
    } else if (matrice.count() == 2) {
        val result = matrice[0][0] * matrice[1][1] - matrice[0][1] * matrice[1][0]
        Timber.i("Valeur de matrice = $result")
        return result
    } else if (matrice.count() == 3) {
        val result: Int
        result = (matrice[0][0] * CalculDeterminant(
            matriceCut(
                matrice,
                0
            )
        )) - (matrice[0][1] * CalculDeterminant(
            matriceCut(
                matrice,
                1
            )
        )) + (matrice[0][1] * CalculDeterminant(matriceCut(matrice, 2)))
        return result
    }
    return 0
    //A FAIRE
}


fun matriceCut(matrice: Array<IntArray>, i: Int): Array<IntArray> {
    var matriceResultat: Array<IntArray>
    when (i) {
        0 -> {
            matriceResultat = arrayOf(
                intArrayOf(matrice[1][1], matrice[1][2]),
                intArrayOf(matrice[2][1], matrice[2][2])
            )
            return matriceResultat
        }
        1 -> {
            matriceResultat = arrayOf(
                intArrayOf(matrice[1][0], matrice[1][2]),
                intArrayOf(matrice[2][0], matrice[2][2])
            )
            return matriceResultat

        }
        2 -> {
            matriceResultat = arrayOf(
                intArrayOf(matrice[1][0], matrice[1][1]),
                intArrayOf(matrice[2][0], matrice[2][1])
            )
            return matriceResultat

        }
    }
    matriceResultat = arrayOf(intArrayOf(0, 0), intArrayOf(0, 0))
    return matriceResultat
}

//fun Comatrice(matrice: Array<IntArray>): Array<IntArray> {
//
//    var comatrice = [0] * matrice.size
//    var i = 0
//    var k = 0
//    while (i < matrice.size) {
//        comatrice[k] = [0] * matrice.size
//        val l = 0
//        while (l < matrice.size) {
//            TODO("A FAIRE")
//        }
//    }
//}

//fun ExtractionLigneColonneMatrice(
//    ligne: Int,
//    colonne: Int,
//    matrice: Array<IntArray>
//): Array<IntArray>? {
//
//    val tailleMatrice = matrice.size
//    var matriceResultat: Array<IntArray>
//
//    return null
//}

