/*导入刚才生成的头文件*/
/*include的<>是到lib中搜索，“”是相对路径中搜索*/
#include "com_jni_JavaJNI.h"
#include <stdio.h>
/*头文件中的方法实现，直接将头文件中的方法原型复制过来加以实现即可，注意添加形参变量，*/
JNIEXPORT void JNICALL Java_com_jni_JavaJNI_printstr__
  (JNIEnv *env, jobject obj)
  {
    printf("%s\n","woshiwucanjni" );
    return;
  }

JNIEXPORT void JNICALL Java_com_jni_JavaJNI_printstr__Ljava_lang_String_2
  (JNIEnv *env, jobject obj, jstring string)
  {
    const char *str = (*env)->GetStringUTFChars(env, string, 0);
    printf("%s!\n", str);
  }
