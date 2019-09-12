package com.ebookfrenzy.withmap.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ebookfrenzy.withmap.data.NotificationData
import com.ebookfrenzy.withmap.data.getNotification

class NotificationViewModel : ViewModel() {

    val notificationLiveData = MutableLiveData<MutableList<NotificationData>>()

    init{
        notificationLiveData.postValue(getNotification())
    }
}