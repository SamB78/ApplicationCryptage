package com.example.applicationcrpytage.methodeCryptageC

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.applicationcrpytage.Utils.calculAlpha
import com.example.applicationcrpytage.Utils.calculDeterminant
import com.example.applicationcrpytage.Utils.checkSizeText
import com.example.applicationcrpytage.Utils.inversionMatrice
import timber.log.Timber

class MethodeCryptageCViewModel: ViewModel(){

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

    }

    fun onClickButtonDecryptage() {

    }
}