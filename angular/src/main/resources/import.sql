insert into role (role_id, name) values (1, 'teacher');
insert into role (role_id, name) values (2, 'student');

insert into user (user_id, role_id, login, password) values (200, 1, 'teacher_login', '1234');
insert into user (user_id, role_id, login, password) values (201, 2, 'student_login', '1234');

insert into tag (tag_id, name, color) values (1, 'tag_1', '47a447');
insert into tag (tag_id, name, color) values (2, 'tag_2', 'E22828');
insert into tag (tag_id, name, color) values (3, 'tag_3', 'E34828');
insert into tag (tag_id, name, color) values (4, 'tag_4', 'E24528');
insert into tag (tag_id, name, color) values (5, 'tag_5', '232828');
insert into tag (tag_id, name, color) values (6, 'tag_6', 'E22878');
insert into tag (tag_id, name, color) values (7, 'tag_7', 'E45628');
insert into tag (tag_id, name, color) values (8, 'tag_8', 'E28518');
insert into tag (tag_id, name, color) values (9, 'tag_9', 'E28348');
insert into tag (tag_id, name, color) values (10, 'tag_10', '345828');
insert into tag (tag_id, name, color) values (11, 'tag_11', '788828');
insert into tag (tag_id, name, color) values (12, 'tag_12', '345828');
insert into tag (tag_id, name, color) values (13, 'tag_13', '576828');
insert into tag (tag_id, name, color) values (14, 'tag_14', '567828');
insert into tag (tag_id, name, color) values (15, 'tag_15', '567828');
insert into tag (tag_id, name, color) values (16, 'tag_16', '567828');
insert into tag (tag_id, name, color) values (17, 'tag_17', '562828');
insert into tag (tag_id, name, color) values (18, 'tag_18', 'E45828');
insert into tag (tag_id, name, color) values (19, 'tag_19', 'E22598');
insert into tag (tag_id, name, color) values (20, 'tag_20', 'E26728');

insert into category (category_id, name, description, parent_category_id) values (11, 'name_1_1', 'description_1_1', null);
insert into category (category_id, name, description, parent_category_id) values (12, 'name_1_2', 'description_1_2', null);
-- insert into category (category_id, name, description, parent_category_id) values (13, 'name_1_3', 'description_1_3', null);
-- insert into category (category_id, name, description, parent_category_id) values (14, 'name_1_4', 'description_1_4', null);
-- insert into category (category_id, name, description, parent_category_id) values (15, 'name_1_5', 'description_1_5', null);

-- insert into category (category_id, name, description, parent_category_id) values (21, 'name_2_1', 'description_2_1', 11);
-- insert into category (category_id, name, description, parent_category_id) values (22, 'name_2_2', 'description_2_2', 11);
-- insert into category (category_id, name, description, parent_category_id) values (23, 'name_2_3', 'description_2_3', 11);
-- insert into category (category_id, name, description, parent_category_id) values (24, 'name_2_4', 'description_2_4', 12);
-- insert into category (category_id, name, description, parent_category_id) values (25, 'name_2_1', 'description_2_1', 12);
-- insert into category (category_id, name, description, parent_category_id) values (26, 'name_2_1', 'description_2_1', 13);

-- insert into category (category_id, name, description, parent_category_id) values (31, 'name_3_1', 'description_3_1', 21);

insert into test (test_id, name, description, category_id) values (100, 'name_100', 'description_100', 11);
insert into test (test_id, name, description, category_id) values (101, 'name_101', 'description_101', 11);
insert into test (test_id, name, description, category_id) values (102, 'name_102', 'description_102', 11);
insert into test (test_id, name, description, category_id) values (103, 'name_103', 'description_103', 11);

insert into task (task_id, question, answer, test_id) values (500, 'question_500', 'answer_500', 100);
insert into task (task_id, question, answer, test_id) values (501, 'question_501', 'answer_501', 100);
insert into task (task_id, question, answer, test_id) values (502, 'question_502', 'answer_502', 100);
insert into task (task_id, question, answer, test_id) values (503, 'question_503', 'answer_503', 100);
insert into task (task_id, question, answer, test_id) values (504, 'question_504', 'answer_504', 100);
insert into task (task_id, question, answer, test_id) values (505, 'question_505', 'answer_505', 100);
insert into task (task_id, question, answer, test_id) values (506, 'question_506', 'answer_506', 100);
insert into task (task_id, question, answer, test_id) values (507, 'question_507', 'answer_507', 100);

insert into task_comment (task_comment_id, reporter_id, task_id, content, created) values (1000, 201, 500, 'comment_1000 from 201', '2014-11-11');
insert into task_comment (task_comment_id, reporter_id, task_id, content, created) values (1001, 200, 500, 'comment_1001 from 200', '2014-11-11');
insert into task_comment (task_comment_id, reporter_id, task_id, content, created) values (1002, 201, 500, 'comment_1002 from 201', '2014-11-11');
insert into task_comment (task_comment_id, reporter_id, task_id, content, created) values (1003, 200, 500, 'comment_1003 from 200', '2014-11-11');
insert into task_comment (task_comment_id, reporter_id, task_id, content, created) values (1004, 201, 500, 'comment_1004 from 201', '2014-11-11');