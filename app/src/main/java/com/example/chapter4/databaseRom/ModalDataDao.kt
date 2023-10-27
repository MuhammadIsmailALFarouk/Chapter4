package com.example.chapter4.databaseRom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

import androidx.room.Query
import androidx.room.Update
import com.example.chapter4.cart.Cart

@Dao
interface ModalDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(chart: Cart)

    @Query("SELECT * FROM CART ORDER BY id DESC")
    fun getAllItem(): LiveData<List<Cart>>

    @Query("DELETE FROM CART WHERE id = :itemIdParams")
    fun delteByItemId(itemIdParams: Int)

    @Query("SELECT * FROM CART WHERE nama_makanan = :name LIMIT 1")
    fun getItemByName(name: String): Cart?

    @Query("DELETE FROM CART")
    fun deleteAllItemCart()
    @Update
    fun update(chart: Cart)

        @Query("UPDATE CART SET jumlahMakanan = :newQuantity where id = :itemIdParams")
    fun  updateQuantityByItemId(newQuantity: Int, itemIdParams: Int)
}

