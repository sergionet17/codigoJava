--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `avaluo_bien_prueba` (
  `avaluo_bien_id` int(11) NOT NULL,
  `prueba_id` varchar(36) NOT NULL,
  UNIQUE KEY `UK_mt8wgvvu4xym85aps0p8wsrc9` (`prueba_id`),
  KEY `FKkh4qyc57xh9rtbibbdbci14xk` (`avaluo_bien_id`),
  CONSTRAINT `FKj7uk1objvgvwuhu5jv6f6p2x8` FOREIGN KEY (`prueba_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FKkh4qyc57xh9rtbibbdbci14xk` FOREIGN KEY (`avaluo_bien_id`) REFERENCES `avaluo_bien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
