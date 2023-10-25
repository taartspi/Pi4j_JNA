# Pi4j_JNA
JNA usage to access Pi4J_V2



# Build
-P profile optional.  By default the pom.xml assumes you built for architecture 64bit.
This assumption is used to copy the .so file to the correct directory.
If building for 32bit architecture -Parmhf,   -Paarch64 is 64bit.
