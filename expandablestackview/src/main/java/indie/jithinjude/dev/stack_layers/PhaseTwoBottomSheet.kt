package indie.jithinjude.dev.stack_layers

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import indie.jithinjude.dev.R
import indie.jithinjude.dev.databinding.BottomSheetPhaseTwoBinding


/**
 * Created by <Jithin/Jude> on 01,July,2023
 */
class PhaseTwoBottomSheet : BottomSheetDialogFragment() {

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


        binding.phaseTwoBtmSheetPop.setOnClickListener {
            PhaseThreeBottomSheet.showPhaseThreeBottomSheet(childFragmentManager)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        return dialog
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
    }

    companion object {
        fun showPhaseTwoBottomSheet(supportFragmentManager: FragmentManager) {
            val bottomSheet = PhaseTwoBottomSheet()
            bottomSheet.show(supportFragmentManager, "PhaseTwoBottomSheet")
        }
    }
}