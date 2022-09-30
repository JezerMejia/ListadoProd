package com.jezerm.listadoprod.dataadapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.jezerm.listadoprod.ProductEdit
import com.jezerm.listadoprod.databinding.ListItemBinding
import com.jezerm.listadoprod.dataclass.Product

// Aquí se crea el ProductAdapter
class ProductAdapter(var list: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductHolder>() {

    inner class ProductHolder(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun load(product: Product) {
            with(binding) {
                this.tvProdCode.text = "#${product.id.toString()}"
                this.tvProdName.text = product.name
                this.tvProdPrice.text = "C$ ${product.price.toString()}"

                this.cardView.setOnClickListener {
                    openProductEdit(binding.root.context, product.id)
                }
            }
        }

        /**
         * Abre la vista de edición de un Producto con el id seleccionado
         */
        private fun openProductEdit(context: Context, id: Int) {
            val intent = Intent(context, ProductEdit::class.java).apply {
                this.putExtra("selectedID", id)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        holder.load(this.list[position])
    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}