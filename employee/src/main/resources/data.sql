drop table if exists employee;
drop table if exists technology;
drop table if exists user;

create table technology(
id INT AUTO_INCREMENT,
name varchar(50) not null,
PRIMARY KEY(id, name)
);

CREATE TABLE employee(
id INT AUTO_INCREMENT  PRIMARY KEY,
NAME VARCHAR(50) NOT NULL,
technology_id int not null,
foreign key (technology_id) references technology(id));

CREATE TABLE USER(
ID INT AUTO_INCREMENT PRIMARY KEY,
USERNAME VARCHAR(50) NOT NULL,
PASSWORD VARCHAR(100) NOT NULL
);


insert into technology (name) values ('Java');
insert into technology (name) values ('Python');
insert into technology (name) values ('C');


insert into employee (name,technology_id) values ('Navin',2);
insert into employee (name,technology_id) values ('Kiran',1);
insert into employee (name,technology_id) values ('Navin',2);
insert into employee (name,technology_id) values ('Kiran',1);
insert into employee (name,technology_id) values ('Navin',2);
insert into employee (name,technology_id) values ('Kiran',1);
insert into employee (name,technology_id) values ('Navin',2);
insert into employee (name,technology_id) values ('Kiran',1);

INSERT INTO USER (USERNAME,PASSWORD) VALUES ('Lorenzo','$2a$10$F9l18vKL0v5iTwOpBAApyO1RhiluMfBfZIaISz4ud.Kl9924X0fKe');
-- password 1234