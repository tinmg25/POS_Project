package com.tmw.mypos.ui.Supplier

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
import com.tmw.mypos.model.Supplier
import kotlinx.android.synthetic.main.fragment_update_supplier.*
import kotlinx.android.synthetic.main.fragment_update_supplier.view.*

class UpdateSupplierFragment : Fragment() {

    private val args by navArgs<UpdateSupplierFragmentArgs>()

    private lateinit var supplierViewModel: SupplierViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_update_supplier, container, false)

        supplierViewModel=ViewModelProvider(this).get(SupplierViewModel::class.java)

        view.etd_update_supplier.setText(args.currentSupplier.supplier_name)

        view.btn_update_supplier.setOnClickListener {
            updateSupplier()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateSupplier() {
        val name=etd_update_supplier.text.toString()
        if(inputCheck(name)){
            val updateSupplier= Supplier(args.currentSupplier.supplierid,name)
            supplierViewModel.updateSupplier(updateSupplier)
            Toast.makeText(context,"Successfully Update!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateSupplierFragment3_to_supplierFragment)
        }else{
            Toast.makeText(context,"Please Fill Supplier Name",Toast.LENGTH_LONG).show()
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
            deleteSupplier()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteSupplier() {
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            supplierViewModel.deleteSupplier(args.currentSupplier)
            Toast.makeText(requireContext(),"Successfully Removed: ${args.currentSupplier.supplier_name}",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateSupplierFragment3_to_supplierFragment)
        }
        builder.setNegativeButton("No"){_, _ ->

        }
        builder.setTitle("Delete ${args.currentSupplier.supplier_name}?")
        builder.setMessage("Are you sure want to delete ${args.currentSupplier.supplier_name}?")
        builder.create().show()
    }

}