package com.ebookfrenzy.withmap.network.response

import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * Created By Yun Hyeok
 * on 9월 25, 2019
 */

data class PinDetail(
    val detailContents: PinDetailExtra,
    val pin: CommonPinInfo,
    val pinImages: List<PinDetailImage>
)

data class CommonPinInfo(
    val crtDate: String,
    var updDate: String,
    val id: Int,
    var userId: Int,
    var unimprovedName: String ,
    var improvedName: String,
    val type: Int,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val state: Boolean,
    val likeCount: Int
)

data class PinDetailImage(
    val imagePath: String,
    val state: Boolean
)

data class PinDetailExtra(
    val crtDate: String,
    val updDate: String,
    val id: Int,
    val useableTime: String,
    val departmentNumber: String
)
//{
//    "pin":
//    {
//        "crtDate":2019-09-21 16:06:00,
//        "updDate":2019-09-24 18:57:39,
//        "id":1,
//        "userId":null,
//        "unimprovedName":null,
//        "improvedName":"세종문화회관 광화문아띠(지하1층) 남측",
//        "type":4,
//        "latitude":126.9757016,
//        "longitude":37.57261267,
//        "address":"종로구 세종로 81-4",
//        "state":true,
//        "likeCount":0 }
//    ,
//    "pinImages":[],
//    "detailContents":
//    {
//        "crtDate":2019-09-21 16:06:00,
//        "updDate":2019-09-21 16:06:00,
//        "id":1,
//        "useableTime":"02-399-1553",
//        "departmentNumber":"정시(09:00 ~ 22:30)"
//    }
//
//}