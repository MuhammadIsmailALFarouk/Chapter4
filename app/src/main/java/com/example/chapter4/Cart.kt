package com.example.chapter4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "nama_makanan") var namaMakanan: String?=null,
    @ColumnInfo(name = "harga_makanan") var hargaMakanan: String?=null,
    @ColumnInfo(name = "image") var image: Int?=null,
    @ColumnInfo(name = "jumlahMakanan") var quantity:Int = -1,
    @ColumnInfo(name = "noted") var noted:String?=null,
    @ColumnInfo(name = "is_deleted")
    var isDeleted: Boolean = false
){
    companion object {
        const val TABLE_NAME = "cart"
    }
}

