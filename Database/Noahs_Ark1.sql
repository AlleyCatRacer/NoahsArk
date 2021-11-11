DROP SCHEMA IF EXISTS noahs_ark CASCADE;
CREATE SCHEMA noahs_ark;
SET SCHEMA 'noahs_ark';

CREATE DOMAIN username AS VARCHAR CHECK (LENGTH(VALUE) > 2) NOT NULL;
CREATE DOMAIN password AS VARCHAR CHECK (LENGTH(VALUE) > 5) NOT NULL;
CREATE DOMAIN postcode AS SMALLINT NOT NULL;
CREATE DOMAIN title AS VARCHAR(60) NOT NULL;

CREATE TABLE app_user (
  username username UNIQUE,
  password password,
  PRIMARY KEY(username, password)
);

CREATE TABLE facility (
  f_id SERIAL PRIMARY KEY,
  name username,
  postcode postcode,
  phone varchar,
  description VARCHAR
);

CREATE TABLE article (
  headline VARCHAR,
  content VARCHAR,
  PRIMARY KEY (headline,content)
);

CREATE TABLE faq_topic (
  title title PRIMARY KEY,
  description VARCHAR
);

CREATE TABLE forum_thread (
  title title PRIMARY KEY,
  description VARCHAR
);

CREATE TABLE comment (
  comment_id SERIAL PRIMARY KEY,
  signature username REFERENCES app_user(username),
	text VARCHAR,
	date date,
	thread title REFERENCES forum_thread(title)
);

CREATE TABLE question (
  q_id SERIAL PRIMARY KEY,
  question VARCHAR NOT NULL,
  answer VARCHAR,
  topic title REFERENCES faq_topic(title)
);

CREATE TABLE professional_user (
  username username UNIQUE,
  password password,
  email varchar,
  phone_no varchar,
  PRIMARY KEY(username, password),
  FOREIGN KEY(username, password) REFERENCES app_user(username, password)
);

CREATE TABLE service (
  service_title title,
  provider username UNIQUE,
  postcode postcode,
  details VARCHAR,
  price SMALLINT,
  PRIMARY KEY(service_title, provider),
  FOREIGN KEY(provider) REFERENCES professional_user(username)
);

ALTER table app_user
Add user_id serial unique;

ALTER table app_user
Add about_me varchar;

CREATE table subscriptions(
  sub_id serial primary key ,
  user_id integer,
  title title references forum_thread(title),
  FOREIGN KEY (user_id) REFERENCES app_user(user_id)

);

CREATE table friends(
  f_id serial primary key ,
  user_id integer,
  friend_id integer references app_user(user_id),
  FOREIGN KEY (user_id) REFERENCES app_user(user_id)

);

CREATE TABLE stories (
  s_id SERIAL PRIMARY KEY ,
  user_id integer,
  story varchar,
  date date,
  FOREIGN KEY(user_id) REFERENCES app_user(user_id)
);

ALTER table service
ALTER column provider Type varchar;

alter table noahs_ark.service drop constraint service_pkey;

alter table noahs_ark.service
	add constraint service_pkey
		primary key (service_title, details);

alter table noahs_ark.service drop constraint service_provider_key;


CREATE table reports(
  r_id serial primary key ,
  user_id integer,
  reported_user_id integer references app_user(user_id),
  FOREIGN KEY (user_id) REFERENCES app_user(user_id)

);

alter table noahs_ark.professional_user drop constraint professional_user_pkey;

alter table noahs_ark.professional_user
	add constraint professional_user_pk
		unique (username);

alter table noahs_ark.professional_user drop constraint professional_user_username_password_fkey;

alter table noahs_ark.professional_user
	add constraint professional_user_username_password_fkey
		foreign key (username) references noahs_ark.app_user (username);

alter table noahs_ark.professional_user drop column password;

alter table noahs_ark.app_user drop constraint app_user_pkey;

alter table noahs_ark.app_user
	add constraint app_user_pkey
		primary key (username);

