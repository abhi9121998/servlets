-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 09, 2018 at 10:04 PM
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
-- Database: `db1`
--
DROP DATABASE IF EXISTS `db1`;
CREATE DATABASE IF NOT EXISTS `db1` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `db1`;

-- --------------------------------------------------------

--
-- Table structure for table `tb1`
--

CREATE TABLE `tb1` (
  `r1` int(5) DEFAULT NULL,
  `r2` varchar(15) DEFAULT NULL,
  `r3` int(7) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
-- Database: `db2`
--
DROP DATABASE IF EXISTS `db2`;
CREATE DATABASE IF NOT EXISTS `db2` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `db2`;

-- --------------------------------------------------------

--
-- Table structure for table `tb2`
--

CREATE TABLE `tb2` (
  `r1` int(4) DEFAULT NULL,
  `r2` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
-- Database: `db3`
--
DROP DATABASE IF EXISTS `db3`;
CREATE DATABASE IF NOT EXISTS `db3` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `db3`;

-- --------------------------------------------------------

--
-- Table structure for table `tb3`
--

CREATE TABLE `tb3` (
  `r1` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
-- Database: `db4`
--
DROP DATABASE IF EXISTS `db4`;
CREATE DATABASE IF NOT EXISTS `db4` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `db4`;

-- --------------------------------------------------------

--
-- Table structure for table `tb1`
--

CREATE TABLE `tb1` (
  `r1` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
-- Database: `my_db`
--
DROP DATABASE IF EXISTS `my_db`;
CREATE DATABASE IF NOT EXISTS `my_db` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `my_db`;

-- --------------------------------------------------------

--
-- Table structure for table `all_databases`
--

CREATE TABLE `all_databases` (
  `Sno` int(11) NOT NULL,
  `Db_name` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `all_databases`
--

INSERT INTO `all_databases` (`Sno`, `Db_name`) VALUES
(30, 'db1'),
(31, 'db2'),
(32, 'db3'),
(33, 'db4');

-- --------------------------------------------------------

--
-- Table structure for table `all_tables`
--

CREATE TABLE `all_tables` (
  `sno` int(4) NOT NULL,
  `db_name` varchar(15) NOT NULL,
  `tb_name` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `all_tables`
--

INSERT INTO `all_tables` (`sno`, `db_name`, `tb_name`) VALUES
(3, 'db1', 'tb1'),
(4, 'db2', 'tb2'),
(5, 'db3', 'tb3'),
(6, 'db4', 'tb4'),
(7, 'db4', 'tb1');

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
  MODIFY `Sno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `all_tables`
--
ALTER TABLE `all_tables`
  MODIFY `sno` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
