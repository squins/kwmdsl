# Package com.squins.kwmdsl

The DSL to write markup and define the component hierarchy. Using the DSL consists of four steps:

1. Enable the use of the DSL for the component or page. See package [com.squins.kwmdsl.component] for how to do that.
2. Define the properties that hold and functions that create the Wicket components.
3. Write the markup, referencing the properties and functions, in the companion object. Use the appropriate function that creates root markup: [markup], [borderMarkup], [standaloneFragmentMarkup].
4. Ask the markup to add the component hierarchy to the component in `onInitialize()`.
