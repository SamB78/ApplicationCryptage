package com.example.applicationcrpytage.methodeCryptageB2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applicationcrpytage.Utils.calculAlpha
import com.example.applicationcrpytage.Utils.calculDeterminant
import com.example.applicationcrpytage.Utils.checkSizeText
import com.example.applicationcrpytage.Utils.inversionMatrice
import timber.log.Timber

class MethodeCryptageB2ViewModel : ViewModel() {

    private val matriceCrypt2 = arrayOf(intArrayOf(4, 1), intArrayOf(3, 2))
    private val matriceCrypt3 = arrayOf(intArrayOf(3, 2,1), intArrayOf(2, 4,3), intArrayOf(3, 1,3))
    private val listeCaracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\u0000"


    val _textToEncrypt = MutableLiveData<String>()

    private val _resultEncryption = MutableLiveData<String>()
    val resultEncryption: LiveData<String>
        get() = this._resultEncryption

    val _textToDecrypt = MutableLiveData<String>()

    private val _resultDecryptage = MutableLiveData<String>()
    val resultDecryptage: LiveData<String>
        get() = this._resultDecryptage

    init {
    }

    fun onClickButtonCryptage() {
        var stringResult = ""
        var matriceA: Array<IntArray>
        var matriceU: Array<IntArray>
        var matriceCryptage = matriceCrypt3

        if (calculDeterminant(matriceCryptage) > 0) {

            Timber.i("La clé est compatible!")
            Timber.i("listeCaracteres.length = ${listeCaracteres.length}")

            _textToEncrypt.value = checkSizeText(_textToEncrypt.value!!, matriceCryptage.size)

            for (i in _textToEncrypt.value!!.indices step matriceCryptage.size) {

                matriceA = returnTextInMatrice(_textToEncrypt.value!!, i, matriceCryptage.size)

                matriceU = returnMatriceEncrypted(matriceA, matriceCryptage)
                Timber.i("C = ${matriceU[0][0]}${matriceU[1][0]}")

                stringResult += convertMatriceInChar(matriceU)
            }
            Timber.e("resultat cryptage = $stringResult")
            _resultEncryption.value = stringResult
        } else {
            Timber.e("La clé est incompatible!")
        }
    }

    fun returnTextInMatrice(text: String, i: Int, matriceCryptageSize: Int): Array<IntArray> {
        var matrice = arrayOf(intArrayOf(0), intArrayOf(0))
        when (matriceCryptageSize) {
            2 -> {
                var matrice = arrayOf(intArrayOf(0), intArrayOf(0))
                val x = listeCaracteres.indexOf(text[i])
                val y = listeCaracteres.indexOf(text[i + 1])
                matrice[0][0] = x
                matrice[1][0] = y
                Timber.i("matriceA[0][0] = ${matrice[0][0]},matriceA[1][0] = ${matrice[1][0]},  i = $i")
                return matrice
            }
            3 -> {
                var matrice = arrayOf(intArrayOf(0), intArrayOf(0), intArrayOf(0))
                val x = listeCaracteres.indexOf(text[i])
                val y = listeCaracteres.indexOf(text[i + 1])
                val z = listeCaracteres.indexOf(text[i + 2])
                matrice[0][0] = x
                matrice[1][0] = y
                matrice[2][0] = z

                Timber.i("matrice[0][0] = ${matrice[0][0]},matrice[1][0] = ${matrice[1][0]},matrice[2][0] = ${matrice[2][0]},  i = $i")
                return matrice

            }
        }
        return matrice
    }

    fun returnMatriceEncrypted(
        matriceA: Array<IntArray>,
        matriceCryptage: Array<IntArray>
    ): Array<IntArray> {
        var matriceU: Array<IntArray> = arrayOf(intArrayOf(0), intArrayOf(0))
        if (matriceCryptage.size == 3) {
            matriceU = arrayOf(intArrayOf(0), intArrayOf(0), intArrayOf(0))
        }
        for (l in 0 until matriceCryptage.size) {
            for (j in 0 until matriceA[0].size) {
                for (k in 0 until matriceA.size) {
                    Timber.i("k = $k ")
                    matriceU[l][j] += matriceCryptage[l][k] * matriceA[k][j]

                    Timber.i("C[$l][$j] = ${matriceU[l][j]}, matriceCryptage[$l][$k] =${matriceCryptage[l][k]},matriceA[$k][$j]= ${matriceA[k][j]}")
                }
            }
        }
        return matriceU
    }

    fun onClickButtonDecryptage() {

        var matriceCryptage = matriceCrypt3

        _textToDecrypt.value = checkSizeText(_textToDecrypt.value!!, matriceCryptage.size)

        var alpha = calculAlpha(calculDeterminant(matriceCryptage), listeCaracteres.length)
        var inversionMatriceCryptage = inversionMatrice(matriceCryptage)
        var matriceDecryptage = calculMatriceCryptage(inversionMatriceCryptage, alpha, matriceCryptage)

        var matriceX: Array<IntArray>
        var matriceC: Array<IntArray>
        var stringResult = ""

        Timber.i("alpha =  $alpha")

        for (i in _textToDecrypt.value!!.indices step matriceDecryptage.size) {
            matriceC = returnTextInMatrice(_textToDecrypt.value!!, i, matriceCryptage.size)
            matriceX = returnMatriceDecrypted(matriceC, matriceDecryptage, alpha)
            stringResult += convertMatriceInChar(matriceX)
            Timber.i("stringResult = $stringResult")
        }

        _resultDecryptage.value = stringResult


    }

    private fun calculMatriceCryptage(
        inversionMatriceCryptage: Array<IntArray>,
        alpha: Int, matriceCryptage: Array<IntArray>
    ): Array<IntArray> {
        var matriceDecryptage = arrayOf(intArrayOf(0,0), intArrayOf(0,0))


        if (matriceCryptage.size == 3) {
            matriceDecryptage = arrayOf(intArrayOf(0,0,0), intArrayOf(0,0,0), intArrayOf(0,0,0))
        }
            for (i in inversionMatriceCryptage.indices) {
                for (j in inversionMatriceCryptage[0].indices) {


                    matriceDecryptage[i][j] =
                        (inversionMatriceCryptage[i][j] * alpha) % listeCaracteres.length

                    while (matriceDecryptage[i][j] < 0) {
                        matriceDecryptage[i][j] += listeCaracteres.length
                    }
                }
            }


        return matriceDecryptage
    }


    fun returnMatriceDecrypted(
        matriceC: Array<IntArray>,
        matriceDecryptage: Array<IntArray>,
        alpha: Int
    ): Array<IntArray> {
        var matriceX: Array<IntArray> = arrayOf(intArrayOf(0), intArrayOf(0))
        Timber.i("Passage matriceDecryptage = ${matriceDecryptage.size}")

        if (matriceDecryptage.size == 3) {
            matriceX = arrayOf(intArrayOf(0,0,0), intArrayOf(0,0,0), intArrayOf(0,0,0))
        }
        for (l in matriceDecryptage.indices) {
            for (j in matriceC[0].indices) {
                for (k in matriceC.indices) {
                    Timber.i("k = $k ")
                    matriceX[l][j] += matriceDecryptage[l][k] * matriceC[k][j]

                    Timber.i("matriceX[$l][$j] = ${matriceX[l][j]}, matriceDecryptage[$l][$k] =${matriceDecryptage[l][k]},matriceC[$k][$j]= ${matriceC[k][j]}")
                }
            }
        }
        Timber.i("sortie matriceDecryptage")
        return matriceX
    }


    fun convertMatriceInChar(matriceU: Array<IntArray>): String {
        var stringResult = ""
        Timber.i("matriceU.size = ${matriceU.size}")
        for (i in 0 until matriceU.size) {
            Timber.i(" valeur dans listeCaracteres = ${matriceU[i][0] % listeCaracteres.length}, matriceU = ${matriceU[i][0]}")
            stringResult += listeCaracteres[matriceU[i][0] % listeCaracteres.length]
            Timber.i("stringResult = $stringResult")
        }
        return stringResult
    }
}