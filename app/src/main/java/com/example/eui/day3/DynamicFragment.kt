package com.example.eui.day3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.eui.R

class DynamicFragment : Fragment() {

    private lateinit var textReceivedMsg: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dynamic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textReceivedMsg = view.findViewById(R.id.text_received_msg)
    }

    fun receiveMessage(data: String) {
        textReceivedMsg.text = data
    }
}