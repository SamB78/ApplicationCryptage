package com.example.applicationcrpytage.Utils

import timber.log.Timber

fun calculDeterminant(matrice: Array<IntArray>): Int {
    if (matrice.count() == 1) {
        Timber.i("Valeur de matrice[0][0] = ${matrice[0][0]}")
        return matrice[0][0]
    } else if (matrice.count() == 2) {
        val result = matrice[0][0] * matrice[1][1] - matrice[0][1] * matrice[1][0]
        Timber.i("Valeur de matrice = $result")
        return result
    } else {
        return 0
        //A FAIRE
    }
}