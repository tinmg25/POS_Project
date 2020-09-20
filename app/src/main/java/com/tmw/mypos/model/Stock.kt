package com.tmw.mypos.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "stock")
class Stock(
    @PrimaryKey(autoGenerate = true)
    val cid: Int,
    val code: String,
    val name: String,
    val purprice: Int,
    val saleprice: Int
):Parcelable