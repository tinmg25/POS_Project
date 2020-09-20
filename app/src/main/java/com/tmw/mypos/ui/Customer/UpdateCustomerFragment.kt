package com.tmw.mypos.ui.Customer

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tmw.mypos.R
import com.tmw.mypos.model.Customer
import com.tmw.mypos.model.Supplier
import kotlinx.android.synthetic.main.fragment_update_customer.*
import kotlinx.android.synthetic.main.fragment_update_customer.view.*
import kotlinx.android.synthetic.main.fragment_update_supplier.*
import kotlinx.android.synthetic.main.fragment_update_supplier.view.*

class UpdateCustomerFragment : Fragment() {

    private val args by navArgs<UpdateCustomerFragmentArgs>()

    private lateinit var customerViewModel: CustomerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_update_customer, container, false)

        customerViewModel=ViewModelProvider(this).get(CustomerViewModel::class.java)

        view.etd_update_customer.setText(args.currentCustomer.cust_name)

        view.btn_update_customer.setOnClickListener {
            updateCustomer()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateCustomer() {
        val name=etd_update_customer.text.toString()
        if(inputCheck(name)){
            val updateCustomer= Customer(args.currentCustomer.custid,name)
            customerViewModel.updateCustomer(updateCustomer)
            Toast.makeText(context,"Successfully Update!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateCustomerFragment_to_customerFragment2)
        }else{
            Toast.makeText(context,"Please Fill Customer Name",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name:String):Boolean{
        return !(TextUtils.isEmpty(name))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
            deleteCustomer()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteCustomer() {
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            customerViewModel.deleteCustomer(args.currentCustomer)
            Toast.makeText(requireContext(),"Successfully Removed: ${args.currentCustomer.cust_name}",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateCustomerFragment_to_customerFragment2)
        }
        builder.setNegativeButton("No"){_, _ ->

        }
        builder.setTitle("Delete ${args.currentCustomer.cust_name}?")
        builder.setMessage("Are you sure want to delete ${args.currentCustomer.cust_name}?")
        builder.create().show()
    }

}