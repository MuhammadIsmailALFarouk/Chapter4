package com.example.chapter4

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ModalData(
    var namaMakanan: String,
    var hargaMakanan: String,
    var image: Int,
    var deskripsi: String,
   var alamatToko: String
) : Parcelable
