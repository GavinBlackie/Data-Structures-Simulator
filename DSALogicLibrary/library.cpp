#include "library.h"
#include <jni.h>
#include <iostream>
#include "blackie_gavin_dsasimulatorserver_logic_primary_Stack.h"
using std::cout, std::endl;

int main() {
    cout << "Hello, from Logic Library!!" << endl;

    //JNIEXPORT jstring name;

    return 0;
}

void hello() {
    std::cout << "Hello, World!" << std::endl;
}

JNIEXPORT void JNICALL Java_blackie_gavin_dsasimulatorserver_logic_primary_Stack_hello
  (JNIEnv * env, jobject jObj) {
    cout << "Hello java, from C++!!" << endl;
}