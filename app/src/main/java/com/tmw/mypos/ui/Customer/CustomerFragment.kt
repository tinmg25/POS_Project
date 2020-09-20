package com.tmw.mypos.ui.Customer

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmw.mypos.R
import kotlinx.android.synthetic.main.fragment_customer.view.*
import kotlinx.android.synthetic.main.fragment_supplier.view.*

class CustomerFragment : Fragment(){

    private lateinit var customerViewModel: CustomerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_customer, container, false)

        val customerAdapter=CustomerAdapter()
        val recyclerView=view.recycler_customer
        recyclerView.adapter=customerAdapter
        recyclerView.layoutManager=LinearLayoutManager(requireContext())

        customerViewModel=ViewModelProvider(this).get(CustomerViewModel::class.java)
        customerViewModel.allCustomer.observe(viewLifecycleOwner, Observer {supplier ->
            customerAdapter.setDataCustomer(supplier)
        })

        view.btn_New_Customer.setOnClickListener {
            findNavController().navigate(R.id.action_customerFragment2_to_newCustomerFragment)
        }

        view.btn_Supplier.setOnClickListener {
            findNavController().navigate(R.id.action_customerFragment2_to_supplierFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
            deleteAllCustomer()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllCustomer() {
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            customerViewModel.deleteAllCustomer()
            Toast.makeText(requireContext(),"Successfully Removed All Customer!",
                Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){_, _ ->

        }
        builder.setTitle("Delete All Customer List")
        builder.setMessage("Are you sure want to delete all customer?")
        builder.create().show()
    }
}