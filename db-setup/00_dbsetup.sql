drop role if exists products;
create role products LOGIN password 'sdfkl242fsf';

drop database if exists products;
create database products encoding 'utf8' owner products;
