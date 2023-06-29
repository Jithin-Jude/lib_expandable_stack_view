package indie.jithinjude.dev

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

/**
 * Created by <Jithin/Jude> on 29,June,2023
 */
class ExpandableStackView: FrameLayout {
    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?,
                defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {
        View.inflate(context, R.layout.layout_expandable_stack_view, this)
    }
}