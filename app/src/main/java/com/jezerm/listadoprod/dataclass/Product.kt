package com.jezerm.listadoprod.dataclass

import android.os.Parcel
import android.os.Parcelable

class Product(var id: Int, var name: String, var price: Double) {
    override fun toString(): String {
        return "{ ID: $id, Name: $name, Price: $price }"
    }
}