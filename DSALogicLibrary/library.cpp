#include "library.h"
#include <jni.h>
#include <iostream>
#include "blackie_gavin_dsasimulatorserver_logic_primary_Stack.h"
using std::cout, std::endl, std::string;

int main() {
    cout << "Hello, from Logic Library!!" << endl;

    return 0;
}

void hello() {
    std::cout << "Hello, World!" << std::endl;
}

jintArray getArray(JNIEnv * env, jclass jObj, const char* fieldName="array") {
    jfieldID arrayId = env->GetFieldID(jObj, fieldName, "[I");
    auto array = (jintArray)env->GetObjectField(jObj, arrayId);
    return array;
}

JNIEXPORT void JNICALL Java_blackie_gavin_dsasimulatorserver_logic_primary_Stack_push
  (JNIEnv * env, jobject jStackObj, jint elem) {
    jclass stackClass = env->GetObjectClass(jStackObj);
    jintArray array = getArray(env, stackClass);
    jint* elements = env->GetIntArrayElements(array, nullptr);


}

JNIEXPORT void JNICALL Java_blackie_gavin_dsasimulatorserver_logic_primary_Stack_hello
  (JNIEnv * env, jobject jStackObj) {
    cout << "Hello java, from C++!!" << endl;

    // https://stackoverflow.com/questions/1086596/how-to-access-arrays-within-an-object-with-jni

    // jclass jClass = env->GetObjectClass(jObj);
    // jfieldID capacityId = env->GetFieldID(jClass, "capacity", "I");
    // jint capacity = env->GetIntField(jObj, capacityId);

    // 1. Get stack class obj
    jclass stackClass = env->GetObjectClass(jStackObj);
    // 2. Get field IDs matching java attribute names
    jfieldID arrayId = env->GetFieldID(stackClass, "array", "[I");
    jfieldID topId = env->GetFieldID(stackClass, "top", "I");
    jfieldID capacityId = env->GetFieldID(stackClass, "capacity", "I");
    // Verify that fields exist (memory management)
    if (arrayId == nullptr || topId == nullptr || capacityId == nullptr) {
        // Field names did not match!
        return;
    }

    // 3. Extract data
    jintArray array = (jintArray)env->GetObjectField(jStackObj, arrayId);
    jint top= env->GetIntField(jStackObj, topId);

    // 4. Safely access internal C++ pointer of java array
    jint* elements = env->GetIntArrayElements(array, nullptr);

    for (jint i = 0; i <= top; i++) {
        cout << "Stack element at: " << i << " : " << elements[i] << endl;
    }

    // 6. Release pinned memory, prevent netive memory leaks
    env->ReleaseIntArrayElements(array, elements, JNI_ABORT);

    //cout << "Current Capacity: " << capacity << endl;
}