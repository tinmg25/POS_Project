package com.tmw.mypos.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
class Category (
    @PrimaryKey
    @ColumnInfo(name="caid")
    val caid:String,
    @ColumnInfo(name="category_name")
    val category_name:String
)