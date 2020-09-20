package com.tmw.mypos.repository

import androidx.lifecycle.LiveData
import com.tmw.mypos.dao.SaleDao
import com.tmw.mypos.model.Sale

class SaleRepository (private val saleDao: SaleDao){

    val allSales: LiveData<List<Sale>> =saleDao.getAllSale()

    suspend fun insertSale(sale:Sale){
        saleDao.insertSale(sale)
    }

    suspend fun deleteSale(sale: Sale){
        saleDao.deleteSale(sale)
    }

    suspend fun deleteAllSale(){
        saleDao.deleteAllSale()
    }

    suspend fun updateSale(sale: Sale){
        saleDao.updateSale(sale)
    }

}