DROP TABLE IF EXISTS `stockentry`;
DROP TABLE IF EXISTS `discount`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `productgroup`;

CREATE TABLE `productgroup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `price` double NOT NULL,
  `productGroup_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`price`,`productGroup_id`),
  KEY `FK50C664CF1B5743CF` (`productGroup_id`),
  CONSTRAINT `FK50C664CF1B5743CF` FOREIGN KEY (`productGroup_id`) REFERENCES `productgroup` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `discount` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `reductionAmount` double NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_id` (`product_id`,`reductionAmount`),
  KEY `FK1422D9614EA02205` (`product_id`),
  CONSTRAINT `FK1422D9614EA02205` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `stockentry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` int(11) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_id` (`product_id`,`amount`),
  KEY `FK9D13FB1C4EA02205` (`product_id`),
  CONSTRAINT `FK9D13FB1C4EA02205` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
