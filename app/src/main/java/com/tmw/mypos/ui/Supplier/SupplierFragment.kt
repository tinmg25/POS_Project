package com.tmw.mypos.ui.Supplier

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmw.mypos.R
import kotlinx.android.synthetic.main.fragment_supplier.view.*

class SupplierFragment : Fragment(){

    private lateinit var supplierViewModel: SupplierViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_supplier, container, false)

        val supplierAdapter=SupplierAdapter()
        val recyclerView=view.recycler_supplier
        recyclerView.adapter=supplierAdapter
        recyclerView.layoutManager=LinearLayoutManager(requireContext())

        supplierViewModel=ViewModelProvider(this).get(SupplierViewModel::class.java)
        supplierViewModel.allSupplier.observe(viewLifecycleOwner, Observer {supplier ->
            supplierAdapter.setDataSupplier(supplier)
        })

        view.btn_New_Supplier.setOnClickListener {
            Log.d("Error","Message")
            findNavController().navigate(R.id.action_supplierFragment_to_newSupplierFragment)
        }

        view.btn_Customer.setOnClickListener {
            findNavController().navigate(R.id.action_supplierFragment_to_customerFragment2)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
            deleteAllSupplier()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllSupplier() {
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            supplierViewModel.deleteAllSupplier()
            Toast.makeText(requireContext(),"Successfully Removed All Supplier!",
                Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){_, _ ->

        }
        builder.setTitle("Delete All Supplier List")
        builder.setMessage("Are you sure want to delete all supplier?")
        builder.create().show()
    }
}