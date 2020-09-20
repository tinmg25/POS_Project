package com.tmw.mypos.database

import android.content.Context
import androidx.room.*
import com.tmw.mypos.dao.*
import com.tmw.mypos.model.*

@Database(entities = arrayOf(Sale::class,Purchase::class,StockStatus::class, Stock::class,
    Category::class,Supplier::class,Customer::class),version=1,exportSchema = false)
abstract class POSDatabase:RoomDatabase(){

    abstract fun saleDao():SaleDao
    abstract fun purchaseDao(): PurchaseDao
    abstract fun stockstatusDao():StockStatusDao
    abstract fun stockDao():StockDao
    abstract fun categoryDao():CategoryDao
    abstract fun supplierDao():SupplierDao
    abstract fun customerDao():CustomerDao

    companion object {
        @Volatile
        private var INSTANCE: POSDatabase? = null

        fun getDatabase(context: Context): POSDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    POSDatabase::class.java,
                    "pos_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}