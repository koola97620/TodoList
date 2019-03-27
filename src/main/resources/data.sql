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
INSERT INTO role(id, name) VALUES (1,'ADMIN');
INSERT INTO role(id, name) VALUES (2,'USER');
INSERT INTO role(id, name) VALUES (3,'TEAMFOUNDER');

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
INSERT INTO board(id,name,user,team,register_date) VALUES (2,'Team1 BOARD1','todoadmin@gmail.com' , 1 ,'2019-03-03 00:00:01');
INSERT INTO board(id,name,user,team,register_date) VALUES (3,'개인 BOARD','todoadmin2@gmail.com' , null ,'2019-03-05 00:00:01');
INSERT INTO board(id,name,user,team,register_date) VALUES (4,'Team2 BOARD1','todoadmin3@gmail.com' , 2 ,'2019-03-05 00:00:01');
INSERT INTO board(id,name,user,team,register_date) VALUES (5,'Team2 BOARD2','todoadmin4@gmail.com' , 2 ,'2019-03-07 00:00:01');
INSERT INTO board(id,name,user,team,register_date) VALUES (6,'개인 BOARD2','todoadmin5@gmail.com' , null ,'2019-03-07 00:00:01');
INSERT INTO board(id,name,user,team,register_date) VALUES (7,'개인 BOARD2','todoadmin@gmail.com' , null ,'2019-03-10 00:00:01');
INSERT INTO board(id,name,user,team,register_date) VALUES (8,'Team1 BOARD2','todoadmin@gmail.com' , 1 ,'2019-03-10 00:00:01');

-- task_content

INSERT INTO task_content(id, content) VALUES (1,'스프링 인액션5 다 읽기');
INSERT INTO task_content(id, content) VALUES (2,'동네한바퀴 돌기');
INSERT INTO task_content(id, content) VALUES (3,'가슴, 등, 하체 3분할 운동하기');
INSERT INTO task_content(id, content) VALUES (4,'스터디 발표 준비하기');
INSERT INTO task_content(id, content) VALUES (5,'어벤져스 예약하기. 30장 예약하기');

INSERT INTO task_content(id, content) VALUES (6,'소주 각 2병 이상 의무');
INSERT INTO task_content(id, content) VALUES (7,'맥주 각 5병 이상 의무');
INSERT INTO task_content(id, content) VALUES (8,'양주 각 1병 이상 의무');

INSERT INTO task_content(id, content) VALUES (9,'점심 소고기');
INSERT INTO task_content(id, content) VALUES (10,'점심 치킨');
INSERT INTO task_content(id, content) VALUES (11,'점심 돼지고기');

-- task
INSERT INTO task(id,title,priority,user_email,board_id,register_date,expire_date,completed,task_content_id) VALUES
(1,'스프링 책 읽기',1 , 'todoadmin@gmail.com' , 1 , '2019-03-07 00:00:01','2019-03-31 00:00:01',false,1);
INSERT INTO task(id,title,priority,user_email,board_id,register_date,expire_date,completed,task_content_id) VALUES
(2,'산책하기', 2 , 'todoadmin@gmail.com' , 1 , '2019-03-08 00:00:01', null , true,2);
INSERT INTO task(id,title,priority,user_email,board_id,register_date,expire_date,completed,task_content_id) VALUES
(3,'운동하기', 3 , 'todoadmin@gmail.com' , 1 , '2019-03-21 00:00:01', null , false,3);
INSERT INTO task(id,title,priority,user_email,board_id,register_date,expire_date,completed,task_content_id) VALUES
(4,'스터디 가기', 4 , 'todoadmin@gmail.com' , 1 , '2019-03-22 14:00:01', '2019-03-22 20:00:01' , false,4);
INSERT INTO task(id,title,priority,user_email,board_id,register_date,expire_date,completed,task_content_id) VALUES
(5,'영화 표 예매하기', 5 , 'todoadmin@gmail.com' , 1 , '2019-03-25 09:00:01', '2019-03-25 09:30:00' , false,5);

-- board 2, 8 은 1번팀의 게시판들이다.

INSERT INTO task(id,title,priority,user_email,board_id,register_date,expire_date,completed,task_content_id) VALUES
(6,'회식하기(소주)', 1 , 'todoadmin@gmail.com' , 2 , '2019-03-11 20:00:01', '2019-03-11 23:30:00' , false,6);
INSERT INTO task(id,title,priority,user_email,board_id,register_date,expire_date,completed,task_content_id) VALUES
(7,'회식하기(맥주)', 2 , 'todoadmin2@gmail.com' , 2 , '2019-03-12 20:00:01', '2019-03-11 23:30:00' , false,7);
INSERT INTO task(id,title,priority,user_email,board_id,register_date,expire_date,completed,task_content_id) VALUES
(8,'회식하기(양주)', 3 , 'todoadmin3@gmail.com' , 2 , '2019-03-13 20:00:01', '2019-03-11 23:30:00' , false,8);

INSERT INTO task(id,title,priority,user_email,board_id,register_date,expire_date,completed,task_content_id) VALUES
(9,'점심먹기(소고기)', 1 , 'todoadmin@gmail.com' , 8 , '2019-03-14 12:00:01', '2019-03-14 13:30:00' , false,9);
INSERT INTO task(id,title,priority,user_email,board_id,register_date,expire_date,completed,task_content_id) VALUES
(10,'점심먹기(치킨)', 2 , 'todoadmin2@gmail.com' , 8 , '2019-03-15 12:00:01', '2019-03-15 13:30:00' , false,10);
INSERT INTO task(id,title,priority,user_email,board_id,register_date,expire_date,completed,task_content_id) VALUES
(11,'점심먹기(돼지고기)', 3 , 'todoadmin3@gmail.com' , 8 , '2019-03-16 12:00:01', '2019-03-16 13:30:00' , false,11);


