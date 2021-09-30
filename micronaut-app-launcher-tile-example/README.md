# micronaut-app-launcher-tile-example

Current example creates a docker image with a minimum micronaut web application composed by a simple controller, and executes an Integration Test tha uses a webclient to consume the micronaut service. Instead of execute test against micronaut context, webclient will consume a service running in a docker container.

In this case, service security is configured with oauth security and a wiremock server is configurred in order to simulate an authorization service  providing the JWKS info necessary to validate JWT bearer tokens.

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
                        <tile>io.archimedesfw.maven.tiles:wiremock-tile:${wiremock-tile.version}</tile>
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
  security:
    enabled: true
    authentication: bearer
    token:
      jwt:
        signatures:
          jwks:
            jwks-wiremock-service:
              url: http://archimedes-it-wiremock:8080/jwks
endpoints:
  all:
    path: /management
    enabled: true
    sensitive: true
  health:
    sensitive: false
...
```
**security** feature is enabled with authentication **bearer**
**jwt token signature** will be validate vs wiremock service configured as **jwks-wiremock-service**

**/management/health** service has been enabled in order to detect the container start-up.

application.yml configuration in test context:
```
micronaut:
  http:
    services:
      user-api-client:
        url: http://localhost:8080/
test-data:
  valid-token: eyJ0eXA...lCdTDBbbkA
  invalid-token: eyJ0eXA...IFnU4j5FnkNf6MLwF_16KU
```
Web client will consume service in localhost:8080, port defined in the tile configuration.
test-data will be used in webclients configurations in order to test different security cases.

 
To build (compile, test, package, integration tests...) the complete project you can run in the root project directory:

 ```bash
mvn clean install
```

Files in **resources/app-launcher-tile.security** resource:
* private_key.pem & public_key.pem: test certificates used to generate jks.json file served by wiremock service. Files are not necessaries to execute tests.
* jks.json jks file generated form private and public keys.

Files in **resources/wiremock-tile**
*__files/jwks.json jwks served by wiremock
* mappings/jwksMapping.json Wiremock configuration in order to simulate jwks service for tests.


Web Clients:
* UserApiTestClient WebClient configured with a static JWT valid token.
* UserApiTestClientInvalidToken WebClient configured with a static JWT invalid token.
* UserApiTestClientNoToken WebClient configured without JWT Token
 

Please check the source code, specially the different `pom.xml` and `application.yml`, to see how all things are configured.