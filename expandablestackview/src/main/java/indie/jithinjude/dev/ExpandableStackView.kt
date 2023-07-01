package indie.jithinjude.dev

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Matrix
import android.graphics.RectF
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.core.app.ActivityCompat.setExitSharedElementCallback
import androidx.core.app.ActivityOptionsCompat
import androidx.core.app.SharedElementCallback
import androidx.core.util.Pair
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import indie.jithinjude.dev.Constants.KEY_CURRENT_ITEM
import indie.jithinjude.dev.Constants.KEY_SELECTED_ITEM
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
    var mItemBinding: StackItemLayoutBinding? = null

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
                super.onSharedElementStart(
                    sharedElementNames,
                    sharedElements,
                    sharedElementSnapshots
                )
                mItemBinding?.layoutMinimizedVr?.visibility = View.INVISIBLE
                mItemBinding?.tvSubtitle?.visibility = View.INVISIBLE
            }

            override fun onSharedElementEnd(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                sharedElementSnapshots: MutableList<View>?
            ) {
                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots)

                val fadeInAnimation = AnimationUtils.loadAnimation(
                    context,
                    R.anim.fade_in
                )

                mItemBinding?.layoutMinimizedVr?.startAnimation(fadeInAnimation)
                mItemBinding?.tvSubtitle?.startAnimation(fadeInAnimation)
                mItemBinding?.viewOverLay?.startAnimation(fadeInAnimation)
            }

            override fun onRejectSharedElements(rejectedSharedElements: MutableList<View>?) {
                super.onRejectSharedElements(rejectedSharedElements)
            }

            override fun onMapSharedElements(
                names: MutableList<String>?,
                sharedElements: MutableMap<String, View>?
            ) {

            }

            override fun onCaptureSharedElementSnapshot(
                sharedElement: View?,
                viewToGlobalMatrix: Matrix?,
                screenBounds: RectF?
            ): Parcelable {
                return super.onCaptureSharedElementSnapshot(
                    sharedElement,
                    viewToGlobalMatrix,
                    screenBounds
                )
            }

            override fun onCreateSnapshotView(context: Context?, snapshot: Parcelable?): View {
                return super.onCreateSnapshotView(context, snapshot)
            }

            override fun onSharedElementsArrived(
                sharedElementNames: MutableList<String>?,
                sharedElements: MutableList<View>?,
                listener: OnSharedElementsReadyListener?
            ) {
                super.onSharedElementsArrived(sharedElementNames, sharedElements, listener)
            }
        }
        setExitSharedElementCallback(activity, sharedElementCallback)

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
                    "${Constants.KEY_SHARED_ELEMENT_ITEM}${binding.vpStackView.currentItem}"
                )

                val buttonView: Pair<View, String> = Pair(
                    itemBinding.button,
                    "${Constants.KEY_SHARED_ELEMENT_BUTTON}${binding.vpStackView.currentItem}"
                )

                val titleView: Pair<View, String> = Pair(
                    itemBinding.tvTitle,
                    "${Constants.KEY_SHARED_ELEMENT_TITLE}${binding.vpStackView.currentItem}"
                )

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity,
                    backgroundView,
                    buttonView,
                    titleView
                )

                intent.putExtra(KEY_SELECTED_ITEM, item)
                intent.putExtra(KEY_CURRENT_ITEM, binding.vpStackView.currentItem)
                context.startActivity(intent, options.toBundle())
            }

            override fun onBindStackViewItem(itemBinding: StackItemLayoutBinding) {
                mItemBinding = itemBinding
            }
        }

        val adapter =
            ExpandableStackViewAdapter(stackItemList, expandableStackViewTapListener)
        binding.vpStackView.adapter = adapter

        binding.vpStackView.offscreenPageLimit = 1
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
        }
        binding.vpStackView.setPageTransformer(pageTransformer)
        val itemDecoration = ItemSpacingDecoration(
            context,
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.vpStackView.addItemDecoration(itemDecoration)
        binding.vpStackView.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mItemBinding?.layoutMinimizedVr?.visibility = View.VISIBLE
                mItemBinding?.tvSubtitle?.visibility = View.VISIBLE
            }
        })

    }
}