package indie.jithinjude.dev

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.transition.Explode
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.viewpager2.widget.ViewPager2
import indie.jithinjude.dev.databinding.LayoutExpandableStackViewBinding


/**
 * Created by <Jithin/Jude> on 29,June,2023
 */
class ExpandableStackView : FrameLayout {
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(
        context: Context, attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    lateinit var binding: LayoutExpandableStackViewBinding

    fun init() {
        binding = LayoutExpandableStackViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun prepareExpandableStackView(activity: Activity) {

        val stackItemList = mutableListOf<StackItemModel>()
        for (i in 1..10) {
            stackItemList.add(StackItemModel())
        }


        val fade = Explode()
        activity.window.enterTransition = fade
        activity.window.exitTransition = fade

        val expandableStackViewTapListener = object :
            ExpandableStackViewAdapter.ExpandableStackViewTapListener {
            override fun onTapExpandableStackView() {
                Log.d("TAG", "onTapExpandableStackView :=>")
                val intent = Intent(context, ExpandedViewActivity::class.java)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity,
                    binding.rvStackView,
                    ViewCompat.getTransitionName(binding.rvStackView)!!
                )
                context.startActivity(intent, options.toBundle())
            }
        }

        val adapter = ExpandableStackViewAdapter(stackItemList, expandableStackViewTapListener)
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