package com.example.carcollection

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    val name: String,
    val description: String,
    val photo: Int
) :Parcelable
