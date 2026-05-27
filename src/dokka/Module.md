# Module Kotlin Wicket Markup DSL

The Kotlin Wicket Markup DSL is a domain-specific language for writing Wicket markup in Kotlin instead of DSL. Additionally, the component hierarchy is described at the same time as the markup, removing the need to keep the component hierarchies in code and in the markup synchronized manually. This makes writing and refactoring Wicket applications written in Kotlin a lot easier.

Most of the Wicket feature are supported, but in a more limited way than Wicket allows. The DSL requires a specific code structure.

The DSL can be enabled in existing applications without any changes to the code, and existing code can be migrated per component or page.

See [`README.md`](https://github.com/squins/kwmdsl/tree/main/README.md) and the examples in the [source code](https://github.com/squins/kwmdsl/tree/main/src/examples/kotlin/kwmdsl/examples) to get started.
