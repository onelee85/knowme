CREATE DATABASE crawl
CHARACTER SET 'utf8'
COLLATE 'utf8_general_ci';


DROP TABLE IF EXISTS crawl.articles;
CREATE TABLE crawl.articles (
	id  bigint unsigned not null AUTO_INCREMENT,
  url varchar(100) not null, #URL
  title varchar(200) not null, #标题
  content varchar(1000) , #内容
  gmt_published datetime ,
  gmt_create timestamp default current_timestamp ,
	gmt_modified datetime,
	unique(url),
  primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;