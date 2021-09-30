# wiremock-tile-example

Current example creates an integration test that consumes a service defined in a wiremock service executed by fabric plugin

Image of the docker container will be created by JIB maven plugin.
Container will be started and deleted by fabric using app-launcher-tile.

**Parameters in pom.xml:**
```
    <properties>
        <assertj.version>3.19.0</assertj.version>
        <hamcrest.version>2.2</hamcrest.version>

        <tiles-maven-plugin.version>2.24</tiles-maven-plugin.version>
        <wiremock-tile.version>0.0.3-SNAPSHOT</wiremock-tile.version>

        <it-wiremock.port>8080</it-wiremock.port>
    </properties>
```

Tile configuration:

```
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>io.repaint.maven</groupId>
                    <artifactId>tiles-maven-plugin</artifactId>
                    <version>${tiles-maven-plugin.version}</version>
                    <extensions>true</extensions>
                    <configuration>
                        <applyBefore>io.archimedesfw.maven.micronaut:micronaut-java-parent</applyBefore>
                    </configuration>
                </plugin>
            </plugins>
        </pluginManagement>

        <plugins>
            <plugin>
                <groupId>io.repaint.maven</groupId>
                <artifactId>tiles-maven-plugin</artifactId>
                <configuration>
                    <tiles>
                        <tile>io.archimedesfw.maven.tiles:wiremock-tile:${wiremock-tile.version}</tile>
                    </tiles>
                </configuration>
            </plugin>
        </plugins>
```

application.yml
```
micronaut:
...
  http:
    services:
      wiremock-test-client:
        url: http://localhost:8080/
...
```
**wiremock-test-client** is the web client used by integration test.

 
To build (compile, test, package, integration tests...) the complete project you can run in the root project directory:

 ```bash
mvn clean install
```

Please check the source code, specially the different `pom.xml` and `application.yml`, to see how all things are configured.