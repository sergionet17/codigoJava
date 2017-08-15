--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `matriz_estado_comparendos` (
  `id` int(11) NOT NULL,
  `fin_vigencia` datetime NOT NULL,
  `incio_vigencia` datetime NOT NULL,
  `de_id` int(11) NOT NULL,
  `hacia_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKeedlysha1widx2t35irqf7yil` (`de_id`),
  KEY `FKedxo9485bcss34gwdgktev3jm` (`hacia_id`),
  CONSTRAINT `FKedxo9485bcss34gwdgktev3jm` FOREIGN KEY (`hacia_id`) REFERENCES `estado_comparendo` (`id`),
  CONSTRAINT `FKeedlysha1widx2t35irqf7yil` FOREIGN KEY (`de_id`) REFERENCES `estado_comparendo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
