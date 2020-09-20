package com.tmw.mypos.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_status")
class StockStatus (
    @PrimaryKey(autoGenerate = true)
    val stock_id:Int,
    val stock_code:String,
    val purchase_qty:Int,
    val sale_qty:Int
)