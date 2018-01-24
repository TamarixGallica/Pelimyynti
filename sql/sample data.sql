/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping data for table pm_alustat: ~8 rows (approximately)
/*!40000 ALTER TABLE `pm_alustat` DISABLE KEYS */;
INSERT INTO `pm_alustat` (`Alustat_id`, `Nimi`) VALUES
	(4, 'NES'),
	(14, 'Playstation 1'),
	(684, 'Playstation 2'),
	(694, 'Playstation 3'),
	(724, 'Wii U'),
	(734, 'SNES'),
	(744, 'Gamecube'),
	(794, 'Vectrex');
/*!40000 ALTER TABLE `pm_alustat` ENABLE KEYS */;

-- Dumping data for table pm_artikkelit: ~7 rows (approximately)
/*!40000 ALTER TABLE `pm_artikkelit` DISABLE KEYS */;
INSERT INTO `pm_artikkelit` (`Artikkelit_id`, `Nimi`, `Lisatiedot`, `Pyyntihinta`, `Myyntihinta`, `Myyntiaika`) VALUES
	(4, '007 Racing', 'Etukannen paperi puuttuu', 5),
	(14, 'Anna Kournikova\'s Smash Court Tennis', '', 5),
	(24, 'Gauntlet II', 'SCN', 23),
	(34, 'Mr. Gimmick', 'Repro', 50),
	(44, 'Super Mario Bros', 'Loose', 10),
	(254, 'Super Mario Sunshine', '', 25),
	(294, 'Star Fox Zero', '', 10);
/*!40000 ALTER TABLE `pm_artikkelit` ENABLE KEYS */;

-- Dumping data for table pm_artikkelit_alustat: ~7 rows (approximately)
/*!40000 ALTER TABLE `pm_artikkelit_alustat` DISABLE KEYS */;
INSERT INTO `pm_artikkelit_alustat` (`Artikkelit_Alustat_id`, `Artikkelit_id`, `Alustat_id`) VALUES
	(4, 4, 14),
	(84, 24, 4),
	(94, 14, 14),
	(104, 34, 4),
	(114, 44, 4),
	(334, 254, 744),
	(374, 294, 724);
/*!40000 ALTER TABLE `pm_artikkelit_alustat` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
