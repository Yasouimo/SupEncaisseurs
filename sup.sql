-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 26, 2024 at 02:21 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sup`
--

-- --------------------------------------------------------

--
-- Table structure for table `agences`
--

CREATE TABLE `agences` (
  `id` bigint(20) NOT NULL,
  `nom_agence` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `agences`
--

INSERT INTO `agences` (`id`, `nom_agence`) VALUES
(1, 'Belle Vue'),
(2, 'Riad Zitoune'),
(3, 'Ouislane'),
(4, 'Sbata'),
(5, 'Bassatine'),
(6, 'Sidi Baba'),
(7, 'Sidi Bouzekri'),
(8, 'Médina'),
(9, 'Mansour'),
(10, 'Borj Moulay Omar');

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `nom`) VALUES
(1, 'ADMIN'),
(2, 'MANAGER'),
(3, 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `superviseurs`
--

CREATE TABLE `superviseurs` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `agence_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `superviseurs`
--

INSERT INTO `superviseurs` (`id`, `email`, `first_name`, `last_name`, `agence_id`) VALUES
(3, 'y.bellmir@radem.ma', 'yahya', 'bellmir', 7),
(5, 'i.menouni@radem.ma', 'Ismail', 'Menouni', 9),
(6, 'a.amar@radem.ma', 'Ahmed', 'Amar', 1),
(10, 'a.amrani@radem.ma', 'Aymen', 'Amrani', 8);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `nom`, `prenom`, `role_id`, `password`) VALUES
(2, 'a.amar@radem.ma', 'Amar', 'Ahmed', 1, '$2a$10$3m22U0ilxSx.T8TAfZGE6OQuGhhZlR8LPnfbcIK6fW5xfXW.wJDQC'),
(12, 'o.filali@radem.ma', 'Filali', 'Ossama', 2, '$2a$10$k1Xi74XDWm8xlXY0XDunF.OobZpjs4P40y4zpniFKHvr8phsZaPbi'),
(13, 'a.hasnaoui@radem.ma', 'Hasnaoui', 'Ahmed', 2, '$2a$10$t0Idq/KsIVJx42EWAnuOUe.q6aZSl9Tb7ZS4CZ4.K2UiOlZ4AlFY.'),
(14, 'i.rachidi@radem.ma', 'Rachidi', 'Ismail', 3, '$2a$10$ftg/sFM3VKCxB.DqMAoAFOKlF/wFNQTnIiFkgdB8Fr3YOhbfN9hCm'),
(18, 'k.kacimi@radem.ma', 'Kacimi', 'Khalid', 3, '$2a$10$HRH8badp7X5D8HPukPL84.2nJpz9/plJDV4zZl5eh1NazdMBjad3K');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agences`
--
ALTER TABLE `agences`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `superviseurs`
--
ALTER TABLE `superviseurs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7tedhq63dtulso2j214i5y192` (`agence_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `agences`
--
ALTER TABLE `agences`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `superviseurs`
--
ALTER TABLE `superviseurs`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `superviseurs`
--
ALTER TABLE `superviseurs`
  ADD CONSTRAINT `FK7tedhq63dtulso2j214i5y192` FOREIGN KEY (`agence_id`) REFERENCES `agences` (`id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
