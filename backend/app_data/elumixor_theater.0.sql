-- phpMyAdmin SQL Dump
-- version 4.3.8
-- http://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Апр 11 2018 г., 10:58
-- Версия сервера: 5.6.32-78.1
-- Версия PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `elumixor_theater`
--

-- --------------------------------------------------------

--
-- Структура таблицы `t_chair`
--

CREATE TABLE IF NOT EXISTS `t_chair` (
  `id` int(11) NOT NULL,
  `row` int(11) NOT NULL DEFAULT '0',
  `number` int(11) NOT NULL DEFAULT '0',
  `hall` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Структура таблицы `t_friendship`
--

CREATE TABLE IF NOT EXISTS `t_friendship` (
  `email_1` varchar(155) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `email_2` varchar(155) CHARACTER SET utf8 NOT NULL DEFAULT ''
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Структура таблицы `t_hall`
--

CREATE TABLE IF NOT EXISTS `t_hall` (
  `id` int(11) NOT NULL,
  `name` varchar(155) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `theater` int(11) NOT NULL DEFAULT '0'
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `t_hall`
--

INSERT INTO `t_hall` (`id`, `name`, `theater`) VALUES
(1, 'TEST_HALL', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `t_order`
--

CREATE TABLE IF NOT EXISTS `t_order` (
  `id` int(11) NOT NULL,
  `date` int(11) NOT NULL,
  `registered_email` varchar(155) CHARACTER SET utf8 DEFAULT NULL,
  `website_email` varchar(155) CHARACTER SET utf8 DEFAULT NULL,
  `phone_number` varchar(155) CHARACTER SET utf8 DEFAULT NULL,
  `is_digital` tinyint(1) NOT NULL DEFAULT '1',
  `is_purchase` tinyint(1) NOT NULL DEFAULT '0',
  `checkout` enum('DELIVERY','SELF_CHECKOUT','PAY_BEFORE') NOT NULL,
  `confirmed` tinyint(1) DEFAULT NULL
) ENGINE=MyISAM AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `t_order`
--

INSERT INTO `t_order` (`id`, `date`, `registered_email`, `website_email`, `phone_number`, `is_digital`, `is_purchase`, `checkout`, `confirmed`) VALUES
(28, 33, NULL, '777291273', NULL, 1, 0, 'PAY_BEFORE', 1),
(29, 34, NULL, '773892964', NULL, 1, 0, 'PAY_BEFORE', 1),
(30, 35, NULL, '776790933', NULL, 1, 0, 'PAY_BEFORE', 1),
(31, 36, NULL, '+971501059159', NULL, 1, 0, 'PAY_BEFORE', 1),
(32, 37, NULL, '222 222 222', NULL, 1, 0, 'PAY_BEFORE', 1),
(33, 38, NULL, '702945171', NULL, 1, 0, 'PAY_BEFORE', 1),
(34, 39, NULL, '776067965', NULL, 1, 0, 'PAY_BEFORE', 1),
(35, 40, NULL, '607653708', NULL, 1, 0, 'PAY_BEFORE', 1),
(36, 41, NULL, '+420777145919', NULL, 1, 0, 'PAY_BEFORE', 1),
(37, 42, NULL, '774894410', NULL, 1, 0, 'PAY_BEFORE', 1),
(38, 43, NULL, '776 790 933', NULL, 1, 0, 'PAY_BEFORE', 1),
(39, 44, NULL, '000 000 000', NULL, 1, 0, 'PAY_BEFORE', 1),
(40, 45, NULL, '608677349', NULL, 1, 0, 'PAY_BEFORE', 1),
(41, 46, NULL, '608607349', NULL, 1, 0, 'PAY_BEFORE', 1),
(42, 47, NULL, '774 987 601', NULL, 1, 0, 'PAY_BEFORE', 1),
(43, 48, NULL, '702896382', NULL, 1, 0, 'PAY_BEFORE', 1),
(44, 49, NULL, '773 592 283', NULL, 1, 0, 'PAY_BEFORE', 1),
(45, 50, NULL, '608540587', NULL, 1, 0, 'PAY_BEFORE', 1),
(46, 51, NULL, '608777119', NULL, 1, 0, 'PAY_BEFORE', 1),
(47, 52, NULL, '776438276', NULL, 1, 0, 'PAY_BEFORE', 1),
(48, 53, NULL, '777655867', NULL, 1, 0, 'PAY_BEFORE', 1),
(49, 54, NULL, '777 777 777', NULL, 1, 0, 'PAY_BEFORE', 1),
(50, 55, NULL, '773615820', NULL, 1, 0, 'PAY_BEFORE', 1),
(51, 56, NULL, '608423174', NULL, 1, 0, 'PAY_BEFORE', 1),
(52, 57, NULL, 'Alexandr.a.prozorov@gmail.com', NULL, 1, 0, 'SELF_CHECKOUT', NULL),
(53, 58, NULL, 'цвфыцвцСОБ@ka.com', NULL, 1, 0, 'SELF_CHECKOUT', 0),
(54, 59, NULL, '776 586 451', NULL, 1, 0, 'SELF_CHECKOUT', NULL),
(55, 60, NULL, '777446836', NULL, 1, 0, 'SELF_CHECKOUT', NULL),
(56, 61, NULL, 'цвфыцвцСОБ@ka.com', NULL, 1, 0, 'SELF_CHECKOUT', 0),
(57, 62, NULL, 'цвфыцвцСОБ@ka.com', NULL, 1, 0, 'SELF_CHECKOUT', 0),
(58, 63, NULL, 'цвфыцвцСОБ@ka.com', NULL, 1, 0, 'SELF_CHECKOUT', 0),
(59, 64, NULL, 'цвфыцвцСОБ@ka.com', NULL, 1, 0, 'SELF_CHECKOUT', 0),
(60, 65, NULL, 'цвфыцвцСОБ@ka.com', NULL, 1, 0, 'PAY_BEFORE', 0),
(61, 66, NULL, 'an@a.aa', NULL, 1, 0, 'DELIVERY', 0),
(62, 67, NULL, 'an@a.aa', NULL, 1, 0, 'DELIVERY', 0),
(63, 68, NULL, 'an@a.aa', NULL, 1, 0, 'DELIVERY', 0);

-- --------------------------------------------------------

--
-- Структура таблицы `t_performance`
--

CREATE TABLE IF NOT EXISTS `t_performance` (
  `id` int(11) NOT NULL,
  `author` varchar(155) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `title` varchar(155) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `image_url` varchar(155) CHARACTER SET utf8 DEFAULT NULL,
  `description` text
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `t_performance`
--

INSERT INTO `t_performance` (`id`, `author`, `title`, `image_url`, `description`) VALUES
(3, 'Sample Author', 'Предложение. Медведь', 'http://elumixor.com/theater/assets/images/bgPredlojenieMedved.jpg', '');

-- --------------------------------------------------------

--
-- Структура таблицы `t_phone_client`
--

CREATE TABLE IF NOT EXISTS `t_phone_client` (
  `phone` varchar(155) CHARACTER SET utf8 NOT NULL,
  `name` varchar(155) CHARACTER SET utf8 NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Структура таблицы `t_profile`
--

CREATE TABLE IF NOT EXISTS `t_profile` (
  `email` varchar(155) CHARACTER SET utf8 NOT NULL,
  `image_url` varchar(155) CHARACTER SET utf8 DEFAULT NULL,
  `facebook` varchar(155) CHARACTER SET utf8 DEFAULT NULL,
  `google` varchar(155) CHARACTER SET utf8 DEFAULT NULL,
  `twitter` varchar(155) CHARACTER SET utf8 DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Структура таблицы `t_registered_user`
--

CREATE TABLE IF NOT EXISTS `t_registered_user` (
  `email` varchar(155) CHARACTER SET utf8 NOT NULL,
  `login` varchar(155) CHARACTER SET utf8 NOT NULL,
  `name` varchar(155) CHARACTER SET utf8 NOT NULL,
  `password` varchar(155) CHARACTER SET utf8 NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Структура таблицы `t_review`
--

CREATE TABLE IF NOT EXISTS `t_review` (
  `date` int(11) NOT NULL DEFAULT '0',
  `email` varchar(155) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `performance` int(11) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Структура таблицы `t_row`
--

CREATE TABLE IF NOT EXISTS `t_row` (
  `id` int(11) NOT NULL,
  `hall` int(11) NOT NULL DEFAULT '0',
  `number` int(11) NOT NULL DEFAULT '0',
  `seat_number` int(11) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `t_row`
--

INSERT INTO `t_row` (`id`, `hall`, `number`, `seat_number`) VALUES
(1, 1, 1, 20),
(2, 1, 2, 17),
(3, 1, 3, 22),
(4, 1, 4, 23),
(5, 1, 5, 24),
(6, 1, 6, 25),
(7, 1, 7, 26),
(8, 1, 8, 27),
(9, 1, 9, 26),
(10, 1, 10, 27),
(11, 1, 11, 31),
(12, 1, 12, 42),
(13, 1, 13, 42),
(14, 1, 14, 42),
(15, 1, 15, 40);

-- --------------------------------------------------------

--
-- Структура таблицы `t_seat`
--

CREATE TABLE IF NOT EXISTS `t_seat` (
  `id` int(11) NOT NULL,
  `number` int(11) NOT NULL DEFAULT '0',
  `row` int(11) NOT NULL DEFAULT '0',
  `order` int(11) DEFAULT NULL,
  `session` int(11) NOT NULL DEFAULT '0',
  `availabillity` enum('FREE','BOOKED','HIDDEN') NOT NULL DEFAULT 'FREE'
) ENGINE=MyISAM AUTO_INCREMENT=246 DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `t_seat`
--

INSERT INTO `t_seat` (`id`, `number`, `row`, `order`, `session`, `availabillity`) VALUES
(1, 4, 12, NULL, 1, 'HIDDEN'),
(2, 4, 13, NULL, 1, 'HIDDEN'),
(3, 4, 14, NULL, 1, 'HIDDEN'),
(4, 4, 12, NULL, 2, 'HIDDEN'),
(5, 4, 13, NULL, 2, 'HIDDEN'),
(6, 4, 14, NULL, 2, 'HIDDEN'),
(7, 4, 12, NULL, 3, 'HIDDEN'),
(8, 4, 13, NULL, 3, 'HIDDEN'),
(9, 4, 14, NULL, 3, 'HIDDEN'),
(10, 5, 12, NULL, 1, 'HIDDEN'),
(11, 5, 13, NULL, 1, 'HIDDEN'),
(12, 5, 14, NULL, 1, 'HIDDEN'),
(13, 5, 12, NULL, 2, 'HIDDEN'),
(14, 5, 13, NULL, 2, 'HIDDEN'),
(15, 5, 14, NULL, 2, 'HIDDEN'),
(16, 5, 12, NULL, 3, 'HIDDEN'),
(17, 5, 13, NULL, 3, 'HIDDEN'),
(18, 5, 14, NULL, 3, 'HIDDEN'),
(19, 6, 12, NULL, 1, 'HIDDEN'),
(20, 6, 13, NULL, 1, 'HIDDEN'),
(21, 6, 14, NULL, 1, 'HIDDEN'),
(22, 6, 12, NULL, 2, 'HIDDEN'),
(23, 6, 13, NULL, 2, 'HIDDEN'),
(24, 6, 14, NULL, 2, 'HIDDEN'),
(25, 6, 12, NULL, 3, 'HIDDEN'),
(26, 6, 13, NULL, 3, 'HIDDEN'),
(27, 6, 14, NULL, 3, 'HIDDEN'),
(28, 7, 12, NULL, 1, 'HIDDEN'),
(29, 7, 13, NULL, 1, 'HIDDEN'),
(30, 7, 14, NULL, 1, 'HIDDEN'),
(31, 7, 12, NULL, 2, 'HIDDEN'),
(32, 7, 13, NULL, 2, 'HIDDEN'),
(33, 7, 14, NULL, 2, 'HIDDEN'),
(34, 7, 12, NULL, 3, 'HIDDEN'),
(35, 7, 13, NULL, 3, 'HIDDEN'),
(36, 7, 14, NULL, 3, 'HIDDEN'),
(37, 34, 12, NULL, 1, 'HIDDEN'),
(38, 34, 13, NULL, 1, 'HIDDEN'),
(39, 34, 14, NULL, 1, 'HIDDEN'),
(40, 34, 12, NULL, 2, 'HIDDEN'),
(41, 34, 13, NULL, 2, 'HIDDEN'),
(42, 34, 14, NULL, 2, 'HIDDEN'),
(43, 34, 12, NULL, 3, 'HIDDEN'),
(44, 34, 13, NULL, 3, 'HIDDEN'),
(45, 34, 14, NULL, 3, 'HIDDEN'),
(46, 35, 12, NULL, 1, 'HIDDEN'),
(47, 35, 13, NULL, 1, 'HIDDEN'),
(48, 35, 14, NULL, 1, 'HIDDEN'),
(49, 35, 12, NULL, 2, 'HIDDEN'),
(50, 35, 13, NULL, 2, 'HIDDEN'),
(51, 35, 14, NULL, 2, 'HIDDEN'),
(52, 35, 12, NULL, 3, 'HIDDEN'),
(53, 35, 13, NULL, 3, 'HIDDEN'),
(54, 35, 14, NULL, 3, 'HIDDEN'),
(55, 36, 12, NULL, 1, 'HIDDEN'),
(56, 36, 13, NULL, 1, 'HIDDEN'),
(57, 36, 14, NULL, 1, 'HIDDEN'),
(58, 36, 12, NULL, 2, 'HIDDEN'),
(59, 36, 13, NULL, 2, 'HIDDEN'),
(60, 36, 14, NULL, 2, 'HIDDEN'),
(61, 36, 12, NULL, 3, 'HIDDEN'),
(62, 36, 13, NULL, 3, 'HIDDEN'),
(63, 36, 14, NULL, 3, 'HIDDEN'),
(64, 37, 12, NULL, 1, 'HIDDEN'),
(65, 37, 13, NULL, 1, 'HIDDEN'),
(66, 37, 14, NULL, 1, 'HIDDEN'),
(67, 37, 12, NULL, 2, 'HIDDEN'),
(68, 37, 13, NULL, 2, 'HIDDEN'),
(69, 37, 14, NULL, 2, 'HIDDEN'),
(70, 37, 12, NULL, 3, 'HIDDEN'),
(71, 37, 13, NULL, 3, 'HIDDEN'),
(72, 37, 14, NULL, 3, 'HIDDEN'),
(200, 15, 1, 51, 5, 'BOOKED'),
(199, 14, 1, 51, 5, 'BOOKED'),
(198, 13, 1, 51, 5, 'BOOKED'),
(197, 12, 1, 51, 5, 'BOOKED'),
(196, 10, 2, 50, 5, 'BOOKED'),
(195, 9, 2, 50, 5, 'BOOKED'),
(194, 8, 2, 49, 5, 'BOOKED'),
(193, 7, 2, 49, 5, 'BOOKED'),
(192, 6, 2, 48, 5, 'BOOKED'),
(191, 4, 2, 47, 5, 'BOOKED'),
(190, 3, 2, 47, 5, 'BOOKED'),
(189, 2, 2, 46, 5, 'BOOKED'),
(188, 1, 2, 46, 5, 'BOOKED'),
(187, 14, 3, 45, 5, 'BOOKED'),
(186, 13, 3, 45, 5, 'BOOKED'),
(185, 12, 3, 44, 5, 'BOOKED'),
(184, 11, 3, 43, 5, 'BOOKED'),
(183, 10, 3, 42, 5, 'BOOKED'),
(182, 9, 3, 42, 5, 'BOOKED'),
(181, 8, 3, 41, 5, 'BOOKED'),
(180, 7, 3, 41, 5, 'BOOKED'),
(179, 6, 3, 41, 5, 'BOOKED'),
(178, 5, 3, 40, 5, 'BOOKED'),
(177, 15, 3, 39, 5, 'BOOKED'),
(176, 3, 3, 39, 5, 'BOOKED'),
(175, 2, 3, 38, 5, 'BOOKED'),
(174, 1, 3, 38, 5, 'BOOKED'),
(173, 14, 4, 37, 5, 'BOOKED'),
(172, 13, 4, 37, 5, 'BOOKED'),
(171, 12, 4, 36, 5, 'BOOKED'),
(170, 11, 4, 36, 5, 'BOOKED'),
(169, 10, 4, 35, 5, 'BOOKED'),
(168, 9, 4, 34, 5, 'BOOKED'),
(167, 15, 5, 33, 5, 'BOOKED'),
(166, 14, 5, 33, 5, 'BOOKED'),
(165, 11, 5, 32, 5, 'BOOKED'),
(164, 10, 5, 32, 5, 'BOOKED'),
(163, 13, 6, 31, 5, 'BOOKED'),
(162, 11, 1, 30, 5, 'BOOKED'),
(161, 10, 1, 30, 5, 'BOOKED'),
(160, 9, 1, 30, 5, 'BOOKED'),
(159, 8, 1, 30, 5, 'BOOKED'),
(158, 13, 5, 30, 5, 'BOOKED'),
(157, 12, 5, 30, 5, 'BOOKED'),
(156, 9, 5, 30, 5, 'BOOKED'),
(155, 8, 5, 30, 5, 'BOOKED'),
(154, 11, 6, 30, 5, 'BOOKED'),
(153, 14, 7, 29, 5, 'BOOKED'),
(152, 13, 7, 28, 5, 'BOOKED'),
(122, 4, 12, NULL, 4, 'HIDDEN'),
(123, 4, 13, NULL, 4, 'HIDDEN'),
(124, 4, 14, NULL, 4, 'HIDDEN'),
(125, 5, 12, NULL, 4, 'HIDDEN'),
(126, 5, 13, NULL, 4, 'HIDDEN'),
(127, 5, 14, NULL, 4, 'HIDDEN'),
(128, 6, 12, NULL, 4, 'HIDDEN'),
(129, 6, 13, NULL, 4, 'HIDDEN'),
(130, 6, 14, NULL, 4, 'HIDDEN'),
(131, 7, 12, NULL, 4, 'HIDDEN'),
(132, 7, 13, NULL, 4, 'HIDDEN'),
(133, 7, 14, NULL, 4, 'HIDDEN'),
(134, 34, 12, NULL, 4, 'HIDDEN'),
(135, 34, 13, NULL, 4, 'HIDDEN'),
(136, 34, 14, NULL, 4, 'HIDDEN'),
(137, 35, 12, NULL, 4, 'HIDDEN'),
(138, 35, 13, NULL, 4, 'HIDDEN'),
(139, 35, 14, NULL, 4, 'HIDDEN'),
(140, 36, 12, NULL, 4, 'HIDDEN'),
(141, 36, 13, NULL, 4, 'HIDDEN'),
(142, 36, 14, NULL, 4, 'HIDDEN'),
(143, 37, 12, NULL, 4, 'HIDDEN'),
(144, 37, 13, NULL, 4, 'HIDDEN'),
(145, 37, 14, NULL, 4, 'HIDDEN'),
(202, 4, 13, NULL, 5, 'HIDDEN'),
(201, 4, 12, NULL, 5, 'HIDDEN'),
(203, 4, 14, NULL, 5, 'HIDDEN'),
(204, 5, 12, NULL, 5, 'HIDDEN'),
(205, 5, 13, NULL, 5, 'HIDDEN'),
(206, 5, 14, NULL, 5, 'HIDDEN'),
(207, 6, 12, NULL, 5, 'HIDDEN'),
(208, 6, 13, NULL, 5, 'HIDDEN'),
(209, 6, 14, NULL, 5, 'HIDDEN'),
(210, 7, 12, NULL, 5, 'HIDDEN'),
(211, 7, 13, NULL, 5, 'HIDDEN'),
(212, 7, 14, NULL, 5, 'HIDDEN'),
(213, 34, 12, NULL, 5, 'HIDDEN'),
(214, 34, 13, NULL, 5, 'HIDDEN'),
(215, 34, 14, NULL, 5, 'HIDDEN'),
(216, 35, 12, NULL, 5, 'HIDDEN'),
(217, 35, 13, NULL, 5, 'HIDDEN'),
(218, 35, 14, NULL, 5, 'HIDDEN'),
(219, 36, 12, NULL, 5, 'HIDDEN'),
(220, 36, 13, NULL, 5, 'HIDDEN'),
(221, 36, 14, NULL, 5, 'HIDDEN'),
(222, 37, 12, NULL, 5, 'HIDDEN'),
(223, 37, 13, NULL, 5, 'HIDDEN'),
(224, 37, 14, NULL, 5, 'HIDDEN'),
(225, 11, 2, 52, 5, 'BOOKED'),
(226, 12, 2, 52, 5, 'BOOKED'),
(228, 7, 4, 54, 5, 'BOOKED'),
(229, 8, 4, 54, 5, 'BOOKED'),
(230, 14, 6, 55, 5, 'BOOKED'),
(231, 15, 6, 55, 5, 'BOOKED'),
(232, 16, 6, 55, 5, 'BOOKED'),
(233, 17, 6, 55, 5, 'BOOKED');

-- --------------------------------------------------------

--
-- Структура таблицы `t_session`
--

CREATE TABLE IF NOT EXISTS `t_session` (
  `id` int(11) NOT NULL,
  `date` int(11) NOT NULL DEFAULT '0',
  `hall` int(11) NOT NULL DEFAULT '0',
  `performance` int(11) NOT NULL DEFAULT '0'
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `t_session`
--

INSERT INTO `t_session` (`id`, `date`, `hall`, `performance`) VALUES
(5, 32, 1, 3);

-- --------------------------------------------------------

--
-- Структура таблицы `t_theater`
--

CREATE TABLE IF NOT EXISTS `t_theater` (
  `id` int(11) NOT NULL,
  `country` varchar(55) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `city` varchar(55) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `street` varchar(55) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `house` varchar(55) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `post_code` varchar(55) CHARACTER SET utf8 DEFAULT NULL,
  `city_part` varchar(55) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(55) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `maximum_seats` int(11) NOT NULL
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `t_theater`
--

INSERT INTO `t_theater` (`id`, `country`, `city`, `street`, `house`, `post_code`, `city_part`, `name`, `maximum_seats`) VALUES
(1, 'Czech republic', 'Prague', 'Na Zatorce', '1048/16', '160 00', 'Praha 6 Bubenec', 'TEST_THEATER', 5);

-- --------------------------------------------------------

--
-- Структура таблицы `t_timestamp`
--

CREATE TABLE IF NOT EXISTS `t_timestamp` (
  `id` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM AUTO_INCREMENT=69 DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `t_timestamp`
--

INSERT INTO `t_timestamp` (`id`, `date`) VALUES
(32, '2018-04-13 18:00:00'),
(33, '2000-07-01 06:00:00'),
(34, '2000-07-01 06:00:05'),
(35, '2000-07-01 06:00:10'),
(36, '2000-07-01 06:00:15'),
(37, '2000-07-01 06:00:20'),
(38, '2000-07-01 06:00:25'),
(39, '2000-07-01 06:00:30'),
(40, '2000-07-01 06:00:35'),
(41, '2000-07-01 06:00:40'),
(42, '2000-07-01 06:00:45'),
(43, '2000-07-01 06:00:50'),
(44, '2000-07-01 06:00:55'),
(45, '2000-07-01 06:01:00'),
(46, '2000-07-01 06:01:05'),
(47, '2000-07-01 06:01:10'),
(48, '2000-07-01 06:01:15'),
(49, '2000-07-01 06:01:20'),
(50, '2000-07-01 06:01:25'),
(51, '2000-07-01 06:01:30'),
(52, '2000-07-01 06:01:35'),
(53, '2000-07-01 06:01:40'),
(54, '2000-07-01 06:01:45'),
(55, '2000-07-01 06:01:50'),
(56, '2000-07-01 06:01:55'),
(57, '2018-04-11 05:15:36'),
(58, '2018-04-11 09:03:52'),
(59, '2018-04-11 11:15:15'),
(60, '2018-04-11 13:07:40'),
(61, '2018-04-11 14:55:13'),
(62, '2018-04-11 14:55:23'),
(63, '2018-04-11 14:55:35'),
(64, '2018-04-11 15:05:46'),
(65, '2018-04-11 15:08:04'),
(66, '2018-04-11 15:29:38'),
(67, '2018-04-11 15:29:58'),
(68, '2018-04-11 15:30:12');

-- --------------------------------------------------------

--
-- Структура таблицы `t_website_client`
--

CREATE TABLE IF NOT EXISTS `t_website_client` (
  `email` varchar(155) CHARACTER SET utf8 NOT NULL,
  `name` varchar(155) CHARACTER SET utf8 NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `t_website_client`
--

INSERT INTO `t_website_client` (`email`, `name`) VALUES
('777291273', 'Ася'),
('773892964', 'Соня'),
('776790933', 'Елена'),
('+971501059159', 'Artem Basargin'),
('222 222 222', 'Ландау'),
('702945171', 'Кирилл Кулагин'),
('776067965', 'Naděžda Akimová'),
('607653708', 'Mikchail Akimov'),
('+420777145919', 'Житников Никита'),
('774894410', 'Полковник Кругель'),
('776 790 933', 'Человек Ж'),
('000 000 000', 'Саша'),
('608677349', 'Ларионова'),
('608607349', 'Ларионова'),
('774 987 601', 'Андрей '),
('702896382', 'Дима'),
('773 592 283', 'Солдатова Евгения'),
('608540587', 'Anna'),
('608777119', 'Vadim Krupnov'),
('776438276', 'Павел'),
('777655867', 'Рахманова'),
('777 777 777', 'Denis Sabanadze'),
('773615820', 'Olesia '),
('608423174', 'Медведкова'),
('Alexandr.a.prozorov@gmail.com', 'Прозоров Александр'),
('цвфыцвцСОБ@ka.com', 'ТЕСТывф'),
('776 586 451', 'Матухно'),
('777446836', 'Влад Гречка '),
('an@a.aa', 'And');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `t_chair`
--
ALTER TABLE `t_chair`
  ADD PRIMARY KEY (`row`,`number`), ADD UNIQUE KEY `id` (`id`), ADD KEY `hall` (`hall`);

--
-- Индексы таблицы `t_friendship`
--
ALTER TABLE `t_friendship`
  ADD PRIMARY KEY (`email_1`,`email_2`), ADD KEY `email_2` (`email_2`);

--
-- Индексы таблицы `t_hall`
--
ALTER TABLE `t_hall`
  ADD PRIMARY KEY (`name`,`theater`), ADD UNIQUE KEY `id` (`id`), ADD KEY `theater` (`theater`);

--
-- Индексы таблицы `t_order`
--
ALTER TABLE `t_order`
  ADD PRIMARY KEY (`date`), ADD UNIQUE KEY `id` (`id`), ADD KEY `registered_email` (`registered_email`), ADD KEY `website_email` (`website_email`), ADD KEY `phone_number` (`phone_number`);

--
-- Индексы таблицы `t_performance`
--
ALTER TABLE `t_performance`
  ADD PRIMARY KEY (`author`,`title`), ADD UNIQUE KEY `id` (`id`);

--
-- Индексы таблицы `t_phone_client`
--
ALTER TABLE `t_phone_client`
  ADD PRIMARY KEY (`phone`);

--
-- Индексы таблицы `t_profile`
--
ALTER TABLE `t_profile`
  ADD PRIMARY KEY (`email`);

--
-- Индексы таблицы `t_registered_user`
--
ALTER TABLE `t_registered_user`
  ADD PRIMARY KEY (`email`), ADD UNIQUE KEY `login` (`login`);

--
-- Индексы таблицы `t_review`
--
ALTER TABLE `t_review`
  ADD PRIMARY KEY (`date`,`email`,`performance`), ADD KEY `performance` (`performance`), ADD KEY `email` (`email`);

--
-- Индексы таблицы `t_row`
--
ALTER TABLE `t_row`
  ADD PRIMARY KEY (`hall`,`number`), ADD UNIQUE KEY `id` (`id`);

--
-- Индексы таблицы `t_seat`
--
ALTER TABLE `t_seat`
  ADD PRIMARY KEY (`number`,`row`,`session`), ADD UNIQUE KEY `id` (`id`), ADD KEY `row` (`row`), ADD KEY `session` (`session`), ADD KEY `order` (`order`);

--
-- Индексы таблицы `t_session`
--
ALTER TABLE `t_session`
  ADD PRIMARY KEY (`date`,`hall`,`performance`), ADD UNIQUE KEY `id` (`id`), ADD KEY `hall` (`hall`), ADD KEY `performance` (`performance`);

--
-- Индексы таблицы `t_theater`
--
ALTER TABLE `t_theater`
  ADD PRIMARY KEY (`country`,`city`,`street`,`house`,`name`), ADD UNIQUE KEY `id` (`id`);

--
-- Индексы таблицы `t_timestamp`
--
ALTER TABLE `t_timestamp`
  ADD PRIMARY KEY (`date`), ADD UNIQUE KEY `id` (`id`);

--
-- Индексы таблицы `t_website_client`
--
ALTER TABLE `t_website_client`
  ADD PRIMARY KEY (`email`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `t_chair`
--
ALTER TABLE `t_chair`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `t_hall`
--
ALTER TABLE `t_hall`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT для таблицы `t_order`
--
ALTER TABLE `t_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=64;
--
-- AUTO_INCREMENT для таблицы `t_performance`
--
ALTER TABLE `t_performance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT для таблицы `t_row`
--
ALTER TABLE `t_row`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT для таблицы `t_seat`
--
ALTER TABLE `t_seat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=246;
--
-- AUTO_INCREMENT для таблицы `t_session`
--
ALTER TABLE `t_session`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT для таблицы `t_theater`
--
ALTER TABLE `t_theater`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT для таблицы `t_timestamp`
--
ALTER TABLE `t_timestamp`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=69;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
