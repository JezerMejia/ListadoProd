package com.jezerm.listadoprod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jezerm.listadoprod.dataadapter.ProductAdapter
import com.jezerm.listadoprod.databinding.ActivityMainBinding
import com.jezerm.listadoprod.dataclass.Product
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var productList = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        this.init()
    }

    private fun init() {
        this.binding.btnAdd.setOnClickListener {
            this.addProduct()
        }
        this.binding.btnClear.setOnClickListener {
            this.clearInput()
        }
    }

    private fun clearInput() {
        this.binding.etId.setText("")
        this.binding.etName.setText("")
        this.binding.etPrice.setText("")
    }

    private fun addProduct() {
        try {
            val productId = this.binding.etId.text.toString().toInt()
            val productName = this.binding.etName.text.toString()
            val productPrice = this.binding.etPrice.text.toString().toDouble()

            val product = Product(productId, productName, productPrice)
            this.productList.add(product)
        } catch (ex: Exception) {
            Toast.makeText(this, "Error: ${ex.toString()}", Toast.LENGTH_SHORT).show()
            println(ex)
            return
        }
        this.binding.rcvProdList.layoutManager = LinearLayoutManager(this@MainActivity)
        this.binding.rcvProdList.adapter = ProductAdapter(this.productList)
        this.clearInput()
    }
}