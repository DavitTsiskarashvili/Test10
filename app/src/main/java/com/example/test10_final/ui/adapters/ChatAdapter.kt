package com.example.test10_final.ui.adapters

import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.test10_final.R
import com.example.test10_final.common.Resource
import com.example.test10_final.databinding.SingleChatBinding
import com.example.test10_final.domain.model.ChatModel
import com.example.test10_final.extensions.loadImage
import java.time.Instant
import java.time.ZoneId
import java.util.*
import kotlin.math.log

class ChatAdapter() :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    var chatList: Resource.Success<MutableList<ChatModel.ChatModelItem>> = Resource.Success(mutableListOf())

    fun submitList(newChatList: Resource.Success<MutableList<ChatModel.ChatModelItem>>) {
        chatList = newChatList
    }

    inner class ChatViewHolder(private val binding: SingleChatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.apply {
                ivUser.loadImage(chatList.data[absoluteAdapterPosition].avatar)
                tvName.text = "${chatList.data[absoluteAdapterPosition].firstName} " +
                        "${chatList.data[absoluteAdapterPosition].lastName}"

                if (chatList.data[absoluteAdapterPosition].updatedDate == null){
                    tvTime.visibility = View.GONE
                } else {
                    val time = chatList.data[absoluteAdapterPosition].updatedDate.toString()
                    val timeDropped = time.dropLast(3)
                    val dt = Instant.ofEpochSecond(timeDropped.toLong())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime().toString()

                    val dropped = dt.drop(11)
                    val droppedLast = dropped.dropLast(3)

                    tvTime.text = droppedLast
                }

                if (chatList.data[absoluteAdapterPosition].isTyping == true) {
                    tvUnread.visibility = View.GONE
                    ivTyping.visibility = View.VISIBLE
                }

                if (chatList.data[absoluteAdapterPosition].unreaMessage!! > 0) {
                    ivTyping.visibility = View.GONE
                    tvUnread.visibility = View.VISIBLE
                    tvUnread.setBackgroundResource(R.drawable.ic_green_dot_msg)
                    tvUnread.text = chatList.data[absoluteAdapterPosition].unreaMessage.toString()
                    tvMessage.setTypeface(tvMessage.typeface, Typeface.BOLD)
                    tvMessage.setTextColor(ContextCompat.getColor(tvMessage.context, R.color.white))
                    tvTextForVoice.setTextColor(ContextCompat.getColor(tvMessage.context, R.color.white))
                } else {
                    tvMessage.setTypeface(tvMessage.typeface, Typeface.NORMAL)
                    tvMessage.setTextColor(ContextCompat.getColor(tvMessage.context, R.color.grey))
                    tvTextForVoice.setTextColor(ContextCompat.getColor(tvMessage.context, R.color.grey))
                }

                when (chatList.data[absoluteAdapterPosition].messageType) {
                    "text" -> {
                        tvMessage.visibility = View.VISIBLE
                        ivAttachmentVoice.visibility = View.GONE
                        tvTextForVoice.visibility = View.GONE
                        tvMessage.text = chatList.data[absoluteAdapterPosition].lastMessage

                    }
                    "voice" -> {
                        tvMessage.visibility = View.GONE
                        tvTextForVoice.visibility = View.VISIBLE
                        ivAttachmentVoice.visibility = View.VISIBLE
                        ivAttachmentVoice.setImageResource(R.drawable.ic_recorder)
                        tvTextForVoice.text = "Sent a voice message"
                    }
                    else -> {
                        tvMessage.visibility = View.GONE
                        tvTextForVoice.visibility = View.VISIBLE
                        ivAttachmentVoice.visibility = View.VISIBLE
                        tvTextForVoice.text = "Sent an attachment"
                        ivAttachmentVoice.setImageResource(R.drawable.ic_attachment)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val binding =
            SingleChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ChatViewHolder(binding)
    }

    override fun getItemCount() = chatList.data.size

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind()
    }





}

