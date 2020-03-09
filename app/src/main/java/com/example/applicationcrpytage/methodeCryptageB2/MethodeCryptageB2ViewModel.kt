package com.example.applicationcrpytage.methodeCryptageB2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applicationcrpytage.Utils.calculDeterminant
import com.example.applicationcrpytage.Utils.checkSizeText
import timber.log.Timber

class MethodeCryptageB2ViewModel : ViewModel() {

    val matriceCryptage = arrayOf(intArrayOf(4, 1), intArrayOf(3, 2))
    val listeCaracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\u0000"


    val textToEncrypt = MutableLiveData<String>()


    val _resultEncryption = MutableLiveData<String>()
    val resultEncryption: LiveData<String>
        get() = this._resultEncryption


    val textToDecrypt = MutableLiveData<String>()

    val _resultDecryptage = MutableLiveData<String>()
    val resultDecryptage: LiveData<String>
        get() = this._resultDecryptage

    init {

        //ESSAIS
        Timber.e("listeCaracteres[5] = ${listeCaracteres[5]}")
        Timber.e("matriceCryptage[2][2] = ${matriceCryptage[0][1]}")
    }

    fun onClickButtonCryptage() {
        var stringResult = ""

        if (calculDeterminant(matriceCryptage) > 0) {
            var matriceA: Array<IntArray>
            Timber.i("La clé est compatible!")

            textToEncrypt.value = checkSizeText(textToEncrypt.value!!, matriceCryptage.size)

            for (i in textToEncrypt.value!!.indices step matriceCryptage.size) {

                matriceA = returnTextToEncryptInMatrice(i,matriceCryptage.size )

                Timber.i("Nombre de lignes de A: ${matriceCryptage[0].size}")


                val matriceU = arrayOf(intArrayOf(0), intArrayOf(0))

                for (l in 0 until matriceCryptage.size) {
                    for (j in 0 until  matriceA[0].size) {
                        for (k in 0 until  matriceA.size) {
                            Timber.i("k = $k ")
                            matriceU[l][j] += matriceCryptage[l][k] * matriceA[k][j]

                            Timber.i("C[$l][$j] = ${matriceU[l][j]}, matriceCryptage[$l][$k] =${matriceCryptage[l][k]},matriceA[$k][$j]= ${matriceA[k][j]}")
                        }
                    }
                }
                Timber.i("C = ${matriceU[0][0]}${matriceU[1][0]}")
                val val1 = matriceU[0][0]
                val val2 = matriceU[1][0]
                Timber.i("C = ${listeCaracteres[val1 % 53]}${listeCaracteres[val2 % 53]}")
                stringResult += listeCaracteres[val1 % 53] + "" + listeCaracteres[val2 % 53]
            }
            Timber.e("resultat cryptage = $stringResult")

            _resultEncryption.value = stringResult
        } else {
            Timber.e("La clé est incompatible!")
        }
    }

    fun returnTextToEncryptInMatrice(i: Int, matriceCryptageSize: Int): Array<IntArray> {
        var matrice = arrayOf(intArrayOf(0), intArrayOf(0))
        when(matriceCryptageSize) {
            2-> {
                var matrice = arrayOf(intArrayOf(0), intArrayOf(0))
                val x = listeCaracteres.indexOf(textToEncrypt.value!![i])
                val y = listeCaracteres.indexOf(textToEncrypt.value!![i + 1])
                matrice[0][0] = x
                matrice[1][0] = y
                Timber.i("matriceA[0][0] = ${matrice[0][0]},matriceA[1][0] = ${matrice[1][0]},  i = $i")
                return matrice
            }
            3->{
                var matrice = arrayOf(intArrayOf(0), intArrayOf(0), intArrayOf(0))
                val x = listeCaracteres.indexOf(textToEncrypt.value!![i])
                val y = listeCaracteres.indexOf(textToEncrypt.value!![i + 1])
                val z = listeCaracteres.indexOf(textToEncrypt.value!![i + 2])
                matrice[0][0] = x
                matrice[1][0] = y
                matrice[2][0] = z

                Timber.i("matrice[0][0] = ${matrice[0][0]},matrice[1][0] = ${matrice[1][0]},matrice[2][0] = ${matrice[2][0]},  i = $i")
                return matrice

            }
        }
        return matrice
    }


    fun onClickButtonDecryptage() {

    }


}