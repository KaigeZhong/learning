#include <stdio.h>
#include <unistd.h>
#include <pthread.h>
/*
gcc thread_demo1.c -o thread_demo1 -lpthread
*/
void thread(void)
{
  int i;
  for(i=0;i<3;i++){
    printf("This is a pthread.\n");
    sleep(1);
  }
}
 
 int main(void)
{
  pthread_t id;
  int i,ret;
  ret=pthread_create(&id,NULL,(void *) thread,NULL); // 成功返回0，错误返回错误编号
  if(ret!=0) {
    printf ("Create pthread error!\n");
  }
  for(i=0;i<3;i++){
    printf("This is the main process.\n");
    sleep(1);
  }
  pthread_join(id,NULL);
  return (0);
}
