create database b5;
use b5;

-- ユーザー
create table users(
   id int primary key auto_increment,
   name varchar(32) not null unique,
   password varchar(20) not null,
   thema int default 1,
   created_at datetime default current_timestamp,
   updated_at datetime default current_timestamp on update current_timestamp
);
 
-- フレーズデータ
create table phrases(
   id int primary key auto_increment,
   name varchar(20) not null,
   remarks varchar(30),
   path varchar(50),
   user_id int,
   created_at datetime default current_timestamp,
   updated_at datetime default current_timestamp on update current_timestamp
);

-- 曲データ
create table musics(
   id int primary key auto_increment,
   name varchar(20) not null,
   user_id int,
   created_at datetime default current_timestamp,
   updated_at datetime default current_timestamp on update current_timestamp
);

-- タグ
create table tags(
   id int primary key auto_increment,
   name varchar(20) not null,
   user_id int,
   created_at datetime default current_timestamp,
   updated_at datetime default current_timestamp on update current_timestamp
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

-- テストusers
 insert into users (name, password) values ('dojouser1', '#SEplus2025SEplus');
 insert into users (name, password) values ('dojouser1', '#SEplus2025SEplus');
 
-- テストphrases
 insert into phrases (name, remarks) value ('テスト1', '音声データは入っていません');
 insert into phrases (name, remarks) value ('テスト2', '音声データは入っていません');
 insert into phrases (name, remarks) value ('テスト3', '音声データは入っていません');
 insert into phrases (name, remarks) value ('あの日の悲しみさえ', '音声データは入っていません');
 insert into phrases (name, remarks) value ('夢ならばどれほどよかったでしょう', '音声データは入っていません');
 insert into phrases (name, remarks) value ('きっともうこれ以上傷つくことなど', '音声データは入っていません');

-- テストmusics
 insert into phrases (name) value ('Lemon');
 insert into phrases (name) value ('テスト曲');
 
-- テストtags
 insert into tags (name) value ('サビ');
 insert into tags (name) value ('アップテンポ');
 insert into tags (name) value ('夏');
 insert into tags (name) value ('Aメロ');
 insert into tags (name) value ('BPM180');
 insert into tags (name) value ('Bメロ');
 insert into tags (name) value ('柑橘系');
 
-- テストphrases_tags
 insert into phrases_tags (phrase_id, tag_id) value (1, 4); 
 insert into phrases_tags (phrase_id, tag_id) value (1, 5); 
 insert into phrases_tags (phrase_id, tag_id) value (1, 6); 
 insert into phrases_tags (phrase_id, tag_id) value (2, 7); 
 insert into phrases_tags (phrase_id, tag_id) value (2, 6); 
 insert into phrases_tags (phrase_id, tag_id) value (2, 8); 
 insert into phrases_tags (phrase_id, tag_id) value (3, 9); 
 insert into phrases_tags (phrase_id, tag_id) value (3, 6); 
 insert into phrases_tags (phrase_id, tag_id) value (4, 4);
 insert into phrases_tags (phrase_id, tag_id) value (4, 10);
 insert into phrases_tags (phrase_id, tag_id) value (5, 7);
 insert into phrases_tags (phrase_id, tag_id) value (5, 10);
 insert into phrases_tags (phrase_id, tag_id) value (6, 9);
 
 select * from users;