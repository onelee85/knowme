CREATE DATABASE crawl
CHARACTER SET 'utf8'
COLLATE 'utf8_general_ci';

DROP TABLE IF EXISTS crawl.articles;
CREATE TABLE crawl.articles
(
   id             BIGINT UNSIGNED   NOT NULL AUTO_INCREMENT,
   source         VARCHAR(100),
   url            VARCHAR(100)      NOT NULL,
   title          VARCHAR(200)      NOT NULL,
   content        VARCHAR(1000),
   gmt_published  DATETIME,
   gmt_create     TIMESTAMP         DEFAULT CURRENT_TIMESTAMP NOT NULL,
   gmt_modified   DATETIME,
   PRIMARY KEY (id)
)
ENGINE=InnoDB;

CREATE UNIQUE INDEX url
   ON crawl.articles (url ASC);

DROP TABLE IF EXISTS `event_logs`;
CREATE TABLE `event_logs` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT ,
  `user_id` bigint(20) NOT NULL,
  `a_id` bigint(20) NOT NULL comment 'articles 文章ID',
  `view_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `prefer_degree` int(11) NOT NULL DEFAULT '0' comment '0：浏览，1：点赞，2：收藏]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

