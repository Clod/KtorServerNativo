# Ktor Server with GraalVM Native Image Example

[![official JetBrains project](https://jb.gg/badges/official-flat-square.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)

This project demonstrates how to build a Ktor server application and compile it into a native image using GraalVM. Native images offer significant performance benefits, including faster startup times and reduced memory footprint.

## Technologies Used

- [Ktor](https://ktor.io/): A framework for building asynchronous servers and clients in Kotlin.
- [GraalVM](https://www.graalvm.org/): A high-performance runtime that can compile applications ahead-of-time into native images.
- [Kotlin](https://kotlinlang.org/): A modern statically typed programming language.
- [Gradle](https://gradle.org/): A powerful build automation system.

## Prerequisites

- [GraalVM](https://graalvm.org) installed and configured. Ensure that `$GRAALVM_HOME` environment variable points to your GraalVM installation directory or that `native-image` is accessible in your PATH.

## Build and Run

1. **Build the Fat JAR:**
   Run the Gradle `shadowJar` task to create a fat JAR containing all dependencies:

   ```bash
   ./gradlew shadowJar
   ```

   This will generate a JAR file in `build/libs` directory, typically named like `[project-name]-all.jar`.

2. **Build the Native Image:**
   Execute the appropriate build script for your operating system:

   - **Linux/macOS:** `./build.sh`
   - **Windows:** `build.cmd`

   These scripts use GraalVM's `native-image` tool to compile the fat JAR into an executable native image.

3. **Run the Native Image:**
   After successful compilation, an executable file named `graal-server` (or `graal-server.exe` on Windows) will be created in the project root. Run this executable:

   ```bash
   ./graal-server
   ```

   The server will start, and you can access it at `https://0.0.0.0:8080`.

## Limitations

- **Netty Engine Incompatibility:**  Currently, the Netty engine is not compatible with GraalVM native image generation. This project uses a compatible engine. Refer to [KTOR-2558](https://youtrack.jetbrains.com/issue/KTOR-2558) for updates on Netty support.

## License

This project is licensed under the Apache 2.0 License. See the [LICENSE](LICENSE) file for details.
