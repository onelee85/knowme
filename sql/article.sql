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