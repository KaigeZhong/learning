#include <stdio.h>
#include <fcntl.h>
#include <error.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/stat.h>
#define SIZE 128
int main()
{
    /*create fifo*/
    int result;
    char *file_name = "swap";
    result=access(file_name,F_OK);//判断该文件是否存在
    if(result==0)				//存在
    {
    	unlink(file_name);		//删除同名文件
    }

    int ret_c;
    ret_c=mkfifo(file_name,0644);		//创建有名管道
    if(ret_c!=0)
    {
    	perror("mkfifo error");
    	exit(1);
    }


    /*write data*/
    char buffer[SIZE];
    int write_fd;
    int ret;
    int len;
    do
    {
    	write_fd=open("./swap",O_WRONLY);//打开管道（只写）
    	if(write_fd<0)
    	{
    		perror("open error");
    		exit(1);
    	}
    	printf("input data\n");
    	fgets(buffer,SIZE,stdin);
    	len=strlen(buffer);
    	buffer[len-1]='\0';
    	ret=write(write_fd,buffer,SIZE);
    	if(ret==-1)
    	{
    		perror("write error");
    		exit(1);
    	}
    	printf("send success\n");
    	close(write_fd);
    }while(strcmp(buffer,"exit")!=0);
    return 0;
}

