INSERT INTO "audience"(audience_number) VALUES(

246);

INSERT INTO "date"(day, mounth, year) VALUES(

'30',
'03',
'2021');

INSERT INTO "course"(course_name) VALUES(
'Math');

INSERT INTO "group"(course_id, group_name) VALUES(
1,
'BI-21');

INSERT INTO "student"(group_id, student_name, student_phone, student_email, student_adress) VALUES(
1,
'Oleh Ivaskiv',
'666666666',
'OLEH@gmail.com',
'London');

INSERT INTO "teacher"(teacher_name, teacher_phone, teacher_email, teacher_adress) VALUES(
'Elon Musk',
'0101010101',
'MuskSpaceX.com',
'Silicon valley');

INSERT INTO "lecture"( teacher_id, student_id, audience_id, date_id ) VALUES(
1,
1,
1,
1);







INSERT INTO "course"(course_name) VALUES(
'English');

INSERT INTO "audience"( audience_number) VALUES(
201);

INSERT INTO "date"(  day, mounth, year) VALUES(
'30',
'03',
'2021');

INSERT INTO "group"( course_id, group_name) VALUES(
2,
'KN-21');


INSERT INTO "student"( group_id, student_name, student_phone, student_email, student_adress) VALUES(
2,
'Alex',
'2222222',
'Alex@gmail.com',
'London');

INSERT INTO "teacher"( teacher_name, teacher_phone, teacher_email, teacher_adress) VALUES(
'Tom Cruise',
'1111112121',
'Tom@Cruise.com',
'New Yourk');

INSERT INTO "lecture"( teacher_id, student_id, audience_id, date_id ) VALUES(
2,
2,
2,
2);



INSERT INTO "audience"( audience_number) VALUES(
146);

INSERT INTO "date"(day, mounth, year) VALUES(
'30',
'03',
'2021');


INSERT INTO "course"(course_name) VALUES(
'Biology');



INSERT INTO "group"( group_name) VALUES(
'IK-21');



INSERT INTO "student"(  student_name, student_phone, student_email, student_adress) VALUES(
'Max',
'0001110001',
'Max@gmail.com',
'London');

INSERT INTO "teacher"( teacher_name, teacher_phone, teacher_email, teacher_adress) VALUES(
'Gipocrat',
'9999944554',
'MuskSpaceX.com',
'Warsaw');

INSERT INTO "lecture"(teacher_id, student_id, audience_id, date_id ) VALUES(
null,
null,
null,
null);

