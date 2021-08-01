package com.umbrella.simplepattern.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class SomeObject {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}