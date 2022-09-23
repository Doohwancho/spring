# MySQL workbench DB 및 사용자 생성

set global validate_password.policy=LOW;       # 비밀번호 길이가 8자 이상이기만 하면 
create user 'cho'@'%' identified by 'doohwancho';
GRANT ALL PRIVILEGES ON *.* TO 'cho'@'%';
create database security;
use security;
