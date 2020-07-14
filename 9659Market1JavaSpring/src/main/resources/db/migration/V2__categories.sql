drop table if exists categories cascade;
create table categories (id bigserial, title varchar(250), primary key(id));

insert into categories
( title) values
( 'dairy'),
( 'meat'),
( 'vegetables');


drop table if exists categories_n cascade;
create table categories_n (id bigserial, products_id int, categories_id int, primary key(id));

insert into categories_n
( products_id, categories_id) values
( 1, 1),
( 1, 2),
( 1, 3),
( 1, 4),
( 2, 1);


