package com.tmw.mypos.ui.Purchase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tmw.mypos.R
import com.tmw.mypos.model.Purchase
import kotlinx.android.synthetic.main.fragment_new_sale.view.*
import kotlinx.android.synthetic.main.item_purchase.view.*

class PurchaseAdapter :RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder>(){

    private var purchaseList= emptyList<Purchase>()

    inner class PurchaseViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        return PurchaseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_supplier,parent,false))
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
         val currentItem=purchaseList[position]
        holder.itemView.txtDate.text=currentItem.date
        holder.itemView.txtSupplier.text=currentItem.supplier
        holder.itemView.txtItem.text=currentItem.name
        holder.itemView.txtQty.text=currentItem.qty.toString()
        holder.itemView.txtPrice.text=currentItem.price.toString()
        holder.itemView.txtAmount.text=currentItem.amount.toString()

        holder.itemView.rowLayoutPurchase.setOnClickListener {
            val action=PurchaseFragmentDirections.actionNavPurchaseToUpdatePurchaseFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return purchaseList.size
    }

    fun setDataPurchase(purchase:List<Purchase>){
        this.purchaseList=purchase
        notifyDataSetChanged()
    }

}