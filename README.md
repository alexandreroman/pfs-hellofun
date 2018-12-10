# Hello Fun!

This project shows how to deploy a simple Java function to
[Pivotal Function Service](https://pivotal.io/platform/pivotal-function-service) (PFS).

Using PFS, you focus on your code, and you just deploy functions:
the platform automatically optimizes resources usage, and takes care
of packaging your functions to running containers.

This project contains the smallest possible Java function implementation,
using the plain JDK
[Function](https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html)
interface. There are no external dependencies required. No even a `Dockerfile`!

```java
public class Hello implements Function<String, String> {
    public String apply(String name) {
        final String n;
        if (name == null || name.isEmpty()) {
            n = "world";
        } else {
            n = name;
        }
        return "Hello " + n;
    }
}
```

As soon as you push this code to PFS, the platform compiles source code and
generates a JAR file, which is then turned into an
[OCI](https://www.opencontainers.org/) container image using the
[Cloud Native Buildpack](https://buildpacks.io/) project.

## How to use it?

Publish this function to PFS using this command:
```shell
$ pfs function create hello \
  --git-repo https://github.com/alexandreroman/pfs-hellofun.git \
  --handler fr.alexandreroman.demos.hellofun.Hello \
  --image $REGISTRY/$REGISTRY_USER/hello \
  --verbose
```

Invoke the function using this command:
```shell
$ pfs service invoke hello --text -- -w '\n' -d 'PFS'
curl 104.155.125.168/ -H 'Host: hello.hello.example.com' -H 'Content-Type: text/plain' -w '\n' -d PFS
Hello PFS
```

You can also use this function with a pipe:
```shell
$ whoami | pfs service invoke hello --text -- -w '\n' -d @-
curl 104.155.125.168/ -H 'Host: hello.hello.example.com' -H 'Content-Type: text/plain' -w '\n' -d PFS
Hello <username>
```

## Contribute

Contributions are always welcome!

Feel free to open issues & send PR.

## License

Copyright &copy; 2018 Pivotal Software, Inc.

This project is licensed under the [Apache Software License version 2.0](https://www.apache.org/licenses/LICENSE-2.0).
