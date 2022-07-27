INSERT INTO game VALUES (default, '오버워치');
INSERT INTO game VALUES (default, '리그오브레전드');
INSERT INTO game VALUES (default, '배틀그라운드');

INSERT INTO member VALUES (default, 'gamer1@gamer.com', '게이머1', 'google000001', '1000', 'google', 'USER');
INSERT INTO member VALUES (default, 'gamer2@gamer.com', '게이머2', 'google000002', '1001', 'google', 'USER');
INSERT INTO member VALUES (default, 'gamer3@gamer.com', '게이머3', 'google000003', '1002', 'google', 'USER');
INSERT INTO member VALUES (default, 'gamer4@gamer.com', '게이머4', 'google000004', '1003', 'google', 'USER');
INSERT INTO member VALUES (default, 'gamer5@gamer.com', '게이머5', 'google000005', '1004', 'google', 'USER');

INSERT INTO recruit VALUES (default, '탱커 장인 구함', '안녕하세요. 오버워치 탱커 장인 1명 구합니다.', 1, 'discord.com', 'HIRING', default, now(), now(), 1, 1);
INSERT INTO recruit VALUES (default, '탑 구함', '안녕하세요. 다이아 이상 탑 라이너 구합니다.', 1, 'discord.com', 'HIRING', default, now(), now(), 2, 2);
INSERT INTO recruit VALUES (default, '정글 구함', '안녕하세요. 다이아 이상 정글러 구합니다.', 1, 'discord.com', 'HIRING', default, now(), now(), 2, 2);
INSERT INTO recruit VALUES (default, '배그 스쿼드 같이 하실분?', '같이 배그 스쿼드 하실분 3명 구함.', 3, 'discord.com', 'HIRING', default, now(), now(), 3, 3);
INSERT INTO recruit VALUES (default, '오버워치 ㄱㄱ', '5명 선착순', 5, 'discord.com', 'HIRING', default, now(), now(), 1, 4);
INSERT INTO recruit VALUES (default, '칼바람 구함', '칼바람 4명 구해요.', 4, 'discord.com', 'HIRING', default, now(), now(), 2, 5);

INSERT INTO reply VALUES (default, now(), now(), '혹시 힐러 유저인데 같이 가능 할까요?', 2, null, 1);
INSERT INTO reply VALUES (default, now(), now(), '지금 자리 남아있나요?', 3, null, 1);
INSERT INTO reply VALUES (default, now(), now(), '네 좋아요', 1, 1, 1);
INSERT INTO reply VALUES (default, now(), now(), '지금 디코 들어가겠습니다', 2, 1, 1);
INSERT INTO reply VALUES (default, now(), now(), '안녕하세요', 4, null, 1);




