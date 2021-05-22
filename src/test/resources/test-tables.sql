DROP TABLE IF EXISTS "lecture", "student","teacher","group", "course", "date", "audience";

CREATE TABLE IF NOT EXISTS "course" (
course_id serial PRIMARY KEY,
course_name VARCHAR (255)
);

CREATE TABLE IF NOT EXISTS "group"(
group_id serial PRIMARY KEY,
course_id INT references "course"(course_id), 
group_name VARCHAR (255)
);
 
CREATE TABLE IF NOT EXISTS "student" (
student_id serial PRIMARY KEY , 
group_id INT references "group"(group_id), 
student_name VARCHAR (255),
student_phone VARCHAR (255),
student_email VARCHAR (255),
student_adress VARCHAR (255)
);


CREATE TABLE IF NOT EXISTS "date" (
date_id serial PRIMARY KEY,
 day VARCHAR (2),
 mounth VARCHAR (2),
 year VARCHAR (4)
);
 
 CREATE TABLE IF NOT EXISTS "teacher" (
 teacher_id serial PRIMARY KEY,
 teacher_name VARCHAR (255),
 teacher_phone VARCHAR (255),
 teacher_email VARCHAR (255),
 teacher_adress VARCHAR (255)
);

CREATE TABLE IF NOT EXISTS "audience" (
 audience_id serial PRIMARY KEY,
 audience_number INT  
);


 CREATE TABLE IF NOT EXISTS "lecture" (
lecture_id serial PRIMARY KEY,
teacher_id INT references "teacher"(teacher_id),
student_id INT references "student"(student_id),
audience_id INT references "audience"(audience_id),
date_id INT references "date"(date_id)
);
