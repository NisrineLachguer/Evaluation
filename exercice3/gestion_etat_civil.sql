-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 23 oct. 2025 à 23:01
-- Version du serveur : 9.1.0
-- Version de PHP : 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestion_etat_civil`
--

-- --------------------------------------------------------

--
-- Structure de la table `femmes`
--

DROP TABLE IF EXISTS `femmes`;
CREATE TABLE IF NOT EXISTS `femmes` (
  `personne_id` bigint NOT NULL,
  PRIMARY KEY (`personne_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `femmes`
--

INSERT INTO `femmes` (`personne_id`) VALUES
(6),
(7),
(8),
(9),
(10),
(11),
(12),
(13),
(14),
(15);

-- --------------------------------------------------------

--
-- Structure de la table `hommes`
--

DROP TABLE IF EXISTS `hommes`;
CREATE TABLE IF NOT EXISTS `hommes` (
  `personne_id` bigint NOT NULL,
  PRIMARY KEY (`personne_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `hommes`
--

INSERT INTO `hommes` (`personne_id`) VALUES
(1),
(2),
(3),
(4),
(5);

-- --------------------------------------------------------

--
-- Structure de la table `mariages`
--

DROP TABLE IF EXISTS `mariages`;
CREATE TABLE IF NOT EXISTS `mariages` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_debut` date NOT NULL,
  `date_fin` date DEFAULT NULL,
  `nombre_enfants` int DEFAULT NULL,
  `statut` varchar(20) DEFAULT NULL,
  `femme_id` bigint NOT NULL,
  `homme_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjn8cmdmw49efvfrfqt4u2uxvm` (`femme_id`),
  KEY `FKsruf7u42s20hvy45cyp8niovi` (`homme_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `mariages`
--

INSERT INTO `mariages` (`id`, `date_debut`, `date_fin`, `nombre_enfants`, `statut`, `femme_id`, `homme_id`) VALUES
(1, '1998-09-03', NULL, 4, 'EN_COURS', 6, 1),
(2, '1998-08-03', NULL, 2, 'EN_COURS', 7, 1),
(3, '1998-11-04', NULL, 3, 'EN_COURS', 8, 1),
(4, '1998-09-03', '1998-09-03', 0, 'ECHOUE', 9, 1);

-- --------------------------------------------------------

--
-- Structure de la table `personnes`
--

DROP TABLE IF EXISTS `personnes`;
CREATE TABLE IF NOT EXISTS `personnes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_naissance` date DEFAULT NULL,
  `lieu_naissance` varchar(100) DEFAULT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `personnes`
--

INSERT INTO `personnes` (`id`, `date_naissance`, `lieu_naissance`, `nom`, `prenom`) VALUES
(1, '1970-05-15', 'Ville1', 'STIOUI', 'Mohamed'),
(2, '1965-03-20', 'Ville2', 'LAGHOUITI', 'Ayman'),
(3, '1975-08-10', 'Ville3', 'LACHGUER', 'Alami'),
(4, '1980-12-05', 'Ville4', 'KHALDOUNE', 'Adam'),
(5, '1972-07-25', 'Ville5', 'BOUHADDA', 'Akram'),
(6, '1972-06-10', 'VilleA', 'KHALDOUN', 'fati'),
(7, '1975-09-15', 'VilleB', 'LACHGUER', 'Loubna'),
(8, '1978-11-20', 'VilleC', 'IRHARISSA', 'Imane'),
(9, '1980-02-25', 'VilleD', 'NJARI', 'ROKAYA'),
(10, '1970-01-01', 'VilleE', 'RZAKI', 'Nawal'),
(11, '1973-04-12', 'VilleF', 'LACHGUER', 'Nisrine'),
(12, '1976-07-18', 'VilleG', 'NADI', 'Hajar'),
(13, '1979-10-22', 'VilleH', 'SKITINA', 'Karima'),
(14, '1982-12-30', 'VilleI', 'BOUHADDA', 'Asma'),
(15, '1968-03-08', 'VilleJ', 'CHAIKE', 'Asiya');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `femmes`
--
ALTER TABLE `femmes`
  ADD CONSTRAINT `FKilfeethyvf7ed78t3m4gx12lo` FOREIGN KEY (`personne_id`) REFERENCES `personnes` (`id`);

--
-- Contraintes pour la table `hommes`
--
ALTER TABLE `hommes`
  ADD CONSTRAINT `FKakt06shceyhh03uilywisur2w` FOREIGN KEY (`personne_id`) REFERENCES `personnes` (`id`);

--
-- Contraintes pour la table `mariages`
--
ALTER TABLE `mariages`
  ADD CONSTRAINT `FKjn8cmdmw49efvfrfqt4u2uxvm` FOREIGN KEY (`femme_id`) REFERENCES `femmes` (`personne_id`),
  ADD CONSTRAINT `FKsruf7u42s20hvy45cyp8niovi` FOREIGN KEY (`homme_id`) REFERENCES `hommes` (`personne_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
