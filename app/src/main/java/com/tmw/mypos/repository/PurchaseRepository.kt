package com.tmw.mypos.repository

import androidx.lifecycle.LiveData
import com.tmw.mypos.dao.PurchaseDao
import com.tmw.mypos.model.Purchase

class PurchaseRepository (private val purchaseDao: PurchaseDao){

    val allPurchases: LiveData<List<Purchase>> =purchaseDao.getAllPurchase()

    suspend fun insertPurchase(purchase:Purchase){
        purchaseDao.insertPurchase(purchase)
    }

    suspend fun deletePurchase(purchase: Purchase){
        purchaseDao.deletePurchase(purchase)
    }

    suspend fun deleteAllPurchase(){
        purchaseDao.deleteAllPurchase()
    }

    suspend fun updatePurchase(purchase: Purchase){
        purchaseDao.updatePurchase(purchase)
    }

}