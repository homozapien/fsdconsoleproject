
#!/bin/bash

echo "Start of compilation of fsdconsoleproject"

javac -d ../bin -cp ../src ../src/com/simplilearn/fsd/main/FileConsoleApp.java

echo "Compilation successful! Project commences"

java -cp ../bin com.simplilearn.fsd.main.FileConsoleApp "$@"
