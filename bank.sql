-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 24 Maj 2015, 14:40
-- Wersja serwera: 5.6.21
-- Wersja PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza danych: `bank`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `konto`
--

CREATE TABLE IF NOT EXISTS `konto` (
  `idKonto` int(4) NOT NULL,
  `nazwa` varchar(30) NOT NULL,
  `firma` varchar(30) NOT NULL,
  `dataZalozenia` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `lokata`
--

CREATE TABLE IF NOT EXISTS `lokata` (
  `idLokata` int(4) NOT NULL,
  `idKonto` int(4) DEFAULT NULL,
  `idRodzaj` int(4) DEFAULT NULL,
  `nazwa` varchar(30) NOT NULL,
  `saldo` int(10) NOT NULL,
  `dataZalozenia` date NOT NULL,
  `dataZakonczenia` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `przelew`
--

CREATE TABLE IF NOT EXISTS `przelew` (
  `idPrzelew` int(4) NOT NULL,
  `idRachunek` int(4) DEFAULT NULL,
  `rachunekOdbiorcy` varchar(30) NOT NULL,
  `nazwaOdbiorcy` varchar(30) NOT NULL,
  `adresOdbiorcy` varchar(30) NOT NULL,
  `tytul` varchar(100) NOT NULL,
  `kwota` int(10) NOT NULL,
  `status` varchar(30) NOT NULL,
  `dataWystawienia` date NOT NULL,
  `dataRealizacji` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rachunek`
--

CREATE TABLE IF NOT EXISTS `rachunek` (
  `idRachunek` int(4) NOT NULL,
  `idKonto` int(4) DEFAULT NULL,
  `nazwa` varchar(30) NOT NULL,
  `saldo` int(10) NOT NULL,
  `dataZalozenia` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `rodzaj`
--

CREATE TABLE IF NOT EXISTS `rodzaj` (
  `idRodzaj` int(4) NOT NULL,
  `nazwa` varchar(30) NOT NULL,
  `procent` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownik`
--

CREATE TABLE IF NOT EXISTS `uzytkownik` (
  `idUzytkownik` int(4) NOT NULL,
  `imie` varchar(30) NOT NULL,
  `nazwisko` varchar(30) NOT NULL,
  `typ` varchar(30) NOT NULL,
  `dataRejestracji` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownikkonta`
--

CREATE TABLE IF NOT EXISTS `uzytkownikkonta` (
  `idKonto` int(4) NOT NULL DEFAULT '0',
  `idUzytkownik` int(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `uzytkownikrachunku`
--

CREATE TABLE IF NOT EXISTS `uzytkownikrachunku` (
  `idRachunek` int(4) NOT NULL DEFAULT '0',
  `idUzytkownik` int(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `konto`
--
ALTER TABLE `konto`
 ADD PRIMARY KEY (`idKonto`);

--
-- Indexes for table `lokata`
--
ALTER TABLE `lokata`
 ADD PRIMARY KEY (`idLokata`);

--
-- Indexes for table `przelew`
--
ALTER TABLE `przelew`
 ADD PRIMARY KEY (`idPrzelew`);

--
-- Indexes for table `rachunek`
--
ALTER TABLE `rachunek`
 ADD PRIMARY KEY (`idRachunek`);

--
-- Indexes for table `rodzaj`
--
ALTER TABLE `rodzaj`
 ADD PRIMARY KEY (`idRodzaj`);

--
-- Indexes for table `uzytkownik`
--
ALTER TABLE `uzytkownik`
 ADD PRIMARY KEY (`idUzytkownik`);

--
-- Indexes for table `uzytkownikkonta`
--
ALTER TABLE `uzytkownikkonta`
 ADD PRIMARY KEY (`idKonto`,`idUzytkownik`);

--
-- Indexes for table `uzytkownikrachunku`
--
ALTER TABLE `uzytkownikrachunku`
 ADD PRIMARY KEY (`idRachunek`,`idUzytkownik`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
