package com.example.applicationcrpytage.mainMenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainMenuViewModel: ViewModel() {

    enum class navigationMainMenu{
        EN_ATTENTE,
        PASSAGE_PAGE_A,
        PASSAGE_PAGE_B1,
        PASSAGE_PAGE_B2
    }

    private var _navigation = MutableLiveData<navigationMainMenu>()
    val navigation: LiveData<navigationMainMenu>
        get() = this._navigation

    init {
        onBoutonClicked()
    }

    fun onClickBoutonA(){
        _navigation.value = navigationMainMenu.PASSAGE_PAGE_A
    }

    fun onClickBoutonB1(){
        _navigation.value = navigationMainMenu.PASSAGE_PAGE_B1
    }
    fun onClickBoutonB2(){
        _navigation.value = navigationMainMenu.PASSAGE_PAGE_B2
    }

    fun onBoutonClicked(){
        _navigation.value = navigationMainMenu.EN_ATTENTE
    }



}