package com.tmw.mypos.ui.Sale

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tmw.mypos.R
import com.tmw.mypos.model.Sale
import kotlinx.android.synthetic.main.item_sale.view.*
import kotlinx.android.synthetic.main.item_stock.view.*


class SaleAdapter:RecyclerView.Adapter<SaleAdapter.SaleViewHolder>() {

    private var saleList= emptyList<Sale>()
    inner class SaleViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleViewHolder {
        return SaleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_sale,parent,false))
    }

    override fun onBindViewHolder(holder: SaleViewHolder, position: Int) {
        val currentItem=saleList[position]
        holder.itemView.txtDate.text=currentItem.date
        holder.itemView.txtCustomer.text=currentItem.customer
        holder.itemView.txtCode.text=currentItem.name
        holder.itemView.txtQty.text=currentItem.qty.toString()
        holder.itemView.txtPrice.text=currentItem.price.toString()
        holder.itemView.txtSaleAmount.text=currentItem.amount.toString()

        holder.itemView.rowLayoutSale.setOnClickListener {
            val action=SaleFragmentDirections.actionNavSaleToUpdateSaleFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return saleList.size
    }

    fun setDataSale(sale:List<Sale>){
        this.saleList=sale
        notifyDataSetChanged()
    }
}
