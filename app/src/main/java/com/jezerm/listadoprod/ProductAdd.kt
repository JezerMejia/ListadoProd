package com.jezerm.listadoprod

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jezerm.listadoprod.databinding.ActivityProductAddBinding
import com.jezerm.listadoprod.dataclass.Product

class ProductAdd() : AppCompatActivity() {
    private lateinit var binding: ActivityProductAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityProductAddBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        this.init()
    }

    private fun init() {
        this.title = "Añadir"
        this.binding.btnAdd.setOnClickListener {
            this.addProduct()
        }
        this.binding.btnClear.setOnClickListener {
            this.clearInput()
        }
    }

    /**
     * Elimina todos los datos ingresados en los Input
     */
    private fun clearInput() {
        this.binding.productInput.etId.setText("")
        this.binding.productInput.etName.setText("")
        this.binding.productInput.etPrice.setText("")
    }

    /**
     * Crea y añade un producto a la lista principal de Productos a partir
     * de los datos ingresados en cada EditView
     */
    private fun addProduct() {
        try {
            val productId = this.binding.productInput.etId.text.toString().toInt()
            val productName = this.binding.productInput.etName.text.toString()
            val productPrice = this.binding.productInput.etPrice.text.toString().toDouble()

            val product = Product(productId, productName, productPrice)
            MainActivity.productList.add(product)
            Toast.makeText(this, "El producto \"$productName\" fue añadido", Toast.LENGTH_SHORT).show()
        } catch (ex: Exception) {
            Toast.makeText(this, "Error: ${ex.toString()}", Toast.LENGTH_SHORT).show()
            println(ex)
            return
        }
        this.clearInput()
    }
}