package com.tmw.mypos.ui.Stock

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tmw.mypos.database.POSDatabase
import com.tmw.mypos.model.Stock
import com.tmw.mypos.repository.StockRepository
import kotlinx.coroutines.launch

class StockViewModel (application: Application):AndroidViewModel(application){

    private val stockRepository:StockRepository

    val allStock: LiveData<List<Stock>>

    init {
        val stockDao=POSDatabase.getDatabase(application).stockDao()

        stockRepository= StockRepository(stockDao)

        allStock=stockRepository.allStock
    }

    fun insert(stock:Stock)=viewModelScope.launch {
        stockRepository.insert(stock)
    }

    fun deleteStock(stock:Stock)=viewModelScope.launch {
        stockRepository.deleteStock(stock)
    }

    fun deleteAll()=viewModelScope.launch {
        stockRepository.deleteAll()
    }

    fun update(stock:Stock)=viewModelScope.launch {
        stockRepository.update(stock)
    }
}