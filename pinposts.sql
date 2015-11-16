

CREATE TABLE IF NOT EXISTS `pinposts` (
  `post_ID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `content` text NOT NULL,
  `postDate` date NOT NULL,
  `expirationDate` date DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`post_ID`),
  KEY `title` (`title`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;


