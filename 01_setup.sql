drop table if exists product;
create table product (
       _id serial primary key,
       product_id varchar (10) unique not null,
       name varchar (50) not null,
       model_number varchar (10) not null,
       product_type varchar (20) not null,
       page_title varchar (100),
       site_name varchar (100),
       description text,
       keywords text,
       canonical text,
       standard_price numeric not null,
       standard_price_no_vat numeric not null,
       current_price numeric not null,
       title text,
       subtitle text,
       text text,
       created_dttm timestamp with time zone default now(),
       updated_dttm timestamp with time zone default now()
);

-- sample data
insert into product
(product_id, name, model_number, product_type, page_title, site_name, description, keywords, canonical, standard_price, standard_price_no_vat, current_price, title, subtitle, text)
values
('AF7761', 'Nite Jogger', 'Nite001', 'inline', 'Nite Jogger', 'Adidas DE', 'Awesome nite jogger', 'Nite,jogger,shoe', '//adidas.de/nite', 119.98, 87.09, 119.98, 'Nite Jogger', 'Ntj', 'This is awesome');

insert into product
(product_id, name, model_number, product_type, page_title, site_name, description, keywords, canonical, standard_price, standard_price_no_vat, current_price, title, subtitle, text)
values
('AF7762', 'Nite Jogger', 'Nite001', 'inline', 'Nite Jogger', 'Adidas DE', 'Awesome nite jogger', 'Nite,jogger,shoe', '//adidas.de/nite', 119.98, 87.09, 119.98, 'Nite Jogger', 'Ntj', 'This is awesome');


insert into product
(product_id, name, model_number, product_type, page_title, site_name, description, keywords, canonical, standard_price, standard_price_no_vat, current_price, title, subtitle, text)
values
('AF7763', 'Nite Jogger', 'Nite001', 'inline', 'Nite Jogger', 'Adidas DE', 'Awesome nite jogger', 'Nite,jogger,shoe', '//adidas.de/nite', 119.98, 87.09, 119.98, 'Nite Jogger', 'Ntj', 'This is awesome');


insert into product
(product_id, name, model_number, product_type, page_title, site_name, description, keywords, canonical, standard_price, standard_price_no_vat, current_price, title, subtitle, text)
values
('AF7764', 'Nite Jogger', 'Nite001', 'inline', 'Nite Jogger', 'Adidas DE', 'Awesome nite jogger', 'Nite,jogger,shoe', '//adidas.de/nite', 119.98, 87.09, 119.98, 'Nite Jogger', 'Ntj', 'This is awesome');


insert into product
(product_id, name, model_number, product_type, page_title, site_name, description, keywords, canonical, standard_price, standard_price_no_vat, current_price, title, subtitle, text)
values
('AF7765', 'Nite Jogger', 'Nite001', 'inline', 'Nite Jogger', 'Adidas DE', 'Awesome nite jogger', 'Nite,jogger,shoe', '//adidas.de/nite', 119.98, 87.09, 119.98, 'Nite Jogger', 'Ntj', 'This is awesome');


insert into product
(product_id, name, model_number, product_type, page_title, site_name, description, keywords, canonical, standard_price, standard_price_no_vat, current_price, title, subtitle, text)
values
('AF7766', 'Nite Jogger', 'Nite001', 'inline', 'Nite Jogger', 'Adidas DE', 'Awesome nite jogger', 'Nite,jogger,shoe', '//adidas.de/nite', 119.98, 87.09, 119.98, 'Nite Jogger', 'Ntj', 'This is awesome');
