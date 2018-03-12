CREATE TABLE BankUsers
(
 
  B_UserName VARCHAR2(100)NOT NULL  UNIQUE ,
  B_PASSWORD VARCHAR2(50)  NOT NULL,
  B_BALANCE NUMBER(15,3)
 );
 INSERT INTO BankUsers values('user1','pass1',233.00);
 INSERT INTO BankUsers values('user2','pass2',2338.00);
 INSERT INTO BankUsers values('user3','pass3',0.00);
 INSERT INTO BankUsers values('user4','pass4',7890.00);
 INSERT INTO BankUsers values('user5','pass5',10000.00);
 INSERT INTO BankUsers values('user6','pass6',493832.00);