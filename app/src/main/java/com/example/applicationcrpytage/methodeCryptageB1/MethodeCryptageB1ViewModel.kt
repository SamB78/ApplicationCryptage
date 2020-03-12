package com.example.applicationcrpytage.methodeCryptageB1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applicationcrpytage.Utils.PGCD
import timber.log.Timber

class MethodeCryptageB1ViewModel : ViewModel() {

    private val matriceCrypt2 = arrayOf(intArrayOf(4, 1), intArrayOf(3, 2))
    private val matriceCrypt3 =
        arrayOf(intArrayOf(3, 2, 1), intArrayOf(2, 4, 3), intArrayOf(3, 1, 3))
    private val listeCaracteres = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\u0000"


    val _textToEncrypt = MutableLiveData<String>()
    val _keyforEncryption = MutableLiveData<String>()

    private val _resultEncryption = MutableLiveData<String>()
    val resultEncryption: LiveData<String>
        get() = this._resultEncryption

    val _textToDecrypt = MutableLiveData<String>()
    val _keyforDecryption = MutableLiveData<String>()

    private val _resultDecryptage = MutableLiveData<String>()
    val resultDecryptage: LiveData<String>
        get() = this._resultDecryptage

    init {
    }

    fun onClickButtonCryptage() {
        var valueCryptedInInt: Int
        var valueCrypted = ""
        if (checkIfKeyIsOk(_keyforEncryption.value!!.toInt(), listeCaracteres.length)) {
            var key = _keyforEncryption.value!!.toInt()
            Timber.i("Key is OK !!")
            for (i in _textToEncrypt.value!!.indices) {
                Timber.i("Caractewe en chiffwe ${listeCaracteres.indexOf(_textToEncrypt.value!![i])}")
                valueCryptedInInt =
                    (key * listeCaracteres.indexOf(_textToEncrypt.value!![i])) % listeCaracteres.length
                valueCrypted += listeCaracteres[valueCryptedInInt].toString()
            }
            _resultEncryption.value = valueCrypted

        }

    }

    private fun checkIfKeyIsOk(key: Int, length: Int): Boolean {
        return PGCD(key, length) == 1
    }


    fun onClickButtonDecryptage() {
        var valueCryptedInInt: Int
        var valueDecrypted = ""

        if (checkIfKeyIsOk(_keyforEncryption.value!!.toInt(), listeCaracteres.length)) {
            var key = _keyforDecryption.value!!.toInt()
            var keyDecryptage = 1

            while ((keyDecryptage * key) % listeCaracteres.length != 1) {
                keyDecryptage++
            }

            Timber.i("Key is OK !!")
            for (i in _textToDecrypt.value!!.indices) {
                Timber.i("Caractewe en chiffwe ${listeCaracteres.indexOf(_textToDecrypt.value!![i])}")

                valueCryptedInInt =
                    (keyDecryptage * listeCaracteres.indexOf(_textToDecrypt.value!![i])) % listeCaracteres.length
                valueDecrypted += listeCaracteres[valueCryptedInInt].toString()
            }
            _resultDecryptage.value = valueDecrypted

        }

    }

}