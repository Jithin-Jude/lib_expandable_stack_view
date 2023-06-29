package indie.jithinjude.dev

import androidx.annotation.IntegerRes

/**
 * Created by <Jithin/Jude> on 29,June,2023
 */
data class StackItemModel(
    var title: String = "Title",
    var subtitle: String = "Subtitle",
    var btc: Double = 0.0,
    var bgImageUrl: String = "https://i.ytimg.com/vi/LQCF4P1Wh6Q/maxresdefault.jpg",

    @IntegerRes
    var weatherIcon: Int = 0,
    var temperature: String = "00",
    var weatherStatus: String = "Light Rain",
    var placeDescription: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
)
