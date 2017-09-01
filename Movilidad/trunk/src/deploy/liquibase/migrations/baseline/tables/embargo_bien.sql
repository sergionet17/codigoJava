--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `embargo_bien` (
  `id` int(11) NOT NULL,
  `valor_embargo` decimal(19,2) NOT NULL,
  `bien_id` int(11) DEFAULT NULL,
  `estado_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKluqcl0e8ocyfp5qayg1ipb3i6` (`bien_id`),
  KEY `FKfkvlfbujs7xactcaqaaxr2bui` (`estado_id`),
  CONSTRAINT `FKfkvlfbujs7xactcaqaaxr2bui` FOREIGN KEY (`estado_id`) REFERENCES `estado_embargo` (`id`),
  CONSTRAINT `FKluqcl0e8ocyfp5qayg1ipb3i6` FOREIGN KEY (`bien_id`) REFERENCES `bien` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
