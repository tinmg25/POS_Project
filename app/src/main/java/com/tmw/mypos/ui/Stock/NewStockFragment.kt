package com.tmw.mypos.ui.Stock

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tmw.mypos.R
import com.tmw.mypos.model.Stock
import kotlinx.android.synthetic.main.fragment_new_stock.*
import kotlinx.android.synthetic.main.fragment_new_stock.view.*


class NewStockFragment : Fragment() {
    private lateinit var stockViewModel: StockViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_new_stock, container, false)

        stockViewModel=ViewModelProvider(this).get(StockViewModel::class.java)
        view.btn_Save.setOnClickListener{
            insertdataintoDatabase()
        }
        return view
    }

    private fun insertdataintoDatabase() {
        val code=etd_code.text.toString()
        val name=etd_stock.text.toString()
        val pur_price=etd_pur_price.text
        val sale_price=etd_sale_price.text

        if(inputCheck(code,name,pur_price,sale_price)){
            val stock= Stock(0,code,name,Integer.parseInt(pur_price.toString()),Integer.parseInt(sale_price.toString()))
            stockViewModel.insert(stock)
            Toast.makeText(context,"Successful Addded!",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.nav_stock)
        }else{
            Toast.makeText(context,"Please Fill out all fields!",Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(code:String, name:String, pur_price: Editable, sale_price:Editable):Boolean{
        return !(TextUtils.isEmpty(code) && TextUtils.isEmpty(name) && pur_price.isEmpty() && sale_price.isEmpty())
    }
}