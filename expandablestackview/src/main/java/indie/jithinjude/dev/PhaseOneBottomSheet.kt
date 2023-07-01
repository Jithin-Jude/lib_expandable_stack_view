package indie.jithinjude.dev

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 * Created by <Jithin/Jude> on 01,July,2023
 */
class PhaseOneBottomSheet : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_phase_one, container, false)
    }

    companion object {
        fun showPhaseOneBottomSheet(supportFragmentManager: FragmentManager) {
            val bottomSheet = PhaseOneBottomSheet()
            bottomSheet.show(supportFragmentManager, "PhaseOneBottomSheet")
        }
    }
}