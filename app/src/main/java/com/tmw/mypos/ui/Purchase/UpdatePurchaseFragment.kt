package com.tmw.mypos.ui.Purchase

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
import com.tmw.mypos.model.Purchase
import com.tmw.mypos.model.Sale
import kotlinx.android.synthetic.main.fragment_new_sale.*
import kotlinx.android.synthetic.main.fragment_new_sale.etdRemark
import kotlinx.android.synthetic.main.fragment_new_sale.spinner_item
import kotlinx.android.synthetic.main.fragment_update_purchase.*
import kotlinx.android.synthetic.main.fragment_update_purchase.view.*


class UpdatePurchaseFragment : Fragment() {

    private val args by navArgs<UpdatePurchaseFragmentArgs>()

    private lateinit var purchaseViewModel: PurchaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_update_purchase, container, false)

        purchaseViewModel=ViewModelProvider(this).get(PurchaseViewModel::class.java)

        view.etd_update_date.setText(args.currentPurchase.date)

        view.btn_Update_Purchase.setOnClickListener {
            updatePurchase()
        }

        setHasOptionsMenu(true)
        return view
    }

    private fun updatePurchase() {
        val date=etd_date.text.toString()
        val supplier=spinner_supplier.selectedItem.toString()
        val code=spinner_item.selectedItem.toString()
        val qty=Integer.parseInt(etdQty.text.toString())
        val price=etdPrice.text.toString().toDouble()
        val amount=qty * price
        val remark=etdRemark.text.toString()

        if(inputCheck(date,supplier,code,qty,price)){
            val purchase= Purchase(0,date,supplier,code,qty,price,amount,remark)
            purchaseViewModel.updatePurchase(purchase)
            Toast.makeText(context,"Updated Purchase Voucher", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.nav_purchase)
        }else{
            Toast.makeText(context,"Please Fill All Fields", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(date:String,supplier: String, code:String,qty:Int,price:Double):Boolean{
        return !(TextUtils.isEmpty(date) && TextUtils.isEmpty(supplier) && TextUtils.isEmpty(code) && TextUtils.isEmpty(qty.toString()) && TextUtils.isEmpty(price.toString()))

    }

}