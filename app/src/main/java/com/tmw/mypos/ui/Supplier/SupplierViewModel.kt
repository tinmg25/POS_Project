package com.tmw.mypos.ui.Supplier

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tmw.mypos.database.POSDatabase
import com.tmw.mypos.model.Supplier
import com.tmw.mypos.repository.SupplierRepository
import kotlinx.coroutines.launch

class SupplierViewModel(application: Application):AndroidViewModel(application){

    private val supplierRepository:SupplierRepository

    val allSupplier: LiveData<List<Supplier>>

    //val supplierList: LiveData<List<Supplier>>


    init {
        val supplierDao=POSDatabase.getDatabase(application).supplierDao()

        supplierRepository= SupplierRepository(supplierDao)

        allSupplier=supplierRepository.allSupplier
        //supplierList=supplierRepository.supplierList

    }

    fun insertSupplier(supplier:Supplier)=viewModelScope.launch {
        supplierRepository.insertSupplier(supplier)
    }

    fun deleteSupplier(supplier:Supplier)=viewModelScope.launch {
        supplierRepository.deleteSupplier(supplier)
    }

    fun deleteAllSupplier()=viewModelScope.launch {
        supplierRepository.deleteAllSupplier()
    }

    fun updateSupplier(supplier:Supplier)=viewModelScope.launch {
        supplierRepository.updateSupplier(supplier)
    }
}