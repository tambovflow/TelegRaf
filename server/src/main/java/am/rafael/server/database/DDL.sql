CREATE DATABASE IF NOT EXISTS raf_chat_clients_2018;
CREATE TABLE IF NOT EXISTS users(
    id int not null AUTO_INCREMENT UNIQUE,
    login varchar(25) not null UNIQUE PRIMARY KEY ,
    pass varchar(25) not null,
    reg_data timestamp not null default now()
);