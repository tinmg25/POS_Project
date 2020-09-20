package com.tmw.mypos.ui.Customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tmw.mypos.R
import com.tmw.mypos.model.Customer
import kotlinx.android.synthetic.main.item_customer.view.*

class CustomerAdapter: RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>(){

    private var customerList= emptyList<Customer>()

    class CustomerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        return CustomerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_customer,parent,false))
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val currentItem=customerList[position]
        holder.itemView.txt_customer_id.text=currentItem.custid.toString()
        holder.itemView.txtCustomer.text=currentItem.cust_name

        holder.itemView.rowLayoutCustomer.setOnClickListener {
            val action= CustomerFragmentDirections.actionCustomerFragment2ToUpdateCustomerFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return customerList.size
    }

    fun setDataCustomer(customer:List<Customer>){
        this.customerList=customer
        notifyDataSetChanged()
    }

}