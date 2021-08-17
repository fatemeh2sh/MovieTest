package co.sharif.namava.interfaces

import co.sharif.namava.data.model.Datum

interface ItemListener {
    fun  onClicked(item: Datum)
}