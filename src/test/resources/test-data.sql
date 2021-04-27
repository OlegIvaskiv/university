INSERT INTO "course"(id,  name) VALUES(
1,
'Math');

INSERT INTO "audience"(id, number) VALUES(
1,
246);

INSERT INTO "date"(id,  day, mounth, year) VALUES(
1,
'30',
'03',
'2021');

INSERT INTO "group"(id, course_id, name) VALUES(
1,
1,
'BI-21');

INSERT INTO "student"(id, group_id, name, phone, email, adress) VALUES(
1,
1,
'Oleh Ivaskiv',
'666666666',
'OLEH@gmail.com',
'London');

INSERT INTO "teacher"(id, group_id, name, phone, email, adress) VALUES(
1,
1,
'Elon Musk',
'0101010101',
'MuskSpaceX.com',
'Silicon valley');

INSERT INTO "lecture"(id, teacher_id, student_id, audience_id, date_id ) VALUES(
1,
1,
1,
1,
1);

INSERT INTO "course"(id,  name) VALUES(
2,
'English');

INSERT INTO "audience"(id, number) VALUES(
2,
201);

INSERT INTO "date"(id,  day, mounth, year) VALUES(
2,
'30',
'03',
'2021');

INSERT INTO "group"(id, course_id, name) VALUES(
2,
2,
'KN-21');

INSERT INTO "student"(id, group_id, name, phone, email, adress) VALUES(
2,
2,
'Alex',
'2222222',
'Alex@gmail.com',
'London');

INSERT INTO "teacher"(id, group_id, name, phone, email, adress) VALUES(
2,
2,
'Tom Cruise',
'1111112121',
'Tom@Cruise.com',
'New Yourk');

INSERT INTO "lecture"(id, teacher_id, student_id, audience_id, date_id ) VALUES(
2,
2,
2,
2,
2);

INSERT INTO "course"(id,  name) VALUES(
3,
'Biology');

INSERT INTO "audience"(id, number) VALUES(
3,
146);

INSERT INTO "date"(id,  day, mounth, year) VALUES(
3,
'30',
'03',
'2021');

INSERT INTO "group"(id, course_id, name) VALUES(
3,
null,
'IK-21');

INSERT INTO "student"(id, group_id, name, phone, email, adress) VALUES(
3,
null,
'Max',
'0001110001',
'Max@gmail.com',
'London');

INSERT INTO "teacher"(id, group_id, name, phone, email, adress) VALUES(
3,
null,
'Gipocrat',
'9999944554',
'MuskSpaceX.com',
'Warsaw');

INSERT INTO "lecture"(id, teacher_id, student_id, audience_id, date_id ) VALUES(
3,
null,
null,
null,
null);
