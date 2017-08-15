--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `tramite_secuestro` (
  `id` int(11) NOT NULL,
  `abogado_comisionado_id` varchar(36) NOT NULL,
  `acto_administrativo_id` varchar(36) NOT NULL,
  `bien_id` int(11) NOT NULL,
  `decision_id` int(11) NOT NULL,
  `secuestre_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8i4h8cpy7pe7fa4nafkbk6c08` (`abogado_comisionado_id`),
  KEY `FK1ey9dyid3wu1p1mjnufru1iss` (`acto_administrativo_id`),
  KEY `FKmgvgd5xtflfufwhjrpfr4f3t7` (`bien_id`),
  KEY `FKfkseqjxdgf055yto6loc2s9qw` (`decision_id`),
  KEY `FKn9ee1pp8ih05vtedxb09ekcsv` (`secuestre_id`),
  CONSTRAINT `FK1ey9dyid3wu1p1mjnufru1iss` FOREIGN KEY (`acto_administrativo_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FK8i4h8cpy7pe7fa4nafkbk6c08` FOREIGN KEY (`abogado_comisionado_id`) REFERENCES `persona` (`id`),
  CONSTRAINT `FKfkseqjxdgf055yto6loc2s9qw` FOREIGN KEY (`decision_id`) REFERENCES `decision_secuestro` (`id`),
  CONSTRAINT `FKmgvgd5xtflfufwhjrpfr4f3t7` FOREIGN KEY (`bien_id`) REFERENCES `bien` (`id`),
  CONSTRAINT `FKn9ee1pp8ih05vtedxb09ekcsv` FOREIGN KEY (`secuestre_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
