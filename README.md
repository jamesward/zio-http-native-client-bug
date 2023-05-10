ZIO HTTP Native Client Bug
--------------------------

## Dev Info

Run with restart:
```
./sbt ~reStart
```

Run and output GraalVM configs, with GraalVM:
```
# uncomment javaOptions in build.sbt
./sbt run
```

Create native image, with GraalVM:
```
./sbt graalvm-native-image:packageBin
```

Build the container:
```
pack build --builder=paketobuildpacks/builder:base zio-http-native-client-bug
```

Run the container:
```
docker run -p8080:8080 zio-http-native-client-bug
```
