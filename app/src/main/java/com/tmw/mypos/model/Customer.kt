package com.tmw.mypos.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "customer")
class Customer(
    @PrimaryKey(autoGenerate = true)
    val custid:Int,
    val cust_name:String
):Parcelable