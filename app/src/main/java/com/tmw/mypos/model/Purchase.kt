package com.tmw.mypos.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "purchase")
class Purchase(
    @PrimaryKey(autoGenerate = true)
    val pid:Int,
    val date:String,
    val supplier:String,
    val name:String,
    val qty:Int,
    val price:Double,
    val amount:Double,
    val remark:String
):Parcelable