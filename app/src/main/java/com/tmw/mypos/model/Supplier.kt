package com.tmw.mypos.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "supplier")
class Supplier(
    @PrimaryKey(autoGenerate = true)
    val supplierid: Int,
    val supplier_name: String
) : Parcelable
