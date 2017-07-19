DROP TABLE IF EXISTS `tb_tings_major`;

CREATE TABLE `tb_tings_major` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(512) DEFAULT NULL COMMENT '标题',
  `content` text COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='事物主表';

LOCK TABLES `tb_tings_major` WRITE;
/*!40000 ALTER TABLE `tb_tings_major` DISABLE KEYS */;

INSERT INTO `tb_tings_major` (`id`, `title`, `content`)
VALUES
	(1,'加拿大','1'),
	(2,'温哥华','2'),
	(3,'oschina','3')
/*!40000 ALTER TABLE `tb_tings_major` ENABLE KEYS */;
UNLOCK TABLES;