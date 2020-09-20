package com.tmw.mypos.repository

import androidx.lifecycle.LiveData
import com.tmw.mypos.dao.SupplierDao
import com.tmw.mypos.model.Supplier

class SupplierRepository (private val supplierDao: SupplierDao){

    val allSupplier:LiveData<List<Supplier>> = supplierDao.getAllSupplier()

   // val supplierList:LiveData<List<Supplier>> = supplierDao.getSupplier()

    suspend fun insertSupplier(supplier: Supplier){
        supplierDao.insertSupplier(supplier)
    }

    suspend fun deleteSupplier(supplier: Supplier){
        supplierDao.deleteSupplier(supplier)
    }

    suspend fun deleteAllSupplier(){
        supplierDao.deleteAllSupplier()
    }

    suspend fun updateSupplier(supplier: Supplier){
        supplierDao.updateSupplier(supplier)
    }
}
