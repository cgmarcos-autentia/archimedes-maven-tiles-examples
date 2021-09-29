-- -------------------------------------------------------------------------------
-- DATABASE INITIALIZATION
-- -------------------------------------------------------------------------------
CREATE DATABASE archimedes;
GO

USE archimedes;
GO

CREATE SCHEMA archimedes;
GO

-- -------------------------------------------------------------------------------
-- INITIAL SCHEMA
-- -------------------------------------------------------------------------------
CREATE TABLE users (
    id       NUMERIC(10)  NOT NULL,
    username VARCHAR(25)  NOT NULL,
    name     VARCHAR(50)  NOT NULL,
    surename VARCHAR(255) NOT NULL
);
GO

ALTER TABLE users
    ADD CONSTRAINT user_pk PRIMARY KEY (id);
GO

-- -------------------------------------------------------------------------------
-- INITIAL DATA
-- -------------------------------------------------------------------------------
INSERT INTO users (id, username, name, surename)
VALUES (1, 'jdoe', 'John', 'Doe'),
       (2, 'asmith', 'Addam', 'Smith');
GO
