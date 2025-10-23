-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 23 oct. 2025 à 20:39
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
-- Base de données : `gestion_stock`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_61bt8i9mmldlno8ctl94y5rgv` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id`, `code`, `libelle`) VALUES
(1, 'Cat1', 'Ordinateurs'),
(2, 'Cat2', 'Périphériques'),
(3, 'Cat3', 'Composants');

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_commande` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `date_commande`) VALUES
(1, '2013-03-14'),
(2, '2013-03-15'),
(3, '2013-03-20');

-- --------------------------------------------------------

--
-- Structure de la table `ligne_commande_produit`
--

DROP TABLE IF EXISTS `ligne_commande_produit`;
CREATE TABLE IF NOT EXISTS `ligne_commande_produit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantite` int DEFAULT NULL,
  `commande_id` int DEFAULT NULL,
  `produit_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcfe786cevfmyia932xt3jcpdk` (`commande_id`),
  KEY `FK4x8bil10blu2dtq7wdxulxu1r` (`produit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `ligne_commande_produit`
--

INSERT INTO `ligne_commande_produit` (`id`, `quantite`, `commande_id`, `produit_id`) VALUES
(1, 7, 1, 1),
(2, 14, 1, 2),
(3, 5, 1, 3);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `prix` double DEFAULT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `categorie_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_27ds49lch5nxj0y5qa4dtf78j` (`reference`),
  KEY `FK52xhp55kbbl6u4rbluxm3g9hw` (`categorie_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `prix`, `reference`, `categorie_id`) VALUES
(1, 120, 'PR1', 1),
(2, 100, 'PR2', 1),
(3, 200, 'PR3', 2);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `ligne_commande_produit`
--
ALTER TABLE `ligne_commande_produit`
  ADD CONSTRAINT `FK4x8bil10blu2dtq7wdxulxu1r` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`),
  ADD CONSTRAINT `FKcfe786cevfmyia932xt3jcpdk` FOREIGN KEY (`commande_id`) REFERENCES `commande` (`id`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `FK52xhp55kbbl6u4rbluxm3g9hw` FOREIGN KEY (`categorie_id`) REFERENCES `categorie` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
