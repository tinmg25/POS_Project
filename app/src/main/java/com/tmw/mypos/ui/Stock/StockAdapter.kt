package com.tmw.mypos.ui.Stock

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tmw.mypos.R
import com.tmw.mypos.model.Stock
import kotlinx.android.synthetic.main.item_stock.view.*

class StockAdapter:RecyclerView.Adapter<StockAdapter.StockViewHolder>(){

    private var stockList= emptyList<Stock>()

    class StockViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        return StockViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_stock,parent,false))
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val currentItem=stockList[position]
        holder.itemView.txtStockID.text=currentItem.cid.toString()
        holder.itemView.txtCode.text=currentItem.code
        holder.itemView.txtStock.text=currentItem.name
        holder.itemView.txtPurPrice.text=currentItem.purprice.toString()
        holder.itemView.txtSalePrice.text=currentItem.saleprice.toString()

        holder.itemView.rowLayout.setOnClickListener {
            val action=StockFragmentDirections.actionNavStockToUpdateStockFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    fun setData(stock:List<Stock>){
        this.stockList=stock
        notifyDataSetChanged()
    }

}