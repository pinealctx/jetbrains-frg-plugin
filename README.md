# FRG JetBrains Plugin

This is a JetBrains plugin for the FRG language, providing rich language support for IntelliJ IDEA and other JetBrains IDEs.

## Features

- **Syntax Highlighting**: Colorization for keywords, types, strings, comments, and more.
- **Go to Definition**:
  - Jump to Type definitions (structs, enums).
  - Jump to `@externDefs` definitions.
- **Structure View**: Outline view of the file structure (Services, Types, Enums).
- **Line Markers**: Quick navigation icons for handlers.
- **Code Completion**: Basic completion for types and keywords.
- **Find Usages**: Find where types and enums are referenced.

## Installation

- **From Marketplace**: Search for "FRG" in the IDE Plugin Marketplace (Coming soon).
- **Manual**: Download the `.zip` file from the Releases page and install it via "Install Plugin from Disk...".

## Development Setup

1. Open this folder (`jetbrains-frg-plugin`) in IntelliJ IDEA.
2. Wait for Gradle sync to complete.
3. **Generate Parser and Lexer**:
   - Install the **Grammar-Kit** plugin in IntelliJ IDEA.
   - Right-click on `src/main/kotlin/com/pinealctx/frg/Frg.bnf` and select **Generate Parser Code**.
   - Right-click on `src/main/kotlin/com/pinealctx/frg/Frg.flex` and select **Run JFlex Generator**.
   - Ensure the generated files are in `src/main/gen`.
4. Run the plugin using the `runIde` Gradle task.

## Project Structure

- `src/main/kotlin/com/pinealctx/frg/Frg.bnf`: Grammar definition.
- `src/main/kotlin/com/pinealctx/frg/Frg.flex`: Lexer definition.
- `src/main/kotlin/com/pinealctx/frg/psi`: Generated PSI classes.
- `src/main/resources/META-INF/plugin.xml`: Plugin configuration.
