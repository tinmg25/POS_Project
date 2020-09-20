package com.tmw.mypos.ui.Purchase

import android.app.Application
import androidx.lifecycle.*
import com.tmw.mypos.database.POSDatabase
import com.tmw.mypos.model.Purchase
import com.tmw.mypos.model.Supplier
import com.tmw.mypos.repository.PurchaseRepository
import com.tmw.mypos.repository.SupplierRepository
import kotlinx.coroutines.launch

class PurchaseViewModel(application: Application):AndroidViewModel(application) {

    private val purchaseRepository: PurchaseRepository

    val allPurchase: LiveData<List<Purchase>>


    init {
        val purchaseDao= POSDatabase.getDatabase(application).purchaseDao()

        purchaseRepository= PurchaseRepository(purchaseDao)


        allPurchase=purchaseRepository.allPurchases
    }

    fun insertPurchase(purchase: Purchase)=viewModelScope.launch {
        purchaseRepository.insertPurchase(purchase)
    }

    fun deletePurchase(purchase: Purchase)=viewModelScope.launch {
        purchaseRepository.deletePurchase(purchase)
    }

    fun deleteAllPurchase()=viewModelScope.launch {
        purchaseRepository.deleteAllPurchase()
    }

    fun updatePurchase(purchase: Purchase)=viewModelScope.launch {
        purchaseRepository.updatePurchase(purchase)
    }

}