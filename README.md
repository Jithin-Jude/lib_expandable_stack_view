# Expandable Stack View

Expandable Stack View is a library which developers can integrate into an app to expand-collapse stack views. The module is basically a combination of ViewPager2 with some item decoration & an Activity with shared element transitions.

## Major Components inside library

1. <b>ExpandableStackView:</b> The class responsible for the creation of stack view with the help of ViewPager2.

2. <b>ExpandableStackViewAdapter:</b> The adapter used to inflate the items to the ViewPager. This adapter requires a list of StackItemModel & a ClickListener.

3. <b>StackItemModel:</b> This is the class responsible for handling data for the stack element template.

4. <b>ExpandedViewActivity:</b> The activity responsible for showing the expanded stack element view with the help of transitions & animations.

## Challenges faced during development & solutions arrived

<p>While implementing the shared element transitions I’m able to implement the background expand-collapse transitions smoothly but there was a glitch in the button expansion. I am able to sort this by modifying the button layout in the ExpandedViewActivity. Previously I was using the AppCompact button; to fix the glitch I made a custom layout for the button with a combination of CardView, LinearLayout, ImageView & TextView. Thus I’m able to achieve smoothness.<p/>
<p>To ensure the flow & integration of all components; I have used a combination of multiple shared transitions & AnimationUtils.<p/>

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

