package com.tmw.mypos.ui.Purchase

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmw.mypos.R
import kotlinx.android.synthetic.main.fragment_purchase.view.*

class PurchaseFragment : Fragment() {

    private lateinit var purchaseViewModel: PurchaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_purchase, container, false)

        val adapter=PurchaseAdapter()
        val recyclerView=view.recycler_purchase
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(requireContext())

        purchaseViewModel=ViewModelProvider(this).get(PurchaseViewModel::class.java)
        purchaseViewModel.allPurchase.observe(viewLifecycleOwner, Observer {sale->
            adapter.setDataPurchase(sale)
        })

        view.btn_New_Purchase.setOnClickListener {
            findNavController().navigate(R.id.action_nav_purchase_to_newPurchaseFragment)
        }

        setHasOptionsMenu(true)

        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
            deleteAllPurchase()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllPurchase() {
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            purchaseViewModel.deleteAllPurchase()
            Toast.makeText(requireContext(),"Successfully Removed All Purchase Voucher!",
                Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){_, _ ->

        }
        builder.setTitle("Delete All Purchase Voucher")
        builder.setMessage("Are you sure want to delete all purchase voucher?")
        builder.create().show()
    }

}