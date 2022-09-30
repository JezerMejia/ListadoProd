package com.jezerm.listadoprod

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jezerm.listadoprod.dataadapter.ProductAdapter
import com.jezerm.listadoprod.databinding.ActivityMainBinding
import com.jezerm.listadoprod.dataclass.Product

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Esto es similar a un static
    companion object {
        val productList = ArrayList<Product>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        this.init()
    }

    /**
     * Cuando la actividad sea resumida, los datos del RecyclerView se actualizarán
     */
    override fun onResume() {
        super.onResume()
        this.updateData()
    }

    private fun init() {
        this.title = "Listado de Productos"
        this.binding.btnAdd.setOnClickListener {
            this.openProductAdd()
        }
        this.updateData()

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val deletedProduct = MainActivity.productList.get(viewHolder.adapterPosition)
                MainActivity.productList.removeAt(viewHolder.adapterPosition)
                binding.rcvProdList.adapter?.notifyItemRemoved(viewHolder.adapterPosition)
                updateProductsVisibility()
            }
        }).attachToRecyclerView(this.binding.rcvProdList)
    }

    /**
     * Abre la Actividad de Añadir producto
     */
    private fun openProductAdd() {
        val intent = Intent(this, ProductAdd::class.java)
        startActivity(intent)
    }

    /**
     * Actualiza los datos del RecyclerView con un nuevo Adapter
     */
    private fun updateData() {
        this.binding.rcvProdList.layoutManager = LinearLayoutManager(this@MainActivity)
        this.binding.rcvProdList.adapter = ProductAdapter(MainActivity.productList)
        this.updateProductsVisibility()
    }

    /**
     * Actualiza la visibilidad de la lista.
     * En caso de no haber ningún producto, se mostrará un mensaje de advertencia.
     */
    private fun updateProductsVisibility() {
        val noProducts: Boolean = MainActivity.productList.isEmpty()

        this.binding.noProductsMessage.visibility = if (noProducts) View.VISIBLE else View.INVISIBLE
        this.binding.scrollView.visibility = if (noProducts) View.INVISIBLE else View.VISIBLE
    }
}