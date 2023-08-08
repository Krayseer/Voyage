package ru.krayseer.voyage.context;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.krayseer.voyage.containers.PostgresTestContainer;

@Testcontainers
public abstract class ContainersEnvironment {

    @Container
    public static PostgreSQLContainer<PostgresTestContainer> postgreSQLContainer = PostgresTestContainer.getInstance();

}
