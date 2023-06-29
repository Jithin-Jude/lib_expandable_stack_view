package indie.jithinjude.dev

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.viewpager2.widget.ViewPager2
import indie.jithinjude.dev.databinding.LayoutExpandableStackViewBinding
import java.lang.Math.abs


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
//        View.inflate(context, R.layout.layout_expandable_stack_view, this)
        val binding =
            LayoutExpandableStackViewBinding.inflate(LayoutInflater.from(context), this, true)

        val stackItemList = mutableListOf<StackItemModel>()
        for (i in 1..10) {
            stackItemList.add(StackItemModel())
        }

        val adapter = ExpandableStackViewAdapter(stackItemList)
        binding.rvStackView.adapter = adapter

        // You need to retain one page on each side so that the next and previous items are visible
        binding.rvStackView.offscreenPageLimit = 1

// Add a PageTransformer that translates the next and previous items horizontally
// towards the center of the screen, which makes them visible
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            // Next line scales the item's height. You can remove it if you don't want this effect
            page.scaleY = 1 - (0.25f * abs(position))
            // If you want a fading effect uncomment the next line:
            // page.alpha = 0.25f + (1 - abs(position))
        }
        binding.rvStackView.setPageTransformer(pageTransformer)

// The ItemDecoration gives the current (centered) item horizontal margin so that
// it doesn't occupy the whole screen width. Without it the items overlap
        val itemDecoration = ItemSpacingDecoration(
            context,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.rvStackView.addItemDecoration(itemDecoration)


//        binding.rvStackView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//
//        val horizontalMargin = resources.getDimensionPixelSize(R.dimen.dimen_8dp)
//        val adjacentVisibleSize = resources.getDimensionPixelSize(R.dimen.dimen_16dp)
//        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing)
//        val itemDecoration = ItemSpacingDecoration(horizontalMargin = spacingInPixels, adjacentVisibleSize = spacingInPixels)
//        binding.rvStackView.addItemDecoration(itemDecoration)
//
//        val helper: SnapHelper = PagerSnapHelper()
//        helper.attachToRecyclerView(binding.rvStackView)

    }
}