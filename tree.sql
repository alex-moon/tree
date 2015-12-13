DROP TABLE IF EXISTS spend;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS branches;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_types;
DROP TABLE IF EXISTS persistent_logins;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS group_members;
DROP TABLE IF EXISTS group_authorities;


-- ------------------------------------------------------------------
-- AUTH
-- ------------------------------------------------------------------
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


-- ------------------------------------------------------------------
-- USERS
-- ------------------------------------------------------------------
CREATE TABLE users (
    id bigserial primary key,
    username text NOT NULL,
    password text NOT NULL,
    email text NOT NULL,
    enabled boolean DEFAULT true NOT NULL,
    user_type_id int NOT NULL DEFAULT 1 REFERENCES user_types (id),
    api_key text NULL
);

INSERT INTO users VALUES
(6, 'admin', 'password', 'admin@tree.com', true, 5, 'fuck'),
-- managers
(5, 'regional', 'password', 'regional@tree.com', true, 4, null),
(4, 'area', 'password', 'area@tree.com', true, 3, null),
-- branches
(3, 'maxi', 'password', 'maxi@tree.com', true, 2, 'CA123456SH'),
(2, 'mini', 'password', 'mini@tree.com', true, 2, 'CA654321SH'),
-- unknown
(1, 'user', 'password', 'user@tree.com', true, 1, null),
-- customers
(7, 'alex', 'password', 'alex@tree.com', true, 6, null),
(8, 'joe', 'password', 'joe@tree.com', true, 6, null);


-- ------------------------------------------------------------------
-- CUSTOMERS
-- ------------------------------------------------------------------
CREATE TABLE customers (
    id bigserial primary key,
    user_id int NULL REFERENCES users (id),
    name text NOT NULL,
    barcode text NOT NULL UNIQUE
);

INSERT INTO customers VALUES
(1, 7, 'Alex Moon', 'TR123456EE'),
(2, 8, 'Joe Faquechitte', 'TR654321EE');


-- ------------------------------------------------------------------
-- BRANCHES
-- ------------------------------------------------------------------
CREATE TABLE branches (
    id bigserial PRIMARY KEY,
    user_id bigint NOT NULL REFERENCES users (id),
    name text NOT NULL,
    postcode text NOT NULL
);

INSERT INTO branches VALUES
(1, 3, 'Maxi Branch', 'N4 1LS'),
(2, 2, 'Mini Branch', 'NE3 1AT');


-- ------------------------------------------------------------------
-- SPEND
-- ------------------------------------------------------------------
CREATE TABLE spend (
    id bigserial NOT NULL PRIMARY KEY,
    customer_id bigint NOT NULL REFERENCES customers (id),
    branch_id bigint NOT NULL REFERENCES branches (id),
    spend_date timestamp NOT NULL,
    spend_amount decimal (9, 2),
    description TEXT NOT NULL
);

INSERT INTO spend VALUES
(1, 1, 1, '2015-07-08 12:34', 42.69, 'Beer'),
(2, 1, 1, '2015-07-10 23:45', 12.34, 'Wine'),
(3, 1, 2, '2015-07-12 01:23', 7.99, 'Spirits'),
(4, 2, 1, '2015-07-14 21:09', 23.50, 'Tarpaulin'),
(5, 2, 1, '2015-07-16 10:01', 12.99, 'Stain Remover'),
(6, 2, 2, '2015-07-18 00:42', 69.42, 'Shovel');


-- here's some stupid fucking shit because postgresql is fucking elite
SELECT pg_catalog.setval('branches_id_seq', 10, false);
SELECT pg_catalog.setval('customers_id_seq', 10, false);
SELECT pg_catalog.setval('group_members_id_seq', 10, false);
SELECT pg_catalog.setval('groups_id_seq', 10, false);
SELECT pg_catalog.setval('spend_id_seq', 10, false);
SELECT pg_catalog.setval('user_types_id_seq', 10, false);
SELECT pg_catalog.setval('users_id_seq', 10, true);
