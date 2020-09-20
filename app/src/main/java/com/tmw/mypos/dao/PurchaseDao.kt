package com.tmw.mypos.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tmw.mypos.model.Purchase
import com.tmw.mypos.model.Sale

@Dao
interface PurchaseDao {
    @Query("select * from purchase")
    fun getAllPurchase(): LiveData<List<Purchase>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPurchase(purchase: Purchase)

    @Delete
    suspend fun deletePurchase(purchase: Purchase)

    @Query("delete from purchase")
    suspend fun deleteAllPurchase()

    @Update
    suspend fun updatePurchase(purchase: Purchase)
}