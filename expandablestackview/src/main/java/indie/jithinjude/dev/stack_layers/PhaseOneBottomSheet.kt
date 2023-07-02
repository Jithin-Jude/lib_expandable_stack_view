package indie.jithinjude.dev.stack_layers

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import indie.jithinjude.dev.ExpandedViewActivity
import indie.jithinjude.dev.R
import indie.jithinjude.dev.databinding.BottomSheetPhaseOneBinding


/**
 * Created by <Jithin/Jude> on 01,July,2023
 */
class PhaseOneBottomSheet(
    val mActivity: ExpandedViewActivity,
    val dismissCallback: ExpandedViewActivity.StackDismissListener
) : BottomSheetDialogFragment() {

    lateinit var binding: BottomSheetPhaseOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            BottomSheetPhaseOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareAnimations()
        prepareTapListeners()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.isDraggable = false
        return dialog
    }

    fun prepareAnimations() {
        val animationMoveUpAndFadeOut = AnimationUtils.loadAnimation(
            binding.loadingLocation.context,
            R.anim.move_up_and_fade_out
        )
        val animationMoveToCenterAndFadeIn = AnimationUtils.loadAnimation(
            binding.layoutContent.context,
            R.anim.move_to_center_and_fade_in
        )

        val animationMoveToCenter = AnimationUtils.loadAnimation(
            binding.phaseTwoBtmSheetPop.context,
            R.anim.move_to_center_and_fade_in
        )

        Handler(Looper.getMainLooper()).postDelayed({
            binding.loadingLocation.startAnimation(animationMoveUpAndFadeOut)
            binding.layoutContent.startAnimation(animationMoveToCenterAndFadeIn)
        }, 1000)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.phaseTwoBtmSheetPop.startAnimation(animationMoveToCenter)
            binding.phaseTwoBtmSheetPop.visibility = View.VISIBLE
        }, 1200)
    }

    fun prepareTapListeners() {
        binding.phaseTwoBtmSheetPop.setOnClickListener {
            PhaseTwoBottomSheet.showPhaseTwoBottomSheet(
                childFragmentManager,
                mActivity,
                stackDismissCallback
            )
        }
    }

    val stackDismissCallback = object : ExpandedViewActivity.StackDismissListener {
        override fun onStackDismiss(dismissAll: Boolean) {
            if (dismissAll) {
                dismiss()
                dismissCallback.onStackDismiss(dismissAll)
            }
        }
    }

    companion object {
        fun showPhaseOneBottomSheet(
            supportFragmentManager: FragmentManager,
            activity: ExpandedViewActivity,
            dismissCallback: ExpandedViewActivity.StackDismissListener
        ) {
            val bottomSheet = PhaseOneBottomSheet(activity, dismissCallback)
            bottomSheet.show(supportFragmentManager, "PhaseOneBottomSheet")
        }
    }
}