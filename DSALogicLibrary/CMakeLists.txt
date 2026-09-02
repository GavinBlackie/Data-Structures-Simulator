cmake_minimum_required(VERSION 4.0)
project(DSALogicLibrary)
set(CMAKE_CXX_STANDARD 26)

#SET(JAVA_HOME "C:/Program Files/Java/jdk-26")
#SET(JAVA_INCLUDE_PATH "C:/Program Files/Java/jdk-26/include")

find_package(Java REQUIRED)
#message(${JAVA_INCLUDE_PATH})

set(JAVA_AWT_LIBRARY "NotNeeded")
set(JAVA_JVM_LIBRARY "NotNeeded")

find_package(JNI REQUIRED)

# #
#find_package(JNI)
#if (JNI_FOUND)
#    message (STATUS "JNI_INCLUDE_DIRS=${JNI_INCLUDE_DIRS}")
#    message (STATUS "JNI_LIBRARIES=${JNI_LIBRARIES}")
#endif()
#
#message("$ENV{JAVA_HOME}")

add_library(DSALogicLibrary SHARED library.cpp)

target_include_directories(DSALogicLibrary
        PRIVATE
        ${JNI_INCLUDE_DIRS}
        ${Java_INCLUDE_DIRS}   # optional, often same as JNI on Windows
)