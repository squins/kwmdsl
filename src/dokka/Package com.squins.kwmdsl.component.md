# Package com.squins.kwmdsl.component

This package contains base classes with which using the DSL is enabled. If you want to enable using the DSL for an existing component of which the base class cannot be changed (easily), this is also possible.

For new components, use one of the provided base classes. The classes all start with `KotlinWicketMarkup` and end with the name of the component they are a replacement for. For example: [KotlinWicketMarkupWebPage], [KotlinWicketMarkupGenericPanel] or [KotlinWicketMarkupBorder].

Existing components must implement `IMarkupResourceStreamProvider` and in `getMarkupResourceStream(...)` invoke [findMarkup].

In addition, functions are provided to generate the link path to a page ([linkPath]), and to generate resource paths to resources (from possibly another class) ([resourcePath]). By using these functions, the paths will still be correct after renaming or moving the classes.
