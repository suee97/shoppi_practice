package com.shoppi.app.ui.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shoppi.app.GlideApp
import com.shoppi.app.R
import com.shoppi.app.databinding.ItemHomeBannerBinding
import com.shoppi.app.model.Banner
import java.text.DecimalFormat
import kotlin.math.roundToInt

class HomeBannerAdapter :
    ListAdapter<Banner, HomeBannerAdapter.HomeBannerViewHolder>(BannerDiffCallback()) {

    private lateinit var binding: ItemHomeBannerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerViewHolder {
        binding = ItemHomeBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeBannerViewHolder(binding)
    }

    // onCreateViewHolder가 호출된 이후에 HomeBannerViewHolder가 잘 생성이 되면
    // holder가 인자로 전달되는 것 -> holder가 잘 생성이 되고 난 이후에 holder의 데이터를 바인딩하는 것
    override fun onBindViewHolder(holder: HomeBannerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    // 여기서의 view는 홈 배너에서 inflate시킬 레이아웃을 의미
    class HomeBannerViewHolder(private val binding: ItemHomeBannerBinding) : RecyclerView.ViewHolder(binding.root) {

        // 데이터를 바인딩 하는 것
        fun bind(banner: Banner) {
            binding.banner = banner
            binding.executePendingBindings()
        }

//        // GlideApp.with 함수가 중복되기 때문에 method로 추출
//        private fun loadImage(urlString: String, imageView: ImageView) {
//            GlideApp.with(itemView)
//                .load(urlString)
//                .into(imageView)
//        }
    }
}


/*
이렇게 BannerDiffCallback을 통해 두 객체를 비교하는 기준을 알려주게 되면
첫 번째 method가 호출되었을 때 두 객체의 productId값이 동일하지 않다면
두 객체는 다른 객체로 판단함
만약 두 객체의 productId가 동일하다면 두번째 method가 호출되어
나머지 프로퍼티까지 다 비교하게 됨
 */
class BannerDiffCallback : DiffUtil.ItemCallback<Banner>() {
    // 식별자를 알려주어야 함
    override fun areItemsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem.productDetail.productId == newItem.productDetail.productId
    }

    // 다른 프로퍼티의 값도 모두 동일한지 확인
    override fun areContentsTheSame(oldItem: Banner, newItem: Banner): Boolean {
        return oldItem == newItem
    }

}