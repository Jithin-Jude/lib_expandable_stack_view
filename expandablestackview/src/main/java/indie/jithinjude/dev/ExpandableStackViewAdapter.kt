package indie.jithinjude.dev

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import indie.jithinjude.dev.databinding.StackItemLayoutBinding

/**
 * Created by <Jithin/Jude> on 29,June,2023
 */
class ExpandableStackViewAdapter(
    private val dataList: List<StackItemModel>,
    private val expandableStackViewTapListener: ExpandableStackViewTapListener
) :
    RecyclerView.Adapter<ExpandableStackViewAdapter.ExpandableStackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpandableStackViewHolder {
        val binding =
            StackItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpandableStackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpandableStackViewHolder, position: Int) {
        val data = dataList[position]
        holder.bindTransition()
        holder.bindData(data)
        holder.bindTapListeners(data)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ExpandableStackViewHolder(private val binding: StackItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTransition() {
            binding.cvRoundedCornerView.transitionName =
                "${Constants.KEY_SHARED_ELEMENT_ITEM}${adapterPosition}"
            binding.button.transitionName =
                "${Constants.KEY_SHARED_ELEMENT_BUTTON}${adapterPosition}"
            binding.tvTitle.transitionName =
                "${Constants.KEY_SHARED_ELEMENT_TITLE}${adapterPosition}"
        }

        fun bindData(data: StackItemModel) {

            binding.tvTitle.text = data.title
            binding.tvSubtitle.text = data.subtitle
            binding.button.text = "${data.btc}BTC"

            Glide.with(binding.root.context)
                .load(data.bgImageUrl)
                .placeholder(R.drawable.bg_img)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.ivBgImage)
        }

        fun bindTapListeners(data: StackItemModel) {
            binding.root.setOnClickListener {
                expandableStackViewTapListener.onTapExpandableStackView(data, binding)
            }
        }
    }

    interface ExpandableStackViewTapListener {
        fun onTapExpandableStackView(item: StackItemModel, itemBinding: StackItemLayoutBinding)
        fun onBindStackViewItem(itemBinding: StackItemLayoutBinding)
    }
}