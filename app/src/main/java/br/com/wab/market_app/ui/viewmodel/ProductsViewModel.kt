package br.com.wab.market_app.ui.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.wab.market_app.model.Product
import br.com.wab.market_app.model.Response
import br.com.wab.market_app.model.Type
import br.com.wab.market_app.repository.ProductRepository
import com.uber.autodispose.AutoDispose.autoDisposable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class ProductsViewModel : ViewModel() {

    private val repository = ProductRepository()

    private val disposables = CompositeDisposable()

    val products  = MutableLiveData<Response<*>>()

    fun getAllProducts(){
        disposables.add(
            repository.getAllProducts()
                .doOnSubscribe { products.value = Response.loading() }
                .subscribeBy (
                    onNext = { products.value = Response.success(it) },
                    onComplete = { products.value = Response.finished() },
                    onError = { products.value =  Response.error(it) }
                )
        )
    }


    fun filterType(list: List<Product>, type: Type){
        disposables.add(Observable.just(list.filter { it.type == type })
            .subscribeBy(
                onNext = { products.value = Response.success(it) },
                onComplete = { products.value = Response.finished() },
                onError = { products.value =  Response.error(it) }
            )
        )

    }


    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}