package indie.jithinjude.dev

import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import indie.jithinjude.dev.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        setContentView(view)

        binding.esvLayout.prepareExpandableStackView(this, getDummyData())
    }

    private fun getDummyData(): MutableList<StackItemModel> {

        val stackItemList = mutableListOf<StackItemModel>()
        for (i in 1..10) {
            if (i % 2 == 0) {
                stackItemList.add(
                    StackItemModel(
                        title = "Santorini",
                        subtitle = "New Osogbq",
                        btc = 0.6,
                        bgImageUrl = "https://www.gannett-cdn.com/presto/2021/06/10/USAT/3d0222f3-5ccd-4e21-ab70-803ce1badd16-GettyImages-510967662.jpg?crop=2120%2C1193%2Cx0%2Cy211&width=1200",
                        weatherIcon = R.drawable.ic_rain,
                        temperature = "32",
                        weatherStatus = "Light Rain",
                        placeDescription = "\"Santorini\" is a popular tourist destination and a Greek island located in the southern Aegean Sea. It is part of the Cyclades archipelago and is known for its stunning natural beauty, picturesque villages, and unique architecture."
                    )
                )
            } else {
                stackItemList.add(
                    StackItemModel(
                        title = "Kochi",
                        subtitle = "Lorem ipsum",
                        btc = 0.6,
                        bgImageUrl = "https://images.newindianexpress.com/uploads/user/imagelibrary/2020/8/27/w900X450/kochi_EPS213.jpg?w=400&dpr=2.6",
                        weatherIcon = R.drawable.ic_rain,
                        temperature = "28",
                        weatherStatus = "Light Rain",
                        placeDescription = "Kochi (also known as Cochin) is a city in southwest India's coastal Kerala state."
                    )
                )
            }
        }
        return stackItemList
    }
}