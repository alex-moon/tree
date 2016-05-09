DROP TABLE IF EXISTS transactions;
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
    group_id BIGINT NOT NULL,
    authority TEXT NOT NULL
);

CREATE TABLE group_members (
    id BIGSERIAL PRIMARY KEY,
    username TEXT NOT NULL,
    group_id BIGINT NOT NULL
);

CREATE TABLE groups (
    id BIGSERIAL PRIMARY KEY,
    group_name TEXT NOT NULL
);

CREATE TABLE persistent_logins (
    username TEXT NOT NULL,
    series TEXT NOT NULL,
    token TEXT NOT NULL,
    last_used timestamp without time zone NOT NULL
);

CREATE TABLE user_types (
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    authority TEXT NOT NULL
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
    id BIGSERIAL PRIMARY KEY,
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL,
    enabled boolean DEFAULT TRUE NOT NULL,
    user_type_id INT NOT NULL DEFAULT 1 REFERENCES user_types (id),
    api_key TEXT NULL,
    manager_user_id INT NULL REFERENCES users (id)
);

INSERT INTO users VALUES
(1, 'admin', 'password', 'admin@tree.com', TRUE, 5, 'fuck', NULL),
-- regions
(2, 'north', 'password', 'north@tree.com', TRUE, 4, NULL, NULL),
(3, 'midlands', 'password', 'midlands@tree.com', TRUE, 4, NULL, NULL),
(4, 'south', 'password', 'south@tree.com', TRUE, 4, NULL, NULL),
-- areas
(5, 'newcastle', 'password', 'newcastle@tree.com', TRUE, 3, NULL, 2),
(6, 'london', 'password', 'london@tree.com', TRUE, 3, NULL, 4),
-- branches
(7, 'maxi', 'password', 'maxi@tree.com', TRUE, 2, 'CA646128SH', 5),
(8, 'mini', 'password', 'mini@tree.com', TRUE, 2, 'CA771234SH', 5),
(9, 'mega', 'password', 'mega@tree.com', TRUE, 2, 'CA847394SH', 6),
(10, 'modo', 'password', 'modo@tree.com', TRUE, 2, 'CA182639SH', 6),
-- unknown
(11, 'user', 'password', 'user@tree.com', TRUE, 1, NULL, NULL),
-- customers
(12, 'alex', 'password', 'alex@tree.com', TRUE, 6, NULL, NULL),
(13, 'joe', 'password', 'joe@tree.com', TRUE, 6, NULL, NULL);


-- ------------------------------------------------------------------
-- CUSTOMERS
-- ------------------------------------------------------------------
CREATE TABLE customers (
    id BIGSERIAL PRIMARY KEY,
    user_id INT NULL REFERENCES users (id),
    name TEXT NOT NULL,
    barcode TEXT NOT NULL UNIQUE
);

INSERT INTO customers VALUES
(1, 12, 'Alex Moon', 'TR834716EE'),
(2, 13, 'Joe Faquechitte', 'TR748263EE');


-- ------------------------------------------------------------------
-- BRANCHES
-- ------------------------------------------------------------------
CREATE TABLE branches (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users (id),
    name TEXT NOT NULL,
    postcode TEXT NOT NULL
);

INSERT INTO branches VALUES
(1, 7, 'Maxi Branch', 'NE2 2AE'),
(2, 8, 'Mini Branch', 'NE3 1AT'),
(3, 9, 'Mega Branch', 'N4 1LS'),
(4, 10, 'Modo Branch', 'N22 6RP');


-- ------------------------------------------------------------------
-- SPEND
-- ------------------------------------------------------------------
CREATE TABLE transactions (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    customer_id BIGINT NOT NULL REFERENCES customers (id),
    branch_id BIGINT NOT NULL REFERENCES branches (id),
    created_date timestamp NOT NULL,
    spend decimal (9, 2),
    description TEXT NOT NULL
);

INSERT INTO transactions VALUES
(1, 1, 1, '2015-07-08 12:34', 42.69, 'Beer'),
(2, 1, 1, '2015-07-10 23:45', 12.38, 'Wine'),
(3, 1, 2, '2015-07-12 01:23', 9.97, 'Spirits'),
(4, 2, 1, '2015-07-14 21:09', 23.56, 'Tarpaulin'),
(5, 2, 1, '2015-07-16 10:01', 12.95, 'Window Cleaner'),
(6, 2, 2, '2015-07-18 00:42', 69.69, 'Bleach'),
(7, 1, 3, '2015-07-09 22:34', 42.63, 'Mop'),
(8, 1, 3, '2015-07-11 03:45', 12.32, 'Tissues'),
(9, 1, 4, '2015-07-13 11:23', 7.91, 'Vaseline'),
(10, 2, 3, '2015-07-15 21:09', 23.50, 'Paracetamol'),
(11, 2, 3, '2015-07-17 03:01', 12.91, 'Shampoo'),
(12, 2, 4, '2015-07-19 03:42', 6.42, 'Toilet Paper'),
(13, 1, 1, '2015-07-21 21:34', 42.63, 'Chocolate'),
(14, 1, 1, '2015-07-22 09:45', 12.34, 'Mushrooms'),
(15, 1, 2, '2015-07-23 13:23', 4.95, 'Capsicums'),
(16, 2, 1, '2015-07-24 11:09', 23.56, 'Avocados'),
(17, 2, 1, '2015-07-25 12:01', 12.97, 'Cigarettes'),
(18, 2, 2, '2015-07-26 01:42', 6.48, 'String'),
(19, 1, 3, '2015-07-27 21:34', 42.69, 'Candles'),
(20, 1, 3, '2015-07-28 09:45', 12.38, 'Wine Glasses'),
(21, 1, 4, '2015-07-10 09:23', 7.97, 'Wholewheat Spaghetti'),
(22, 2, 3, '2015-07-01 11:09', 23.56, 'Large Metal Bucket'),
(23, 2, 3, '2015-07-04 14:01', 5.95, 'Mayonnaise'),
(24, 2, 4, '2015-07-06 15:42', 2.44, 'Eggs');


-- here's some stupid fucking shit because postgresql is fucking elite
SELECT pg_catalog.setval('branches_id_seq', 100, false);
SELECT pg_catalog.setval('customers_id_seq', 100, false);
SELECT pg_catalog.setval('group_members_id_seq', 100, false);
SELECT pg_catalog.setval('groups_id_seq', 100, false);
SELECT pg_catalog.setval('transactions_id_seq', 100, false);
SELECT pg_catalog.setval('user_types_id_seq', 100, false);
SELECT pg_catalog.setval('users_id_seq', 100, TRUE);
