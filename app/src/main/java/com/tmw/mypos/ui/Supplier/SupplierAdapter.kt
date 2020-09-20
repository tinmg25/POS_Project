package com.tmw.mypos.ui.Supplier

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tmw.mypos.R
import com.tmw.mypos.model.Supplier
import kotlinx.android.synthetic.main.item_supplier.view.*

class SupplierAdapter:RecyclerView.Adapter<SupplierAdapter.SupplierViewHolder>(){

    private var supplierList= emptyList<Supplier>()

    class SupplierViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplierViewHolder {
        return SupplierViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_supplier,parent,false))
    }

    override fun onBindViewHolder(holder: SupplierViewHolder, position: Int) {
        val currentItem=supplierList[position]
        holder.itemView.txt_supplier_id.text=currentItem.supplierid.toString()
        holder.itemView.txtSupplier.text=currentItem.supplier_name

        holder.itemView.rowLayoutSupplier.setOnClickListener {
            val action=SupplierFragmentDirections.actionSupplierFragmentToUpdateSupplierFragment3(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return supplierList.size
    }

    fun setDataSupplier(supplier:List<Supplier>){
        this.supplierList=supplier
        notifyDataSetChanged()
    }

}