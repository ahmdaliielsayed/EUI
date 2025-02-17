package com.example.eui.day3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eui.R
import com.example.eui.day4.APIClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

        val recycler: RecyclerView = view.findViewById(R.id.my_product_rv)
        val myAdapter = MySecondAdapter()
        recycler.adapter = myAdapter
        recycler.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)

        // https://dummyjson.com/products
        val service = APIClient.serivce

        // show progress bar
        lifecycleScope.launch(Dispatchers.IO) {
            val response = service.getProductsResponse()

            // background thread
            val productList = response.products

            withContext(Dispatchers.Main) {
                if (productList != null)
                    myAdapter.submitList(productList)
            }
        }

    }

    fun receiveMessage(data: String) {
        textReceivedMsg.text = data
    }
}