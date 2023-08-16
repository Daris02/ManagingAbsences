
-----------------------------------
-- Create table "group"
CREATE TABLE "group" (
        id BIGINT PRIMARY KEY,
        name VARCHAR(10) NOT NULL
);

-----------------------------------
-- Insert "group"
INSERT INTO "group" VALUES
                        (1, 'H1'),
                        (2, 'H2'),
                        (3, 'H3'),
                        (4, 'H4');

-----------------------------------
-- Create table "student"
CREATE TABLE "student" (
       id BIGINT PRIMARY KEY,
       ref_student VARCHAR(9) NOT NULL,
       first_name VARCHAR(200) NOT NULL,
       last_name VARCHAR(200) NOT NULL,
       email VARCHAR(200) NOT NULL UNIQUE,
       gender CHAR(1) NOT NULL,
       active BOOLEAN DEFAULT true,
       id_group INT REFERENCES "group"(id)
);

-----------------------------------
-- Insert "student"
INSERT INTO "student" VALUES
        (1, 'STD22001', 'Alice', 'Viana', 'alice@gmail.com', 'F', true, 1),
        (2, 'STD22002', 'Bob', 'Crick', 'bob@gmail.com', 'M', true, 2),
        (3, 'STD22003', 'Clark', 'Kent', 'clark@gmail.com', 'M', true, 3),
        (4, 'STD22004', 'Derek', 'Phil', 'derek@gmail.com', 'M', true, 4),
        (5, 'STD22005', 'Eva', 'Stone', 'eva@gmail.com', 'F', true, 1);


-----------------------------------
-- Create table "entry"
CREATE TABLE "entry" (
        id SERIAL PRIMARY KEY,
        date TIMESTAMP DEFAULT current_timestamp,
        id_student INT REFERENCES "student"(id)
);

-----------------------------------
-- Insert "entry"
INSERT INTO "entry" (date, id_student) VALUES
        ('2023-09-01 07:30:00', 1),
        ('2023-09-01 07:45:00', 2),
        ('2023-09-01 07:53:00', 3),
        ('2023-09-01 08:05:00', 4);

-----------------------------------
-- Create table "exit"
CREATE TABLE "exit" (
        id SERIAL PRIMARY KEY,
        date TIMESTAMP DEFAULT current_timestamp,
        id_student INT REFERENCES "student"(id)
);

-----------------------------------
-- Insert "entry"
INSERT INTO "exit" (date, id_student) VALUES
        ('2023-09-01 14:23:00', 1),
        ('2023-09-01 13:15:00', 2),
        ('2023-09-01 11:33:00', 3),
        ('2023-09-01 12:05:00', 4);

-----------------------------------
-- SELECT
-- Ce qui sont present durant 3 heure ou plus pour aujourd'hui
SELECT
    st.*
FROM "student" st
         INNER JOIN "entry" en ON st.id = en.id_student
         INNER JOIN "exit" ex ON st.id = ex.id_student
WHERE
        date_part('hour', ex.date) >= date_part('hour', en.date) + 3
  AND date_part('day', ex.date) = date_part('day', en.date)
  AND date_part('month', ex.date) = date_part('month', en.date)
  AND date_part('year', ex.date) = date_part('year', en.date)
  AND date_part('year', current_timestamp) = date_part('year', en.date)
-- AND date_part('month', current_timestamp) = date_part('month', en.date)
-- AND date_part('day', current_timestamp) = date_part('day', en.date)
;