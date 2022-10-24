package ru.sb066coder.mathhelper.ui.columndivizion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.sb066coder.mathhelper.mathlogic.ColumnDivision

class ColumnDivisionViewModel : ViewModel() {

    private val _result = MutableLiveData<String>().apply {
        value = ""
    }
    val result: LiveData<String> = _result

    private val _remain = MutableLiveData<String>().apply {
        value = ""
    }
    val remain: LiveData<String> = _remain

    private val _column = MutableLiveData<String>().apply {
        value = "init"
    }
    val column: LiveData<String> = _column

    fun divide(divided: String, divisor: String) {
            _result.value = ColumnDivision.result(divided, divisor)
            _remain.value = ColumnDivision.remain(divided, divisor)
            _column.value = ColumnDivision.columnText(divided, divisor)
    }
}