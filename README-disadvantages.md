# Disadvantages

The DSL is not perfect of course. It has its own limitations and quirks. Those are listed below.

## DSL

### Cannot Work Together With a Designer

As the markup is embedded in Kotlin source files when using the DSL, there are no separate markup files that a designer can work on anymore.

### Only HTML Generated

The generated markup is always HTML. It is not possible to generate XHTML. This decision was made assuming that XHTML is almost never used anymore.

### No Deprcated HTML Elements and Attributes

The DSL does not supply pre-defined functions for HTML elements and attributes that have been deprecated. This does not mean that you cannot use those at all. And it is easy to create the functions for them yourself.

### Component Must Be Stored in Properties

You have to declare a property for each component (that is not in a repeater). This enables the use of the rename refactoring for IDs, and ensures that components are instantiated only once.

The property must also be initialized directly or, in combination with `lateinit var`, once in the constructor or `onInitialize()`. Using a `get()` function will result in issues because a new instance of the component will be created each time the value of the property is retrieved.

### IDs Unique in Hierarchy

Because IDs are property names, they have to be unique withing the hierarchy. `:repeater1:child` and `repeater2:child` are not possible.

### Cannot Use Private Classes

Because the DSL uses reflection for references to properties and functions, the page and component classes cannot be private.

### Specify Component Class if No Descendents

If the page or component does not have descendents, you have to specify the component class in the markup declaration. Otherwise you will get a duplicate cache key, resulting in the wrong markup being used for the components.

As it is easy to forget, the DSL checks this.

## Kotlin

Some limitations are limitations of the Kotlin language and tooling.

### Property Type Must Be Specified

Kotlin cannot infer the type of the property if its initializer refers to the property, or the type of a function if its definition refers to the function, so the type of a component members must always be specified explicitly.

### Code Completion for Component Member Names Does Not Work

When having to add a reference to the component properties and functions in the property initializers, function definitions and markup, IntelliJ does not suggest the property and function names (most of the time).

### Slow Editing With More Complex Components

Kotlin type inference is used to determine the supplier class of markup. When this markup gets larger, the inference can become very slow.

One solution is to specify the supplier class explicitly (just like when the component does not have dependents), even though this is not necessary to be able to compile the code.

### Large Indentations

The standard code formatting rules moves markup too much to the right, requiring a lot of horizontal scrolling with deeply nested markup.

With `// @formatter:off` it is possible to make the root tag start at the left edge of the editor. But this does not affect the indentation size (standard 4 spaces), so large indentations are still common in more complex components.
