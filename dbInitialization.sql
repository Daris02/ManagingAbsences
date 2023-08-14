-- Active: 1690631579809@@127.0.0.1@5432@hei

DROP DATABASE IF EXISTS hei_absence;

CREATE DATABASE hei_absence;

\c hei_absence;

-----------------------------------
-- Create table "group"
CREATE TABLE "group" (
    id SERIAL PRIMARY KEY,
    name VARCHAR(10) NOT NULL
);

-----------------------------------
-- Create table "student"
CREATE TABLE "student" (
    id SERIAL PRIMARY KEY,
    ref_student VARCHAR(9) NOT NULL,
    first_name VARCHAR(200) NOT NULL,
    last_name VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL UNIQUE,
    gender CHAR(1) NOT NULL,
    active BOOLEAN DEFAULT true,
    id_group INT REFERENCES "group"(id)
);

-----------------------------------
-- Create table "entry"
CREATE TABLE "entry" (
    id SERIAL PRIMARY KEY,
    date TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp,
    id_student INT REFERENCES "student"(id)
);

-----------------------------------
-- Create table "exit"
CREATE TABLE "exit" (
    id SERIAL PRIMARY KEY,
    date TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp,
    id_student INT REFERENCES "student"(id)
);


----------------------------------
-- Insert mock data
    -- Insert "group"
    INSERT INTO "group" VALUES
        (1, 'H1'),
        (2, 'H2'),
        (3, 'H3'),
        (4, 'H4');

    -- Insert "student"
    INSERT INTO "student" VALUES 
        (1, 'STD22001', 'Alice', 'Vianay', 'alice@gmail.com', 'F', true, 1),
        (2, 'STD22002', 'Bob', 'Crick', 'bob@gmail.com', 'M', true, 2),
        (3, 'STD22003', 'Clark', 'Kent', 'clark@gmail.com', 'M', true, 3),
        (4, 'STD22004', 'Derek', 'Phil', 'derek@gmail.com', 'M', true, 4);
    -- -----------------------------------------
        INSERT INTO "student" VALUES 
            (5, 'STD22005', 'Eva', 'Liones', 'eva@gmail.com', 'F', true, 1);
    -- -----------------------------------------

    -- Inser "register"
        -- Insert for entry date
            INSERT INTO "entry" (id_student) VALUES
                (1);
        -- Inser for exit date
            INSERT INTO "exit" (date, id_student) VALUES
                ('2023-08-13 19:07:53', 1);

------------------------ REQUEST ------------------------------
-- List des ceux qui sont entrer
SELECT
    st.*
FROM "student" st
    INNER JOIN "entry" en ON st.id = en.id_student;



-- List de ceux qui ne sont pas entrer
SELECT
    st.*
FROM "student" st
    FULL OUTER JOIN "entry" en ON st.id = en.id_student
WHERE
    st.id NOT IN (
        SELECT
            st.id
        FROM "student" st
            INNER JOIN "entry" en ON st.id = en.id_student
    );



-- Present durant 3 heure ou plus pour aujourd'hui
SELECT 
    st.*
FROM "student" st 
    INNER JOIN "entry" en ON st.id = en.id_student
    INNER JOIN "exit" ex ON st.id = ex.id_student
WHERE
    date_part('hour', ex.date) >= date_part('hour', en.date) + 3
    AND date_part('day', ex.date) = date_part('day', en.date)
    AND date_part('month', ex.date) = date_part('month', en.date)
    AND date_part('year', ex.date) = date_part('year', en.date);