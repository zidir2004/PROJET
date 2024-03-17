-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 11 jan. 2024 à 12:38
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `erp`
--

-- --------------------------------------------------------

--
-- Structure de la table `achat`
--

DROP TABLE IF EXISTS `achat`;
CREATE TABLE IF NOT EXISTS `achat` (
  `id_achat` int NOT NULL AUTO_INCREMENT,
  `prix` float NOT NULL,
  PRIMARY KEY (`id_achat`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Déchargement des données de la table `achat`
--

INSERT INTO `achat` (`id_achat`, `prix`) VALUES
(1, 600),
(2, 1000);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id_client` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `prenom` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `adresse` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `telephone` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  PRIMARY KEY (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id_client`, `nom`, `prenom`, `adresse`, `email`, `telephone`) VALUES
(1, 'Gojo', 'Satoru', 'Shibuya', 'satoru@gmail.com', '+81 7 81 45 03 28'),
(2, 'Monet', 'Claude', 'Paris', 'Claude@gmail.com', '+33 7 81 42 36 25');

-- --------------------------------------------------------

--
-- Structure de la table `commande_client`
--

DROP TABLE IF EXISTS `commande_client`;
CREATE TABLE IF NOT EXISTS `commande_client` (
  `id_commande` int NOT NULL AUTO_INCREMENT,
  `quantite` int NOT NULL,
  `valide` int NOT NULL DEFAULT '0',
  `id_client` int NOT NULL,
  `id_produit` int NOT NULL,
  PRIMARY KEY (`id_commande`),
  KEY `id_client` (`id_client`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Déchargement des données de la table `commande_client`
--

INSERT INTO `commande_client` (`id_commande`, `quantite`, `valide`, `id_client`, `id_produit`) VALUES
(1, 20, 0, 1, 5),
(2, 30, 0, 2, 6),
(3, 10, 0, 1, 2),
(4, 10, 0, 1, 4),
(5, 20, 0, 1, 3),
(6, 12550, 0, 1, 3);

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

DROP TABLE IF EXISTS `fournisseur`;
CREATE TABLE IF NOT EXISTS `fournisseur` (
  `id_fournisseur` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `id_matiere` int NOT NULL,
  PRIMARY KEY (`id_fournisseur`),
  KEY `Fournisseur_MatierePremiere_FK` (`id_matiere`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `matierepremiere`
--

DROP TABLE IF EXISTS `matierepremiere`;
CREATE TABLE IF NOT EXISTS `matierepremiere` (
  `id_matiere` int NOT NULL AUTO_INCREMENT,
  `type` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `quantite` float NOT NULL,
  `prix_kg` float NOT NULL,
  `seuil_reapprovisionnement` float NOT NULL,
  PRIMARY KEY (`id_matiere`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Déchargement des données de la table `matierepremiere`
--

INSERT INTO `matierepremiere` (`id_matiere`, `type`, `quantite`, `prix_kg`, `seuil_reapprovisionnement`) VALUES
(1, 'alu', 1040, 120, 1000),
(2, 'pvc', 350, 90, 1000),
(3, 'pvdc', 670, 110, 1000),
(4, 'PVC/PE', 540, 60, 500),
(5, 'PVC PVDC', 560, 100, 800),
(6, 'OPA', 800, 60, 800);

-- --------------------------------------------------------

--
-- Structure de la table `produitfini`
--

DROP TABLE IF EXISTS `produitfini`;
CREATE TABLE IF NOT EXISTS `produitfini` (
  `id_produit` int NOT NULL AUTO_INCREMENT,
  `type` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `prix_kg` float NOT NULL,
  `quantite` float NOT NULL,
  `seuil_reapprovisionnement` float NOT NULL,
  PRIMARY KEY (`id_produit`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Déchargement des données de la table `produitfini`
--

INSERT INTO `produitfini` (`id_produit`, `type`, `prix_kg`, `quantite`, `seuil_reapprovisionnement`) VALUES
(1, 'alu', 222, 700, 500),
(2, 'pvc', 100, 500, 1200),
(3, 'pvdc', 120, 340, 140),
(4, 'PVC/PE', 85, 1100, 1000),
(5, 'PVC PVDC', 60, 680, 1200),
(6, 'OPA', 240, 250, 800);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id_utilisateur` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `prenom` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `password` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_utilisateur`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_utilisateur`, `nom`, `prenom`, `email`, `role`, `password`) VALUES
(1, 'Corneille', 'Pierre', 'pierre@gmail.com', 'stock', 'pierre'),
(2, 'jaeger', 'eren', 'eren@gmail.com', 'admin', 'eren'),
(3, 'idir', 'idir', 's', 'stock', 's'),
(4, 'pitt', 'brad', 'brad@gmail.com', 'commercial', 'brad'),
(5, 'may', 'may', 'c', 'commercial', 'c'),
(6, 'production_test', 'p', 'p', 'production', 'p'),
(7, 'Clinton', 'Bill', 'bill@gmail.com', 'production', 'bill'),
(8, 't', 't', 't', 'comptable', 't'),
(9, 'Cesar', 'Jules', 'jules@gmail.com', 'comptable', 'jules'),
(10, 'a', 'a', 'a', 'admin', 'a');

-- --------------------------------------------------------

--
-- Structure de la table `vente`
--

DROP TABLE IF EXISTS `vente`;
CREATE TABLE IF NOT EXISTS `vente` (
  `id_vente` int NOT NULL AUTO_INCREMENT,
  `prix` float NOT NULL,
  `facture` int NOT NULL DEFAULT '0',
  `id_client` int NOT NULL,
  PRIMARY KEY (`id_vente`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;

--
-- Déchargement des données de la table `vente`
--

INSERT INTO `vente` (`id_vente`, `prix`, `facture`, `id_client`) VALUES
(5, -20, 1, 1),
(3, 7200, 0, 2),
(6, 1200, 0, 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD CONSTRAINT `Fournisseur_MatierePremiere_FK` FOREIGN KEY (`id_matiere`) REFERENCES `matierepremiere` (`id_matiere`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
