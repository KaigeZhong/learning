#include <stdio.h>
#include <unistd.h>
int main(){
  printf("it's the main process step 1!!\n\n");
 
  fork();
  
  printf("step2 after fork() !!\n\n");
 /*
  fork()函数将这个程序分叉了
  可以见到程序在fork()函数执行时都只有1条主进程, 所以 step 1 会被打印输出1次.
  执行 fork()函数后,  程序分叉成为了两个进程, 1个是原来的主进程,  另1个是新的子进程, 它们都会执行fork() 函数后面的代码, 所以 step2 会被 两条进程分别打印输出各一次, 屏幕上就总共3条printf 语句了!
 */ 
  return 0;
}
