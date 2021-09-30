# micronaut-sqlserver-tile-example

Current example contains an Integration Tests that validates a simple Repository against an sqlserver container with test data.
In this example we're using **flyway** to populate database test contents.

Sqlserver container will be started and deleted by fabric using micronaut-sqlserver-tile.

**Parameters in pom.xml:**
```
    <properties>
        <assertj.version>3.19.0</assertj.version>
        <hamcrest.version>2.2</hamcrest.version>

        <tiles-maven-plugin.version>2.24</tiles-maven-plugin.version>
        <sqlserver-tile.version>0.0.3-SNAPSHOT</sqlserver-tile.version>
        <sqlserver.version>8.4.0.jre11</sqlserver.version>

        <it.archimedesfw.sqlserver.user>SA</it.archimedesfw.sqlserver.user>
        <it.archimedesfw.sqlserver.password>V3rY.S3CreT2020</it.archimedesfw.sqlserver.password>
        <it.archimedesfw.sqlserver.flyway.locations>classpath:db/schema,classpath:db/data</it.archimedesfw.sqlserver.flyway.locations>
        <it.archimedesfw.sqlserver.datasource>default</it.archimedesfw.sqlserver.datasource>
        <it.archimedesfw.sqlserver.schema>archimedes</it.archimedesfw.sqlserver.schema>
        <it.archimedesfw.sqlserver.db>archimedes</it.archimedesfw.sqlserver.db>
        <it.archimedesfw.sqlserver.port>1433</it.archimedesfw.sqlserver.port>
        <it.archimedesfw.sqlserver.stopMode>kill</it.archimedesfw.sqlserver.stopMode>
    </properties>
```
Directories with initial flyway sql scripts are defined in parameter **it.archimedesfw.sqlserver.flyway.locations**

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
                        <tile>io.archimedesfw.maven.tiles:micronaut-sqlserver-tile:${micronaut-sqlserver-tile.version}</tile>
                    </tiles>
                </configuration>
            </plugin>
        </plugins>
```

application.yml configuration in prod context:
```
datasources:
  default:
    url: jdbc:sqlserver://localhost:1433;databaseName=archimedes
    schema: archimedes
    username: SA
    password: V3rY.S3CreT2020
    driverClassName: com.microsoft.sqlserver.jdbc.SQLServerDriver
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