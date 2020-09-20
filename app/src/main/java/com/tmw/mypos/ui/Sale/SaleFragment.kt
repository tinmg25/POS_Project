package com.tmw.mypos.ui.Sale


import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmw.mypos.R
import kotlinx.android.synthetic.main.fragment_sale.view.*

class SaleFragment : Fragment() {

    private lateinit var saleViewModel: SaleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstaneState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_sale, container, false)

        val adapter=SaleAdapter()
        val recyclerView=view.recycler_sale
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(requireContext())

        saleViewModel=ViewModelProvider(this).get(SaleViewModel::class.java)
        saleViewModel.allSale.observe(viewLifecycleOwner, androidx.lifecycle.Observer {sale->
            adapter.setDataSale(sale)
        })

        view.btn_New_Sale.setOnClickListener {
            findNavController().navigate(R.id.action_nav_sale_to_newSaleFragment)
        }

        setHasOptionsMenu(true)

        return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
            deleteAllSale()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllSale() {
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            saleViewModel.deleteAllSale()
            Toast.makeText(requireContext(),"Successfully Removed All Sale Voucher!",
                Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){_, _ ->

        }
        builder.setTitle("Delete All Sale Voucher")
        builder.setMessage("Are you sure want to delete all sale voucher?")
        builder.create().show()
    }


}
