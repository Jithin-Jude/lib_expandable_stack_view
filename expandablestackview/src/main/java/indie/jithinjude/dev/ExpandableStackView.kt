package indie.jithinjude.dev

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Matrix
import android.graphics.RectF
import android.os.Parcelable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.core.app.ActivityCompat.setExitSharedElementCallback
import androidx.core.app.ActivityOptionsCompat
import androidx.core.app.SharedElementCallback
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
    lateinit var mItemBinding: StackItemLayoutBinding

    fun init() {
        binding = LayoutExpandableStackViewBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun prepareExpandableStackView(activity: Activity, stackItemList: MutableList<StackItemModel>) {

        val sharedElementCallback = object : SharedElementCallback() {
            override fun onSharedElementStart(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                Log.d("TAG", "SharedElementCallback :=> onSharedElementStart")
                super.onSharedElementStart(
                    sharedElementNames,
                    sharedElements,
                    sharedElementSnapshots
                )
                mItemBinding.layoutMinimizedVr.visibility = View.INVISIBLE
                mItemBinding.tvSubtitle.visibility = View.INVISIBLE
            }

            override fun onSharedElementEnd(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots)
                Log.d("TAG", "SharedElementCallback :=> onSharedElementEnd")

                val fadeInAnimation = AnimationUtils.loadAnimation(
                    mItemBinding.layoutMinimizedVr.context,
                    R.anim.fade_in
                )

                mItemBinding.layoutMinimizedVr.startAnimation(fadeInAnimation)
                mItemBinding.tvSubtitle.startAnimation(fadeInAnimation)
                mItemBinding.viewOverLay.startAnimation(fadeInAnimation)
            }

            override fun onRejectSharedElements(rejectedSharedElements: MutableList<View>?) {
                Log.d("TAG", "SharedElementCallback :=> onRejectSharedElements")
                super.onRejectSharedElements(rejectedSharedElements)
            }

            override fun onMapSharedElements(
                names: MutableList<String>?,
                sharedElements: MutableMap<String, View>?
            ) {
                Log.d("TAG", "SharedElementCallback :=> onMapSharedElements")

                // Called when mapping shared elements between activities
                // You can customize the mapping of shared elements here

                // Example: Map a specific shared element by name to a different view
//                sharedElements?.put("my_shared_element", myCustomView)
            }

            override fun onCaptureSharedElementSnapshot(
                sharedElement: View?,
                viewToGlobalMatrix: Matrix?,
                screenBounds: RectF?
            ): Parcelable {
                Log.d("TAG", "SharedElementCallback :=> onCaptureSharedElementSnapshot")
                return super.onCaptureSharedElementSnapshot(
                    sharedElement,
                    viewToGlobalMatrix,
                    screenBounds
                )
            }

            override fun onCreateSnapshotView(context: Context?, snapshot: Parcelable?): View {
                Log.d("TAG", "SharedElementCallback :=> onCreateSnapshotView")
                return super.onCreateSnapshotView(context, snapshot)
            }

            override fun onSharedElementsArrived(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                listener: OnSharedElementsReadyListener?
            ) {
                Log.d("TAG", "SharedElementCallback :=> onSharedElementsArrived")
                super.onSharedElementsArrived(sharedElementNames, sharedElements, listener)
            }
        }

        setExitSharedElementCallback(activity, sharedElementCallback)
//        setEnterSharedElementCallback(activity, sharedElementCallback)

        val expandableStackViewTapListener = object :
            ExpandableStackViewAdapter.ExpandableStackViewTapListener {
            override fun onTapExpandableStackView(
                item: StackItemModel,
                itemBinding: StackItemLayoutBinding
            ) {
                mItemBinding = itemBinding

                val intent = Intent(context, ExpandedViewActivity::class.java)

                val backgroundView: Pair<View, String> = Pair(
                    itemBinding.cvRoundedCornerView,
                    "$KEY_SHARED_ELEMENT_ITEM${binding.rvStackView.currentItem}"
                )

                val buttonView: Pair<View, String> = Pair(
                    itemBinding.button,
                    "$KEY_SHARED_ELEMENT_BUTTON${binding.rvStackView.currentItem}"
                )

                val titleView: Pair<View, String> = Pair(
                    itemBinding.tvTitle,
                    "$KEY_SHARED_ELEMENT_TITLE${binding.rvStackView.currentItem}"
                )

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity,
                    backgroundView,
                    buttonView,
                    titleView
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
        const val KEY_SHARED_ELEMENT_TITLE = "shared_element_title_"
    }
}