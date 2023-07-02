# Expandable Stack View

Expandable Stack View is a library which developers can integrate into an app to expand-collapse stack views. The module is basically a combination of ViewPager2 with some item decoration & an Activity with shared element transitions.

## Major Components inside library

1. <b>ExpandableStackView:</b> The class responsible for the creation of stack view with the help of ViewPager2.

2. <b>ExpandableStackViewAdapter:</b> The adapter used to inflate the items to the ViewPager. This adapter requires a list of StackItemModel & a ClickListener.

3. <b>StackItemModel:</b> This is the class responsible for handling data for the stack element template.

4. <b>ExpandedViewActivity:</b> The activity responsible for showing the expanded stack element view with the help of transitions & animations.

## Challenges faced during development & solutions arrived

While implementing the shared element transitions I’m able to implement the background expand-collapse transitions smoothly but there was a glitch in the button expansion. I am able to sort this by modifying the button layout in the ExpandedViewActivity. Previously I was using the `AppCompatButton`; to fix the glitch I made a custom layout for the button with a combination of `CardView`, `LinearLayout`, `ImageView` & `TextView`. Thus I’m able to achieve smoothness.
<p>To ensure the flow & integration of all components; I have used a combination of <b>multiple shared transitions</b> & `AnimationUtils`.<p/>

## Future improvements
More actions can be added to the expanded view (means more states). `ChangeBounds`, `ChangeTransform`, `ChangeImageTransform`, and `ChangeClipBounds` can be customize for the transition of each shared elements. So that it will more improve the flow.

## Notes:

1. <b>My major concern is to implement the dribble video UI & flow (animations) as closely as possible.</b>

2. Didn't add user interaction in each Stack item content since it is a prototype (I mean only view is added.)

3. BottomView stack element can be dismissed by taping just outside of the bottom sheet (I mean current highlighted stack element).

Demo video:
-
[![demo video](https://img.youtube.com/vi/RGbrFVa4TiI/0.jpg)](https://www.youtube.com/watch?v=RGbrFVa4TiI)

[APK](https://drive.google.com/file/d/1W4CXsKe2O7rNN4Tuzu9tvKeRERpAlUQf/view?usp=sharing "link to APK")

```xml
<indie.jithinjude.dev.ExpandableStackView
        android:id="@+id/esvLayout"
        android:layout_width="match_parent"
        android:layout_height="550dp"
        android:layout_marginTop="12dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/tvSubtitle" />
```
```kt
binding.esvLayout.prepareExpandableStackView(this, stackElementList)
```

## Libraries used
Glide: for image loading:<br/>
https://github.com/bumptech/glide

## References
Codepath android guides:<br/>
https://github.com/codepath/android_guides/wiki/Shared-Element-Activity-Transition

StackOverflow:<br/>
https://stackoverflow.com/a/33859633/6914403

Geeks for Geeks:<br/>
https://www.geeksforgeeks.org/shared-element-transition-in-android-with-example/

