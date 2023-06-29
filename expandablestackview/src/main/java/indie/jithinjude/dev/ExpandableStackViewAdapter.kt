package indie.jithinjude.dev

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import indie.jithinjude.dev.databinding.StackItemLayoutBinding

/**
 * Created by <Jithin/Jude> on 29,June,2023
 */
class ExpandableStackViewAdapter(private val dataList: List<StackItemModel>) :
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
            binding.tvTitle.text = data.title
        }
    }
}