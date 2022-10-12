package com.example.test10_final.domain.repository

import com.example.test10_final.common.Resource
import com.example.test10_final.data.remote.ChatService
import com.example.test10_final.domain.model.ChatModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: ChatService
) {
    suspend fun getItems(): Flow<Resource<MutableList<ChatModel.ChatModelItem>>> = flow {

        val response = api.getInfo()
        val result = response.body()
        var value: Resource<MutableList<ChatModel.ChatModelItem>>
        if (response.isSuccessful && result != null) {
            value = Resource.Success(result)
        } else {
            value = Resource.Error(response.message())
        }
        emit(value)
        emit(Resource.Loader(false))
    }

}



//    private suspend fun <M> handleResponse (
//        request: suspend () -> Response<M>
//        defaultMessage: String
//    ) : Resource<M> {
//        return try {
//            val result = request.invoke()
//            val body = result.body()
//            if (result.isSuccesful && body != null) {
//                return Resource.Success (body)
//            } else {
//                    only for hackers -> here you can parse error body
//                Resource.Error(result.message()?: defaultMessage)
//            }
//        } catch (e: Throwable) {
//            Resource.Error("Something went wrong", null)
//        }
//    }





