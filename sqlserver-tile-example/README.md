# sqlserver-tile-example

Current example contains an Integration Tests that validates a simple Repository against an sqlserver container with test data.

SqlServer container will be started and deleted by fabric using sqlserver-tile.
Test data will be generate executing an SQL script directly in sqlserver container started for tests.

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

        <it.archimedesfw.sqlserver.datasource>default</it.archimedesfw.sqlserver.datasource>
        <it.archimedesfw.sqlserver.schema>archimedes</it.archimedesfw.sqlserver.schema>
        <it.archimedesfw.sqlserver.db>archimedes</it.archimedesfw.sqlserver.db>
        <it.archimedesfw.sqlserver.port>1433</it.archimedesfw.sqlserver.port>
        <it.archimedesfw.sqlserver.stopMode>kill</it.archimedesfw.sqlserver.stopMode>

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
                        <tile>io.archimedesfw.maven.tiles:sqlserver-tile:${sqlserver-tile.version}</tile>
                    </tiles>
                </configuration>
            </plugin>
        </plugins>
```

application.yml configuration:
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
```
It would be different for test and production datasource definition.


Initial database data is defined in next files:
* test/resources/sqlserver.scripts/entrypoint.sh  script that starts sqlserver in container and executes initial sql script.
* test/resources/sqlserver.scripts/setup.sql script executed by sh with database and schema creation.


To build (compile, test, package, integration tests...) the complete project you can run in the root project directory:

 ```bash
mvn clean install
```

Please check the source code, specially the different `pom.xml` and `application.yml`, to see how all things are configured.