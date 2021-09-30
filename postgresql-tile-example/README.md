# postgres-tile-example

Current example contains an Integration Tests that validates a simple Repository against a postgresql container with test data.

Postgresql container will be started and deleted by fabric using postgresql-tile.
Test data will be generate executing an SQL script directly in postgresql container started for tests. 

**Parameters in pom.xml:**
```
    <properties>
        <assertj.version>3.19.0</assertj.version>
        <hamcrest.version>2.2</hamcrest.version>

        <tiles-maven-plugin.version>2.24</tiles-maven-plugin.version>
        <postgresql-tile.version>0.0.3-SNAPSHOT</postgresql-tile.version>
        <postgresql.version>42.2.20</postgresql.version>

        <it.archimedesfw.postgresql.image>postgres:10-alpine</it.archimedesfw.postgresql.image>
        <it.archimedesfw.postgresql.host>localhost</it.archimedesfw.postgresql.host>
        <it.archimedesfw.postgresql.port>5432</it.archimedesfw.postgresql.port>
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
                        <tile>io.archimedesfw.maven.tiles:postgresql-tile:${postgresql-tile.version}</tile>
                    </tiles>
                </configuration>
            </plugin>
        </plugins>
```

application.yml configuration in prod context:
```
datasources:
  default:
    url: jdbc:postgresql://localhost:5432/archimedes
    username: postgres
    password: verysecret
    driverClassName: org.postgresql.Driver
    minimumIdle: 1
    maximumPoolSize: 5
```
Will Contain the prod datasource definition. 

application.yml configuration in test context:
```
datasources:
  default:
    url: jdbc:postgresql://localhost:5432/archimedes
    username: postgres
    password: verysecret
    driverClassName: org.postgresql.Driver
    minimumIdle: 1
    maximumPoolSize: 5
```
It would be different from production datasource definition.


Initial database data is defined in sql scripts located at **resources/initdb** directory.
 
To build (compile, test, package, integration tests...) the complete project you can run in the root project directory:

 ```bash
mvn clean install
```

Please check the source code, specially the different `pom.xml` and `application.yml`, to see how all things are configured.