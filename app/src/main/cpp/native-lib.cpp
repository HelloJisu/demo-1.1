#include <jni.h>
#include <android/log.h>
#include <opencv2/opencv.hpp>

using namespace cv;
using namespace std;

extern "C" JNIEXPORT jstring JNICALL
Java_com_reziena_user_reziena_11_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT void JNICALL
Java_com_reziena_user_reziena_11_MainActivity_Detect(JNIEnv *env, jobject instance) {

    // TODO

}

extern "C"
JNIEXPORT jlong JNICALL
Java_com_reziena_user_reziena_11_MainActivity_loadCascade(JNIEnv *env, jobject instance,
                                                          jstring cascadeFileName_) {
    //    const char *cascadeFileName = env->GetStringUTFChars(cascadeFileName_, 0);


    const char *nativeFileNameString = env->GetStringUTFChars(cascadeFileName_, 0);

    string baseDir("/storage/emulated/0/");
    baseDir.append(nativeFileNameString);
    const char *pathDir = baseDir.c_str();

    jlong ret = 0;
    ret = (jlong) new CascadeClassifier(pathDir);
    if (((CascadeClassifier *) ret)->empty()) {
        __android_log_print(ANDROID_LOG_DEBUG, "native-lib :: ",
                            "CascadeClassifier로 로딩 실패  %s", nativeFileNameString);
    }

    else
        __android_log_print(ANDROID_LOG_DEBUG, "native-lib :: ",
                            "CascadeClassifier로 로딩 성공 %s", nativeFileNameString);

    env->ReleaseStringUTFChars(cascadeFileName_, nativeFileNameString);

    return ret;

//    env->ReleaseStringUTFChars(cascadeFileName_, cascadeFileName);

}

extern "C"
JNIEXPORT void JNICALL
Java_com_reziena_user_reziena_11_MainActivity_loadImage(JNIEnv *env, jobject instance,
                                                        jstring imageFileName_, jlong img,
                                                        jlong cascadeClassifier_face) {
    const char *imageFileName = env->GetStringUTFChars(imageFileName_, 0);

    Mat &img_input = *(Mat *) img;
    img_input = imread(imageFileName, IMREAD_COLOR);

    env->ReleaseStringUTFChars(imageFileName_, imageFileName);
}