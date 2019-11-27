package br.com.wab.market_app.repository

import android.util.Log
import br.com.wab.market_app.model.Product
import br.com.wab.market_app.repository.data.Connection
import br.com.wab.market_app.util.applySchedulers
import io.reactivex.Observable

class ProductRepository {

    private val api = Connection.productRepository

    fun getAllProducts() : Observable<List<Product>>{
        return api.getAllProducts()
            .applySchedulers()
    }




}