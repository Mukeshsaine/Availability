package com.example.availability.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
class ItemDataEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Primary key
    val name: String,
    val description: String
)