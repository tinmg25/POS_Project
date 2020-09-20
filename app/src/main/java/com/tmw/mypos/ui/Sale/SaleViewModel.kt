package com.tmw.mypos.ui.Sale


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tmw.mypos.database.POSDatabase
import com.tmw.mypos.repository.SaleRepository
import com.tmw.mypos.model.Sale
import kotlinx.coroutines.launch

class SaleViewModel(application: Application) : AndroidViewModel(application) {

    private val saleRepository: SaleRepository

    val allSale: LiveData<List<Sale>>

    init {
        val saleDao=POSDatabase.getDatabase(application).saleDao()

        saleRepository= SaleRepository(saleDao)

        allSale=saleRepository.allSales
    }

    fun insertSale(sale:Sale)=viewModelScope.launch {
        saleRepository.insertSale(sale)
    }

    fun deleteSale(sale:Sale)=viewModelScope.launch {
        saleRepository.deleteSale(sale)
    }

    fun deleteAllSale()=viewModelScope.launch {
        saleRepository.deleteAllSale()
    }

    fun updateSale(sale: Sale)=viewModelScope.launch {
        saleRepository.updateSale(sale)
    }

}