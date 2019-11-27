package br.com.wab.market_app.model

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val type: Type
)

enum class Type{
    FRUIT,
    DRINK,
    OTHER
}