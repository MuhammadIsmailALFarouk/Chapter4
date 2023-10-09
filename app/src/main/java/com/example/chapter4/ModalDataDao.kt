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

    @Query("SELECT * FROM cart ORDER BY id DESC")
    fun getAllItem(): LiveData<List<Cart>>

    @Delete
    fun delete(chart: Cart)

    @Query("DELETE FROM cart WHERE id = :itemIdParams")
    fun delteByItemId(itemIdParams: Long)

    @Update
    fun update(chart: Cart)

        @Query("UPDATE cart SET jumlahMakanan = :newQuantity where id = :itemIdParams")
    fun  updateQuantityByItemId(newQuantity: Int, itemIdParams: Long)
}

