# Introduction

The Kotlin Wicket markup DSL enables you to write markup in the Kotlin source file, instead of in a separate HTML file. The markup uses references to component properties or functions, so the wicket ID only has to be specified once (TODO("Only if using a property delegate")) and updated in all places using a rename refactoring. The hierarchy also only has to be specified once: in the markup. Components are added by the markup to the page or component according to the markup hierarchy.

## Properties

TODO("KDoc, order of members, visibility")

TODO("Tiny, most is (generated) convenience functions. But large impact.")

TODO("Matching IDs checked during creation of the components")

TODO("Unsafe: must escape yourself. Reason: more efficient and more control. For example: https://www.unbescape.org/")

TODO("Efficient: minimal code that will work. Only once for all instances")

TODO("No conditional markup: must use visibility")

# How to Use

## Dependency

## Basic Usage

TODO("Companion object IMarkup...Provider")

## Convenience Functions 

### HTML Elements

TODO("Convenience: each element (that is not deprecated) 4 functions")

### Class Elements

TODO("Class elements")

## Base Classes

TODO("Provide DSL panel, page, ...?")

## Example Code

TODO("See examples directory")
