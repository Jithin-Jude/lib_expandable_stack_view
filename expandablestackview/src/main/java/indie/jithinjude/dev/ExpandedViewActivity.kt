package indie.jithinjude.dev

import android.os.Bundle
import android.transition.Fade
import android.view.Window
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
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        setContentView(view)

        val data = intent.getSerializableExtra(KEY_SELECTED_ITEM) as? StackItemModel
        val currentItem = intent.getIntExtra(KEY_CURRENT_ITEM, 0)

        val rootView = binding.root
        rootView.transitionName = "shared_element_$currentItem"

        Glide.with(binding.root.context)
            .load(data?.bgImageUrl)
            .placeholder(R.drawable.bg_img)
            .into(binding.ivExpandedImage)

        val fade = Fade()

        window.enterTransition = fade
        window.exitTransition = fade
    }
}