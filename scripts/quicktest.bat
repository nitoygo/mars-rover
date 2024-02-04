@echo off

echo Compiling the project...

cd /d %~dp0
cd ..
call .\gradlew build --exclude-task test
cd /d %~dp0
echo Testing the app using the following commands

(
  echo LMLMLMLMLM
  echo 0,0,N
  echo M
  echo 0,0,N
  echo L
  echo 1,2,E
  echo MMLM
  echo exit
) | java -jar ..\build\libs\mars-rover-1.0-SNAPSHOT.jar

pause