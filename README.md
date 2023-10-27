# Pi4j_JNA
JNA usage to access Pi4J_V2

openjdk version "17.0.8" 2023-07-18
OpenJDK Runtime Environment JBR-17.0.8+7-1000.8-nomod

# Build

Execute the Makefile in /src/main/native

mvn clean install
 The build process copies the native C code to the target directories.
-P profile optional.  By default the pom.xml assumes you built for architecture 64bit.
This assumption is used to copy the .so file to the correct directory.
If building for 32bit architecture -Parmhf,   -Paarch64 is 64bit.

mvn clean install -Parmhf