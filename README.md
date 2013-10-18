Abacus3D
========

Abacus GUI
==========

Scripts to create MySQL tables:

- CREATE TABLE `App` (
  `idApp` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `status` varchar(45) DEFAULT 'available',
  `instDate` datetime DEFAULT NULL,
  `logo` varchar(180) DEFAULT 'blankLogo.gif',
  `instUser` int(11) DEFAULT NULL,
  PRIMARY KEY (`idApp`),
  UNIQUE KEY `idApp_UNIQUE` (`idApp`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `insUser_idx` (`instUser`),
  CONSTRAINT `insUser` FOREIGN KEY (`instUser`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

- CREATE TABLE `Node` (
  `idNode` int(11) NOT NULL AUTO_INCREMENT,
  `hostname` varchar(45) DEFAULT NULL,
  `ip` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `cpu` varchar(45) DEFAULT NULL,
  `mem` varchar(45) DEFAULT NULL,
  `disk` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idNode`),
  UNIQUE KEY `idNode_UNIQUE` (`idNode`),
  UNIQUE KEY `hostname_UNIQUE` (`hostname`),
  UNIQUE KEY `ip_UNIQUE` (`ip`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

- CREATE TABLE `Process` (
  `idProcess` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `priority` int(11) NOT NULL DEFAULT '0',
  `status` varchar(45) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `idUser` int(11) NOT NULL,
  PRIMARY KEY (`idProcess`),
  UNIQUE KEY `idProcess_UNIQUE` (`idProcess`),
  KEY `idUser_idx` (`idUser`),
  CONSTRAINT `idUser` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=latin1;

- CREATE TABLE `Role` (
  `idRole` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(45) NOT NULL,
  PRIMARY KEY (`idRole`),
  UNIQUE KEY `idRole_UNIQUE` (`idRole`),
  UNIQUE KEY `roleName_UNIQUE` (`roleName`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

- CREATE TABLE `RunProcs` (
  `idRunProcs` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `type` varchar(45) DEFAULT 'custom',
  PRIMARY KEY (`idRunProcs`),
  UNIQUE KEY `idRunProcs_UNIQUE` (`idRunProcs`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

- CREATE TABLE `User` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `created` datetime DEFAULT NULL,
  `lastConnection` datetime DEFAULT NULL,
  `Comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `userName_UNIQUE` (`userName`),
  UNIQUE KEY `idUser_UNIQUE` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

- CREATE TABLE `UsersRole` (
  `idUsersRole` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) DEFAULT NULL,
  `idRole` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUsersRole`),
  UNIQUE KEY `idUsersRole_UNIQUE` (`idUsersRole`),
  KEY `fk_roleId_idx` (`idRole`),
  KEY `fk_userId_idx` (`idUser`),
  CONSTRAINT `fk_roleId` FOREIGN KEY (`idRole`) REFERENCES `Role` (`idRole`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_userId` FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

===========

Examples to create data:

- INSERT INTO `App` VALUES (1,'Octave','regular','installed','2013-07-01 10:00:01','octaveLogo.png',1);

- INSERT INTO `Node` VALUES (1,'master','192.168.1.25','ok','ok','ok','warning');

- INSERT INTO `Process` VALUES (1,'javaTest','test',0,'finished','2013-07-01 10:00:05','2013-07-01 10:00:07',1);

- INSERT INTO `Role` VALUES (1,'Admin');

- INSERT INTO `RunProcs` VALUES (1,'2013-07-01 10:00:05',5,'octave');

- INSERT INTO `User` VALUES (1,'admin','c93ccd78b2076528346216b3b2f701e6','2013-07-01 10:00:00','2013-10-16 12:57:35',NULL);

- INSERT INTO `UsersRole` VALUES (1,1,1);




