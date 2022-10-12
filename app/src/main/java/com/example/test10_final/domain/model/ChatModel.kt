package com.example.test10_final.domain.model

import com.google.gson.annotations.SerializedName

data class ChatModel(
    val chatModelItem: MutableList<ChatModelItem>
) {

    data class ChatModelItem(
        val avatar: String?,
        val email: String?,
        @SerializedName("first_name")
        val firstName: String?,
        val id: Int?,
        @SerializedName("is_typing")
        val isTyping: Boolean?,
        @SerializedName("last_message")
        val lastMessage: String?,
        @SerializedName("last_name")
        val lastName: String?,
        @SerializedName("message_type")
        val messageType: String?,
        @SerializedName("unrea_message")
        val unreaMessage: Int?,
        @SerializedName("updated_date")
        val updatedDate: Long?
    )
}