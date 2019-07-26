drop role if exists products;
create role products LOGIN password 'stcudorp231';

drop database if exists products;
create database products encoding 'utf8' owner products;
