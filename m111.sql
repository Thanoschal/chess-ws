-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 07, 2019 at 02:30 PM
-- Server version: 5.7.26-0ubuntu0.16.04.1
-- PHP Version: 7.0.33-0ubuntu0.16.04.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `m111`
--

-- --------------------------------------------------------

--
-- Stand-in structure for view `gamestats`
--
CREATE TABLE `gamestats` (
`number` decimal(24,4)
,`description` varchar(11)
);

-- --------------------------------------------------------

--
-- Table structure for table `tables`
--

CREATE TABLE `tables` (
  `id` int(11) NOT NULL,
  `white` varchar(50) NOT NULL,
  `black` varchar(50) NOT NULL,
  `moves` int(11) NOT NULL,
  `winner` varchar(50) DEFAULT NULL,
  `winnerColor` varchar(5) DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tables`
--

INSERT INTO `tables` (`id`, `white`, `black`, `moves`, `winner`, `winnerColor`, `date`) VALUES
(39, 'thanos', 'george', 3, 'george', 'black', '2019-05-06 15:50:40'),
(40, 'thanos', 'george', 4, 'thanos', 'white', '2019-05-06 15:55:01'),
(41, 'thanos', 'george', 2, 'george', 'black', '2019-05-06 15:57:13'),
(42, 'thanos', 'george', 3, 'thanos', 'white', '2019-05-06 15:58:32'),
(43, 'thanos', 'george', 0, 'george', 'black', '2019-05-06 16:00:00'),
(44, 'thanos', 'cpats', 0, 'thanos', 'white', '2019-05-06 16:15:06'),
(45, 'cpats', 'aoua', 4, 'cpats', 'white', '2019-05-06 16:34:26'),
(46, 'aoua', 'cpats', 0, 'cpats', 'black', '2019-05-06 16:55:29'),
(47, 'aaa', 'cpats', 0, 'aaa', 'white', '2019-05-06 19:46:02'),
(48, 'thanos', 'XrisKats', 0, 'XrisKats', 'black', '2019-05-06 19:51:36'),
(49, 'cpats', 'thanos', 3, 'cpats', 'white', '2019-05-06 20:29:22'),
(50, 'cpats', 'thanos', 0, 'thanos', 'black', '2019-05-06 20:35:01'),
(51, 'vasilis7', 'thanos', 0, 'thanos', 'black', '2019-05-07 10:04:26');

-- --------------------------------------------------------

--
-- Stand-in structure for view `top5`
--
CREATE TABLE `top5` (
`winner` varchar(50)
,`wins` bigint(21)
);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(64) NOT NULL,
  `connected` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `connected`) VALUES
(1, 'thanos', 'df805239a17c4c49d0851f3a0c6fc3225b76f6bbbe5b8bc7cb1f12bc9965f78c', 0),
(7, 'cpats', '2345170fd0eaddf2deeba377c0498b4f6265bc0034a8bee345170ddaf4d4a323', 0),
(9, 'george', '03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4', 0),
(10, 'aoua', '990382bb46464a345a7cdaa4e4774b462b01e826e5d24ce0fec56682e7a0f454', 0),
(11, 'dimitri', 'ad2ee1849d40341140b1029652d4f0428f66160a0da1b73c85ca1b9c1c9b6b20', 0),
(12, 'aaa', '9834876dcfb05cb167a5c24953eba58c4ac89b1adf57f28f2f9d09af107ee8f0', 0),
(13, 'XrisKats', '15e2b0d3c33891ebb0f1ef609ec419420c20e320ce94c65fbc8c3312448eb225', 0),
(15, 'vasilis7', '63a5cdb1651cfb0eea47573e6ea561032c22be9887d40f36c9816a4b1fe8fa14', 0);

-- --------------------------------------------------------

--
-- Structure for view `gamestats`
--
DROP TABLE IF EXISTS `gamestats`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `gamestats`  AS  select count(0) AS `number`,'gamesPlayed' AS `description` from `tables` union all select count(0) AS `number`,'whiteWon' AS `description` from `tables` where (`tables`.`winnerColor` = 'white') union all select count(0) AS `number`,'blackWon' AS `description` from `tables` where (`tables`.`winnerColor` = 'black') union all select count(0) AS `number`,'draws' AS `description` from `tables` where isnull(`tables`.`winnerColor`) union all select avg(`tables`.`moves`) AS `number`,'avgMoves' AS `description` from `tables` ;

-- --------------------------------------------------------

--
-- Structure for view `top5`
--
DROP TABLE IF EXISTS `top5`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `top5`  AS  select `tables`.`winner` AS `winner`,count(0) AS `wins` from `tables` where (`tables`.`winner` is not null) group by `tables`.`winner` order by `wins` desc,`tables`.`winner` limit 5 ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tables`
--
ALTER TABLE `tables`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tables`
--
ALTER TABLE `tables`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
