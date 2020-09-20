package com.tmw.mypos.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.tmw.mypos.model.StockStatus

@Dao
interface StockStatusDao {
    @Query("select * from stock_status")
    fun getAllStock(): LiveData<List<StockStatus>>
}