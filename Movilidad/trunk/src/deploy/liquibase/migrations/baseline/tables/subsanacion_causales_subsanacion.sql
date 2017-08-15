--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `subsanacion_causales_subsanacion` (
  `subsanacion_id` binary(255) NOT NULL,
  `causales_subsanacion_id` int(11) NOT NULL,
  UNIQUE KEY `UK_iirxrbl3samnkx2vy63m6buo2` (`causales_subsanacion_id`),
  KEY `FKq8qqgbmwuu30ht6pe1781djm8` (`subsanacion_id`),
  CONSTRAINT `FK3r3o6120mv8c12jbig2wiu5gf` FOREIGN KEY (`causales_subsanacion_id`) REFERENCES `causal_subsanacion` (`id`),
  CONSTRAINT `FKq8qqgbmwuu30ht6pe1781djm8` FOREIGN KEY (`subsanacion_id`) REFERENCES `subsanacion` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
