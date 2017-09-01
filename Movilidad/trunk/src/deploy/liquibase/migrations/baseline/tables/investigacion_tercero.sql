--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `investigacion_tercero` (
  `investigacion_id` binary(255) NOT NULL,
  `tercero_id` varchar(36) NOT NULL,
  UNIQUE KEY `UK_rpc0001j5mu5ynddbqyua0itp` (`tercero_id`),
  KEY `FKpns0sfani3hueaagag21dgyc5` (`investigacion_id`),
  CONSTRAINT `FKpns0sfani3hueaagag21dgyc5` FOREIGN KEY (`investigacion_id`) REFERENCES `investigacion` (`id`),
  CONSTRAINT `FKtp19my0kqvyrlygoucc248k8n` FOREIGN KEY (`tercero_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
