-- ----------------------------------------------------------------
-- INITIAL SCHEMA
-- ----------------------------------------------------------------
CREATE TABLE users (
    id       NUMERIC(10)  NOT NULL,
    username VARCHAR(25)  NOT NULL,
    name     VARCHAR(50)  NOT NULL,
    surename VARCHAR(255) NOT NULL
);

ALTER TABLE users
    ADD CONSTRAINT user_pk PRIMARY KEY (id);

-- ----------------------------------------------------------------
-- INITIAL DATA
-- ----------------------------------------------------------------
INSERT INTO users (id, username, name, surename)
VALUES (1, 'jdoe', 'John', 'Doe'),
       (2, 'asmith', 'Addam', 'Smith');

