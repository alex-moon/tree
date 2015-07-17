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
(5, 'Superuser', 'ROLE_ADMIN');

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
(3, 'branch', 'password', 'branch@tree.com', true, 2, 4),
(2, 'outlet', 'password', 'outlet@tree.com', true, 2, 4),
(1, 'user', 'password', 'user@tree.com', true, 1, null);

CREATE TABLE customers (
    id bigserial primary key,
    name text NOT NULL,
    manager_user_id int NOT NULL REFERENCES users (id)
);

INSERT INTO customers VALUES
(1, 'Outlet Manager Customer', 2),
(2, 'Branch Manager Customer', 3);

CREATE TABLE customer_spend (
    customer_id bigint NOT NULL REFERENCES customers (id),
    spend_date date NOT NULL,
    spend_value decimal (9, 2)
);

INSERT INTO customer_spend VALUES
(1, '2015-07-08', 42.69),
(1, '2015-07-10', 12.34),
(1, '2015-07-12', 7.99),
(2, '2015-07-14', 23.50),
(2, '2015-07-16', 12.99),
(2, '2015-07-18', 69.42);
