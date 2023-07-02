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
import indie.jithinjude.dev.databinding.BottomSheetPhaseTwoBinding


/**
 * Created by <Jithin/Jude> on 01,July,2023
 */
class PhaseTwoBottomSheet(
    val mActivity: ExpandedViewActivity,
    val dismissCallback: ExpandedViewActivity.StackDismissListener
) : BottomSheetDialogFragment() {

    lateinit var binding: BottomSheetPhaseTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            BottomSheetPhaseTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareAnimations()
        prepareTapListeners()
    }

    fun prepareAnimations() {
        val animationMoveToCenterAndFadeIn = AnimationUtils.loadAnimation(
            binding.layoutContent.context,
            R.anim.move_to_center_and_fade_in
        )

        val animationMoveToCenter = AnimationUtils.loadAnimation(
            binding.phaseThreeBtmSheetPop.context,
            R.anim.move_to_center_and_fade_in
        )

        Handler(Looper.getMainLooper()).postDelayed({
            binding.layoutContent.startAnimation(animationMoveToCenterAndFadeIn)
            binding.layoutContent.visibility = View.VISIBLE
        }, 100)

        Handler(Looper.getMainLooper()).postDelayed({
            binding.phaseThreeBtmSheetPop.startAnimation(animationMoveToCenter)
            binding.phaseThreeBtmSheetPop.visibility = View.VISIBLE
        }, 300)
    }

    fun prepareTapListeners() {
        binding.phaseThreeBtmSheetPop.setOnClickListener {
            PhaseThreeBottomSheet.showPhaseThreeBottomSheet(
                childFragmentManager,
                mActivity,
                stackDismissCallback
            )
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.isDraggable = false
        return dialog
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
        fun showPhaseTwoBottomSheet(
            supportFragmentManager: FragmentManager,
            activity: ExpandedViewActivity,
            dismissCallback: ExpandedViewActivity.StackDismissListener
        ) {
            val bottomSheet = PhaseTwoBottomSheet(activity, dismissCallback)
            bottomSheet.show(supportFragmentManager, "PhaseTwoBottomSheet")
        }
    }
}