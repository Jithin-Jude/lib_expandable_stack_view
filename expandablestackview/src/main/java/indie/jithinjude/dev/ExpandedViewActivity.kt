package indie.jithinjude.dev

import android.os.Bundle
import android.transition.Fade
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import indie.jithinjude.dev.ExpandableStackView.Companion.KEY_SELECTED_ITEM
import indie.jithinjude.dev.databinding.ActivityExpandedViewBinding

class ExpandedViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExpandedViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExpandedViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val data = intent.getSerializableExtra(KEY_SELECTED_ITEM) as? StackItemModel

        Glide.with(binding.root.context)
            .load(data?.bgImageUrl)
            .placeholder(R.drawable.bg_img)
            .into(binding.ivExpandedImage)

        val fade = Fade()

        window.enterTransition = fade
        window.exitTransition = fade
    }
}