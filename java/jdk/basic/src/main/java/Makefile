javajni.so:javajni.o
	gcc -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -shared -o javajni.so javajni.o
javajni.o:javajni.c
	gcc -I${JAVA_HOME}/include -I${JAVA_HOME}/include/linux -c -fPIC -o javajni.o javajni.c
