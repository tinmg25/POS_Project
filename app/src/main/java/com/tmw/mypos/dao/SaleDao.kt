package com.tmw.mypos.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tmw.mypos.model.Sale

@Dao
interface SaleDao {
    @Query("select * from sale")
    fun getAllSale(): LiveData<List<Sale>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSale(sale: Sale)

    @Delete
    suspend fun deleteSale(sale: Sale)

    @Query("delete from sale")
    suspend fun deleteAllSale()

    @Update
    suspend fun updateSale(sale:Sale)
}