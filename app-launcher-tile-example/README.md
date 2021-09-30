# app-launcher-tile-example

Current example creates a docker image with a minimum micronaut web application composed by a simple controller, and executes an Integration Test tha uses a webclient to consume the micronaut service. Instead of execute test against micronaut context, webclient will consume a service running in a docker container.

Image of the docker container will be created by JIB maven plugin.
Container will be started and deleted by fabric using app-launcher-tile.

**Parameters in pom.xml:**
```
    <properties>
        <assertj.version>3.19.0</assertj.version>
        <hamcrest.version>2.2</hamcrest.version>

        <!-- JIB-Maven-Plugin Configuration -->
        <docker.from.image>gcr.io/distroless/java:11</docker.from.image>
        <docker.image.name>${project.artifactId}</docker.image.name>
        <docker.tag>${project.version}</docker.tag>

        <!-- App-launcher-tile configuration -->
        <app-launcher-tile.version>0.0.3-SNAPSHOT</app-launcher-tile.version>
        <it.archimedesfw.app.docker.image>local/${project.artifactId}:${docker.tag}</it.archimedesfw.app.docker.image>
        <!-- Forcing service port for IT example with container -->
        <it.archimedesfw.app.port>8080</it.archimedesfw.app.port>
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
                        <tile>io.archimedesfw.maven.tiles:app-launcher-tile:${app-launcher-tile.version}</tile>
                    </tiles>
                </configuration>
            </plugin>
            ...
        </plugins>
```

application.yml configuration in prod context:
```
micronaut:
...
endpoints:
  all:
    path: /management
    enabled: true
    sensitive: true
  health:
    sensitive: false
...
```
**/management/health** service has been enabled in order to detect the container start-up.

application.yml configuration in test context:
```
  http:
    services:
      user-api-client:
        url: http://localhost:8080/
```
Web client will consume service in localhost:8080, port defined in the tile configuration.

 
To build (compile, test, package, integration tests...) the complete project you can run in the root project directory:

 ```bash
mvn clean install
```

Please check the source code, specially the different `pom.xml` and `application.yml`, to see how all things are configured.