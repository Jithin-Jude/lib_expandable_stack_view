package indie.jithinjude.dev

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import indie.jithinjude.dev.databinding.StackItemLayoutBinding

/**
 * Created by <Jithin/Jude> on 29,June,2023
 */
class ExpandableStackViewAdapter(
    private val dataList: List<StackItemModel>,
    private val expandableStackViewTapListener: ExpandableStackViewTapListener,
    private val mActivity: Activity
) :
    RecyclerView.Adapter<ExpandableStackViewAdapter.ExpandableStackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpandableStackViewHolder {
        val binding =
            StackItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpandableStackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpandableStackViewHolder, position: Int) {
        val data = dataList[position]
        holder.bindData(data)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ExpandableStackViewHolder(private val binding: StackItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: StackItemModel) {
            binding.cvRoundedCornerView.transitionName =
                "${ExpandableStackView.KEY_SHARED_ELEMENT_ITEM}${adapterPosition}"
            binding.button.transitionName =
                "${ExpandableStackView.KEY_SHARED_ELEMENT_BUTTON}${adapterPosition}"


//            val transitionSet = TransitionSet()
//
//            val changeBounds = ChangeBounds()
//            changeBounds.addTarget(binding.button)
//
//            val changeTransform = ChangeTransform()
//            changeTransform.addTarget(binding.button)
//
//            val changeImageTransform = ChangeImageTransform()
//            changeImageTransform.addTarget(binding.button)
//
//            val changeClipBounds = ChangeClipBounds()
//            changeClipBounds.addTarget(binding.button)
//
//            transitionSet.addTransition(changeBounds)
//            transitionSet.addTransition(changeTransform)
//            transitionSet.addTransition(changeImageTransform)
//            transitionSet.addTransition(changeClipBounds)
//
//            transitionSet.excludeTarget(binding.button, true)
//            mActivity.window.sharedElementEnterTransition = transitionSet

            binding.tvTitle.text = data.title
            binding.tvSubtitle.text = data.subtitle
            binding.button.text = "${data.btc}BTC"

            Glide.with(binding.root.context)
                .load(data.bgImageUrl)
                .placeholder(R.drawable.bg_img)
                .into(binding.ivBgImage)

            binding.root.setOnClickListener {
                expandableStackViewTapListener.onTapExpandableStackView(data, binding)
            }
        }
    }

    interface ExpandableStackViewTapListener {
        fun onTapExpandableStackView(item: StackItemModel, binding: StackItemLayoutBinding)
    }
}