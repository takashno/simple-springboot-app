CREATE DATABASE IF NOT EXISTS workdb;
CREATE DATABASE IF NOT EXISTS sampledb;
CREATE USER 'user'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'user'@'%' WITH GRANT OPTION;
