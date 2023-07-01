package indie.jithinjude.dev

import androidx.annotation.DrawableRes
import java.io.Serializable

/**
 * Created by <Jithin/Jude> on 29,June,2023
 */
data class StackItemModel(
    var title: String = "Title",
    var subtitle: String = "Subtitle",
    var btc: Double = 0.0,
    var bgImageUrl: String = "https://w0.peakpx.com/wallpaper/116/427/HD-wallpaper-iphone-14-nature-in-summer-thumbnail.jpg",

    @DrawableRes
    var weatherIcon: Int = 0,
    var temperature: String = "00",
    var weatherStatus: String = "Light Rain",
    var placeDescription: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
) : Serializable
