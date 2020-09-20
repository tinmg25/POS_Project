package com.tmw.mypos.ui.Stock

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tmw.mypos.R
import kotlinx.android.synthetic.main.fragment_stock.*
import kotlinx.android.synthetic.main.fragment_stock.view.*

class StockFragment : Fragment() {

    private lateinit var stockViewModel: StockViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_stock, container, false)

        //recyclerview
        val adapter=StockAdapter()
        val recyclerView=view.recycler_stock
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(requireContext())

        stockViewModel=ViewModelProvider(this).get(StockViewModel::class.java)
        stockViewModel.allStock.observe(viewLifecycleOwner, Observer {stock ->
            adapter.setData(stock)
        })


        view.btn_New_Stock.setOnClickListener {
            findNavController().navigate(R.id.action_nav_stock_to_newStockFragment2)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
            deleteAllStock()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllStock() {
        val builder= AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            stockViewModel.deleteAll()
            Toast.makeText(requireContext(),"Successfully Removed All Stocks!",
                Toast.LENGTH_LONG).show()
        }
        builder.setNegativeButton("No"){_, _ ->

        }
        builder.setTitle("Delete All Stock List")
        builder.setMessage("Are you sure want to delete all stock?")
        builder.create().show()
    }

}