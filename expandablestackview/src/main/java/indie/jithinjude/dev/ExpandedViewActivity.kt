package indie.jithinjude.dev

import android.os.Bundle
import android.transition.Explode
import androidx.appcompat.app.AppCompatActivity

class ExpandedViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expanded_view)

        val fade = Explode()

        window.enterTransition = fade
        window.exitTransition = fade
    }
}