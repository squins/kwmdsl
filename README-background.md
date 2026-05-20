# Background

## Motivation

Apache Wicket is a great front-end framework: fast, flexible and powerful. But working with the code of components can be a bit cumbersome: 2 files for 1 component, IDs that are strings and have to be manually synchronized, the hierarchy of descendent components that has to be kept synchronized between the markup and the code.

Kotlin has powerful DSL capabilities, and this DSL is the result of an attempt to improve the experience of Kotlin developers who use Wicket.

## Prior Art

This is not the only solution making development with Wicket easier. But the existing ones did not meet the requirements (see [goals](#design-goals) below).

### Tooling

There is a plug-in for IntelliJ ([WicketForge](https://plugins.jetbrains.com/plugin/1545-wicketforge)) to help, but that only works for Java code, does not find all ID occurrences, and is (unfortunately) no longer maintained.

### DSLs

There have been earlier attempts at writing DSLs for Wicket, but each of these attempts came with its own limitations:

* [kWicket](https://github.com/ageery/kwicket-core)
  * A wrapper around Wicket components, with quite a bit of code per component.
  * Fine for simple cases, makes customizations and Ajax more difficult.
  * First- (components with wrapper) and second-class citizens.
  * The markup is generated multiple times. This is flexible, but expensive.
* [Wicket HTML DSL](https://github.com/noobymatze/wicket-html-dsl/)
  * (Some) wrapper functions around common constructs.
  * Fine for simple cases, makes customizations and Ajax more difficult.
  * Cannot handle more complex markup: repeaters.
* [Apache Wicket Groovy DSL](https://github.com/eugene-kamenev/wicket-groovy-DSL)
  * A wrapper around Wicket components.
  * Fine for simple cases, but makes customization more difficult.
  * First- (components with wrapper) and second-class citizens.
  * Only replaces component (hierarchy) creation, so hierarchy synchronization with markup is still needed. 

## Design Goals

The design of the DSL was to achieve the following goals:

* Make it (a lot) easier to work with Wicket in Kotlin.
* Keep the Wicket semantics. In other words, do things the same as Wicket does, just written down differently.
* Support the common use cases. Wicket is very flexible and supporting all of that would require a full programming language. The DSL only has to support pages and components with static markup.
* The required code structure must be acceptable to most Wicket developers.
* Existing third-party components must be usable within having to request changes and/or having to write wrappers.
* Improve the developer experience:
  * 1 file instead of 2.
  * Make markup easier to read (subjective).
  * Have 1 location for the IDs plus references to them, so renaming an ID is an operation supported by the IDE.
  * Define the hierarchy only once, so there is no longer a need to synchronize markup and code.
  * Use references for component and page paths, so renaming and moving classes does not break the components.
  * Make reusable snippets easy to write and use.
* Allow mixing of markup types (HTML and DSL) in the same hierarchy.
* Have a (very) small impact on the performance and memory usage of component instances.

## Non-Goals

The following are not goals of the DSL:

* Support everything that is possible with Wicket and HTML files.
* Ensure only valid Wicket code can be written. You have to know what you are doing, just like with the HTML files.
* Terse code. Where possible, the code is as terse as possible. But strong typing and a small runtime overhead take precedence.
