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
                bgImageUrl = "https://w0.peakpx.com/wallpaper/116/427/HD-wallpaper-iphone-14-nature-in-summer-thumbnail.jpg",
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
                bgImageUrl = "https://i.pinimg.com/550x/e3/a8/72/e3a87289806e46442ed7791899eeb627.jpg",
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
                bgImageUrl = "https://e0.pxfuel.com/wallpapers/281/308/desktop-wallpaper-latest-nature-iphone-cool-nature.jpg",
                weatherIcon = R.drawable.ic_rain,
                temperature = "30",
                weatherStatus = "Heavy Rain",
                placeDescription = "Thiruvananthapuram (or Trivandrum) is the capital of the southern Indian state of Kerala."
            )
        )

        return stackItemList
    }
}