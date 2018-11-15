#include <sys/types.h>		/* some systems still require this */
#include <sys/stat.h>
#include <sys/termios.h>	/* for winsize */
#if defined(MACOS) || !defined(TIOCGWINSZ)
#include <sys/ioctl.h>
#endif
 
#include <stdio.h>		/* for convenience */
#include <stdlib.h>		/* for convenience */
#include <stddef.h>		/* for offsetof */
#include <string.h>		/* for convenience */
#include <unistd.h>		/* for convenience */
#include <signal.h>		/* for SIG_ERR */
 
#define	MAXLINE	4096			/* max line length */
#define err_sys printf
 
int
main(void)
{
	int		n;
	int		fd[2];
	pid_t	pid;
	char	line[MAXLINE];
 
	if (pipe(fd) < 0) //创建pipe，fd[0] read, fd[1] write
		err_sys("pipe error");
	if ((pid = fork()) < 0) {
		err_sys("fork error");
	} else if (pid > 0) {		/* parent */
		close(fd[0]);//父进程只写，所以关闭读pipe.这样只是将fd[0]的引用减少到1，fd[0]没有被系统回收，仍然在子进程中有效
		write(fd[1], "hello world\n", 12);//父管道写数据
		printf("parent process pid=%d : write end\n", getpid());
	} else {					/* child */
		close(fd[1]);//子进程只读，关闭写pipe
		n = read(fd[0], line, MAXLINE);
		write(STDOUT_FILENO, line, n);//子进程读取数据
		printf("child process pid=%d : read end\n", getpid());
	}
	exit(0);
}
