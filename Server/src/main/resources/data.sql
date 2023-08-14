
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
        (1, 'STD22001', 'Alice', 'Vianay', 'alice@gmail.com', true, 1),
        (2, 'STD22002', 'Bob', 'Crick', 'bob@gmail.com', true, 2),
        (3, 'STD22003', 'Clark', 'Kent', 'clark@gmail.com', true, 3),
        (4, 'STD22004', 'Derek', 'Phil', 'derek@gmail.com', true, 4);
