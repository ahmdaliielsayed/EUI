package com.example.eui.day4

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.eui.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsActivity : AppCompatActivity() {

    private lateinit var productDao: ProductDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_products)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // https://dummyjson.com/products
        val service = APIClient.serivce

        productDao = MyDatabase.getInstance(applicationContext).productDao()

        // show progress bar
        lifecycleScope.launch(Dispatchers.IO) {
            val response = service.getProductsResponse()

            // background thread
            val productList = response.products

            if (productList != null) {
                for (product in productList) {
                    if (product != null) {
                        productDao.insert(product)
                    }
                }
            }

            withContext(Dispatchers.Main) {
                Glide.with(this@ProductsActivity)
                    .load(response.products?.get(0)?.thumbnail)
                    .into(this@ProductsActivity.findViewById(R.id.img_item))
            }
        }
        /*service.getProductsResponse().enqueue(object : Callback<ProductsResponse>{
            override fun onResponse(p0: Call<ProductsResponse>, response: Response<ProductsResponse>) {
                // hide progress bar
                Log.d("asd --> ", response.body().toString())
                Log.d("asd --> products --> ", response.body()?.products.toString())
                Log.d("asd --> skip --> ", response.body()?.skip.toString())
                Log.d("asd --> limit --> ", response.body()?.limit.toString())
                Log.d("asd --> total --> ", response.body()?.total.toString())

                Glide.with(this@ProductsActivity)
                    .load(response.body()?.products?.get(0)?.thumbnail)
                    .into(this@ProductsActivity.findViewById(R.id.img_item))
            }

            override fun onFailure(p0: Call<ProductsResponse>, throwable: Throwable) {
                // hide progress bar
                Log.e("asd --> ", throwable.message.toString())
            }

        })*/
    }
}