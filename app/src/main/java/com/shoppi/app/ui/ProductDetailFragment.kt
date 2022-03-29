package com.shoppi.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shoppi.app.R

class ProductDetailFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 액티비티가 생성된 이후에 뷰를 inflate 해야하므로 false
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }
}