package com.tmw.mypos.ui.Sale

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tmw.mypos.R
import com.tmw.mypos.model.Sale
import kotlinx.android.synthetic.main.fragment_new_sale.*
import kotlinx.android.synthetic.main.fragment_update_customer.view.*
import kotlinx.android.synthetic.main.fragment_update_sale.view.*

class UpdateSaleFragment : Fragment() {

    private val args by navArgs<UpdateSaleFragmentArgs>()

    private lateinit var saleViewModel: SaleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_update_sale, container, false)

        saleViewModel=ViewModelProvider(this).get(SaleViewModel::class.java)

        view.etd_update_date.setText(args.currentSale.date)


        view.btn_Update_Sale.setOnClickListener {
            updateSale()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateSale() {
        val date=etd_date.text.toString()
        val customer=spinner_customer.selectedItem.toString()
        val code=spinner_item.selectedItem.toString()
        val qty=Integer.parseInt(etdQty.text.toString())
        val price=etdPrice.text.toString().toDouble()
        val amount=qty * price
        val remark=etdRemark.text.toString()

        if(inputCheck(date,customer,code,qty,price)){
            val sale= Sale(0,date,customer,code,qty,price,amount,remark)
            saleViewModel.updateSale(sale)
            Toast.makeText(context,"Update Sale Voucher", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.nav_sale)
        }else{
            Toast.makeText(context,"Please Fill All Fields", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(date:String,customer: String, code:String,qty:Int,price:Double):Boolean{
        return !(TextUtils.isEmpty(date) && TextUtils.isEmpty(customer) && TextUtils.isEmpty(code) && TextUtils.isEmpty(qty.toString()) && TextUtils.isEmpty(price.toString()))

    }

}