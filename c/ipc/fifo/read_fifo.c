#include <stdio.h>
#include <fcntl.h>
#include <error.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <string.h>
#define SIZE 128
int main()
{
	char buffer[SIZE];
	int read_fd;
	int ret;
	do
	{
		read_fd=open("./swap",O_RDONLY);
		if(read_fd<0)
		{
			perror("open error");
			exit(1);
		}
		ret=read(read_fd,buffer,SIZE);
		if(ret==-1)
		{
			perror("read error");
			exit(1);
		}
		printf("the data is:\n");
		printf("%s\n",buffer);
		close(read_fd);
	}while(strcmp(buffer,"exit")!=0);
	return 0;
}

