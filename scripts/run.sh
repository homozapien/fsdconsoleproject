
#!/bin/bash

OPTS= -Djava.awt.headless=true -Djava.net.useSystemProxies=true
java $OPTS -cp ../bin com.simplilearn.fsd.main.FileConsoleApp "$@"
