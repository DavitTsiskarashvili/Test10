package com.example.test10_final.data.remote

import com.example.test10_final.common.ApiEndpoints
import com.example.test10_final.domain.model.ChatModel
import retrofit2.Response
import retrofit2.http.GET

interface ChatService {

    @GET(ApiEndpoints.Items)
    suspend fun getInfo () : Response<MutableList<ChatModel.ChatModelItem>>

}