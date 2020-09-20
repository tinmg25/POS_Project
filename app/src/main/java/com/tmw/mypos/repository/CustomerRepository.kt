package com.tmw.mypos.repository

import androidx.lifecycle.LiveData
import com.tmw.mypos.dao.CustomerDao
import com.tmw.mypos.model.Customer

class CustomerRepository(private val customerDao: CustomerDao) {

    val allCustomer:LiveData<List<Customer>> =customerDao.getAllCustomer()

    suspend fun insertCustomer(customer: Customer){
        customerDao.insertcustomer(customer)
    }

    suspend fun deleteCustomer(customer: Customer){
        customerDao.deletecustomer(customer)
    }

    suspend fun deleteAllCustomer(){
        customerDao.deleteAllcustomer()
    }

    suspend fun updateCustomer(customer:Customer){
        customerDao.updatecustomer(customer)
    }
}