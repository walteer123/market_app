package br.com.wab.market_app.repository.service

import br.com.wab.market_app.model.Product
import io.reactivex.Observable
import retrofit2.http.GET


interface ProductService {

    @GET("products")
    fun getAllProducts() : Observable<List<Product>>


}