package com.example.chapter4

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.Companion.REPLACE

import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ModalDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(chart: Cart)

    @Query("SELECT * FROM CART ORDER BY id DESC")
    fun getAllItem(): LiveData<List<Cart>>

    @Query("DELETE FROM CART WHERE id = :itemIdParams")
    fun delteByItemId(itemIdParams: Int)

    @Update
    fun update(chart: Cart)

        @Query("UPDATE CART SET jumlahMakanan = :newQuantity where id = :itemIdParams")
    fun  updateQuantityByItemId(newQuantity: Int, itemIdParams: Int)
}

