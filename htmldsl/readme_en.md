# HTML DSL

A lightweight, type-safe HTML generation library written in Kotlin.

**Designed for Kotlin developers who need to generate HTML, not build web applications.**

Instead of manually concatenating HTML strings, this library provides an idiomatic Kotlin DSL that makes HTML generation easier, safer, and more maintainable.

This project focuses on generating HTML directly from Kotlin code. It is particularly suitable for reports, exported documents, email templates, offline pages, and other scenarios where HTML generation is only one part of a Kotlin application.

---

# Features

* ✅ Idiomatic Kotlin DSL
* ✅ Type-safe HTML generation
* ✅ Strongly typed HTML attributes
* ✅ Compile-time restrictions for specific HTML structures
* ✅ Lightweight with no external dependencies
* ✅ Easy to extend with custom tags and attributes
* ✅ Focused on HTML generation only (no CSS or JavaScript DSL)

---

# Example

```kotlin
val html = html {
    header {
        title("HTML DSL")
    }

    body {
        h2("HTML DSL")

        p("Generate HTML directly from Kotlin.")

        hr()

        ul {
            li("Type-safe")
            li("Lightweight")
            li("Easy to extend")
        }

        br()

        a {
            href = "https://kotlinlang.org"
            target = "_blank"
            text = "Visit Kotlin"
        }
    }
}.toHtmlCode()
```

The generated HTML can be written directly to a file or embedded into any Kotlin application.

---

# Why this library?

There are already many excellent frontend frameworks such as Vue, React, and Angular.

However, they solve a different problem.

When HTML generation is only a small part of a Kotlin application, introducing a complete frontend toolchain is often unnecessary.

Typical use cases include:

* HTML reports
* Exported documents
* Static pages
* Email templates
* Generated documentation
* Diagnostic or debug pages
* Simple embedded web content

For these scenarios, generating HTML directly from Kotlin is often simpler, easier to maintain, and easier to integrate into existing projects.

---

# Type Safety

The DSL leverages Kotlin's type system to reduce invalid HTML structures.

Some HTML elements only allow specific child elements.

Instead of detecting these mistakes at runtime, the library uses generic types to restrict invalid structures during compilation whenever possible.

This provides better IDE completion and catches mistakes earlier.

---

# Strongly Typed Attributes

HTML attributes are exposed as Kotlin properties instead of string-based APIs.

```kotlin
a {
    href = "https://kotlinlang.org"
    target = "_blank"
}
```

Developers no longer need to remember attribute names or manually concatenate strings.

IDE auto-completion makes discovering available attributes straightforward.

---

# Architecture

The library separates responsibilities into independent components.

### HtmlTag

Defines the common behavior shared by every HTML element.

### HtmlBodyGroup

Represents HTML elements containing multiple child elements.

Examples include:

* body
* div
* table
* tr

### HtmlBodySingle

Represents HTML elements containing a single child element.

This keeps the internal implementation simple while preserving a clean DSL.

### Attribute System

Attributes are implemented through reusable interfaces and entity classes.

Instead of duplicating common attributes across every HTML element, attribute definitions can be shared and composed.

This makes implementing new HTML elements much easier.

### DSL Extensions

The Kotlin DSL itself is implemented using extension functions.

Adding support for a new HTML element generally requires only a new tag implementation and a corresponding DSL extension.

---

# Comparison

| Manual String Building            | HTML DSL                  |
| --------------------------------- | ------------------------- |
| Manual string concatenation       | Kotlin DSL                |
| Easy to introduce syntax mistakes | Type-safe API             |
| Attribute names written manually  | Strongly typed properties |
| Difficult to maintain             | Readable nested structure |
| No IDE guidance                   | IDE auto-completion       |
| Hard to extend                    | Easy to extend            |

---

# Current Limitations

The library intentionally focuses on HTML generation.

The following features are not implemented yet:

* CSS DSL
* JavaScript DSL
* Pretty-printed HTML output

These features are planned for future versions if they become useful.

---

# Roadmap

Future improvements include:

* More HTML tags
* More HTML attributes
* CSS DSL
* JavaScript DSL
* Pretty-print HTML output
* More examples
* Better documentation
* Additional unit tests
* Kotlin Multiplatform evaluation

---

# Motivation

This project originally started as a personal utility for generating HTML from Kotlin applications.

Since then, it has evolved into a reusable DSL centered around three principles:

* Readability
* Type safety
* Extensibility

Later I learned frontend frameworks such as Vue.

However, I found that this library continued to be the most practical solution whenever HTML generation was only a small part of a Kotlin project.

The goal of this project is **not to replace frontend frameworks**, but to provide a lightweight HTML generation library that integrates naturally into Kotlin applications.

---

# Contributing

Issues, feature requests, and pull requests are always welcome.

If you have ideas for additional HTML tags, attributes, or improvements to the DSL, feel free to open an issue or submit a pull request.

---

# License

MIT License.
