#include <stdio.h>
#include <unistd.h>
int main(){
        int pid,ppid;

        int ret=fork();
        if(ret>0){
                pid=getpid();
                ppid=getppid();
                printf("我是父进程，pid=%d , ppid=%d ,我新建的子进程pid=%d\n",pid,ppid,ret);
                sleep(3);//父进程不能太快终止，否则看不出子进程ppid的效果
        }else if(ret==0){
                pid=getpid();
                ppid=getppid();
                printf("我是子进程，pid=%d , ppid=%d \n",pid,ppid);
        }else if(ret==-1){
                perror("fork");
        }   
}
