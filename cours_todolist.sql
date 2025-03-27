-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 27 mars 2025 à 18:02
-- Version du serveur : 8.3.0
-- Version de PHP : 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `cours_todolist`
--

-- --------------------------------------------------------

--
-- Structure de la table `liste`
--

DROP TABLE IF EXISTS `liste`;
CREATE TABLE IF NOT EXISTS `liste` (
  `id_liste` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`id_liste`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Structure de la table `tache`
--

DROP TABLE IF EXISTS `tache`;
CREATE TABLE IF NOT EXISTS `tache` (
  `id_tache` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `etat` int NOT NULL,
  `ref_liste` int NOT NULL,
  `ref_type` int NOT NULL,
  PRIMARY KEY (`id_tache`),
  KEY `fk_tache_liste` (`ref_liste`),
  KEY `fk_tache_type` (`ref_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Structure de la table `type`
--

DROP TABLE IF EXISTS `type`;
CREATE TABLE IF NOT EXISTS `type` (
  `id_type` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `code_couleur` varchar(7) NOT NULL,
  PRIMARY KEY (`id_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id_utilisateur` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(255) NOT NULL,
  `mot_de_passe` varchar(255) NOT NULL,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id_utilisateur`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id_utilisateur`, `nom`, `prenom`, `email`, `mot_de_passe`, `role`) VALUES
(3, 'a', 'a', 'a', '$2a$10$5Moymy4CyQV207roRtC.z.si9zCldpIF7Whj.Usjh8SUsOIyFIBMm', 'admin'),
(4, 'z', 'z', 'z', '$2a$10$ZhxxQks3m5pqSqk2unXum.tEF3vXoXbRPnIsFVJmj041CtpttQIfK', 'user'),
(5, 'e', 'e', 'e', '$2a$10$xQzdv.2i4P0Vv6LOdmzlceaFOAfLJIjFG1uSL1Nmxo3tcjRO9xFz.', 'user'),
(6, 'r', 'r', 'r', '$2a$10$sT9pIIwkxHHQp7WhjHuAV.1ktjEJi8uq735vfybm4YFXKQtsV3oKS', 'user');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur_liste`
--

DROP TABLE IF EXISTS `utilisateur_liste`;
CREATE TABLE IF NOT EXISTS `utilisateur_liste` (
  `ref_utilisateur` int NOT NULL,
  `ref_liste` int NOT NULL,
  PRIMARY KEY (`ref_utilisateur`,`ref_liste`),
  KEY `fk_utilisateur_liste_liste` (`ref_liste`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `tache`
--
ALTER TABLE `tache`
  ADD CONSTRAINT `fk_tache_liste` FOREIGN KEY (`ref_liste`) REFERENCES `liste` (`id_liste`),
  ADD CONSTRAINT `fk_tache_type` FOREIGN KEY (`ref_type`) REFERENCES `type` (`id_type`);

--
-- Contraintes pour la table `utilisateur_liste`
--
ALTER TABLE `utilisateur_liste`
  ADD CONSTRAINT `fk_utilisateur_liste_liste` FOREIGN KEY (`ref_liste`) REFERENCES `liste` (`id_liste`),
  ADD CONSTRAINT `fk_utilisateur_liste_utilisateur` FOREIGN KEY (`ref_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
