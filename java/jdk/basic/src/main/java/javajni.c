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

JNIEXPORT void JNICALL Java_com_jni_JavaJNI_changeAge
  (JNIEnv *env, jobject obj)
  {
    //获致obj中对象的class
    jclass clazz = (*env)->GetObjectClass(env, obj);
    // 获取java中age字段的ID(最后一个参数是age的签名)
    jfieldID id_age = (*env)->GetFieldID(env, clazz, "age", "I");
    // 获取age字段对应的值
    jint age = (*env)->GetIntField(env, obj, id_age);
    // 输出
    // printf("current age is: %d\n", number);
    printf("age before changed: %d\n", age);
    // 重新修改age字段的值为100。
    (*env)->SetIntField(env, obj, id_age, 100);
  }
