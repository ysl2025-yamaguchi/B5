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
 delete from users;
 alter table `users` auto_increment = 1;
 
 insert into users (name, password) values ('dojouser1', '#SEplus2025SEplus');
 
-- テストphrases
 delete from phrases;
 alter table `phrases` auto_increment = 1;
 
 insert into phrases (name, remarks, user_id) value ('テスト1', '音声データは入っていません', 1);
 insert into phrases (name, remarks, user_id) value ('テスト2', '音声データは入っていません', 1);
 insert into phrases (name, remarks, user_id) value ('テスト3', '音声データは入っていません', 1);
 insert into phrases (name, remarks, user_id) value ('夢ならばどれほどよかったでしょう', '音声データは入っていません', 1); 
 insert into phrases (name, remarks, user_id) value ('きっともうこれ以上傷つくことなど', '音声データは入っていません', 1);
 insert into phrases (name, remarks, user_id) value ('あの日の悲しみさえ', '音声データは入っていません', 1);

-- テストmusics
 delete from musics;
 alter table `musics` auto_increment = 1;
 
 insert into musics (name, user_id) value ('テスト曲', 1);
 insert into musics (name, user_id) value ('Lemon', 1);
 
-- テストtags
 delete from tags;
 alter table `tags` auto_increment = 1;
 
 insert into tags (name, user_id) value ('サビ', 1);
 insert into tags (name, user_id) value ('アップテンポ', 1);
 insert into tags (name, user_id) value ('夏', 1);
 insert into tags (name, user_id) value ('BPM180', 1);
 insert into tags (name, user_id) value ('Bメロ', 1);
 insert into tags (name, user_id) value ('Aメロ', 1);
 insert into tags (name, user_id) value ('柑橘系', 1);
 insert into tags (name, user_id) value ('バラード', 1);
 insert into tags (name, user_id) value ('暗い', 1);
 insert into tags (name, user_id) value ('ギター', 1);
 insert into tags (name, user_id) value ('イントロ', 1);
 
-- テストphrases_tags
 delete from phrases_tags;
 alter table `phrases_tags` auto_increment = 1;
 
 insert into phrases_tags (phrase_id, tag_id) value (1, 1); 
 insert into phrases_tags (phrase_id, tag_id) value (1, 2); 
 insert into phrases_tags (phrase_id, tag_id) value (1, 3); 
 insert into phrases_tags (phrase_id, tag_id) value (2, 3); 
 insert into phrases_tags (phrase_id, tag_id) value (2, 4); 
 insert into phrases_tags (phrase_id, tag_id) value (2, 5); 
 insert into phrases_tags (phrase_id, tag_id) value (3, 6); 
 insert into phrases_tags (phrase_id, tag_id) value (3, 3); 
 insert into phrases_tags (phrase_id, tag_id) value (4, 7);
 insert into phrases_tags (phrase_id, tag_id) value (4, 6);
 insert into phrases_tags (phrase_id, tag_id) value (5, 5);
 insert into phrases_tags (phrase_id, tag_id) value (5, 7);
 insert into phrases_tags (phrase_id, tag_id) value (5, 8);
 insert into phrases_tags (phrase_id, tag_id) value (6, 1);
 insert into phrases_tags (phrase_id, tag_id) value (6, 7);
 insert into phrases_tags (phrase_id, tag_id) value (6, 9);
 insert into phrases_tags (phrase_id, tag_id) value (7, 10);
 insert into phrases_tags (phrase_id, tag_id) value (7, 3);
 insert into phrases_tags (phrase_id, tag_id) value (7, 11);
 
 -- テストmusics_phrases
 delete from musics_phrases;
 alter table `musics_phrases` auto_increment = 1;
 
  insert into musics_phrases (music_id, phrase_id, title, remarks, phrase_order) 
  			  value (2, 4, 'Aメロ', 'Bメジャーコード、イントロなし、クラップ音', 1);
  insert into musics_phrases (music_id, phrase_id, title, remarks, phrase_order) 
  			  value (2, 5, 'Bメロ', '', 2);
  insert into musics_phrases (music_id, phrase_id, title, remarks, phrase_order) 
  			  value (2, 6, 'サビ', 'ストリングス強め', 3);
  insert into musics_phrases (music_id, phrase_id, title, remarks, phrase_order) 
  			  value (1, 7, 'イントロ', 'ギターソロ', 1);
  insert into musics_phrases (music_id, phrase_id, title, remarks, phrase_order) 
  			  value (1, 3, 'Aメロ', '4-5-6、夏のイメージ', 2);
  insert into musics_phrases (music_id, phrase_id, title, remarks, phrase_order) 
  			  value (1, 2, 'Bメロ', '', 3);  			  
  insert into musics_phrases (music_id, phrase_id, title, remarks, phrase_order) 
  			  value (1, 1, 'サビ', '転調', 4); 
  			  
  			  
 select * from users;
 select * from phrases;
 select * from musics;
 select * from tags;
 select * from phrases_tags;
 select * from musics_phrases;