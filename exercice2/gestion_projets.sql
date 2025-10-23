-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 23 oct. 2025 à 21:44
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
-- Base de données : `gestion_projets`
--

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

DROP TABLE IF EXISTS `employe`;
CREATE TABLE IF NOT EXISTS `employe` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`id`, `email`, `nom`, `prenom`) VALUES
(1, 'jean.dupont@email.com', 'Dupont', 'Jean'),
(2, 'marie.martin@email.com', 'Martin', 'Marie');

-- --------------------------------------------------------

--
-- Structure de la table `employetache`
--

DROP TABLE IF EXISTS `employetache`;
CREATE TABLE IF NOT EXISTS `employetache` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dateDebut` date DEFAULT NULL,
  `dateFin` date DEFAULT NULL,
  `employe_id` int DEFAULT NULL,
  `tache_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcm4rkn43oh0spc9dnt7m6gdmt` (`employe_id`),
  KEY `FK82gyt40ohsj3n4oc08x5id1d6` (`tache_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `employetache`
--

INSERT INTO `employetache` (`id`, `dateDebut`, `dateFin`, `employe_id`, `tache_id`) VALUES
(1, '2013-02-10', '2013-02-20', 1, 1),
(2, '2013-03-10', '2013-03-15', 1, 2),
(3, '2013-04-10', '2013-04-25', 2, 3);

-- --------------------------------------------------------

--
-- Structure de la table `projet`
--

DROP TABLE IF EXISTS `projet`;
CREATE TABLE IF NOT EXISTS `projet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dateDebut` date DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `chef_projet_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbj8u5tatatexeg7v1hn3htcf7` (`chef_projet_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `projet`
--

INSERT INTO `projet` (`id`, `dateDebut`, `nom`, `chef_projet_id`) VALUES
(1, '2013-01-14', 'Gestion de stock', 1),
(2, '2013-02-01', 'Site E-commerce', 2);

-- --------------------------------------------------------

--
-- Structure de la table `tache`
--

DROP TABLE IF EXISTS `tache`;
CREATE TABLE IF NOT EXISTS `tache` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dateDebutPlanifie` date DEFAULT NULL,
  `dateDebutReelle` date DEFAULT NULL,
  `dateFinPlanifie` date DEFAULT NULL,
  `dateFinReelle` date DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prix` double NOT NULL,
  `projet_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcb0x7p6kcs8h6ijwif67qdj9b` (`projet_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `tache`
--

INSERT INTO `tache` (`id`, `dateDebutPlanifie`, `dateDebutReelle`, `dateFinPlanifie`, `dateFinReelle`, `nom`, `prix`, `projet_id`) VALUES
(1, '2013-02-10', '2013-02-10', '2013-02-20', '2013-02-20', 'Analyse', 1500, 1),
(2, '2013-03-10', '2013-03-10', '2013-03-15', '2013-03-15', 'Conception', 1200, 1),
(3, '2013-04-10', '2013-04-10', '2013-04-25', '2013-04-25', 'Développement', 2000, 1),
(4, '2013-05-01', NULL, '2013-05-10', NULL, 'Test', 800, 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `employetache`
--
ALTER TABLE `employetache`
  ADD CONSTRAINT `FK82gyt40ohsj3n4oc08x5id1d6` FOREIGN KEY (`tache_id`) REFERENCES `tache` (`id`),
  ADD CONSTRAINT `FKcm4rkn43oh0spc9dnt7m6gdmt` FOREIGN KEY (`employe_id`) REFERENCES `employe` (`id`);

--
-- Contraintes pour la table `projet`
--
ALTER TABLE `projet`
  ADD CONSTRAINT `FKbj8u5tatatexeg7v1hn3htcf7` FOREIGN KEY (`chef_projet_id`) REFERENCES `employe` (`id`);

--
-- Contraintes pour la table `tache`
--
ALTER TABLE `tache`
  ADD CONSTRAINT `FKcb0x7p6kcs8h6ijwif67qdj9b` FOREIGN KEY (`projet_id`) REFERENCES `projet` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
