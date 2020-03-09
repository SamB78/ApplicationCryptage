package com.example.applicationcrpytage.methodeCryptageB2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applicationcrpytage.Utils.CalculDeterminant
import timber.log.Timber

class MethodeCryptageB2ViewModel : ViewModel() {

    val matrice = arrayOf(intArrayOf(4, 1), intArrayOf(3, 2))
    val listeCaracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"


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
        Timber.e("matrice[2][2] = ${matrice[0][1]}")
    }

    fun onClickButtonCryptage() {
        var stringResult = ""

        if (CalculDeterminant(matrice) > 0) {
            var matriceA =
                arrayOf(intArrayOf(0), intArrayOf(0))// Ajouter modulo pour nombres impaires
            Timber.i("La clé est compatible!")
            for (i in textToEncrypt.value!!.indices step 2) {

                //Passage des caractères du texte dans la matriceA
                val x = listeCaracteres.indexOf(textToEncrypt.value!![i])
                val y = listeCaracteres.indexOf(textToEncrypt.value!![i + 1])
                matriceA[0][0] = x
                matriceA[1][0] = y
                Timber.i("matriceA[0][0] = ${matriceA[0][0]},matriceA[1][0] = ${matriceA[1][0]},  i = $i")

                Timber.i("Nombre de lignes de A: ${matrice[0].size}")

                val nbLignesMatrice = matrice.size
                val nbLignesMatriceA = matriceA.size
                val nbColonnesMatriceA = matriceA[0].size

                val C = arrayOf(intArrayOf(0), intArrayOf(0))

                for (l in 0 until nbLignesMatrice) {
                    for (j in 0 until nbColonnesMatriceA) {
                        for (k in 0 until nbLignesMatriceA) {
                            Timber.i("k = $k ")
                            C[l][j] += matrice[l][k] * matriceA[k][j]

                            Timber.i("C[$l][$j] = ${C[l][j]}, matrice[$l][$k] =${matrice[l][k]},matriceA[$k][$j]= ${matriceA[k][j]}")
                        }
                    }
                }
                Timber.i("C = ${C[0][0]}${C[1][0]}")
                val val1 = C[0][0]
                val val2 = C[1][0]
                Timber.i("C = ${listeCaracteres[val1 % 53]}${listeCaracteres[val2 % 53]}")
                stringResult += listeCaracteres[val1 % 53] + "" + listeCaracteres[val2 % 53]
            }
            Timber.e("resultat cryptage = $stringResult")

            _resultEncryption.value = stringResult


        } else {

            Timber.e("La clé est incompatible!")
        }


    }


    fun onClickButtonDecryptage() {

    }


}