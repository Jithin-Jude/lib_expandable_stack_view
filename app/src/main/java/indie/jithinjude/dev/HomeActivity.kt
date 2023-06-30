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

        binding.esvLayout.prepareExpandableStackView(this, getDummyData())
    }

    private fun getDummyData(): MutableList<StackItemModel> {

        val stackItemList = mutableListOf<StackItemModel>()
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

        stackItemList.add(
            StackItemModel(
                title = "Kochi",
                subtitle = "Fort Kochi",
                btc = 0.5,
                bgImageUrl = "https://raw.githubusercontent.com/Jithin-Jude/temp_host/main/images/bg_img_1.jpeg",
                weatherIcon = R.drawable.ic_rain,
                temperature = "28",
                weatherStatus = "Light Rain",
                placeDescription = "Kochi (also known as Cochin) is a city in southwest India's coastal Kerala state."
            )
        )
        stackItemList.add(
            StackItemModel(
                title = "Trivandrum",
                subtitle = "Trivian",
                btc = 0.3,
                bgImageUrl = "https://www.ekeralatourism.net/wp-content/uploads/2019/01/places-to-visit.jpg",
                weatherIcon = R.drawable.ic_rain,
                temperature = "30",
                weatherStatus = "Heavy Rain",
                placeDescription = "Thiruvananthapuram (or Trivandrum) is the capital of the southern Indian state of Kerala."
            )
        )

        return stackItemList
    }
}