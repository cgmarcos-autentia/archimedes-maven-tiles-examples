CREATE TABLE users (
    id            NUMERIC(10) NOT NULL,
    username      VARCHAR(25) NOT NULL,
    name          VARCHAR(50) NOT NULL,
    surename      VARCHAR(255) NOT NULL
);

ALTER TABLE users ADD CONSTRAINT user_pk PRIMARY KEY (id);
