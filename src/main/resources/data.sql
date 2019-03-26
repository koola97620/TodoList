-- table list
-- user  team   team_user   role  user_role
-- board  task  task_content


-- user
INSERT INTO user(email, name) VALUES ('todoadmin@gmail.com','Admin');
INSERT INTO user(email, name) VALUES ('todoadmin2@gmail.com','Admin2');
INSERT INTO user(email, name) VALUES ('todoadmin3@gmail.com','Admin3');
INSERT INTO user(email, name) VALUES ('todoadmin4@gmail.com','Admin4');
INSERT INTO user(email, name) VALUES ('todoadmin5@gmail.com','Admin5');


-- team
INSERT INTO team(id, name, founder) VALUES (1, 'GROUP1' , 'MAKER-Admin');
INSERT INTO team(id, name, founder) VALUES (2, 'GROUP2' , 'MAKER-Admin2');

-- team_user
INSERT INTO team_user(id, team_id, user_email) VALUES (1, 1 , 'todoadmin@gmail.com');
INSERT INTO team_user(id, team_id, user_email) VALUES (2, 1 , 'todoadmin2@gmail.com');
INSERT INTO team_user(id, team_id, user_email) VALUES (3, 1 , 'todoadmin3@gmail.com');

INSERT INTO team_user(id, team_id, user_email) VALUES (4, 2 , 'todoadmin3@gmail.com');
INSERT INTO team_user(id, team_id, user_email) VALUES (5, 2 , 'todoadmin4@gmail.com');
INSERT INTO team_user(id, team_id, user_email) VALUES (6, 2 , 'todoadmin5@gmail.com');

-- role
INSERT INTO role(id, name) VALUES (1,'admin');
INSERT INTO role(id, name) VALUES (2,'user');
INSERT INTO role(id, name) VALUES (3,'teamMaker');

-- user_role
INSERT INTO user_role(user_email, role_id) VALUES ('todoadmin@gmail.com' ,2);
INSERT INTO user_role(user_email, role_id) VALUES ('todoadmin2@gmail.com' ,2);
INSERT INTO user_role(user_email, role_id) VALUES ('todoadmin3@gmail.com' ,2);
INSERT INTO user_role(user_email, role_id) VALUES ('todoadmin4@gmail.com' ,2);
INSERT INTO user_role(user_email, role_id) VALUES ('todoadmin5@gmail.com' ,2);

INSERT INTO user_role(user_email, role_id) VALUES ('todoadmin@gmail.com' ,3);
INSERT INTO user_role(user_email, role_id) VALUES ('todoadmin3@gmail.com' ,3);


-- board
INSERT INTO board(id,name,user,team,register_date) VALUES (1,'개인 BOARD','todoadmin@gmail.com' , null ,'2019-03-01 00:00:01');
INSERT INTO board(id,name,user,team,register_date) VALUES (2,'Team1 BOARD','todoadmin@gmail.com' , 1 ,'2019-03-03 00:00:01');
INSERT INTO board(id,name,user,team,register_date) VALUES (3,'개인 BOARD','todoadmin2@gmail.com' , null ,'2019-03-05 00:00:01');
INSERT INTO board(id,name,user,team,register_date) VALUES (4,'Team2 BOARD1','todoadmin3@gmail.com' , 2 ,'2019-03-05 00:00:01');
INSERT INTO board(id,name,user,team,register_date) VALUES (5,'Team2 BOARD2','todoadmin4@gmail.com' , 2 ,'2019-03-07 00:00:01');
INSERT INTO board(id,name,user,team,register_date) VALUES (6,'개인 BOARD2','todoadmin5@gmail.com' , null ,'2019-03-07 00:00:01');

-- task_content

INSERT INTO task_content(id, content) VALUES (1,'스프링 인액션5 다 읽기');
INSERT INTO task_content(id, content) VALUES (2,'동네한바퀴 돌기');
INSERT INTO task_content(id, content) VALUES (3,'가슴, 등, 하체 3분할 운동하기');

-- task
INSERT INTO task(id,title,priority,user_email,board_id,register_date,expire_date,completed,task_content_id) VALUES
(1,'스프링 책 읽기',1 , 'todoadmin@gmail.com' , 1 , '2019-03-07 00:00:01','2019-03-31 00:00:01',false,1);
INSERT INTO task(id,title,priority,user_email,board_id,register_date,expire_date,completed,task_content_id) VALUES
(2,'산책하기', 2 , 'todoadmin@gmail.com' , 1 , '2019-03-08 00:00:01', null , true,2);
INSERT INTO task(id,title,priority,user_email,board_id,register_date,expire_date,completed,task_content_id) VALUES
(3,'운동하기', 3 , 'todoadmin@gmail.com' , 1 , '2019-03-21 00:00:01', null , false,3);



