package com.example.applicationcrpytage.Utils

import timber.log.Timber


fun PGCD(a: Int, b: Int): Int {
    return if (a == b) {
        a
    } else {
        if (a > b)
            PGCD(a - b, b)
        else
            PGCD(a, b - a)
    }
}

fun calculDeterminant(matrice: Array<IntArray>): Int {
    when {
        matrice.count() == 1 -> {
            Timber.i("Valeur de matriceCryptage[0][0] = ${matrice[0][0]}")
            return matrice[0][0]
        }
        matrice.count() == 2 -> {
            val result = matrice[0][0] * matrice[1][1] - matrice[0][1] * matrice[1][0]
            Timber.i("Valeur de matriceCryptage = $result")
            return result
        }
        matrice.count() == 3 -> {
            val result: Int = (matrice[0][0] * calculDeterminant(
                matriceCut(
                    matrice,
                    0
                )
            )) - (matrice[0][1] * calculDeterminant(
                matriceCut(
                    matrice,
                    1
                )
            )) + (matrice[0][2] * calculDeterminant(matriceCut(matrice, 2)))

            Timber.i(" result determinant = $result")
            return result
        }
        else -> return 0
    }
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

fun transposeeComatrice(matrice: Array<IntArray>, i: Int, j: Int): Int {

    var matriceResultat: Int


    if (i == 0 && j == 0) { //a OK
        matriceResultat = (matrice[1][1] * matrice[2][2]) - (matrice[1][2] * matrice[2][1])
        return matriceResultat

    } else if (i == 0 && j == 1) { //b OK
        matriceResultat = (matrice[0][2] * matrice[2][1]) - (matrice[0][1] * matrice[2][2])
        return matriceResultat

    } else if (i == 0 && j == 2) {//c OK
        matriceResultat = (matrice[0][1] * matrice[1][2]) - (matrice[0][2] * matrice[1][1])
        return matriceResultat

    } else if (i == 1 && j == 0) { //d OK
        matriceResultat = (matrice[1][2] * matrice[2][0]) - (matrice[1][0] * matrice[2][2])
        return matriceResultat

    } else if (i == 1 && j == 1) { //e OK
        matriceResultat = (matrice[0][0] * matrice[2][2]) - (matrice[0][2] * matrice[2][0])
        return matriceResultat

    } else if (i == 1 && j == 2) {//f OK
        matriceResultat = (matrice[0][2] * matrice[1][0]) - (matrice[0][0] * matrice[1][2])
        return matriceResultat

    } else if (i == 2 && j == 0) { //g OK
        matriceResultat = (matrice[1][0] * matrice[2][1]) - (matrice[1][1] * matrice[2][0])
        return matriceResultat

    } else if (i == 2 && j == 1) { //h
        matriceResultat = (matrice[0][1] * matrice[2][0]) - (matrice[0][0] * matrice[2][1])
        return matriceResultat

    } else if (i == 2 && j == 2) {//i
        matriceResultat = (matrice[0][0] * matrice[1][1]) - (matrice[0][1] * matrice[1][0])
        return matriceResultat

    }else {
        return 0
    }
}


fun checkSizeText(text: String, sizeMatrice: Int): String {
    var textResult = text

    while (textResult.length % sizeMatrice != 0) {
        textResult += "\u0000"
        Timber.i("Ajout caractère null, taille textResult = ${textResult.length}")
    }
    return textResult
}

fun inversionMatrice(matrice: Array<IntArray>): Array<IntArray> {
    var matriceResult: Array<IntArray>

    if (matrice.size == 2) {
        Timber.i("matrice.size = 2")
        matriceResult = arrayOf(intArrayOf(0, 0), intArrayOf(0, 0))
        matriceResult[0][0] = matrice[1][1]
        matriceResult[0][1] = -(matrice[0][1])
        matriceResult[1][0] = -(matrice[1][0])
        matriceResult[1][1] = matrice[0][0]

    } else if (matrice.size == 3) {
        Timber.i("matrice.size = 3")
        matriceResult = arrayOf(intArrayOf(0, 0, 0), intArrayOf(0, 0, 0), intArrayOf(0, 0, 0))
        for (i in matrice.indices) {
            for (j in matrice[0].indices) {
                matriceResult[i][j] = transposeeComatrice(matrice, i, j)

                Timber.e("Matriceinverse[$i][$j] = ${matriceResult[i][j]}")
            }
        }

    } else {
        matriceResult = arrayOf(intArrayOf(0), intArrayOf(0))
    }


    return matriceResult
}

fun calculAlpha(determinant: Int, nbCaracteresMax: Int): Int {

    var alpha = 1
    Timber.e("alpha determinant: $determinant, nbCaracteresMax = $nbCaracteresMax")

    while ((determinant * alpha) % nbCaracteresMax != 1) {
        alpha++
    }


//    if (nbCaracteresMax % 2 == 0) {
//        Timber.i("alpha nbCaracteresMax % 2 == ${nbCaracteresMax % 2} ")
//        while ((determinant * alpha) % nbCaracteresMax != 1 && alpha % 2 != 0) {
//            alpha++
//        }
//    } else {
//        while ((determinant * alpha) % nbCaracteresMax != 1 || alpha % 2 == 0) {
//            alpha++
//        }
//    }

    return alpha
}

