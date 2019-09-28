package com.ebookfrenzy.withmap.network.response

data class PinRegisterData(
    val address : String,
    val improvedName :String,
    val latitude : Double,
    val longitude : Double,
    val state : Boolean,
    val type : Int,
    val likeCount : Int,
    val unimprovedName : String?,
    val userId : Int

)