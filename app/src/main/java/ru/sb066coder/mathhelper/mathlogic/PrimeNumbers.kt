package ru.sb066coder.mathhelper.mathlogic

import java.util.*
import kotlin.math.ceil
import kotlin.math.sqrt


class PrNum {
    private var sqrt: Int = 1
    private var lim: Int = 1
    var value: Int = 1

    constructor()
    constructor(list: List<PrNum>) {
        setSqrt(list)
        var chValue = list.last().value + 1
        if (chValue > lim) plusSqrt()
        var found = false
        while (!found) {
            found = true
            for (i in list) {
                if (i.value == 1) continue
                if (i.value > sqrt) {
                    found = true
                    break
                }
                if (chValue % i.value == 0) {
                    found = false
                    chValue++
                    break
                }
            }
            if (found) value = chValue
        }
    }

    private fun plusSqrt() {
        sqrt++
        lim = sqrt * sqrt
    }

    private fun setSqrt(list: List<PrNum>) {
        sqrt = list.last().sqrt
        lim = sqrt * sqrt
    }
}

fun createListOfPrimes(lim: Int): List<Int> {
    val list = ArrayList<PrNum>()
    fillList(list, lim)
    return list.map { it.value }
}

fun fillList(list: ArrayList<PrNum>, lim: Int) {
    list.add(PrNum())
    while (list.last().value < lim) {
        list.add(PrNum(list))
    }
}

fun divMultipliers(n: Long): List<Long> {
    val list = ArrayList<PrNum>()
    var remain = n
    val mults = LinkedList<Long>()
    if (remain == 1L || remain == 2L) return mults
    fillList(list, ceil(sqrt(n * 1.0)).toInt())
    var end = false
    while (!end) {
        for (i in list) {
            end = false
            if (i.value == 1) continue//если i = 1 пропуск и переход к следующему i
            if (remain % i.value == 0L) {//если поделилось
                mults.add(i.value.toLong())//добавляем делитель в список
                remain /= i.value//делим
                break//заход в while
            }
            end = true
        }//закончилась проба деления на i переход к следующему i
    }
    if (remain > 1 && remain != n) mults.add(remain)
    return mults
}