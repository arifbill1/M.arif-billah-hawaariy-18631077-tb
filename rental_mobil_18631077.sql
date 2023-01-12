-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 12, 2023 at 05:18 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rental_mobil_18631077`
--

-- --------------------------------------------------------

--
-- Table structure for table `mobil`
--

CREATE TABLE `mobil` (
  `nopol` varchar(10) NOT NULL,
  `merk` varchar(100) NOT NULL,
  `warna` varchar(100) NOT NULL,
  `tahun` int(11) NOT NULL DEFAULT 1990,
  `harga_sewa` bigint(20) NOT NULL DEFAULT 0,
  `status` tinyint(4) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `mobil`
--

INSERT INTO `mobil` (`nopol`, `merk`, `warna`, `tahun`, `harga_sewa`, `status`) VALUES
('DA 0022 TA', 'Toyota Fortuner', 'Hitam', 2020, 900000, 0),
('DA 1650 TA', 'Pajero Sport Dakar', 'Putih', 2022, 1250000, 0),
('DA 1655 AD', 'toyota Calya', 'putih', 2019, 2000000, 0);

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `ktp` varchar(16) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `hp` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pelanggan`
--

INSERT INTO `pelanggan` (`ktp`, `nama`, `alamat`, `hp`) VALUES
('6371041511000004', 'nadif', 'jl. gotong royong', '081321251135'),
('6371042124230004', 'sani', 'jl. buntu ', '081212125544'),
('6371042201920003', 'Ferdi', 'jl. kemuning raya 3', '082123545651'),
('6371042321000004', 'rian', 'jl. guntung paikat', '081221551355');

-- --------------------------------------------------------

--
-- Table structure for table `sewa`
--

CREATE TABLE `sewa` (
  `id` bigint(20) NOT NULL,
  `ktp` varchar(16) DEFAULT NULL,
  `nopol` varchar(10) DEFAULT NULL,
  `harga_sewa` bigint(20) NOT NULL DEFAULT 0,
  `waktu_mulai` date NOT NULL,
  `waktu_selesai` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sewa`
--

INSERT INTO `sewa` (`id`, `ktp`, `nopol`, `harga_sewa`, `waktu_mulai`, `waktu_selesai`) VALUES
(1, '6371042124230004', 'DA 1655 AD', 2000000, '2023-01-04', '2023-01-05'),
(3, '6371042201920003', 'DA 1650 TA', 1250000, '2023-01-02', '2023-01-03'),
(5, '6371042201920003', 'DA 0022 TA', 900000, '2023-01-18', '2023-01-19');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mobil`
--
ALTER TABLE `mobil`
  ADD PRIMARY KEY (`nopol`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`ktp`);

--
-- Indexes for table `sewa`
--
ALTER TABLE `sewa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ktp` (`ktp`),
  ADD KEY `nopol` (`nopol`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sewa`
--
ALTER TABLE `sewa`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `sewa`
--
ALTER TABLE `sewa`
  ADD CONSTRAINT `sewa_ibfk_1` FOREIGN KEY (`ktp`) REFERENCES `pelanggan` (`ktp`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sewa_ibfk_2` FOREIGN KEY (`nopol`) REFERENCES `mobil` (`nopol`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
