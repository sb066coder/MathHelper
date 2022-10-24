package ru.sb066coder.mathhelper.ui.factorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.sb066coder.mathhelper.mathlogic.createListOfPrimes
import ru.sb066coder.mathhelper.mathlogic.divMultipliers

class FactorizationViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = ""
    }
    val text: LiveData<String> = _text

    fun calculate(factorize: Boolean, number: String) {
        _text.value =  if (number == "") {
            ""
        } else if (factorize) {
            val returned = divMultipliers(number.toLong())
            if (returned.isNotEmpty()) returned.toString() else "PRM"
        } else {
            createListOfPrimes(number.toInt()).toString()
        }
    }
}