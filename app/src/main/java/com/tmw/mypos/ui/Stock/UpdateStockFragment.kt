package com.tmw.mypos.ui.Stock

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tmw.mypos.R
import com.tmw.mypos.model.Stock
import kotlinx.android.synthetic.main.fragment_update_stock.*
import kotlinx.android.synthetic.main.fragment_update_stock.view.*

class UpdateStockFragment : Fragment() {

    private val args by navArgs<UpdateStockFragmentArgs>()

    private lateinit var stockViewModel: StockViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_update_stock, container, false)

        stockViewModel=ViewModelProvider(this).get(StockViewModel::class.java)

        view.etd_update_code.setText(args.currentStock.code)
        view.etd_update_stock.setText(args.currentStock.name)
        view.etd_update_pur_price.setText(args.currentStock.purprice.toString())
        view.etd_update_sale_price.setText(args.currentStock.saleprice.toString())

        view.btn_Update.setOnClickListener {
            updateStock()
        }

        //Add Menu
        setHasOptionsMenu(true)

        return view
    }

    private fun updateStock() {
        val code = etd_update_code.text.toString()
        val name = etd_update_stock.text.toString()
        val pur_price = Integer.parseInt(etd_update_pur_price.text.toString())
        val sale_price = Integer.parseInt(etd_update_sale_price.text.toString())

        if (inputCheck(code, name, etd_update_pur_price.text, etd_update_sale_price.text)) {
            //Create Stock Object
            val updateStock= Stock(args.currentStock.cid, code,name,pur_price,sale_price)
            //Update Current Stock
            stockViewModel.update(updateStock)
            Toast.makeText(requireContext(),"Update Successfully!",Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.action_updateStockFragment_to_nav_stock)
        }else{
            Toast.makeText(requireContext(),"Please fill out all fields",Toast.LENGTH_LONG).show()
        }
    }
    private fun inputCheck(code:String, name:String, pur_price: Editable, sale_price: Editable):Boolean{
        return !(TextUtils.isEmpty(code) && TextUtils.isEmpty(name) && pur_price.isEmpty() && sale_price.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder=AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            stockViewModel.deleteStock(args.currentStock)
            Toast.makeText(requireContext(),"Successfully Removed: ${args.currentStock.code}",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateStockFragment_to_nav_stock)
        }
        builder.setNegativeButton("No"){_, _ ->

        }
        builder.setTitle("Delete ${args.currentStock.code}?")
        builder.setMessage("Are you sure want to delete ${args.currentStock.code}?")
        builder.create().show()
    }

}