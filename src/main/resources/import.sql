INSERT INTO USER(ID,USERID, PASSWORD, USERNAME, EMAIL) VALUES (1,'gintoki','test','긴토키','gintoki@gintama.com');
INSERT INTO USER(ID,USERID, PASSWORD, USERNAME, EMAIL) VALUES (2,'kagura','test','카구라','kagura@gintama.com');
INSERT INTO USER(ID,USERID, PASSWORD, USERNAME, EMAIL) VALUES (3,'shinpachi','test','신파치','shinpachi@gintama.com');
INSERT INTO QUESTION(QUESTION_ID,CREATE_DATE, CONTENTS, TITLE, WRITER_ID) VALUES (1,CURRENT_TIMESTAMP(),'QnA Maker는 주로 응용 프로그램에서 쿼리할 수 있는 FAQ 데이터 원본을 제공합니다.','QnA Maker 도구의 대상 사용자는 누구인가요?',1);
INSERT INTO QUESTION(QUESTION_ID,CREATE_DATE, CONTENTS, TITLE, WRITER_ID) VALUES (2,CURRENT_TIMESTAMP(),'기술 자료는 Markdown을 지원합니다.','KB는 다양한 데이터를 지원하나요?',2);