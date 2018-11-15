#include <sys/eventfd.h>
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <stdint.h>  
/*
./eventfd_demo 1 2 3
*/
int main(int argc, char*argv[])
{
    int efd, j;
    uint64_t u;
    ssize_t s;
    
    if (argc < 2)
    {
        printf("number of argc is wrong!\n");
        return 0;
    }
    
    efd = eventfd(0,0);
    if (-1 == efd)
    {
        printf("failed to create eventfd\n");
    }
    
    switch(fork())
    {
        case 0:
        {
            for(j=1; j<argc;j++)
            {
                printf("child writing %s to efd\n", argv[j]);
                u = strtoull(argv[j], NULL, 0);
                s = write(efd, &u, sizeof(uint64_t));
                if (s!=sizeof(uint64_t))
                {
                    printf("write efd failed\n");
                }
            }
            printf("Child completed write loop\n");
            exit(0);
        }
        default:
            sleep(2);
            printf("Parents about to read\n");
            s = read(efd, &u, sizeof(uint64_t));
            if (s != sizeof(uint64_t))
            {
                printf("read efd failed\n");
            }
            printf("Parents first read %lu (0x%lx) from efd\n", u, u);
            exit(0);
        case -1:
        {
            printf("fork error\n");
        }
    }
    
    return 0;
}
