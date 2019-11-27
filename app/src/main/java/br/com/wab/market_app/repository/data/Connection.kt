package br.com.wab.market_app.repository.data

import br.com.wab.market_app.repository.service.ProductService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object Connection {

    private val BASE_URL = "YOUR_BASE_URL"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


    val productRepository by lazy {
        retrofit.create(ProductService::class.java)
    }

}