package com.example.eui.day3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.eui.R

class StaticFragment : Fragment() {

    private lateinit var communicator: Communicator
    private lateinit var btnSendMessage: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_static, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSendMessage = view.findViewById(R.id.btn_send_message)

        communicator = activity as Communicator

        btnSendMessage.setOnClickListener {
            communicator.sendMessage("message from static fragment")
        }
    }
}