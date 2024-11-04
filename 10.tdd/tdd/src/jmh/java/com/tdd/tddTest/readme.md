# A. how to run jmh?

## step1. build.gradle에서 실험 옵션 맞추기
docs: https://github.com/melix/jmh-gradle-plugin

## step2. run

case1) 모두 run 하는 경우 (쓰는 jdk의 위치를 지정해줘야 한다.)
```
./gradlew -Dorg.gradle.java.home=/Users/cho-cho/.asdf/installs/java/zulu-11.60.19 jmh
```

case2) 특정 클래스만 run 하는 경우 (쓰는 jdk의 위치를 지정해줘야 한다.)
```
./gradlew -Dorg.gradle.java.home=/Users/cho-cho/.asdf/installs/java/zulu-11.60.19 jmh -PjmhIncludes=ProductRankingBenchmark
```


# B. troubleshooting 

## a. log level DEBUG -> warn으로 올리는 법 

https://github.com/melix/jmh-gradle-plugin/issues/106#issuecomment-2453829916

