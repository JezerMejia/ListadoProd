package com.jezerm.listadoprod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jezerm.listadoprod.databinding.ActivityProductEditBinding
import com.jezerm.listadoprod.dataclass.Product

class ProductEdit : AppCompatActivity() {
    private lateinit var binding: ActivityProductEditBinding
    private var selectedID: Int = 0

    private var selectedProduct: Product? = null
        get() {
            val productList = MainActivity.productList
            return productList.find { prod -> prod.id == this.selectedID }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Aquí se obtienen los datos extras que se enviaron
        // a través de la creación de un Intent.
        val bundle = intent.extras
        if (bundle != null) {
            this.selectedID = bundle.getInt("selectedID")
        }
        this.binding = ActivityProductEditBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        this.init()
    }

    private fun init() {
        // Deshabilitar el EditView del ID
        this.binding.productInput.etId.isEnabled = false
        this.title = "Editar"

        this.binding.btnEdit.setOnClickListener {
            this.editProduct()
        }
        this.binding.btnRestore.setOnClickListener {
            this.restoreInput()
        }
        this.binding.btnDelete.setOnClickListener { 
            this.deleteProduct()
        }

        this.restoreInput()
    }

    /**
     * Edita el producto con los datos registrados
     */
    private fun editProduct() {
        try {
            val productName = this.binding.productInput.etName.text.toString()
            val productPrice = this.binding.productInput.etPrice.text.toString().toDouble()

            val product = this.selectedProduct ?: return
            product.name = productName
            product.price = productPrice

            Toast.makeText(this, "El producto #$selectedID fue editado", Toast.LENGTH_SHORT).show()
        } catch (ex: Exception) {
            Toast.makeText(this, "Error: ${ex.toString()}", Toast.LENGTH_SHORT).show()
            println(ex)
            return
        }
    }

    /**
     * Restaura o reinicia los valores de todos los Input a los valores
     * del producto seleccionado
     */
    private fun restoreInput() {
        with (this.binding.productInput) {
            this.etId.setText(selectedProduct?.id.toString() ?: "")
            this.etName.setText(selectedProduct?.name ?: "")
            this.etPrice.setText(selectedProduct?.price.toString() ?: "")
        }
    }

    /**
     * Elimina un producto de la lista de productos principal
     */
    private fun deleteProduct() {
        val productList = MainActivity.productList
        val id = this.selectedProduct?.id ?: 0
        if (productList.remove(this.selectedProduct)) {
            Toast.makeText(this, "El producto #$id fue eliminado", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            Toast.makeText(this, "El producto no pudo ser eliminado", Toast.LENGTH_SHORT).show()
        }
    }
}