--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `secuestro_bien` (
  `id` int(11) NOT NULL,
  `bien_id` int(11) DEFAULT NULL,
  `estado_id` int(11) DEFAULT NULL,
  `prueba_id` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc5h563kim7no5ylqw9wvm8vbu` (`bien_id`),
  KEY `FKk3svwtn9dlfb4dwii41s3jk9x` (`estado_id`),
  KEY `FK8mohm4fi5xgintxfh4cv8210e` (`prueba_id`),
  CONSTRAINT `FK8mohm4fi5xgintxfh4cv8210e` FOREIGN KEY (`prueba_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FKc5h563kim7no5ylqw9wvm8vbu` FOREIGN KEY (`bien_id`) REFERENCES `bien` (`id`),
  CONSTRAINT `FKk3svwtn9dlfb4dwii41s3jk9x` FOREIGN KEY (`estado_id`) REFERENCES `estado_secuestro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
