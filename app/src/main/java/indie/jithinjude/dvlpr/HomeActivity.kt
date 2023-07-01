package indie.jithinjude.dvlpr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import indie.jithinjude.dev.R
import indie.jithinjude.dev.StackItemModel
import indie.jithinjude.dvlpr.databinding.ActivityMainBinding

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
                title = "Ophiuchi",
                subtitle = "Kaduna",
                btc = 0.4,
                bgImageUrl = "https://e0.pxfuel.com/wallpapers/380/774/desktop-wallpaper-cape-town-iphone-11-cape-town-south-africa-thumbnail.jpg",
                weatherIcon = R.drawable.ic_rain,
                temperature = "36",
                weatherStatus = "Light Rain",
                placeDescription = "Kaduna is the capital city of Kaduna State, and the former political capital of Northern Nigeria."
            )
        )
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
                title = "Marrakech",
                subtitle = "Neptune",
                btc = 2.8,
                bgImageUrl = "https://w0.peakpx.com/wallpaper/295/964/HD-wallpaper-morocco-marrakech-africa-city-mosque-red-city.jpg",
                weatherIcon = R.drawable.ic_rain,
                temperature = "32",
                weatherStatus = "Heavy Rain",
                placeDescription = "Marrakesh, a former imperial city in western Morocco, is a major economic center and home to mosques, palaces and gardens."
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
                title = "Maldives",
                subtitle = "Asia",
                btc = 1.8,
                bgImageUrl = "https://w0.peakpx.com/wallpaper/132/815/HD-wallpaper-maldives-beach-beauty-blue-clouds-house-ocean-palm-sea-slum-sun-water.jpg",
                weatherIcon = R.drawable.ic_rain,
                temperature = "28",
                weatherStatus = "Light Rain",
                placeDescription = "The Maldives, officially the Republic of Maldives, is an archipelagic state and country in South Asia, situated in the Indian Ocean."
            )
        )

        return stackItemList
    }
}