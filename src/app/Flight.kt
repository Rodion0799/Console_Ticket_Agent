package app

data class Flight (
    val from: String,
    val to: String,
    val date: String,
    val price: Int,
    var stock: Int
)