# micronaut-postgres-tile-example

Current example contains an Integration Tests that validates a simple Repository against a postgresql container with test data.
In this example we're using **flyway** to populate database test contents.

Postgresql container will be started and deleted by fabric using micronaut-postgresql-tile.

**Parameters in pom.xml:**
```
    <properties>
        <assertj.version>3.19.0</assertj.version>
        <hamcrest.version>2.2</hamcrest.version>

        <postgresql.version>42.2.20</postgresql.version>
        <tiles-maven-plugin.version>2.24</tiles-maven-plugin.version>
        <micronaut-postgresql-tile.version>0.0.3-SNAPSHOT</micronaut-postgresql-tile.version>
        <it.archimedesfw.postgresql.image>postgres:10-alpine</it.archimedesfw.postgresql.image>
        <it.archimedesfw.postgresql.host>localhost</it.archimedesfw.postgresql.host>
        <it.archimedesfw.postgresql.port>5432</it.archimedesfw.postgresql.port>
        <it.archimedesfw.postgresql.flyway.locations>classpath:db/schema,classpath:db/data
        </it.archimedesfw.postgresql.flyway.locations>
    </properties>


```
Directories with initial flyway sql scripts are defined in parameter **it.archimedesfw.postgresql.flyway.locations**

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
                        <tile>io.archimedesfw.maven.tiles:micronaut-postgresql-tile:${micronaut-postgresql-tile.version}</tile>
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

flyway:
  datasources:
    default:
      locations:
        - classpath:db/schema
        - classpath:db/data
```
Will Contain the prod datasource definition and flyway location configuration for datasource.

It would be different in test application.yml definition.


Initial database data is defined in sql scripts located at **resources/db/schema** and **resources/db/data** directories.

To build (compile, test, package, integration tests...) the complete project you can run in the root project directory:

 ```bash
mvn clean install
```

Please check the source code, specially the different `pom.xml` and `application.yml`, to see how all things are configured.