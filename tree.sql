DROP DATABASE IF EXISTS tree;
CREATE ROLE tree WITH LOGIN CREATEDB PASSWORD 'tree';
CREATE DATABASE tree WITH OWNER tree;
\c tree;
--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: authorities; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE authorities (
    username text NOT NULL,
    authority text NOT NULL,
    unique (username, authority)
);


ALTER TABLE public.authorities OWNER TO postgres;

--
-- Name: group_authorities; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE group_authorities (
    group_id bigint NOT NULL,
    authority text NOT NULL
);


ALTER TABLE public.group_authorities OWNER TO postgres;

--
-- Name: group_members; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE group_members (
    id bigint NOT NULL,
    username text NOT NULL,
    group_id bigint NOT NULL
);


ALTER TABLE public.group_members OWNER TO postgres;

--
-- Name: group_members_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE group_members_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.group_members_id_seq OWNER TO postgres;

--
-- Name: group_members_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE group_members_id_seq OWNED BY group_members.id;


--
-- Name: groups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE groups (
    id bigint NOT NULL,
    group_name text NOT NULL
);


ALTER TABLE public.groups OWNER TO postgres;

--
-- Name: groups_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE groups_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.groups_id_seq OWNER TO postgres;

--
-- Name: groups_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE groups_id_seq OWNED BY groups.id;


--
-- Name: persistent_logins; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE persistent_logins (
    username text NOT NULL,
    series text NOT NULL,
    token text NOT NULL,
    last_used timestamp without time zone NOT NULL
);


ALTER TABLE public.persistent_logins OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE users (
    id integer NOT NULL,
    username text NOT NULL,
    password text NOT NULL,
    email text NOT NULL,
    enabled boolean DEFAULT true NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE users_id_seq OWNED BY users.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY group_members ALTER COLUMN id SET DEFAULT nextval('group_members_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY groups ALTER COLUMN id SET DEFAULT nextval('groups_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);


--
-- Data for Name: authorities; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY authorities (username, authority) FROM stdin;
moona	ROLE_USER
admin	ROLE_ADMIN
admin	ROLE_USER
\.


--
-- Data for Name: group_authorities; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY group_authorities (group_id, authority) FROM stdin;
\.


--
-- Data for Name: group_members; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY group_members (id, username, group_id) FROM stdin;
\.


--
-- Name: group_members_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('group_members_id_seq', 1, false);


--
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY groups (id, group_name) FROM stdin;
\.


--
-- Name: groups_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('groups_id_seq', 1, false);


--
-- Data for Name: persistent_logins; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY persistent_logins (username, series, token, last_used) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users (id, username, password, email, enabled) FROM stdin;
4	moona	fuck	alex.moon@tangentsnowball.com	t
5	admin	admin	admin@admin.com	t
\.


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_id_seq', 5, true);


--
-- Name: group_members_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY group_members
    ADD CONSTRAINT group_members_pkey PRIMARY KEY (id);


--
-- Name: groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY groups
    ADD CONSTRAINT groups_pkey PRIMARY KEY (id);


--
-- Name: persistent_logins_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY persistent_logins
    ADD CONSTRAINT persistent_logins_pkey PRIMARY KEY (series);


--
-- Name: users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: users_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_username_key UNIQUE (username);


--
-- Name: fk_authorities_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY authorities
    ADD CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username);


--
-- Name: fk_group_authorities_group; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY group_authorities
    ADD CONSTRAINT fk_group_authorities_group FOREIGN KEY (group_id) REFERENCES groups(id);


--
-- Name: fk_group_members_group; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY group_members
    ADD CONSTRAINT fk_group_members_group FOREIGN KEY (group_id) REFERENCES groups(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: authorities; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE authorities FROM PUBLIC;
REVOKE ALL ON TABLE authorities FROM postgres;
GRANT ALL ON TABLE authorities TO postgres;
GRANT ALL ON TABLE authorities TO tree;


--
-- Name: group_authorities; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE group_authorities FROM PUBLIC;
REVOKE ALL ON TABLE group_authorities FROM postgres;
GRANT ALL ON TABLE group_authorities TO postgres;
GRANT ALL ON TABLE group_authorities TO tree;


--
-- Name: group_members; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE group_members FROM PUBLIC;
REVOKE ALL ON TABLE group_members FROM postgres;
GRANT ALL ON TABLE group_members TO postgres;
GRANT ALL ON TABLE group_members TO tree;


--
-- Name: group_members_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE group_members_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE group_members_id_seq FROM postgres;
GRANT ALL ON SEQUENCE group_members_id_seq TO postgres;
GRANT ALL ON SEQUENCE group_members_id_seq TO tree;


--
-- Name: groups; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE groups FROM PUBLIC;
REVOKE ALL ON TABLE groups FROM postgres;
GRANT ALL ON TABLE groups TO postgres;
GRANT ALL ON TABLE groups TO tree;


--
-- Name: groups_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE groups_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE groups_id_seq FROM postgres;
GRANT ALL ON SEQUENCE groups_id_seq TO postgres;
GRANT ALL ON SEQUENCE groups_id_seq TO tree;


--
-- Name: persistent_logins; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE persistent_logins FROM PUBLIC;
REVOKE ALL ON TABLE persistent_logins FROM postgres;
GRANT ALL ON TABLE persistent_logins TO postgres;
GRANT ALL ON TABLE persistent_logins TO tree;


--
-- Name: users; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON TABLE users FROM PUBLIC;
REVOKE ALL ON TABLE users FROM postgres;
GRANT ALL ON TABLE users TO postgres;
GRANT ALL ON TABLE users TO tree;


--
-- Name: users_id_seq; Type: ACL; Schema: public; Owner: postgres
--

REVOKE ALL ON SEQUENCE users_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE users_id_seq FROM postgres;
GRANT ALL ON SEQUENCE users_id_seq TO postgres;
GRANT ALL ON SEQUENCE users_id_seq TO tree;


--
-- PostgreSQL database dump complete
--

