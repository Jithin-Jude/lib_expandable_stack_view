package indie.jithinjude.dev

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import indie.jithinjude.dev.ExpandableStackView.Companion.KEY_CURRENT_ITEM
import indie.jithinjude.dev.ExpandableStackView.Companion.KEY_SELECTED_ITEM
import indie.jithinjude.dev.databinding.ActivityExpandedViewBinding

class ExpandedViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExpandedViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpandedViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        val transitionEnter: Transition =
//            TransitionInflater.from(this).inflateTransition(android.R.transition.slide_top)
//        val transitionExit: Transition =
//            TransitionInflater.from(this).inflateTransition(android.R.transition.slide_bottom)
//
//        window.enterTransition = transitionEnter
//        window.exitTransition = transitionExit

        val data = intent.getSerializableExtra(KEY_SELECTED_ITEM) as? StackItemModel
        val currentItem = intent.getIntExtra(KEY_CURRENT_ITEM, 0)

        binding.root.transitionName = "${ExpandableStackView.KEY_SHARED_ELEMENT_ITEM}$currentItem"

        data?.let {
            binding.tvTitle.text = it.title
            binding.tvDescription.text = it.placeDescription
            binding.tvWeatherTemperature.text = "${it.temperature}\u2103"
            binding.tvStatus.text = it.weatherStatus

            Glide.with(binding.root.context)
                .load(it.bgImageUrl)
                .into(binding.ivExpandedImage)
        }

        binding.root.setOnClickListener {
            supportFinishAfterTransition()
        }
    }
}