package com.example.applicationcrpytage.methodeCryptageA

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applicationcrpytage.Utils.PGCD
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import timber.log.Timber


class MethodeCryptageAViewModel : ViewModel() {

    private val matriceCrypt2 = arrayOf(intArrayOf(4, 1), intArrayOf(3, 2))
    private val matriceCrypt3 =
        arrayOf(intArrayOf(3, 2, 1), intArrayOf(2, 4, 3), intArrayOf(3, 1, 3))
    private val listeCaracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\u0000"


    val _textToEncrypt = MutableLiveData<String>()
    val _keyforEncryption1 = MutableLiveData<String>()
    val _keyforEncryption2 = MutableLiveData<String>()

    private val _resultEncryption = MutableLiveData<String>()
    val resultEncryption: LiveData<String>
        get() = this._resultEncryption

    val _textToDecrypt = MutableLiveData<String>()
    val _keyforDecryption1 = MutableLiveData<String>()
    val _keyforDecryption2 = MutableLiveData<String>()

    private val _resultDecryptage = MutableLiveData<String>()
    val resultDecryptage: LiveData<String>
        get() = this._resultDecryptage

    init {
    }

    fun onClickButtonCryptage() {
        var text = _textToEncrypt.value
        var k1 = _keyforEncryption1.value?.toInt()
        var k2 = _keyforEncryption2.value?.toInt()
        var textResult = ""

        if (checkIfParametersAreOk(k1, k2, text)) {
            for (i in text!!.indices) {
                Timber.i("indexOf listeCaracteres = ${listeCaracteres.indexOf(text[i])}, k1 = $k1, k2 = $k2")
                textResult += listeCaracteres[((k1!! * listeCaracteres.indexOf(text[i])) + k2!!) % listeCaracteres.length]
            }
            _resultEncryption.value = textResult
        }

    }

    fun onClickButtonDecryptage() {
        var text = _textToDecrypt.value
        var k1 = _keyforDecryption1.value?.toInt()
        var k2 = _keyforDecryption2.value?.toInt()
        var kprime = -1
        var textResult = ""

        if (checkIfParametersAreOk(k1, k2, text)) {
            Timber.i("PASSAGE BOUCLE")

            for (i in listeCaracteres.indices) // parcour de la chaine de charactère
            {
                if ((i * k1!!) % listeCaracteres.length == 1) // calcul du charactère i donc la lettre de la chaine par k1 modulo listeCaracteres.length qui doit être égale à 1
                    kprime = i
                Timber.i("kprime = $kprime")
            }

            for (i in text!!.indices) {

                var resultCalcul = (kprime * (listeCaracteres.indexOf(text[i]) - k2!!)) % listeCaracteres.length
                Timber.i("numero caractere: ${listeCaracteres.indexOf(text[i])} resultCalcul =  $resultCalcul")
                textResult += listeCaracteres[resultCalcul]
                Timber.i("textResult =  $textResult")
            }
            _resultDecryptage.value = textResult
        }

    }


    private fun checkIfParametersAreOk(k1: Int?, k2: Int?, text: String?): Boolean {
        Timber.i(" k1 = $k1, k2 = $k2, text = $text ")
        if (k1 != null && k2 != null && !text.isNullOrEmpty()) {
            return !(PGCD(k1!!, listeCaracteres.length) != 1 || k2 == 0 || k2 == listeCaracteres.length)
        }
        Timber.i("ERROR :  k1 = $k1, k2 = $k2, text = $text ")
        return false
    }
}
