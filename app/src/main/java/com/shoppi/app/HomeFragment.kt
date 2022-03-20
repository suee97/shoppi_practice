package com.shoppi.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class HomeFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    // onCreateView에서 생성된 view가 전달됨
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.btn_enter_product_detail)
        button.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.add(R.id.container_main, ProductDetailFragment())
            transaction.commit()
        }
    }
}


/*
Fragment에도 life cycle이 존재하는 이유
Fragment는 activity에서 inflate 시키는 것이므로 액티비티의 상태가 변함에 따라 프래그먼트의 상태가 변하기 때문
프래그먼트도 생성~소멸까지 상태가 변할 때 이를 알려주는 콜백이 존재

Create단계에서 호출되는 콜백들은 fragment를 fragment manager에 추가할 때 호출되는 것
액티비티에서 onStart onResume onPause onStop이 호출될 때 fragment에서도 동일한 콜백이 호출
onSavedInstanceState(): 화면이 소멸되기 전에 화면을 보관할 때 필요한 데이터를 저장하는 단계
이것이 onViewStateRestored() 콜백으로 전달이 되고 이 데이터를 기반으로 뷰를 보관
onCreateView <-> onDestroyView

스플래쉬 화면은 api31/api30이하 다름
 */