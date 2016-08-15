CREATE DATABASE fenghuo_data;
CREATE TABLE download_zip
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  title VARCHAR(200),
  filePath VARCHAR(50),
  fileName VARCHAR(50)
);
CREATE TABLE basicinfo
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  host VARCHAR(200),
  updatetime VARCHAR(45),
  countryid VARCHAR(45),
  topicid VARCHAR(45),
  heat VARCHAR(45),
  revertcount VARCHAR(45),
  taskurl VARCHAR(2000),
  topictype VARCHAR(45),
  url VARCHAR(2000),
  createtime VARCHAR(45),
  projectid VARCHAR(45),
  areaid VARCHAR(45),
  sitename VARCHAR(45),
  ip VARCHAR(45),
  domaintype VARCHAR(45),
  title VARCHAR(5000),
  forumname VARCHAR(200),
  imagepath VARCHAR(300),
  domain VARCHAR(45),
  datasourcetype VARCHAR(45),
  charset VARCHAR(45),
  filePath TEXT
);
CREATE TABLE article
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  nickname VARCHAR(45),
  postip VARCHAR(45),
  postfloor VARCHAR(45),
  posttime VARCHAR(45),
  location VARCHAR(45),
  userid VARCHAR(45),
  summary TEXT,
  source TEXT,
  postdata TEXT,
  imgurl TEXT,
  basicinfo_id INT(11),
  CONSTRAINT article_basicinfo_id_fk FOREIGN KEY (basicinfo_id) REFERENCES basicinfo (id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE INDEX article_basicinfo_id_fk ON article (basicinfo_id);