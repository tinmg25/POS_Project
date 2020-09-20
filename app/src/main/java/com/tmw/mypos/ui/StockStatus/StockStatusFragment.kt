package com.tmw.mypos.ui.StockStatus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tmw.mypos.R

class StockStatusFragment : Fragment() {

    private lateinit var stockstatusViewModel: StockStatusViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        stockstatusViewModel =
                ViewModelProviders.of(this).get(StockStatusViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_stock_status, container, false)
        val textView: TextView = root.findViewById(R.id.text_stockstatus)
        stockstatusViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}