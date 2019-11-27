package br.com.wab.market_app.model

class Response<T>(
    val status: Status?,
    val data: T?,
    val error: Throwable?
) {
    companion object{

        fun loading() = Response(Status.LOADING, null, null)

        fun <T> success(data: T?) = Response(Status.SUCCESS, data, null)

        fun error(error: Throwable) = Response(Status.ERROR, null, error)

        fun finished() = Response(Status.FINISHED, null, null)
    }


}

enum class Status {
    LOADING,
    SUCCESS,
    ERROR,
    FINISHED
}