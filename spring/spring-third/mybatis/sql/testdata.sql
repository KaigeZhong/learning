CREATE DATABASE `mybatis` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
USE mybatis;
CREATE TABLE city (
id INT PRIMARY KEY auto_increment,
c_name VARCHAR(30),
state VARCHAR(30),
country VARCHAR(30)
);

INSERT INTO city (c_name, state, country) VALUES ('San Francisco', 'CA', 'US');