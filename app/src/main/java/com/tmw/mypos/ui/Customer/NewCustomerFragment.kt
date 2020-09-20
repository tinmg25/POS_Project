package com.tmw.mypos.ui.Customer

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tmw.mypos.R
import com.tmw.mypos.model.Customer
import com.tmw.mypos.model.Supplier
import kotlinx.android.synthetic.main.fragment_new_customer.*
import kotlinx.android.synthetic.main.fragment_new_customer.view.*
import kotlinx.android.synthetic.main.fragment_new_supplier.*
import kotlinx.android.synthetic.main.fragment_new_supplier.view.*
import kotlinx.android.synthetic.main.fragment_supplier.view.*

class NewCustomerFragment : Fragment() {

    private lateinit var customerViewModel: CustomerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_new_customer, container, false)

        customerViewModel=ViewModelProvider(this).get(CustomerViewModel::class.java)

        view.btn_Save_Customer.setOnClickListener {
            insertdataintoDatabase()
        }
        return view
    }

    private fun insertdataintoDatabase(){
        val name=etd_new_customer.text.toString()

        if(inputCheck(name)){
            val customer= Customer(0,name)
            customerViewModel.insertCustomer(customer)
            Toast.makeText(context,"Successfully Added New Customer!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_newCustomerFragment_to_customerFragment2)
        }else{
            Toast.makeText(context,"Please Fill Customer!",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name:String):Boolean{
        return !(TextUtils.isEmpty(name))
    }
}