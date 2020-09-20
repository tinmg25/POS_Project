package com.tmw.mypos.ui.Customer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tmw.mypos.database.POSDatabase
import com.tmw.mypos.model.Customer
import com.tmw.mypos.model.Supplier
import com.tmw.mypos.repository.CustomerRepository
import com.tmw.mypos.repository.SupplierRepository
import kotlinx.coroutines.launch

class CustomerViewModel (application: Application):AndroidViewModel(application){
    private val customerRepository: CustomerRepository

    val allCustomer: LiveData<List<Customer>>

    init {
        val customerDao= POSDatabase.getDatabase(application).customerDao()

        customerRepository= CustomerRepository(customerDao)

        allCustomer=customerRepository.allCustomer
    }

    fun insertCustomer(customer:Customer)=viewModelScope.launch {
        customerRepository.insertCustomer(customer)
    }

    fun deleteCustomer(customer: Customer)=viewModelScope.launch {
        customerRepository.deleteCustomer(customer)
    }

    fun deleteAllCustomer()=viewModelScope.launch {
        customerRepository.deleteAllCustomer()
    }

    fun updateCustomer(customer: Customer)=viewModelScope.launch {
        customerRepository.updateCustomer(customer)
    }
}