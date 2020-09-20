package com.tmw.mypos.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tmw.mypos.model.Stock

@Dao
interface StockDao {
    @Query("select * from stock order by cid ASC")
    fun getAllStock(): LiveData<List<Stock>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(stock:Stock)

    @Delete
    suspend fun deleteStock(stock: Stock)

    @Query("delete from stock")
    suspend fun deleteAll()

    @Update
    suspend fun update(stock: Stock)
}