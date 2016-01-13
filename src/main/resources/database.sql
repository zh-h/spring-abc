-- --------------------------------------------------------
-- 主机:                           spring-abc.xyz
-- 服务器版本:                        5.5.43-MariaDB - MariaDB Server
-- 服务器操作系统:                      Linux
-- HeidiSQL 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 spring_winds 的数据库结构
CREATE DATABASE IF NOT EXISTS `spring_winds` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `spring_winds`;


-- 导出  表 spring_winds.collection 结构
CREATE TABLE IF NOT EXISTS `collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_at` datetime DEFAULT NULL,
  `topic_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_miyylw8b3lg44gmh7k5y3u72h` (`topic_id`),
  KEY `FK_aanxkg6n45mck5fl454yd9aaa` (`user_id`),
  CONSTRAINT `FK_aanxkg6n45mck5fl454yd9aaa` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_miyylw8b3lg44gmh7k5y3u72h` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 正在导出表  spring_winds.collection 的数据：~6 rows (大约)
DELETE FROM `collection`;



-- 导出  表 spring_winds.comment 结构
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` mediumtext NOT NULL,
  `create_at` datetime NOT NULL,
  `floor` bigint(20) NOT NULL,
  `like_count` bigint(20) NOT NULL,
  `status` int(11) NOT NULL,
  `topic_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1by25k3whex972v78w01wbnar` (`topic_id`),
  KEY `FK_mxoojfj9tmy8088avf57mpm02` (`user_id`),
  CONSTRAINT `FK_1by25k3whex972v78w01wbnar` FOREIGN KEY (`topic_id`) REFERENCES `topic` (`id`),
  CONSTRAINT `FK_mxoojfj9tmy8088avf57mpm02` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=utf8;

-- 正在导出表  spring_winds.comment 的数据：~26 rows (大约)
DELETE FROM `comment`;



-- 导出  表 spring_winds.comment_user 结构
CREATE TABLE IF NOT EXISTS `comment_user` (
  `comment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`comment_id`,`user_id`),
  KEY `FK_obutvg4o609wp066ipmoky7ar` (`user_id`),
  CONSTRAINT `FK_obutvg4o609wp066ipmoky7ar` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_ri39mcdgmam0363rvww15yxoo` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- 导出  表 spring_winds.field 结构
CREATE TABLE IF NOT EXISTS `field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` mediumtext NOT NULL,
  `meta` varchar(255) NOT NULL,
  `property_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_r5pkebdshlf1tcq0ldc7k8i0x` (`property_id`),
  CONSTRAINT `FK_r5pkebdshlf1tcq0ldc7k8i0x` FOREIGN KEY (`property_id`) REFERENCES `property` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- 正在导出表  spring_winds.field 的数据：~19 rows (大约)
DELETE FROM `field`;
/*!40000 ALTER TABLE `field` DISABLE KEYS */;
INSERT INTO `field` (`id`, `content`, `meta`, `property_id`) VALUES
	(1, 'Spring abc', '33333', 1),
	(2, 'Spring abc', '233', 2),
	(3, 'Spring abc', '233', 3),
	(4, 'Spring abc', '233', 4),
	(5, '<div class="row">\r\n            <div class="col-md-4">\r\n                <h4 class="logo">Spring abc</h4>\r\n                <p class="">\r\n                    向<a href="https://pivotal.io/terms-of-use" target="_blank">Spring 的开发者</a>致敬，积极寻求探索革新之道，改变软件工程世界。这里是专注<a target="_blank" href="http://spring.io/">Spring</a>的中文社区：你可以讨论<a target="_blank" href="http://spring.io/">Spring</a>的话题和查询一系列的<a target="_blank" href="http://spring.io/">Spring</a>文章/资料/教程，有任何跟<a target="_blank" href="http://spring.io/projects">Spring</a>相关的看法见解，都可以在此发表\r\n</p><p>\r\n<strong>Build Up Your Enterprise !</strong>\r\n</p>\r\n                <p></p>\r\n                \r\n            </div>\r\n            <div class="col-md-2">\r\n                <h5>学习资料</h5>\r\n                <ul class="list-unstyled">\r\n                   <li><a href="http://spring.io/" target="_blank">Spring.io</a></li>\r\n<li><a href="http://spring.io/" target="_blank">Spring.io</a></li>\r\n<li><a href="http://spring.io/" target="_blank">Spring.io</a></li>\r\n<li><a href="http://spring.io/" target="_blank">Spring.io</a></li>\r\n<li><a href="http://spring.io/" target="_blank">Spring.io</a></li>\r\n<li><a href="http://spring.io/" target="_blank">Spring.io</a></li>\r\n                </ul>\r\n            </div>\r\n            <div class="col-md-2">\r\n                <h5>话题讨论</h5>\r\n                <ul class="list-unstyled">\r\n<li><a href="http://spring.io/" target="_blank">Spring.io</a></li>\r\n<li><a href="http://spring.io/" target="_blank">Spring.io</a></li>\r\n<li><a href="http://spring.io/" target="_blank">Spring.io</a></li>\r\n<li><a href="http://spring.io/" target="_blank">Spring.io</a></li>\r\n<li><a href="http://spring.io/" target="_blank">Spring.io</a></li>\r\n<li><a href="http://spring.io/" target="_blank">Spring.io</a></li>\r\n                </ul>\r\n            </div>\r\n            <div class="col-md-4">\r\n                <h5>友情链接</h5>\r\n                <div class="row">\r\n                    <div class="col-md-6">\r\n                        <ul class="list-unstyled">\r\n <li><a href="http://spring.io/" target="_blank">Spring.io</a></li>\r\n<li><a href="http://spring.io/" target="_blank">Spring.io</a></li>\r\n<li><a href="http://spring.io/" target="_blank">Spring.io</a></li>\r\n<li><a href="http://spring.io/" target="_blank">Spring.io</a></li>\r\n<li><a href="http://spring.io/" target="_blank">Spring.io</a></li>\r\n<li><a href="http://spring.io/" target="_blank">Spring.io</a></li>\r\n                        </ul>\r\n                    </div>\r\n                    <div class="col-md-6">\r\n                        <ul class="list-unstyled">\r\n   <li><a href="https://github.com/zh-h/Spring-abc" target="_blank">GitHub</a></li>\r\n   <li><a href="http://spring-abc.xyz/docs.html" target="_blank">文档</a></li>\r\n                        </ul>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </div>', '233', 6),
	(6, '4444444', '233', 7),
	(7, '<script></script>', '233', 8),
	(8, '<div class="panel-body">\r\n	  \r\n	  <a href="http://www.oneapm.com/ci/docker.html?utm_source=golangtc&utm_medium=logo&utm_term=docker&utm_content=logolink&utm_campaign=NovAds&from=maadfiNoxj" target="_blank"><img src="http://77fkk5.com1.z0.glb.clouddn.com/bar/oneapm-4.png" width="100%"></a>\r\n	  \r\n	  <a href="http://www.51idc.com/activity/ACCloudHK.html" target="_blank"><img src="http://77fkk5.com1.z0.glb.clouddn.com/bar/51idc-5.png" width="100%"></a>\r\n	  \r\n	  <a href="https://www.teambition.com/?utm_source=golangtc&utm_medium=banner" target="_blank"><img src="http://77fkk5.com1.z0.glb.clouddn.com/bar/Teambition-Banner-2.jpg" width="100%"></a>\r\n	  \r\n	  <a href="http://click.aliyun.com/m/1621/" target="_blank"><img src="http://77fkk5.com1.z0.glb.clouddn.com/bar/aliyun-c7.jpg" width="100%"></a>\r\n	  \r\n	  <a href="http://www.jiankongbao.com/new_docker/?ref=golangtc" target="_blank"><img src="http://77fkk5.com1.z0.glb.clouddn.com/bar/jiankongbao-7.png" width="100%"></a>\r\n	  \r\n	  <a href="http://www.ucloud.cn/site/active/tic.html?utm_source=cost&utm_campaign=Golang%E7%A4%BE%E5%8C%BA&utm_medium=display&utm_content=tic" target="_blank"><img src="http://77fkk5.com1.z0.glb.clouddn.com/bar/ucloud-2.jpg" width="100%"></a>\r\n	  \r\n	</div>', '233', 9),
	(9, '<div class="panel-body">\r\n	  \r\n	  <a href="http://www.oneapm.com/ci/docker.html?utm_source=golangtc&utm_medium=logo&utm_term=docker&utm_content=logolink&utm_campaign=NovAds&from=maadfiNoxj" target="_blank"><img src="http://77fkk5.com1.z0.glb.clouddn.com/bar/oneapm-4.png" width="100%"></a>\r\n	  \r\n	  <a href="http://www.51idc.com/activity/ACCloudHK.html" target="_blank"><img src="http://77fkk5.com1.z0.glb.clouddn.com/bar/51idc-5.png" width="100%"></a>\r\n	  \r\n	  <a href="https://www.teambition.com/?utm_source=golangtc&utm_medium=banner" target="_blank"><img src="http://77fkk5.com1.z0.glb.clouddn.com/bar/Teambition-Banner-2.jpg" width="100%"></a>\r\n	  \r\n	  <a href="http://click.aliyun.com/m/1621/" target="_blank"><img src="http://77fkk5.com1.z0.glb.clouddn.com/bar/aliyun-c7.jpg" width="100%"></a>\r\n	  \r\n	  <a href="http://www.jiankongbao.com/new_docker/?ref=golangtc" target="_blank"><img src="http://77fkk5.com1.z0.glb.clouddn.com/bar/jiankongbao-7.png" width="100%"></a>\r\n	  \r\n	  <a href="http://www.ucloud.cn/site/active/tic.html?utm_source=cost&utm_campaign=Golang%E7%A4%BE%E5%8C%BA&utm_medium=display&utm_content=tic" target="_blank"><img src="http://77fkk5.com1.z0.glb.clouddn.com/bar/ucloud-2.jpg" width="100%"></a>\r\n	  \r\n	</div>', '233', 10),
	(10, '2323', '233', 11),
	(11, '2323', '233', 12),
	(12, 'tipc_signup', '233', 13),
	(13, '<ul style="margin-top: 0px;">\r\n            <li><span class="f13">主题标题</span><div class="sep10"></div>\r\n            请在标题中描述内容要点。如果一件事情在标题的长度内就已经可以说清楚，那就没有必要写正文了。\r\n            <div class="sep10"></div>\r\n            </li>\r\n            <li><div class="fr" style="margin-top: -5px; margin-right: 5px;"><span class="f13">正文</span><div class="sep10"></div>\r\n            可以在正文中为你要发布的主题添加更多细节。 支持 <span style="font-family: Consolas, \'Panic Sans\', mono"><a href="https://help.github.com/articles/github-flavored-markdown" target="_blank">GitHub Flavored Markdown</a></span> 文本标记语法。\r\n            <div class="sep10"></div>\r\n            在正式提交之前，你可以点击“preview”来查看 Markdown 正文的实际渲染效果。\r\n             <div class="sep10"></div>\r\n             你可以点击“upload” 选择需要的图片后上传后点击插入按钮，插入md的图片标签。\r\n            <div class="sep10"></div>\r\n            </li>\r\n            <li><span class="f13">选择节点</span><div class="sep10"></div>\r\n            请为你的主题选择一个节点。恰当的归类会让你发布的信息更加有用。\r\n            <div class="sep10"></div>\r\n          	如果有必要，管理员会帮助迁移节点\r\n            </li>\r\n           <li><span class="f13">选择节点</span><div class="sep10"></div>\r\n            请为你的主题选择一个节点。恰当的归类会让你发布的信息更加有用。\r\n            <div class="sep10"></div>\r\n          	如果有必要，管理员会帮助迁移节点\r\n            </li>\r\n        </ul>', '233', 14),
	(14, 'tipc_signup', '233', 15),
	(15, '233', '233', 16),
	(16, '233', '233', 17),
	(17, '233', '233', 18),
	(19, '<li class=""><a href="http://spring-abc.xyz/">话题</a></li>\r\n<li class=""><a href="http://spring-abc.xyz/nodes">节点</a></li>\r\n<li><a href="http://spring-abc.xyz/users/" >OOP</a></li>\r\n<li><a href="http://spring-abc.xyz/videos.html" >Videos</a></li>\r\n<li class=""><a href="http://spring-abc.xyz/docs.html">文档</a></li>\r\n<li><a href="http://spring.io/" target="_blank">Spring.io</a></li>', '233', 5),
	(20, 'admin', 'sfsf', 19);
/*!40000 ALTER TABLE `field` ENABLE KEYS */;


-- 导出  表 spring_winds.focus 结构
CREATE TABLE IF NOT EXISTS `focus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_at` datetime NOT NULL,
  `node_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_dcysq5t6w4yvt0twe7a6umw1h` (`node_id`),
  KEY `FK_shp43xvgrewctw5s4vo2993eq` (`user_id`),
  CONSTRAINT `FK_dcysq5t6w4yvt0twe7a6umw1h` FOREIGN KEY (`node_id`) REFERENCES `node` (`id`),
  CONSTRAINT `FK_shp43xvgrewctw5s4vo2993eq` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在导出表  spring_winds.focus 的数据：~4 rows (大约)
DELETE FROM `focus`;



-- 导出  表 spring_winds.follow 结构
CREATE TABLE IF NOT EXISTS `follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_at` datetime NOT NULL,
  `follower_id` int(11) NOT NULL,
  `following_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_i2ewwo9inkvk8vaqrxj5v8f5i` (`follower_id`),
  KEY `FK_tkjr0k3x569btja89gb5vccck` (`following_id`),
  CONSTRAINT `FK_i2ewwo9inkvk8vaqrxj5v8f5i` FOREIGN KEY (`follower_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_tkjr0k3x569btja89gb5vccck` FOREIGN KEY (`following_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- 正在导出表  spring_winds.follow 的数据：~4 rows (大约)
DELETE FROM `follow`;



-- 导出  表 spring_winds.node 结构
CREATE TABLE IF NOT EXISTS `node` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `topic_count` bigint(20) DEFAULT '0',
  `name` varchar(255) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `section_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fwigxdmj6bsrpcmhcgpmlsirh` (`name`),
  KEY `FK_kihhhba7yo0oh5kplfgafba4e` (`section_id`),
  CONSTRAINT `FK_kihhhba7yo0oh5kplfgafba4e` FOREIGN KEY (`section_id`) REFERENCES `section` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- 正在导出表  spring_winds.node 的数据：~12 rows (大约)
DELETE FROM `node`;
/*!40000 ALTER TABLE `node` DISABLE KEYS */;
INSERT INTO `node` (`id`, `avatar`, `description`, `topic_count`, `name`, `status`, `section_id`) VALUES
	(8, NULL, '就是用来测试的', 6, 'test', 0, 2);
/*!40000 ALTER TABLE `node` ENABLE KEYS */;


-- 导出  表 spring_winds.notification 结构
CREATE TABLE IF NOT EXISTS `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` int(11) NOT NULL DEFAULT '0',
  `comment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_inxhsk5bf6gpm7b41orsar7fc` (`comment_id`),
  KEY `FK_1urdwwsh2ti15ta6f6p5dbdcp` (`user_id`),
  CONSTRAINT `FK_1urdwwsh2ti15ta6f6p5dbdcp` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_inxhsk5bf6gpm7b41orsar7fc` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- 正在导出表  spring_winds.notification 的数据：~33 rows (大约)
DELETE FROM `notification`;



-- 导出  表 spring_winds.page 结构
CREATE TABLE IF NOT EXISTS `page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` longtext NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `view_count` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_f6ctahf1jnokrtpp5wblm0shb` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  spring_winds.page 的数据：~3 rows (大约)
DELETE FROM `page`;
/*!40000 ALTER TABLE `page` DISABLE KEYS */;
INSERT INTO `page` (`id`, `content`, `create_at`, `title`, `update_at`, `url`, `view_count`) VALUES
	(1, '# Spring abc 社区\r\n\r\n```\r\n呵呵哒\r\n\r\n```', '2015-12-03 03:02:04', 'about', NULL, 'about', 1),
	(2, '# Comming soon !', '2015-12-08 21:08:05', 'Videos', NULL, 'videos', 1),
	(3, '# 这是文档', '2015-12-15 21:37:58', '相关文档', NULL, 'docs', 1);
/*!40000 ALTER TABLE `page` ENABLE KEYS */;


-- 导出  表 spring_winds.property 结构
CREATE TABLE IF NOT EXISTS `property` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- 正在导出表  spring_winds.property 的数据：~19 rows (大约)
DELETE FROM `property`;
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
INSERT INTO `property` (`id`, `keyword`, `name`) VALUES
	(1, 'site_name', 'site_name'),
	(2, 'site_description', 'site_description'),
	(3, 'site_keywords', 'site_keywords'),
	(4, 'site_ICP', 'site_ICP'),
	(5, 'nav_main', 'nav_main'),
	(6, 'nav_footer', 'nav_footer'),
	(7, 'footer_description', 'footer_description'),
	(8, 'footer_script', 'footer_script'),
	(9, 'ad_side', 'ad_side'),
	(10, 'ad_inner', 'ad_inner'),
	(11, 'tip_notic', 'tip_notic'),
	(12, 'tip_welcome', 'tip_welcome'),
	(13, 'tip_signup', 'tip_signup'),
	(14, 'tip_topic', 'tip_topic'),
	(15, 'tip_comment', 'tip_comment'),
	(16, 'limit_edit', 'limit_edit'),
	(17, 'limit_delete', 'limit_delete'),
	(18, 'limit_comment', 'limit_comment'),
	(19, 'comment_edit', 'comment_edit');
/*!40000 ALTER TABLE `property` ENABLE KEYS */;


-- 导出  表 spring_winds.section 结构
CREATE TABLE IF NOT EXISTS `section` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rwqtt5x96oahjdtqt20vxu4um` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在导出表  spring_winds.section 的数据：~4 rows (大约)
DELETE FROM `section`;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` (`id`, `name`, `sort`) VALUES
	(2, 'test', 0);
/*!40000 ALTER TABLE `section` ENABLE KEYS */;


-- 导出  表 spring_winds.topic 结构
CREATE TABLE IF NOT EXISTS `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `collecton_id` int(11) DEFAULT NULL,
  `comment_count` bigint(20) unsigned DEFAULT '0',
  `content` longtext NOT NULL,
  `create_at` datetime DEFAULT NULL,
  `last_comment_at` datetime DEFAULT NULL,
  `last_comment_user` tinyblob,
  `last_comment_user_id` int(11) DEFAULT NULL,
  `like_count` bigint(20) unsigned DEFAULT '0',
  `score` bigint(20) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `update_at` datetime DEFAULT NULL,
  `view_count` bigint(20) unsigned DEFAULT '0',
  `node_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_99um5d29ufxu4mrm2lbt3a9ms` (`node_id`),
  KEY `FK_c975xs66f0v6extdjdl0csctr` (`user_id`),
  CONSTRAINT `FK_99um5d29ufxu4mrm2lbt3a9ms` FOREIGN KEY (`node_id`) REFERENCES `node` (`id`),
  CONSTRAINT `FK_c975xs66f0v6extdjdl0csctr` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- 正在导出表  spring_winds.topic 的数据：~8 rows (大约)
DELETE FROM `topic`;



-- 导出  表 spring_winds.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `comment_count` bigint(20) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `create_at` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `github` varchar(255) DEFAULT NULL,
  `home_page` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `number` bigint(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `points` bigint(20) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `signature` varchar(255) DEFAULT NULL,
  `topic_count` bigint(20) DEFAULT NULL,
  `twitter` varchar(255) DEFAULT NULL,
  `update_at` datetime DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 正在导出表  spring_winds.user 的数据：~6 rows (大约)
DELETE FROM `user`;

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
