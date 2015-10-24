DROP TABLE IF EXISTS customer_spend;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_types;
DROP TABLE IF EXISTS persistent_logins;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS group_members;
DROP TABLE IF EXISTS group_authorities;

CREATE TABLE group_authorities (
    group_id bigint NOT NULL,
    authority text NOT NULL
);

CREATE TABLE group_members (
    id bigserial primary key,
    username text NOT NULL,
    group_id bigint NOT NULL
);

CREATE TABLE groups (
    id bigserial primary key,
    group_name text NOT NULL
);

CREATE TABLE persistent_logins (
    username text NOT NULL,
    series text NOT NULL,
    token text NOT NULL,
    last_used timestamp without time zone NOT NULL
);

CREATE TABLE user_types (
    id bigserial primary key,
    name text NOT NULL,
    authority text NOT NULL
);

INSERT INTO user_types VALUES
(1, 'Unassigned User', 'ROLE_USER'),
(2, 'Outlet Manager', 'ROLE_OUTLET_MANAGER'),
(3, 'Local Area Manager', 'ROLE_AREA_MANAGER'),
(4, 'Regional Manager', 'ROLE_REGION_MANAGER'),
(5, 'Superuser', 'ROLE_ADMIN'),
-- customers
(6, 'Customer', 'ROLE_CUSTOMER');

CREATE TABLE users (
    id bigserial primary key,
    username text NOT NULL,
    password text NOT NULL,
    email text NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    user_type_id int NOT NULL DEFAULT 1 REFERENCES user_types (id),
    manager_user_id int DEFAULT NULL REFERENCES users (id)
);

INSERT INTO users VALUES
(6, 'admin', 'password', 'admin@tree.com', true, 5, null),
(5, 'regional', 'password', 'regional@tree.com', true, 4, null),
(4, 'area', 'password', 'area@tree.com', true, 3, 5),
(3, 'maxi', 'password', 'maxi@tree.com', true, 2, 4),
(2, 'mini', 'password', 'mini@tree.com', true, 2, 4),
(1, 'user', 'password', 'user@tree.com', true, 1, null),
-- customers
(7, 'alex', 'password', 'alex@tree.com', true, 6, null),
(8, 'joe', 'password', 'joe@tree.com', true, 6, null);

CREATE TABLE customers (
    id bigserial primary key,
    user_id int NULL REFERENCES users (id),
    name text NOT NULL
);

INSERT INTO customers VALUES
(1, 7, 'Alex Moon'),
(2, 8, 'Joe Faquechitte'),
(3, null, null);

CREATE TABLE branches (
    id bigserial PRIMARY KEY,
    user_id bigint NOT NULL REFERENCES users (id),
    name text NOT NULL,
    postcode text NOT NULL
);

CREATE TABLE spend (
    customer_id bigint NOT NULL REFERENCES customers (id),
    branch_id bigint NOT NULL REFERENCES branches (id),
    spend_date date NOT NULL,
    spend_value decimal (9, 2),
    description TEXT NOT NULL
);

INSERT INTO spend VALUES
(1, 3, '2015-07-08', 42.69, 'Beer'),
(1, 3, '2015-07-10', 12.34, 'Wine'),
(1, 2, '2015-07-12', 7.99, 'Spirits'),
(2, 3, '2015-07-14', 23.50, 'Tarpaulin'),
(2, 3, '2015-07-16', 12.99, 'Stain Remover'),
(2, 2, '2015-07-18', 69.42, 'Shovel'),
(3, 2, '2015-10-01', 1.23, 'Bread');
