package com.ebookfrenzy.withmap.config

import com.ebookfrenzy.withmap.network.KakaoService
import com.ebookfrenzy.withmap.viewmodel.SearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created By Yun Hyeok
 * on 9월 12, 2019
 */

private val httpClient = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("Authorization", "KakaoAK REST_API_KEY").build()
                return@addInterceptor it.proceed(request)
            }
            .build()
    }
}

val apiModule = module {

}

val kakaoApiModule = module {
    single<KakaoService> {
        Retrofit.Builder()
            .baseUrl(KakaoService.baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(KakaoService::class.java)
    }
}

val viewModelModule = module {
    viewModel {
        SearchViewModel()
    }
}

val diModules = listOf(apiModule, kakaoApiModule, viewModelModule)