--liquibase formatted sql

--changeset converter:baseline dbms:mysql
CREATE TABLE `persona_emails` (
  `persona_id` varchar(36) NOT NULL,
  `emails_id` int(11) NOT NULL,
  UNIQUE KEY `UK_g9n98eingt1yroep3p07mjgte` (`emails_id`),
  KEY `FKe72xbchkv6tmoitc6fwd89343` (`persona_id`),
  CONSTRAINT `FK6xq82htnye1k99pwsnh22kwhu` FOREIGN KEY (`emails_id`) REFERENCES `email` (`id`),
  CONSTRAINT `FKe72xbchkv6tmoitc6fwd89343` FOREIGN KEY (`persona_id`) REFERENCES `persona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
