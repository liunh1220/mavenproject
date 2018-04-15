#include    

#ifndef _Included_TestdllManager   
#define _Included_TestdllManager   
#ifdef __cplusplus   
extern "C" {   
#endif   

JNIEXPORT jint JNICALL Java_TestdllManager_getVal   
(JNIEnv *, jclass);   

JNIEXPORT void JNICALL Java_TestdllManager_setVal  
(JNIEnv *, jclass, jint);   
#ifdef __cplusplus   
}   
#endif   
#endif   