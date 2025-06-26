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
 
-- テストphrases
 insert into phrases (name, remarks) value ('テスト1', '音声データは入っていません');
 insert into phrases (name, remarks) value ('テスト2', '音声データは入っていません');
 insert into phrases (name, remarks) value ('テスト3', '音声データは入っていません');
 insert into phrases (name, remarks) value ('夢ならばどれほどよかったでしょう', '音声データは入っていません'); 
 insert into phrases (name, remarks) value ('きっともうこれ以上傷つくことなど', '音声データは入っていません');
 insert into phrases (name, remarks) value ('あの日の悲しみさえ', '音声データは入っていません');

-- テストmusics
 insert into phrases (name) value ('テスト曲');
 insert into phrases (name) value ('Lemon');
 
-- テストtags
 insert into tags (name) value ('サビ');
 insert into tags (name) value ('アップテンポ');
 insert into tags (name) value ('夏');
 insert into tags (name) value ('BPM180');
 insert into tags (name) value ('Bメロ');
 insert into tags (name) value ('Aメロ');
 insert into tags (name) value ('柑橘系');
 insert into tags (name) value ('バラード');
 insert into tags (name) value ('暗い');
 insert into tags (name) value ('ギター');
 insert into tags (name) value ('イントロ');
 
-- テストphrases_tags
 insert into phrases_tags (phrase_id, tag_id) value (7, 13); 
 insert into phrases_tags (phrase_id, tag_id) value (7, 14); 
 insert into phrases_tags (phrase_id, tag_id) value (7, 15); 
 insert into phrases_tags (phrase_id, tag_id) value (8, 15); 
 insert into phrases_tags (phrase_id, tag_id) value (8, 16); 
 insert into phrases_tags (phrase_id, tag_id) value (8, 17); 
 insert into phrases_tags (phrase_id, tag_id) value (9, 18); 
 insert into phrases_tags (phrase_id, tag_id) value (9, 15); 
 insert into phrases_tags (phrase_id, tag_id) value (10, 19);
 insert into phrases_tags (phrase_id, tag_id) value (10, 18);
 insert into phrases_tags (phrase_id, tag_id) value (11, 17);
 insert into phrases_tags (phrase_id, tag_id) value (11, 19);
 insert into phrases_tags (phrase_id, tag_id) value (11, 20);
 insert into phrases_tags (phrase_id, tag_id) value (12, 13);
 insert into phrases_tags (phrase_id, tag_id) value (12, 19);
 insert into phrases_tags (phrase_id, tag_id) value (12, 21);
 insert into phrases_tags (phrase_id, tag_id) value (13, 22);
 insert into phrases_tags (phrase_id, tag_id) value (13, 15);
 insert into phrases_tags (phrase_id, tag_id) value (13, 23);
 
 -- テストmusics_phrases
  insert into musics_phrases (music_id, phrase_id, title, remarks phrase_order) 
  			  value (4, 10, 'Aメロ', 'Bメジャーコード、イントロなし、クラップ音', 1);
  insert into musics_phrases (music_id, phrase_id, title, remarks phrase_order) 
  			  value (4, 11, 'Bメロ', '', 2);
  insert into musics_phrases (music_id, phrase_id, title, remarks phrase_order) 
  			  value (4, 12, 'サビ', 'ストリングス強め', 3);
  insert into musics_phrases (music_id, phrase_id, title, remarks phrase_order) 
  			  value (3, 13, 'イントロ', 'ギターソロ', 1);
  insert into musics_phrases (music_id, phrase_id, title, remarks phrase_order) 
  			  value (3, 9, 'Aメロ', '4-5-6、夏のイメージ', 2);
  insert into musics_phrases (music_id, phrase_id, title, remarks phrase_order) 
  			  value (3, 8, 'Bメロ', '', 3);  			  
  insert into musics_phrases (music_id, phrase_id, title, remarks phrase_order) 
  			  value (3, 7, 'サビ', '転調', 4); 
  			  
  			  
 select * from users;
 select * from phrases;
 select * from musics;
 select * from tags;
 select * from phrases_tags;
 select * from musics_phrases;