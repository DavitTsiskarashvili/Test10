package com.example.test10_final.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.test10_final.R
import com.example.test10_final.common.Resource
import com.example.test10_final.databinding.FragmentChatBinding
import com.example.test10_final.domain.model.ChatModel
import com.example.test10_final.ui.adapters.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private var binding: FragmentChatBinding? = null
    private lateinit var chatAdapter : ChatAdapter
    private val viewModel: ChatViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemsObservers()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


    private fun itemsObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.items().collect {
                when (it) {
                    is Resource.Success <*> -> {
                        chatAdapter = ChatAdapter()
                        binding?.recyclerView?.adapter = chatAdapter
                        chatAdapter.submitList(it as Resource.Success<MutableList<ChatModel.ChatModelItem>>)

                    }

                    is Resource.Error <*> -> {}

                    is Resource.Loader <*> -> {}

                }
            }
        }
    }



}