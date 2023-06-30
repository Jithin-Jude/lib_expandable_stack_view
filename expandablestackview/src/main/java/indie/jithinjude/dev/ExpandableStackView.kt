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

    fun prepareExpandableStackView(activity: Activity, stackItemList: MutableList<StackItemModel>) {

//        val fade = Fade()
//        activity.window.enterTransition = fade
//        activity.window.exitTransition = fade

        val expandableStackViewTapListener = object :
            ExpandableStackViewAdapter.ExpandableStackViewTapListener {
            override fun onTapExpandableStackView(item: StackItemModel, view: View) {
                Log.d("TAG", "onTapExpandableStackView :=>")
                val intent = Intent(context, ExpandedViewActivity::class.java)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity,
                    view,
                    "$KEY_TRANSITION_NAME_PREFIX${binding.rvStackView.currentItem}"
                )
                intent.putExtra(KEY_SELECTED_ITEM, item)
                intent.putExtra(KEY_CURRENT_ITEM, binding.rvStackView.currentItem)
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


//        val callback = object : SharedElementCallback() {
//            override fun onMapSharedElements(
//                names: MutableList<String>,
//                sharedElements: MutableMap<String, View>
//            ) {
//                // Get the currently selected item in the ViewPager2
//                val currentItem = binding.rvStackView.currentItem
//
//                // Map the shared element name to the root view of the current fragment
//                val sharedElementView = binding.rvStackView.findViewWithTag<View>("shared_element_$currentItem")
//                sharedElements[names[0]] = sharedElementView
//            }
//        }
//        setEnterSharedElementCallback(activity, callback)
    }

    companion object {
        const val KEY_CURRENT_ITEM = "current_item"
        const val KEY_SELECTED_ITEM = "selected_item"
        const val KEY_TRANSITION_NAME_PREFIX = "shared_element_"
    }
}