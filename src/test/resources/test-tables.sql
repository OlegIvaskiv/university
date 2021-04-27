DROP TABLE IF EXISTS "lecture",  "student","teacher","group", "course", "date",  "audience";

CREATE TABLE IF NOT EXISTS "course" (
id serial PRIMARY KEY,
name VARCHAR (255)
);

CREATE TABLE IF NOT EXISTS "group"(
id serial PRIMARY KEY,
course_id INT references "course"(id), 
name VARCHAR (255)
);
 
CREATE TABLE IF NOT EXISTS "student" (
id serial PRIMARY KEY , 
group_id INT references "group"(id), 
name VARCHAR (255),
phone VARCHAR (255),
email VARCHAR (255),
adress VARCHAR (255)
);


CREATE TABLE IF NOT EXISTS "date" (
 id serial PRIMARY KEY,
 day VARCHAR (2),
 mounth VARCHAR (2),
 year VARCHAR (4)
);
 
 CREATE TABLE IF NOT EXISTS "teacher" (
 id serial PRIMARY KEY,
 group_id INT references "group"(id), 
 name VARCHAR (255),
 phone VARCHAR (255),
 email VARCHAR (255),
 adress VARCHAR (255)
);

CREATE TABLE IF NOT EXISTS "audience" (
 id serial PRIMARY KEY,
 number INT  
);


 CREATE TABLE IF NOT EXISTS "lecture" (
id serial PRIMARY KEY,
teacher_id INT references "teacher"(id),
student_id INT references "student"(id),
audience_id INT references "audience"(id),
date_id INT references "date"(id)
);
