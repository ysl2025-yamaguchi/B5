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