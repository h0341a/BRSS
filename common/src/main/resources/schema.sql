create table role(
                     id integer auto_increment primary key ,
                     role VARCHAR(16) NOT NULL,
                     index (id)
);
create table user(
                     id integer auto_increment primary key ,
                     username varchar(32) NOT NULL UNIQUE ,
                     nickname varchar(50) NOT NULL UNIQUE ,
                     password varchar(16) NOT NULL,
                     role_id integer NOT NULL  ,
                     foreign key (role_id) references role(id) ON DELETE CASCADE ON UPDATE CASCADE,
                     index(username)
);
create table user_info(
                          uid INTEGER NOT NULL ,
                          bio VARCHAR(160),
                          avatar_url VARCHAR(128),
                          fans_number INTEGER DEFAULT 0,
                          idol_number INTEGER DEFAULT 0,
                          read_books INTEGER DEFAULT 0,
                          unread_books INTEGER DEFAULT 0,
                          register_date datetime not null default now(),
                          FOREIGN KEY (uid) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE ,
                          INDEX (uid)
);
create table relation_group(
                               id integer auto_increment primary key ,
                               group_name VARCHAR(16) NOT NULL UNIQUE,
                               index (id)
);
create table user_group(
                           id integer auto_increment primary key ,
                           uid integer not null ,
                           gid integer not null ,
                           FOREIGN KEY (uid) REFERENCES user(id),
                           index (uid)
);
create table user_relation(
                              uid integer not null ,
                              idol_id integer not null ,
                              status tinyint(1) not null COMMENT '用户关系状态:0为拉黑，1为关注,2为互相关注',
                              ugid integer not null ,
                              FOREIGN KEY (uid) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE ,
                              FOREIGN KEY (idol_id) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE ,
                              FOREIGN KEY (ugid) REFERENCES user_group(id) ON DELETE CASCADE ON UPDATE CASCADE,
                              index (uid)
);
create table user_message(
                             send_uid integer,
                             accept_uid integer,
                             content VARCHAR(320) not null ,
                             send_date datetime not null default now(),
                             send_status tinyint not null COMMENT '发送状态:0发送失败,1发送成功',
                             read_status tinyint not null COMMENT '阅读状态:(0,1)->(已读,未读)',
                             FOREIGN KEY (send_uid) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE ,
                             FOREIGN KEY (accept_uid) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE ,
                             index (accept_uid)
);
create table book(
                     id integer auto_increment primary key ,
                     name VARCHAR(64) NOT NULL ,
                     author VARCHAR(64) NOT NULL,
                     introduction varchar(336) NOT NULL ,
                     commit_date datetime default now(),
                     commit_user integer not null,
                     status tinyint(1) not null default 0 comment '判断该书籍是否通过审核0未通过1通过',
                     FOREIGN KEY (commit_user) REFERENCES user(id)
);
create table user_recommend(
                               id integer auto_increment primary key ,
                               title varchar(128),
                               content text,
                               stars INTEGER DEFAULT 0,
                               recommend_date datetime default now(),
                               status tinyint(1) not null default 0 comment '判断该推荐是否通过审核0未通过1通过',
                               bid integer,
                               uid integer,
                               FOREIGN KEY (uid) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE,
                               FOREIGN KEY (bid) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE,
                               index (uid)
);
create table user_comment(
                             id INTEGER PRIMARY KEY AUTO_INCREMENT,
                             content TEXT NOT NULL,
                             parent_id INTEGER DEFAULT 0,
                             comment_date DATETIME NOT NULL DEFAULT NOW(),
                             uid INTEGER NOT NULL ,
                             rid INTEGER NOT NULL ,
                             FOREIGN KEY (uid) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE ,
                             FOREIGN KEY (rid) REFERENCES user_recommend(id) ON DELETE CASCADE ON UPDATE CASCADE
);
create table user_collection(
                                bid INTEGER NOT NULL ,
                                uid INTEGER NOT NULL ,
                                status INTEGER DEFAULT 0,
                                collection_date DATETIME NOT NULL DEFAULT NOW(),
                                FOREIGN KEY (uid) REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE ,
                                FOREIGN KEY (bid) REFERENCES book(id) ON DELETE CASCADE ON UPDATE CASCADE
);




