package indie.jithinjude.dev.stack_layers

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import indie.jithinjude.dev.ExpandedViewActivity
import indie.jithinjude.dev.R
import indie.jithinjude.dev.databinding.BottomSheetPhaseThreeBinding


/**
 * Created by <Jithin/Jude> on 01,July,2023
 */
class PhaseThreeBottomSheet(
    val mActivity: ExpandedViewActivity,
    val dismissCallback: ExpandedViewActivity.StackDismissListener
) : BottomSheetDialogFragment() {

    lateinit var binding: BottomSheetPhaseThreeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            BottomSheetPhaseThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btmSheetLayout.setOnClickListener {
            binding.btnDone.visibility = View.VISIBLE

            val layoutParams = binding.btmSheetLayout.layoutParams
            val displayMetrics: DisplayMetrics = resources.displayMetrics
            val dpHeight = displayMetrics.heightPixels
            layoutParams.height = dpHeight
            binding.btmSheetLayout.layoutParams = layoutParams
        }
        binding.btnDone.setOnClickListener {
            dismiss()
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
        dismissCallback.onStackDismiss()
    }

    companion object {
        fun showPhaseThreeBottomSheet(
            supportFragmentManager: FragmentManager,
            activity: ExpandedViewActivity,
            dismissCallback: ExpandedViewActivity.StackDismissListener
        ) {
            val bottomSheet = PhaseThreeBottomSheet(activity, dismissCallback)
            bottomSheet.show(supportFragmentManager, "PhaseThreeBottomSheet")
        }
    }
}