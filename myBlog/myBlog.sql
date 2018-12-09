create database myblog;

create user 'yuanBlog'@'localhost' identified by 'myblog123';
grant all privileges on myblog.* to 'yuanBlog'@'localhost';

create table userLogin(
	user_id bigint not null,
	user_name varchar(50) not null,
	password varchar(50) not null,
	status tinyint not null default 0,
	last_login_time timestamp default now(),
	primary key(user_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table blogName(
	blog_id bigint primary key not null,
	blog_name varchar(50) not null,
	blog_status tinyint not null default 0,
	build_time timestamp default now()
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table blogContent(
	blog_id bigint primary key not null,
	blog_essay mediumtext not null
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

create table blogComment(
  id int auto_increment primary key not null,
	blog_id bigint not null,
	blog_com varchar(100) not null,
	comment_status tinyint not null default 0,
	comment_time timestamp default now()
)ENGINE=InnoDB DEFAULT CHARSET=utf8;




insert into userLogin(user_id,user_name,password,status,last_login_time) values(1,'yuanpeng','123456',1,now());

insert into blogName(blog_id,blog_name,blog_status,build_time) values(11,'test1',1,now());

insert into blogName(blog_id,blog_name,blog_status,build_time) values(12,'test2',1,now());

insert into blogName(blog_id,blog_name,blog_status,build_time) values(13,'test3',1,now());

insert into blogContent(blog_id,blog_essay) values(11,'床前明月光，疑是地上霜。');

insert into blogContent(blog_id,blog_essay) values(12,'举头望明月，低头思故乡。');

insert into blogContent(blog_id,blog_essay) values(13,'举头望明月，低头思故乡。dadadad撒大塞埃达带一个行政成本的凯撒就会打开绝对不撒看见大家但大家卡的哈克计划三 大卡萨诺大家可能萨克你的卡今年的快女撒扩大拿开你的苦恼三年大家开始的那肯定你看撒旦你卡的那就看你三大看点健康上的那看到那看到你三年大家看的哪款呢大师的那是肯定那肯定那可否你是盛大的那块对你撒娇看到你撒电脑卡死你打开的那块');

insert into blogComment(blog_id,blog_com,comment_status) values(11,'测试1',1);

insert into blogComment(blog_id,blog_com,comment_status) values(11,'测试2',1);

insert into blogComment(blog_id,blog_com,comment_status) values(11,'测试3',1);