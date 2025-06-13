-- ユーザー
create table users(
   id int primary key auto_increment,
   name varchar(32) not null,
   password varchar(20) not null,
   thema int,
   created_at datetime default current_timestamp,
   updated_at datetime default current_timestamp on update current_timestamp
);

-- フレーズデータ
create table phrases(
   id int primary key auto_increment,
   name varchar(20) not null,
   remarks varchar(30),
   path varchar(50),
   created_at datetime default current_timestamp,
   updated_at datetime default current_timestamp on update current_timestamp,
   user_id int

);

-- 曲データ
create table musics(
   id int primary key auto_increment,
   name varchar(20) not null,
   created_at datetime default current_timestamp,
   updated_at datetime default current_timestamp on update current_timestamp,
   user_id int

);

-- タグ
create table tags(
   id int primary key auto_increment,
   name varchar(20) not null,
   created_at datetime default current_timestamp,
   updated_at datetime default current_timestamp on update current_timestamp,
   user_id int

);

-- フレーズタグデータ
create table phrases_tags(
   id int primary key auto_increment,
   phrase_id int,
   tag_id int,
   created_at datetime default current_timestamp,
   updated_at datetime default current_timestamp on update current_timestamp
);

-- 曲フレーズデータ
create table musics_phrases(
   id int primary key auto_increment,
   music_id int,
   phrase_id int,
   title varchar(20),
   remarks varchar(30),
   phrase_order int,
   created_at datetime default current_timestamp,
   updated_at datetime default current_timestamp on update current_timestamp

);