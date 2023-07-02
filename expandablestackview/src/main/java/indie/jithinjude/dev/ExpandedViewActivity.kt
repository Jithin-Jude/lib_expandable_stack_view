package indie.jithinjude.dev

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import indie.jithinjude.dev.Constants.KEY_CURRENT_ITEM
import indie.jithinjude.dev.Constants.KEY_SELECTED_ITEM
import indie.jithinjude.dev.databinding.ActivityExpandedViewBinding
import indie.jithinjude.dev.stack_layers.PhaseOneBottomSheet

class ExpandedViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExpandedViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpandedViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val data = intent.getSerializableExtra(KEY_SELECTED_ITEM) as? StackItemModel
        val currentItem = intent.getIntExtra(KEY_CURRENT_ITEM, 0)

        prepareTransitions(currentItem)
        prepareViews(data)
        prepareAnimations()
        prepareTapListeners()
    }

    fun prepareTransitions(currentItem: Int) {
        binding.ivExpandedImage.transitionName =
            "${Constants.KEY_SHARED_ELEMENT_ITEM}$currentItem"
        binding.button.transitionName =
            "${Constants.KEY_SHARED_ELEMENT_BUTTON}$currentItem"
        binding.tvTitle.transitionName =
            "${Constants.KEY_SHARED_ELEMENT_TITLE}$currentItem"
    }

    fun prepareViews(data: StackItemModel?) {
        data?.let {
            binding.tvTitle.text = it.title
            binding.tvDescription.text = it.placeDescription
            binding.tvWeatherTemperature.text = "${it.temperature}\u2103"
            binding.tvStatus.text = it.weatherStatus

            Glide.with(binding.root.context)
                .load(it.bgImageUrl)
                .into(binding.ivExpandedImage)
        }
    }

    fun prepareAnimations() {

        val fadeInAnimation = AnimationUtils.loadAnimation(
            this,
            R.anim.fade_in
        )
        binding.layoutWeather.startAnimation(fadeInAnimation)
        binding.tvDescription.startAnimation(fadeInAnimation)
        binding.linearLayout.startAnimation(fadeInAnimation)
        binding.expandedViewOverLay.startAnimation(fadeInAnimation)
    }

    fun prepareTapListeners() {
        binding.root.setOnClickListener {
            binding.btnText.visibility = View.GONE
            supportFinishAfterTransition()
        }
        binding.button.setOnClickListener {
            PhaseOneBottomSheet.showPhaseOneBottomSheet(
                supportFragmentManager,
                this,
                stackDismissCallback
            )
        }
    }

    val stackDismissCallback = object : StackDismissListener {
        override fun onStackDismiss() {
            supportFinishAfterTransition()
        }
    }

    interface StackDismissListener {
        fun onStackDismiss()
    }
}