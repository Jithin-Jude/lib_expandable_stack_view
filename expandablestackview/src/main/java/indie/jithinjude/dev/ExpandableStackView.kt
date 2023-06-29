package indie.jithinjude.dev

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.viewpager2.widget.ViewPager2
import indie.jithinjude.dev.databinding.LayoutExpandableStackViewBinding


/**
 * Created by <Jithin/Jude> on 29,June,2023
 */
class ExpandableStackView : FrameLayout {
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {
        val binding =
            LayoutExpandableStackViewBinding.inflate(LayoutInflater.from(context), this, true)

        val stackItemList = mutableListOf<StackItemModel>()
        for (i in 1..10) {
            stackItemList.add(StackItemModel())
        }

        val adapter = ExpandableStackViewAdapter(stackItemList)
        binding.rvStackView.adapter = adapter

        binding.rvStackView.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
        }
        binding.rvStackView.setPageTransformer(pageTransformer)

        val itemDecoration = ItemSpacingDecoration(
            context,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.rvStackView.addItemDecoration(itemDecoration)
    }
}