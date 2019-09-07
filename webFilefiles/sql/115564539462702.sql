SELECT * FROM permission.user;
select now();
set global time_zone = '+8:00';
use permission;
INSERT INTO user
 (id,username,password)
VALUES (null,"test","123456");
select count(*) from user