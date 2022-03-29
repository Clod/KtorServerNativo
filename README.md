shadow[![official JetBrains project](https://jb.gg/badges/official-flat-square.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)

# GraalVM sample for Ktor Server

A demo project that shows how to combine Ktor Server applications with GraalVM.

## Steps

1. Make sure that you have [GraalVM](https://graalvm.org) installed and `$GRAALVM_HOME` environment
variable points to the folder where GraalVM is installed, or alternatively that `native-image` is on your path (if on Windows). 
   
2. Build the project by executing the task `shadowJar` which will build and produce a fat jar containing
all the necessary dependencies.

3. PS D:\home\Kotlin\Ktor\graalvm> .\gradlew shadowJar

y genera: "D:\home\Kotlin\Ktor\graalvm\build\libs\graalvm-0.0.1-all.jar"

   
4. Run the file `build.sh` if on Linux or macOS, or `build.cmd` if on Windows.

https://medium.com/graalvm/using-graalvm-and-native-image-on-windows-10-9954dc071311

Tuve que actualizar las Visual Studio Build tools:
https://visualstudio.microsoft.com/es/thank-you-downloading-visual-studio/?sku=BuildTools&rel=16

y ya que estaba, el Visual Studio propiamente dicho.

Creo que no hacía falta porque ya estaba: Desktop development with C++ (que entiendo que es lo necesario).

PERO también instalo: Windows 11 SDK

Desde el prompt que me provee "x64 Native Tools Command Prompt" hago:

C:\Program Files (x86)\Microsoft Visual Studio\2019\BuildTools\VC\Auxiliary\Build>vcvars64.bat
**********************************************************************
** Visual Studio 2019 Developer Command Prompt v16.11.11
** Copyright (c) 2021 Microsoft Corporation
**********************************************************************
[vcvarsall.bat] Environment initialized for: 'x64'

y después:

C:\Program Files (x86)\Microsoft Visual Studio\2019\BuildTools\VC\Auxiliary\Build>gu install native-image
Downloading: Release index file from oca.opensource.oracle.com
Downloading: Component catalog for GraalVM Enterprise Edition 22.0.0.1 on jdk17 from oca.opensource.oracle.com
Downloading: Component catalog for GraalVM Enterprise Edition 22.0.0 on jdk17 from oca.opensource.oracle.com
Downloading: Component catalog from www.graalvm.org
Processing Component: Native Image
Downloading: Component native-image: Native Image  from github.com
Installing new component: Native Image (org.graalvm.native-image, version 22.0.0.2)

y, aparentemente, lo istaló:

C:\Program Files (x86)\Microsoft Visual Studio\2019\BuildTools\VC\Auxiliary\Build>native-image
Please specify options for native-image building or use --help for more info.

Desde ESA ventana corro el Build.cmd (le saqué los \ y quedó una linea laaaarga). Empieza a compilar  pero me tira un error.

Error: Classes that should be initialized at run time got initialized during image building:
ch.qos.logback.classic.Logger was unintentionally initialized at build time. 
To see why ch.qos.logback.classic. Logger got initialized use --trace-class-initialization=ch.qos.logback.classic.Logger

Lo arreglo agregando:

--initialize-at-run-time=org.slf4j.impl.StaticLoggerBinder

a la línea del build.cmg

6. The previous step produces an executable file named `graal-server` which can then be run. Open up
`https://0.0.0.0:8080` to test the server.
   
### Current limitations

Using the `Netty` engine is not compatible with GraalVM. Please following the [corresponding issue](https://youtrack.jetbrains.com/issue/KTOR-2558) for
updates.

## License

This sample is provided as is under the Apache 2 OSS license. 

----------------------------------------------------------------------------------------------

Ejemplo de configuración para https:

https://github.com/ktorio/ktor-documentation/blob/main/codeSnippets/snippets/ssl-embedded-server/src/main/kotlin/com/example/Application.kt

----------------------------------------------------------------------------------------------
Notas sobre el experimento en Linux:

1) En la máquina virtual puse el network en bridged y salió andando de una (no hizo falta ni abrir el FW ni forwarder puertos).

Nada que ver con esto pero se ponía la pantalla negra. Se arregló seteando:

Settings > System > Acceleration -> Default

2) Instalé Java:
sudo apt install openjdk-17-jdk-headless (creo que la pedo porque GraalVM ya tiene un JDK)

en .bashrc de mi home definí las variables de entorno:

    export JAVA_HOME=/home/claudio/graalvm-ce-java17-22.0.0.2
    
    export GRAALVM_HOME=/home/claudio/graalvm-ce-java17-22.0.0.2
    
    PATH=$PATH:/home/claudio/graalvm-ce-java17-22.0.0.2/bin

3) Instalé native-image 

    gu install native-image

5) Instalé gcc

sudo apt install gcc

6) Instalé zlib1g-dev

   sudo apt-get install zlib1g-dev

y finalmente me armó el ejecutable que funcionó!!!!