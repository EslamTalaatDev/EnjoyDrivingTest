package com.io.enjoydrivintask.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
 class OrderMangmentModel(
    var orderRef:String ,
    var image: String,
    var orderStatus: String,
    var userName:String,
    var phone:String
) : Parcelable