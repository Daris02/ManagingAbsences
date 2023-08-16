
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
        date TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp,
        id_student INT REFERENCES "student"(id)
);

-----------------------------------
-- Insert "entry"
INSERT INTO "entry" (date, id_student) VALUES
        ('2023-09-01 07:30:00', 1),
        ('2023-09-01 07:45:00', 2),
        ('2023-09-01 07:53:00', 3),
        ('2023-09-01 08:05:00', 4),
        ('2023-09-02 08:02:00', 5);

-----------------------------------
-- Create table "exit"
CREATE TABLE "exit" (
        id SERIAL PRIMARY KEY,
        date TIMESTAMP WITH TIME ZONE DEFAULT current_timestamp,
        id_student INT REFERENCES "student"(id)
);

-----------------------------------
-- Insert "entry"
INSERT INTO "exit" (date, id_student) VALUES
        ('2023-09-01 14:23:00', 1),
        ('2023-09-01 13:15:00', 2),
        ('2023-09-01 11:33:00', 3),
        ('2023-09-01 12:05:00', 4);

