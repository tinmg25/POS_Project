package com.tmw.mypos.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tmw.mypos.model.Supplier

@Dao
interface SupplierDao {
    @Query("select * from supplier")
    fun getAllSupplier(): LiveData<List<Supplier>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSupplier(supplier: Supplier)

    @Delete
    suspend fun deleteSupplier(supplier: Supplier)

    @Query("delete from supplier")
    suspend fun deleteAllSupplier()

    @Update
    suspend fun updateSupplier(supplier: Supplier)

//    @Query("select supplier_name from Supplier")
//    fun getSupplier(): LiveData<List<Supplier>>
}