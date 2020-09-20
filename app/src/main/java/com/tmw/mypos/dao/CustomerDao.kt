package com.tmw.mypos.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tmw.mypos.model.Customer

@Dao
interface CustomerDao {
    @Query("select * from customer")
    fun getAllCustomer(): LiveData<List<Customer>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertcustomer(customer: Customer)

    @Delete
    suspend fun deletecustomer(customer: Customer)

    @Query("delete from customer")
    suspend fun deleteAllcustomer()

    @Update
    suspend fun updatecustomer(customer: Customer)
}