--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `titulo_ejecutivo_soporte` (
  `titulo_ejecutivo_id` int(11) NOT NULL,
  `soporte_id` varchar(36) NOT NULL,
  UNIQUE KEY `UK_84md7j2en68p9twd0f0i10kir` (`soporte_id`),
  KEY `FKtc0jnpjxhw0lxyc1l07y2fbyg` (`titulo_ejecutivo_id`),
  CONSTRAINT `FKgk94dsbg8ftrduf85grgjkr9v` FOREIGN KEY (`soporte_id`) REFERENCES `documento` (`id`),
  CONSTRAINT `FKtc0jnpjxhw0lxyc1l07y2fbyg` FOREIGN KEY (`titulo_ejecutivo_id`) REFERENCES `titulo_ejecutivo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
