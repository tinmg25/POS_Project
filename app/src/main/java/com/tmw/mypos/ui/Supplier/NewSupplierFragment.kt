package com.tmw.mypos.ui.Supplier

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
import com.tmw.mypos.model.Supplier
import kotlinx.android.synthetic.main.fragment_new_supplier.*
import kotlinx.android.synthetic.main.fragment_new_supplier.view.*
import kotlinx.android.synthetic.main.fragment_supplier.view.*

class NewSupplierFragment : Fragment() {

    private lateinit var supplierViewModel: SupplierViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_new_supplier, container, false)

        supplierViewModel=ViewModelProvider(this).get(SupplierViewModel::class.java)

        view.btn_Save_Supplier.setOnClickListener {
           insertdataintoDatabase()
        }
        return view
    }

    private fun insertdataintoDatabase(){
        val name=etd_new_supplier.text.toString()

        if(inputCheck(name)){
            val supplier= Supplier(0,name)
            supplierViewModel.insertSupplier(supplier)
            Toast.makeText(context,"Successfully Added New Supplier!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_newSupplierFragment_to_supplierFragment)
        }else{
            Toast.makeText(context,"Please Fill Supplier!",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name:String):Boolean{
        return !(TextUtils.isEmpty(name))
    }
}