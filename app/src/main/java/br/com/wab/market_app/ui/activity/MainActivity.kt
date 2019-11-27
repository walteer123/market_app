package br.com.wab.market_app.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.wab.market_app.R
import br.com.wab.market_app.model.Product
import br.com.wab.market_app.model.Response
import br.com.wab.market_app.model.Status
import br.com.wab.market_app.model.Type
import br.com.wab.market_app.ui.viewmodel.ProductsViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    lateinit var viewModel: ProductsViewModel

    lateinit var  productsList: List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)

        viewModel.products.observe(this, Observer { response->
            proccessResponse(response)
        })

        simple_button.setOnClickListener {
            filterType(type = Type.FRUIT)
        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllProducts()
    }

    private fun <T> proccessResponse(response: Response<T>) {
        when(response.status){
            Status.LOADING ->{ /*show progress*/ }
            Status.FINISHED ->{/*hide progress*/ }
            Status.ERROR ->{ Log.d("Status", "Erro! \n ${response.error?.message}")}
            Status.SUCCESS ->{
                productsList = response.data as List<Product>
                Log.d("Status", "Successo! \n ${productsList.size}")
                //atualizaLista(lista)
            }
        }
    }

    private fun filterType(type: Type){
        viewModel.filterType(productsList, type)
    }
}
