package com.tmw.mypos.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Sale")
class Sale(
    @PrimaryKey(autoGenerate = true)
    val sid:Int,
    val name:String,
    val date:String,
    val customer:String,
    val qty:Int,
    val price:Double,
    val amount:Double,
    val remark:String
):Parcelable