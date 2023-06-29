package indie.jithinjude.dev

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import indie.jithinjude.dev.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.esvLayout.prepareExpandableStackView(this)
    }
}