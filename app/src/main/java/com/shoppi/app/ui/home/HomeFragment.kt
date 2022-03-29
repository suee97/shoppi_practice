package com.shoppi.app.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.shoppi.app.*
import com.shoppi.app.databinding.FragmentHomeBinding
import com.shoppi.app.model.Banner
import com.shoppi.app.ui.common.ViewModelFactory

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    // onCreateView에서 생성된 view가 전달됨
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // fragment home binding 클래스가 생성되면서 뷰에 대한 참조를 얻어올 필요가 없음
//        val toolbarTitle = view.findViewById<TextView>(R.id.toolbar_home_title)
//        val toolbarIcon = view.findViewById<ImageView>(R.id.toolbar_home_icon)
//        val viewpager = view.findViewById<ViewPager2>(R.id.viewpager_home_banner)
//        val viewpagerIndicator = view.findViewById<TabLayout>(R.id.viewpager_home_banner_indicator)

//        if (!homeJsonString.isNullOrEmpty()) {
//            val gson = Gson()
//            // 첫 번째는 json format을 문자열 타입으로 변환한 homeJsonString
//            // 두 번째는 어떠한 객체로 변경을 할 것인지 클래스를 전달
//            val homeData = gson.fromJson(homeJsonString, HomeData::class.java)


        // Gson 라이브러리를 사용하지 않을 때 1
//            val jsonObject = JSONObject(homeJsonString)
//            val title = jsonObject.getJSONObject("title")
//            val text = title.getString("text")
//            val iconUrl = title.getString("icon_url")


        // Gson 라이브러리를 사용하지 않을 때 2
//            val topBanners = jsonObject.getJSONArray("top_banners")
//            val size = topBanners.length()
//            for (index in 0 until size) {
//                val bannerObject = topBanners.getJSONObject(index)
//                val backgroundImageUrl = bannerObject.getString("background_image_url")
//                val badgeObject = bannerObject.getJSONObject("badge")
//                val badgeLabel = badgeObject.getString("label")
//                val badgeBackgroundColor = badgeObject.getString("background_color")
//                val bannerBadge = BannerBadge(badgeLabel, badgeBackgroundColor)
//
//                val banner = Banner(
//                    backgroundImageUrl,
//                    bannerBadge,
//                    bannerLabel,
//                    bannerProductDetail
//                )
//            }
//        }

        binding.lifecycleOwner = viewLifecycleOwner // 필수

        setToolbar()
        setTopBanners()
    }

    private fun setToolbar() {
        // title 데이터가 변경되면 아래 블록이 호출됨
        viewModel.title.observe(viewLifecycleOwner, { title ->
            binding.title = title
        })
    }

    private fun setTopBanners() {
        with(binding.viewpagerHomeBanner) {
            adapter = HomeBannerAdapter().apply {
                viewModel.topBanners.observe(viewLifecycleOwner, {
                    submitList(it)
                })
            }
            val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)
            val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin) // 픽셀로 반환

            val screenWidth = resources.displayMetrics.widthPixels
            val offset = screenWidth - pageWidth - pageMargin

            offscreenPageLimit = 3
            setPageTransformer { page, position ->
                page.translationX = position * -offset
            }
            TabLayoutMediator(
                binding.viewpagerHomeBannerIndicator, this
            ) { tab, position ->

            }.attach()
        }
    }


}