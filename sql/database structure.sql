/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table pm_alustat
CREATE TABLE IF NOT EXISTS `pm_alustat` (
  `Alustat_id` int(11) NOT NULL AUTO_INCREMENT,
  `Nimi` varchar(40) NOT NULL,
  PRIMARY KEY (`Alustat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=795 DEFAULT CHARSET=utf8;

-- Dumping structure for table pm_artikkelit
CREATE TABLE IF NOT EXISTS `pm_artikkelit` (
  `Artikkelit_id` int(11) NOT NULL AUTO_INCREMENT,
  `Nimi` varchar(40) NOT NULL,
  `Lisatiedot` varchar(40) DEFAULT NULL,
  `Pyyntihinta` float NOT NULL,
  PRIMARY KEY (`Artikkelit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=295 DEFAULT CHARSET=utf8;

-- Dumping structure for table pm_artikkelit_alustat
CREATE TABLE IF NOT EXISTS `pm_artikkelit_alustat` (
  `Artikkelit_Alustat_id` int(11) NOT NULL AUTO_INCREMENT,
  `Artikkelit_id` int(11) NOT NULL,
  `Alustat_id` int(11) NOT NULL,
  PRIMARY KEY (`Artikkelit_Alustat_id`),
  KEY `Alustat_id_fk` (`Alustat_id`),
  KEY `pm_artikkelit_alustat_pm_artikkelit_Artikkelit_id_fk` (`Artikkelit_id`),
  CONSTRAINT `Alustat_id_fk` FOREIGN KEY (`Alustat_id`) REFERENCES `pm_alustat` (`Alustat_id`),
  CONSTRAINT `pm_artikkelit_alustat_pm_artikkelit_Artikkelit_id_fk` FOREIGN KEY (`Artikkelit_id`) REFERENCES `pm_artikkelit` (`Artikkelit_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=375 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
