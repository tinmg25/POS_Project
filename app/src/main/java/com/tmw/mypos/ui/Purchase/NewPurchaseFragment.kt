package com.tmw.mypos.ui.Purchase

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tmw.mypos.R
import com.tmw.mypos.model.Purchase
import com.tmw.mypos.model.Sale
import com.tmw.mypos.model.Supplier
import com.tmw.mypos.ui.Supplier.SupplierViewModel
import kotlinx.android.synthetic.main.fragment_new_purchase.*
import kotlinx.android.synthetic.main.fragment_new_purchase.etdPrice
import kotlinx.android.synthetic.main.fragment_new_purchase.etdQty
import kotlinx.android.synthetic.main.fragment_new_purchase.etdRemark
import kotlinx.android.synthetic.main.fragment_new_purchase.etd_date
import kotlinx.android.synthetic.main.fragment_new_purchase.spinner_item
import kotlinx.android.synthetic.main.fragment_new_purchase.view.*
import kotlinx.android.synthetic.main.fragment_new_sale.*

class NewPurchaseFragment : Fragment() {

    private var supplierArray: List<Supplier> = ArrayList()

    private lateinit var purchaseViewModel: PurchaseViewModel
    private lateinit var supplierViewModel: SupplierViewModel

    lateinit var supplierSpinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_new_purchase, container, false)

        supplierSpinner=view.findViewById(R.id.spinner_supplier)

        //observeViewModel()

        view.btn_Save_Purchase.setOnClickListener {
            insertdataintoDatabase()
        }

        return view
    }
//
//    private fun observeViewModel() {
//        supplierViewModel = ViewModelProvider(this).get(SupplierViewModel::class.java)
//        supplierViewModel.supplierList.observe(
//            viewLifecycleOwner, Observer {supplier->
//                supplierArray=supplier
//
//                supplierSpinner.adapter=context?.let{ArrayAdapter(it,R.layout.support_simple_spinner_dropdown_item,supplierArray)}
//            }
//        )
//    }

    private fun insertdataintoDatabase() {
        val date = etd_date.text.toString()
        val supplier = spinner_supplier.selectedItem.toString()
        val code = spinner_item.selectedItem.toString()
        val qty = Integer.parseInt(etdQty.text.toString())
        val price = etdPrice.text.toString().toDouble()
        val amount = qty * price
        val remark = etdRemark.text.toString()

        if (inputCheck(date, supplier, code, qty, price)) {
            val purchase = Purchase(0, date, supplier, code, qty, price, amount, remark)
            purchaseViewModel.insertPurchase(purchase)
            Toast.makeText(context, "New Purchase Voucher Confirm", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.nav_purchase)
        } else {
            Toast.makeText(context, "Please Fill All Fields", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(
        date: String,
        supplier: String,
        code: String,
        qty: Int,
        price: Double
    ): Boolean {
        return !(TextUtils.isEmpty(date) && TextUtils.isEmpty(supplier) && TextUtils.isEmpty(code) && TextUtils.isEmpty(
            qty.toString()
        ) && TextUtils.isEmpty(price.toString()))

    }
}
