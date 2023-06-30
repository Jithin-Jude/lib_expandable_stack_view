package indie.jithinjude.dev

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.viewpager2.widget.ViewPager2
import indie.jithinjude.dev.databinding.LayoutExpandableStackViewBinding
import indie.jithinjude.dev.databinding.StackItemLayoutBinding


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

    fun prepareExpandableStackView(activity: Activity, stackItemList: MutableList<StackItemModel>) {

//        val transitionEnter: Transition =
//            TransitionInflater.from(context).inflateTransition(android.R.transition.slide_top)
//        val transitionExit: Transition =
//            TransitionInflater.from(context).inflateTransition(android.R.transition.slide_bottom)
//
//        activity.window.enterTransition = transitionEnter
//        activity.window.exitTransition = transitionExit

        val expandableStackViewTapListener = object :
            ExpandableStackViewAdapter.ExpandableStackViewTapListener {
            override fun onTapExpandableStackView(
                item: StackItemModel,
                itemBinding: StackItemLayoutBinding
            ) {
                Log.d("TAG", "onTapExpandableStackView :=>")
                val intent = Intent(context, ExpandedViewActivity::class.java)
//                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
//                    activity,
//                    view,
//                    "$KEY_SHARED_ELEMENT_ITEM${binding.rvStackView.currentItem}"
//                )

                val backgroundView: Pair<View, String> = Pair(
                    itemBinding.root,
                    "$KEY_SHARED_ELEMENT_ITEM${binding.rvStackView.currentItem}"
                )


                val buttonView: Pair<View, String> = Pair(
                    itemBinding.button,
                    "$KEY_SHARED_ELEMENT_BUTTON${binding.rvStackView.currentItem}"
                )

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity,
                    backgroundView,
                    buttonView
                )

                intent.putExtra(KEY_SELECTED_ITEM, item)
                intent.putExtra(KEY_CURRENT_ITEM, binding.rvStackView.currentItem)
                context.startActivity(intent, options.toBundle())
            }
        }

        val adapter =
            ExpandableStackViewAdapter(stackItemList, expandableStackViewTapListener, activity)
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

    companion object {
        const val KEY_CURRENT_ITEM = "current_item"
        const val KEY_SELECTED_ITEM = "selected_item"

        const val KEY_SHARED_ELEMENT_ITEM = "shared_element_item_"
        const val KEY_SHARED_ELEMENT_BUTTON = "shared_element_button_"
    }
}