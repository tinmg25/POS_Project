package com.tmw.mypos.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tmw.mypos.model.Category

@Dao
interface CategoryDao {
    @Query("select * from category")
    fun getAllCategory():LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category:Category)

    @Query("delete from category where category_name=:category_name")
    suspend fun delete(category_name:String)

    @Query("update category set category_name=:new_category_name where category_name=:category_name")
    suspend fun update(new_category_name:String,category_name:String)
}