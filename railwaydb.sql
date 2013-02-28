
DROP TABLE IF EXISTS `Passenger`;
CREATE TABLE IF NOT EXISTS `Passenger` (
  `id` varchar(255) NOT NULL,
  `administrator` tinyint(1) NOT NULL,
  `birthday_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `Passenger` (`id`, `administrator`, `birthday_date`, `name`, `password`, `surname`) VALUES
('e2f621fc-31e5-4430-8e82-df8e42cf85d8', 0, NULL, 'Vasya', 'password', 'Ivanov');

DROP TABLE IF EXISTS `Shedule`;
CREATE TABLE IF NOT EXISTS `Shedule` (
  `id` varchar(255) NOT NULL,
  `station_id` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `train_id` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKDDDBB65AD881E345` (`train_id`),
  KEY `FKDDDBB65A9E0D05` (`station_id`)
);

DROP TABLE IF EXISTS `Station`;
CREATE TABLE IF NOT EXISTS `Station` (
  `id` varchar(255) NOT NULL,
  `name` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `Ticket`;
CREATE TABLE IF NOT EXISTS `Ticket` (
  `id` varchar(255) NOT NULL,
  `number` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `passenger_id` varchar(255) DEFAULT NULL,
  `train_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK954D572C29E0C485` (`passenger_id`),
  KEY `FK954D572CD881E345` (`train_id`)
);

DROP TABLE IF EXISTS `Train`;
CREATE TABLE IF NOT EXISTS `Train` (
  `id` varchar(255) NOT NULL,
  `sits_number` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);


ALTER TABLE `Shedule`
  ADD CONSTRAINT `FKDDDBB65A9E0D05` FOREIGN KEY (`station_id`) REFERENCES `Station` (`id`),
  ADD CONSTRAINT `FKDDDBB65AD881E345` FOREIGN KEY (`train_id`) REFERENCES `Train` (`id`);

ALTER TABLE `Ticket`
  ADD CONSTRAINT `FK954D572CD881E345` FOREIGN KEY (`train_id`) REFERENCES `Train` (`id`),
  ADD CONSTRAINT `FK954D572C29E0C485` FOREIGN KEY (`passenger_id`) REFERENCES `Passenger` (`id`);

