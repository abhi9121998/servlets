-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 11, 2018 at 08:24 PM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `my_db`
--
CREATE DATABASE IF NOT EXISTS `my_db` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `my_db`;

-- --------------------------------------------------------

--
-- Table structure for table `all_databases`
--

DROP TABLE IF EXISTS `all_databases`;
CREATE TABLE `all_databases` (
  `Sno` int(11) NOT NULL,
  `Db_name` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `all_databases`
--

INSERT INTO `all_databases` (`Sno`, `Db_name`) VALUES
(53, 'db1'),
(54, 'db2'),
(55, 'db3'),
(56, 'db4');

-- --------------------------------------------------------

--
-- Table structure for table `all_tables`
--

DROP TABLE IF EXISTS `all_tables`;
CREATE TABLE `all_tables` (
  `sno` int(4) NOT NULL,
  `db_name` varchar(15) NOT NULL,
  `tb_name` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `all_tables`
--

INSERT INTO `all_tables` (`sno`, `db_name`, `tb_name`) VALUES
(13, 'db1', 'tb1'),
(14, 'db2', 'tb1'),
(15, 'db3', 'tb1'),
(16, 'db1', 'tb2'),
(17, 'db4', 'tb1'),
(18, 'db1', 'tb3'),
(19, 'db2', 'tb2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `all_databases`
--
ALTER TABLE `all_databases`
  ADD PRIMARY KEY (`Db_name`),
  ADD UNIQUE KEY `Sno` (`Sno`);

--
-- Indexes for table `all_tables`
--
ALTER TABLE `all_tables`
  ADD PRIMARY KEY (`db_name`,`tb_name`),
  ADD UNIQUE KEY `sno` (`sno`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `all_databases`
--
ALTER TABLE `all_databases`
  MODIFY `Sno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT for table `all_tables`
--
ALTER TABLE `all_tables`
  MODIFY `sno` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
