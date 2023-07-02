package indie.jithinjude.dev.stack_layers

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import indie.jithinjude.dev.R
import indie.jithinjude.dev.databinding.BottomSheetPhaseOneBinding


/**
 * Created by <Jithin/Jude> on 01,July,2023
 */
class PhaseOneBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: BottomSheetPhaseOneBinding
    var phaseTwoBtmSheetActive = false

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

        val animationMoveUpAndFadeOut = AnimationUtils.loadAnimation(
            binding.loadingLocation.context,
            R.anim.move_up_and_fade_out
        )
        val animationMoveToCenterAndFadeIn = AnimationUtils.loadAnimation(
            binding.loadingLocation.context,
            R.anim.move_to_center_and_fade_in
        )
//        animationMoveUpAndFadeOut.setAnimationListener(object: Animation.AnimationListener{
//            override fun onAnimationStart(p0: Animation?) {
//
//            }
//
//            override fun onAnimationEnd(p0: Animation?) {
//
//            }
//
//            override fun onAnimationRepeat(p0: Animation?) {
//
//            }
//
//        })

        Handler(Looper.getMainLooper()).postDelayed({
            binding.loadingLocation.startAnimation(animationMoveUpAndFadeOut)
            binding.layoutContent.startAnimation(animationMoveToCenterAndFadeIn)
        }, 2000)

        binding.tvTimeBtn.setOnClickListener {
            binding.tvTimeBtn.setBackgroundColor(
                ContextCompat.getColor(
                    binding.tvTimeBtn.context,
                    R.color.pink
                )
            )
            binding.phaseTwoBtmSheetPop.setBackgroundResource(R.drawable.rounded_corner_bottom_sheet_pink)
            phaseTwoBtmSheetActive = true
        }

        binding.phaseTwoBtmSheetPop.setOnClickListener {
            if (phaseTwoBtmSheetActive) {
                PhaseTwoBottomSheet.showPhaseTwoBottomSheet(childFragmentManager)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.isDraggable = false
        return dialog
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }

    companion object {
        fun showPhaseOneBottomSheet(supportFragmentManager: FragmentManager) {
            val bottomSheet = PhaseOneBottomSheet()
            bottomSheet.show(supportFragmentManager, "PhaseOneBottomSheet")
        }
    }
}