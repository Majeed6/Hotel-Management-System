CREATE DATABASE hotel2;

USE hotel2;
CREATE TABLE login(username varchar(25), password varchar(25), is_manager BOOLEAN);
INSERT INTO login VALUES('admin', '12345',1);
INSERT INTO login VALUES('s', 's',0);

CREATE TABLE employee(name varchar(25), age varchar(25), gender varchar(25), job varchar(25), salary varchar(25), phone varchar(25), email varchar(25), ID varchar(25), PRIMARY KEY (ID));

CREATE TABLE room(roomnumber varchar(20), availability varchar(20), cleaning_status varchar(20), price varchar(20), bed_type varchar(20),PRIMARY KEY (roomnumber));

CREATE TABLE customerInfo(document varchar(20), number varchar(20), name varchar(30), gender varchar(30), country varchar(30), room varchar(10), checkintime varchar(80), deposit varchar(20),PRIMARY KEY (number), FOREIGN KEY (room) REFERENCES room(roomnumber));

CREATE TABLE records(document varchar(20), number varchar(20), name varchar(30), gender varchar(30), country varchar(30), room varchar(10), checkintime varchar(80), checkouttime varchar(80) );
