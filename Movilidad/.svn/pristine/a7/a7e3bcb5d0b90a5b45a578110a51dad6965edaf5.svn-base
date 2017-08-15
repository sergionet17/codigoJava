--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `historico_password` (
  `id` varchar(36) NOT NULL,
  `fecha_registro` date NOT NULL,
  `password` varchar(255) NOT NULL,
  `usuario_id` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6exex7raccvb0qkrhb6gym2f1` (`usuario_id`),
  CONSTRAINT `FK6exex7raccvb0qkrhb6gym2f1` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
