package com.tmw.mypos.repository

import androidx.lifecycle.LiveData
import com.tmw.mypos.dao.StockDao
import com.tmw.mypos.model.Stock

class StockRepository (private val stockDao: StockDao){

    val allStock: LiveData<List<Stock>> = stockDao.getAllStock()

    suspend fun insert(stock:Stock){
        stockDao.insert(stock)
    }

    suspend fun deleteStock(stock: Stock){
        stockDao.deleteStock(stock)
    }

    suspend fun deleteAll(){
        stockDao.deleteAll()
    }

    suspend fun update(stock:Stock){
        stockDao.update(stock)
    }
}