package com.latihan.teamlabs

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamData(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable