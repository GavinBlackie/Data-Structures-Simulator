#include "library.h"
#include <jni.h>
#include <iostream>
#include "blackie_gavin_dsasimulatorserver_logic_primary_Stack.h"
using std::cout, std::endl;

int main() {
    cout << "Hello, from Logic Library!!" << endl;

    return 0;
}

void hello() {
    std::cout << "Hello, World!" << std::endl;
}

JNIEXPORT void JNICALL Java_blackie_gavin_dsasimulatorserver_logic_primary_Stack_hello
  (JNIEnv * env, jobject jObj) {
    cout << "Hello java, from C++!!" << endl;

    // https://stackoverflow.com/questions/1086596/how-to-access-arrays-within-an-object-with-jni

    jclass jClass = env->GetObjectClass(jObj);
    jfieldID capacityId = env->GetFieldID(jClass, "capacity", "I");
    jint capacity = env->GetIntField(jObj, capacityId);

    cout << "Current Capacity: " << capacity << endl;
}