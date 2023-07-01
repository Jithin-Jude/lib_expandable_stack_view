package indie.jithinjude.dev

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import indie.jithinjude.dev.databinding.BottomSheetPhaseOneBinding


/**
 * Created by <Jithin/Jude> on 01,July,2023
 */
class PhaseOneBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: BottomSheetPhaseOneBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            BottomSheetPhaseOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun showPhaseOneBottomSheet(supportFragmentManager: FragmentManager) {
            val bottomSheet = PhaseOneBottomSheet()
            bottomSheet.show(supportFragmentManager, "PhaseOneBottomSheet")
        }
    }
}