package indie.jithinjude.dev

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
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

        binding.ivExpandedImage.transitionName =
            "${ExpandableStackView.KEY_SHARED_ELEMENT_ITEM}$currentItem"
        binding.button.transitionName =
            "${ExpandableStackView.KEY_SHARED_ELEMENT_BUTTON}$currentItem"
        binding.tvTitle.transitionName =
            "${ExpandableStackView.KEY_SHARED_ELEMENT_TITLE}$currentItem"

//        val transitionSet = TransitionSet()
//        val changeBounds = ChangeBounds()
//        changeBounds.addTarget(binding.button)
//        val changeTransform = ChangeTransform()
//        changeTransform.addTarget(binding.button)
//        val changeImageTransform = ChangeImageTransform()
//        changeImageTransform.addTarget(binding.button)
//        val changeClipBounds = ChangeClipBounds()
//        changeClipBounds.addTarget(binding.button)
//        transitionSet.addTransition(changeBounds)
//        transitionSet.addTransition(changeTransform)
//        transitionSet.addTransition(changeImageTransform)
//        transitionSet.addTransition(changeClipBounds)
//        transitionSet.addTarget(binding.button)
//        window.sharedElementEnterTransition = transitionSet

        data?.let {
            binding.tvTitle.text = it.title
            binding.tvDescription.text = it.placeDescription
            binding.tvWeatherTemperature.text = "${it.temperature}\u2103"
            binding.tvStatus.text = it.weatherStatus

            Glide.with(binding.root.context)
                .load(it.bgImageUrl)
                .into(binding.ivExpandedImage)
        }

        val fadeInAnimation = AnimationUtils.loadAnimation(
            this,
            R.anim.fade_in
        )
        binding.layoutWeather.startAnimation(fadeInAnimation)
        binding.tvDescription.startAnimation(fadeInAnimation)
        binding.linearLayout.startAnimation(fadeInAnimation)
        binding.expandedViewOverLay.startAnimation(fadeInAnimation)

        binding.root.setOnClickListener {
            binding.btnText.visibility = View.GONE
            supportFinishAfterTransition()
        }
    }
}