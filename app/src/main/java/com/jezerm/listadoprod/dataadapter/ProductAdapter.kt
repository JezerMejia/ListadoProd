package com.jezerm.listadoprod.dataadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jezerm.listadoprod.databinding.ListItemBinding
import com.jezerm.listadoprod.dataclass.Product

class ProductAdapter(var list: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductHolder>() {
    inner class ProductHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun load(product: Product) {
            with(binding) {
                this.tvProdCode.text = product.id.toString()
                this.tvProdName.text = product.name
                this.tvProdPrice.text = product.price.toString()
            }
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