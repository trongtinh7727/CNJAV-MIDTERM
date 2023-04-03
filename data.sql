-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Generation Time: Apr 03, 2023 at 01:36 PM
-- Server version: 5.7.41
-- PHP Version: 8.1.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `manager`
--

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`ID`, `NAME`) VALUES
(1, 'Gucci'),
(2, 'Louis Vuitton	'),
(3, 'Hermès');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`ID`, `NAME`) VALUES
(1, 'Áo'),
(2, 'Quần'),
(3, 'Trang sức'),
(4, 'Giày');

-- --------------------------------------------------------

--
-- Table structure for table `color`
--

CREATE TABLE `color` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `color`
--

INSERT INTO `color` (`ID`, `NAME`, `code`) VALUES
(1, 'Blue', '#125ce3'),
(5, 'White', '#FFFFFF'),
(6, 'Black', '#000000');

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `ID` int(11) NOT NULL,
  `TRANSACTION_ID` int(11) NOT NULL,
  `PRODUCT_ID` int(11) NOT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `PRICE` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`ID`, `TRANSACTION_ID`, `PRODUCT_ID`, `QUANTITY`, `PRICE`) VALUES
(1, 2, 1, 1, 14000),
(2, 3, 2, 2, 300000),
(3, 3, 1, 1, 14000),
(4, 3, 3, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `ID` int(11) NOT NULL,
  `BRAND_ID` int(11) NOT NULL,
  `CATEGORY_ID` int(11) NOT NULL DEFAULT '1',
  `NAME` varchar(255) DEFAULT NULL,
  `PRICE` double DEFAULT NULL,
  `CONTENT` longtext,
  `IMG_LINKS` longtext,
  `CREATED` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`ID`, `BRAND_ID`, `CATEGORY_ID`, `NAME`, `PRICE`, `CONTENT`, `IMG_LINKS`, `CREATED`) VALUES
(1, 1, 1, 'Áo Polo Gucci Stretch Cotton Piquet Polo With Embroidery Màu Xanh Blue', 14000, 'Áo Polo Gucci Stretch Cotton Piquet Polo With Embroidery Màu Xanh Blue', '/img/polo.jpg', NULL),
(2, 1, 1, 'GUCCI Áo Polo Gucci With Pocket 713997 XJETR 9088 Màu Trắng', 150000, 'Áo Polo Gucci With Pocket 713997 XJETR 9088 Màu Trắng ', '/img/polo.jpg', NULL),
(3, 2, 2, 'Quần  Louis 1', 0, 'Một chiếc quần đến từ thương hiệu nổi tiếng là Lousi Vuitton', '/img/kingsman.jpg', NULL),
(4, 2, 2, 'Quần Louis 2', 500000, 'Một chiếc quần đến từ thương hiệu nổi tiếng là Lousi Vuitton', '/img/kingsman.jpg', NULL),
(5, 1, 1, 'Áo Polo Gucci Stretch Cotton', 0, 'Áo Polo Gucci Stretch Cotton Piquet Polo With Embroidery Màu Xanh Blue là chiếc áo thời trang dành cho nam đến từ thương hiệu Guccl nổi tiếng.', '/img/wooyoungmi.jpg', NULL),
(6, 1, 4, 'Giày Gucci 1', 850000, 'Giày đến từ nhà gucci', '/img/giay.jpg', NULL),
(7, 3, 1, 'Áo Polo Gucci Stretch Cotton 3', 22241420, 'Áo Polo Gucci With Pocket 713997 XJETR 9088 Màu Trắng ', '/img/wooyoungmi.jpg', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `product_color`
--

CREATE TABLE `product_color` (
  `COLOR_ID` int(11) NOT NULL,
  `PRODUCT_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product_color`
--

INSERT INTO `product_color` (`COLOR_ID`, `PRODUCT_ID`) VALUES
(1, 1),
(5, 2),
(1, 4),
(5, 4),
(1, 6),
(5, 6),
(1, 7),
(5, 7);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`ID`, `NAME`) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `ID` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `STATUS` int(11) DEFAULT '0',
  `AMOUNT` double DEFAULT NULL,
  `PAYMENT` varchar(50) DEFAULT NULL,
  `MESSAGE` varchar(50) DEFAULT NULL,
  `CREATED` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`ID`, `USER_ID`, `STATUS`, `AMOUNT`, `PAYMENT`, `MESSAGE`, `CREATED`) VALUES
(2, 3, 1, 14000, 'cod', 'Lê Văn Sỹ, Q3, TP HCM', '2023-04-03 06:50:15'),
(3, 3, 0, 314000, 'cod', 'null', '2023-04-03 06:50:42'),
(5, 2, 0, 0, 'cod', 'null', '2023-04-03 15:32:42');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `ADDRESS` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(254) DEFAULT NULL,
  `CREATED` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `NAME`, `EMAIL`, `ADDRESS`, `PASSWORD`, `CREATED`) VALUES
(1, 'Lê Hoàng', 'admin@app.com', 'Q7, Thành phố Hồ Chí minh', '$2a$10$8tRr2EOsqa7d/9lzbHSLJe492DBCmmNsSIRgahMultIZSqlvwUW/G', '2023-03-27 03:23:00'),
(2, 'Võ Trọng Tình', 'admin', 'Lê Văn Sỹ, Q3, TP HCM', '$2a$10$Z5ZISmvAipOONOzuEKKyp.wj6TfSJitJaPTOtzf.mG0ivx4QrTCFq', NULL),
(3, 'Võ Trọng Tình', 'user', 'Lê Văn Sỹ, Q3, TP HCM', '$2a$10$lFl7Sb12h44GUilkODFzAuzDBhbKkSN7EGU4L6WaqoJ56lsjMl0mW', '2023-03-28 08:31:33');

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

CREATE TABLE `users_roles` (
  `USER_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (`USER_ID`, `ROLE_ID`) VALUES
(1, 1),
(2, 1),
(2, 2),
(3, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `color`
--
ALTER TABLE `color`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_PRODUCT_ORDER` (`PRODUCT_ID`),
  ADD KEY `FK_TRANSACTION_ORDER` (`TRANSACTION_ID`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_CATEGORY_PRODUCT` (`CATEGORY_ID`),
  ADD KEY `FK_PRODUCT_BRAND` (`BRAND_ID`);

--
-- Indexes for table `product_color`
--
ALTER TABLE `product_color`
  ADD PRIMARY KEY (`COLOR_ID`,`PRODUCT_ID`),
  ADD KEY `FK_PRODUCT_COLOR` (`PRODUCT_ID`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `FK_USER_TRANSACTION` (`USER_ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD PRIMARY KEY (`USER_ID`,`ROLE_ID`),
  ADD KEY `FK_ROLES_USERS` (`ROLE_ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `brand`
--
ALTER TABLE `brand`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `color`
--
ALTER TABLE `color`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `FK_PRODUCT_ORDER` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_TRANSACTION_ORDER` FOREIGN KEY (`TRANSACTION_ID`) REFERENCES `transaction` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK_CATEGORY_PRODUCT` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_PRODUCT_BRAND` FOREIGN KEY (`BRAND_ID`) REFERENCES `brand` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `product_color`
--
ALTER TABLE `product_color`
  ADD CONSTRAINT `FK_PRODUCT_COLOR` FOREIGN KEY (`PRODUCT_ID`) REFERENCES `product` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `product_color_ibfk_1` FOREIGN KEY (`COLOR_ID`) REFERENCES `color` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FK_USER_TRANSACTION` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FK_ROLES_USERS` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_USERS_ROLES` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
