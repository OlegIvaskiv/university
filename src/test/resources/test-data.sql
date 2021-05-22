INSERT INTO "course"(course_id, course_name) VALUES(
1,
'Math');

INSERT INTO "audience"(audience_id, audience_number) VALUES(
1,
246);

INSERT INTO "date"(date_id,  day, mounth, year) VALUES(
1,
'30',
'03',
'2021');

INSERT INTO "group"(group_id, course_id, group_name) VALUES(
1,
1,
'BI-21');

INSERT INTO "student"(student_id, group_id, student_name, student_phone, student_email, student_adress) VALUES(
1,
1,
'Oleh Ivaskiv',
'666666666',
'OLEH@gmail.com',
'London');

INSERT INTO "teacher"(teacher_id,  teacher_name, teacher_phone, teacher_email, teacher_adress) VALUES(
1,
'Elon Musk',
'0101010101',
'MuskSpaceX.com',
'Silicon valley');

INSERT INTO "lecture"(lecture_id, teacher_id, student_id, audience_id, date_id ) VALUES(
1,
1,
1,
1,
1);

INSERT INTO "course"(course_id,  course_name) VALUES(
2,
'English');

INSERT INTO "audience"(audience_id, audience_number) VALUES(
2,
201);

INSERT INTO "date"(date_id,  day, mounth, year) VALUES(
2,
'30',
'03',
'2021');

INSERT INTO "group"(group_id, course_id, group_name) VALUES(
2,
2,
'KN-21');

INSERT INTO "student"(student_id, group_id, student_name, student_phone, student_email, student_adress) VALUES(
2,
2,
'Alex',
'2222222',
'Alex@gmail.com',
'London');

INSERT INTO "teacher"(teacher_id, teacher_name, teacher_phone, teacher_email, teacher_adress) VALUES(
2,
'Tom Cruise',
'1111112121',
'Tom@Cruise.com',
'New Yourk');

INSERT INTO "lecture"(lecture_id, teacher_id, student_id, audience_id, date_id ) VALUES(
2,
2,
2,
2,
2);

INSERT INTO "course"(course_id,  course_name) VALUES(
3,
'Biology');

INSERT INTO "audience"(audience_id, audience_number) VALUES(
3,
146);

INSERT INTO "date"(date_id,  day, mounth, year) VALUES(
3,
'30',
'03',
'2021');

INSERT INTO "group"(group_id, course_id, group_name) VALUES(
3,
null,
'IK-21');

INSERT INTO "student"(student_id, group_id, student_name, student_phone, student_email, student_adress) VALUES(
3,
null,
'Max',
'0001110001',
'Max@gmail.com',
'London');

INSERT INTO "teacher"(teacher_id, teacher_name, teacher_phone, teacher_email, teacher_adress) VALUES(
3,
'Gipocrat',
'9999944554',
'MuskSpaceX.com',
'Warsaw');

INSERT INTO "lecture"(lecture_id, teacher_id, student_id, audience_id, date_id ) VALUES(
3,
null,
null,
null,
null);
