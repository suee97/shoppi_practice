package com.shoppi.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class CategoryFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 액티비티가 inflate된 이후에 fragment를 인플레이트 해야하므로 그 시점을 뒤로 늦추는 것이
        // attachToRoot false
        return inflater.inflate(R.layout.fragment_category, container, false)
    }
}