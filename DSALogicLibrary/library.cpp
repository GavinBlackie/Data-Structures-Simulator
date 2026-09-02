#include "library.h"
#include <jni.h>
#include <iostream>

using std::cout, std::endl;

int main() {
    cout << "Hello, from Logic Library!!" << endl;

    JNIEXPORT jstring name;

    return 0;
}

void hello() {
    std::cout << "Hello, World!" << std::endl;
}