-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 08, 2023 at 09:52 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `course_management_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `assigned_modules`
--

CREATE TABLE `assigned_modules` (
  `Teacher_Name` varchar(50) NOT NULL,
  `Module_1` varchar(40) NOT NULL,
  `Module_2` varchar(40) NOT NULL,
  `Module_3` varchar(40) NOT NULL,
  `Module_4` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `assigned_modules`
--

INSERT INTO `assigned_modules` (`Teacher_Name`, `Module_1`, `Module_2`, `Module_3`, `Module_4`) VALUES
('Ashish Acharya', 'OODP', 'NMC', 'IPA', 'ISA'),
('Prabin Sapkota', 'OODP', 'NMC', 'ISA', 'ASP'),
('Kush Paudel', 'ISA', 'Web.Tech', 'Database', 'NMC'),
('Raj Shrestha', 'OODP', 'NMC', 'ISA', 'Database'),
('Kisha Shrestha', 'NMC', 'Database', 'AI', 'Big Data'),
('Kriti Parajuli', 'OODP', 'Big Data', 'NMC', 'Statistics'),
('Jayesh Rajbhandari', 'OODP', '', '', ''),
('Ramesh kc', 'NMC', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `assignment`
--

CREATE TABLE `assignment` (
  `Id` int(11) NOT NULL,
  `Teacher_Name` varchar(40) NOT NULL,
  `Module_Name` varchar(40) NOT NULL,
  `AssignmentOne` varchar(70) NOT NULL,
  `AssignmentTwo` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `assignment`
--

INSERT INTO `assignment` (`Id`, `Teacher_Name`, `Module_Name`, `AssignmentOne`, `AssignmentTwo`) VALUES
(1, 'Ashish Acharya', 'NMC', 'Complete Lab report 7', 'Complete WorkShop 7'),
(2, 'Prashant Shrestha', 'AI', 'Complete workshop 7', 'complete tutorial 7'),
(3, 'Jayesh Khanal', 'NMC', 'workshop6', 'coursework 1'),
(4, 'Prabin Sapkota', 'NMC', 'Tutorial 1', 'workshop1'),
(5, 'Manisha Maharjan', 'AI', 'Ques 5', 'workshop 10'),
(7, 'Subash karki', 'AI', 'Tutorial 1', 'wkshop 2');

-- --------------------------------------------------------

--
-- Table structure for table `bds_optional_modules`
--

CREATE TABLE `bds_optional_modules` (
  `Id` int(11) NOT NULL,
  `Optional_One` varchar(50) NOT NULL,
  `Optional_Two` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bds_optional_modules`
--

INSERT INTO `bds_optional_modules` (`Id`, `Optional_One`, `Optional_Two`) VALUES
(1, 'Organizing Skills', '');

-- --------------------------------------------------------

--
-- Table structure for table `bibm_optional_modules`
--

CREATE TABLE `bibm_optional_modules` (
  `Id` int(11) NOT NULL,
  `Optional_One` varchar(50) NOT NULL,
  `Optional_Two` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bibm_optional_modules`
--

INSERT INTO `bibm_optional_modules` (`Id`, `Optional_One`, `Optional_Two`) VALUES
(1, 'Organizing Skills', 'PPD');

-- --------------------------------------------------------

--
-- Table structure for table `bit_optional_modules`
--

CREATE TABLE `bit_optional_modules` (
  `Id` int(11) NOT NULL,
  `Optional_One` varchar(50) NOT NULL,
  `Optional_Two` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bit_optional_modules`
--

INSERT INTO `bit_optional_modules` (`Id`, `Optional_One`, `Optional_Two`) VALUES
(1, 'Networking', 'Robotics'),
(2, 'Model Designing', 'Statistics');

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `Course_Id` int(11) NOT NULL,
  `Course_Name` varchar(20) NOT NULL,
  `No_Of_Modules` int(11) NOT NULL,
  `Active_Status` varchar(15) NOT NULL,
  `Length` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`Course_Id`, `Course_Name`, `No_Of_Modules`, `Active_Status`, `Length`) VALUES
(4, 'BIBM', 13, 'NO', 4),
(7, 'BDS', 16, 'NO', 4),
(8, 'BIT', 16, 'YES', 3);

-- --------------------------------------------------------

--
-- Table structure for table `marks`
--

CREATE TABLE `marks` (
  `student_Id` int(11) NOT NULL,
  `Student_Name` varchar(50) NOT NULL,
  `Level` int(11) NOT NULL,
  `Module_1` int(11) NOT NULL,
  `Module_2` int(11) NOT NULL,
  `Module_3` int(11) NOT NULL,
  `Module_4` int(11) NOT NULL,
  `Module_5` int(11) NOT NULL,
  `Module_6` int(11) NOT NULL,
  `Module_7` int(11) NOT NULL,
  `Module_8` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `marks`
--

INSERT INTO `marks` (`student_Id`, `Student_Name`, `Level`, `Module_1`, `Module_2`, `Module_3`, `Module_4`, `Module_5`, `Module_6`, `Module_7`, `Module_8`) VALUES
(1, 'Saurya Shretha', 6, 57, 61, 75, 97, 36, 57, 68, 82),
(2, 'Kailash Parajuli', 6, 23, 32, 54, 65, 87, 24, 45, 43),
(4, 'Manish Maharjan', 5, 34, 32, 23, 12, 32, 23, 12, 23),
(5, 'Hailey Michaels', 6, 56, 54, 43, 23, 43, 54, 43, 34),
(6, 'Jeremy Walters', 5, 34, 23, 45, 45, 34, 65, 87, 56),
(7, 'Isha Shijapati', 5, 34, 23, 45, 45, 34, 65, 87, 56),
(8, 'Tom henks', 5, 34, 23, 45, 45, 34, 65, 87, 56),
(9, 'Krisha paudel', 5, 34, 23, 45, 45, 34, 65, 87, 56),
(10, 'Justin Timberlake', 5, 34, 23, 45, 45, 34, 65, 87, 56),
(11, 'Taylor Swift', 5, 34, 23, 45, 45, 34, 65, 87, 56),
(12, 'Tom Cruise', 5, 34, 23, 45, 45, 34, 65, 87, 56),
(13, 'Sharukh Khan', 5, 34, 23, 45, 45, 34, 65, 87, 56),
(14, 'Osama Binladin', 5, 34, 23, 45, 45, 34, 65, 87, 56),
(15, 'Harry Potter', 5, 23, 32, 12, 43, 45, 65, 32, 12),
(16, 'Jessy Pinkman', 5, 23, 56, 86, 23, 54, 67, 45, 45),
(17, 'Jesh Thapa', 5, 34, 54, 67, 34, 56, 43, 23, 43);

-- --------------------------------------------------------

--
-- Table structure for table `module`
--

CREATE TABLE `module` (
  `Module_Name` varchar(50) NOT NULL,
  `Course_Name` varchar(20) NOT NULL,
  `Level` varchar(20) NOT NULL,
  `Semester` varchar(20) NOT NULL,
  `Credit_Value` int(11) NOT NULL,
  `Optional_Module` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `module`
--

INSERT INTO `module` (`Module_Name`, `Course_Name`, `Level`, `Semester`, `Credit_Value`, `Optional_Module`) VALUES
('Concepts And technologies AI', 'BIBM', 'Level 5', 'Semester 3', 4, 'YES'),
('Marketing', 'BIBM', 'Level 4', 'Semester 2', 4, 'YES');

-- --------------------------------------------------------

--
-- Table structure for table `registered_students`
--

CREATE TABLE `registered_students` (
  `NMC` varchar(50) NOT NULL,
  `ISA` varchar(50) NOT NULL,
  `OODP` varchar(50) NOT NULL,
  `ISP` varchar(50) NOT NULL,
  `DSA` varchar(50) NOT NULL,
  `AI` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `registered_students`
--

INSERT INTO `registered_students` (`NMC`, `ISA`, `OODP`, `ISP`, `DSA`, `AI`) VALUES
('Justin Timberlake', 'Austin Theory', 'Roman Reigns', 'Ram Thapa', 'Jesh Thapa', 'Yuman Thapa'),
('Ojaswee Rai', 'Bibek Neupane', 'Kimber Kardasian', 'Justin Newa', 'Pushpa Kamal', 'Tom Henks'),
('Kush paudel', 'Karki Ramesh', 'Kunfu panda', 'Lunna Walters', 'Jessy Pinkman', 'Pinky Thapa'),
('Ojaswee Rai', 'Kritika Thapa', 'Jery Ham', 'Hungary karki', 'Jenisha rai', 'Ragnar Lothbroke'),
('Rajendra Rai', 'Bunty Maharjan', 'Ganga Naima', 'Derik Walter', 'Pokemon Karki', 'Lothbroke Timbers'),
('Isha Maharjan', 'dinerys Targerian', 'Tyrion lannister', 'Josh pinkman', 'Tom Granger', 'Lex Luther'),
('Nikky Wlaters', 'Herseys Nuggy', 'Nuggets hums', 'Tiffany Jam', 'Jeremy Suik', 'Suikka Juna'),
('Chinay Nauma', 'Naima Pravin', 'Justin Kerry', 'Grager junm', 'Jamuna', 'Lother'),
('Rajendra Rai', 'Bunty Maharjan', 'Ganga Naima', 'Derik Walter', 'Jeremy Suik', 'Suikka Juna'),
('Ojaswee Rai', 'Kritika Thapa', 'Justin Kerry', 'Justin Newa', 'Jenisha rai', 'Ragnar Lothbroke'),
('Nikky Wlaters', 'Herseys Nuggy', 'Nuggets hums', 'Ram Thapa', 'Jeremy Suik', 'Pinky Thapa'),
('Isha Maharjan', 'Bibek Neupane', 'Tyrion lannister', 'Hungary karki', 'Tom Granger', 'Lother lexy'),
('Newa rocks', 'Kimmy kimee', 'Lume Nepa', 'Heneary Dumphrese', 'Rock Rokcy', 'Bobby Walters'),
('Manish Rai', 'Kriti Shretha', 'Makalu Makal', 'badma Wlaa', 'Poke master', 'Holy Master');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `Id` int(11) NOT NULL,
  `Student_Name` varchar(40) NOT NULL,
  `Semester` varchar(20) NOT NULL,
  `Level` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`Id`, `Student_Name`, `Semester`, `Level`) VALUES
(1, 'Manish Maharjan', '2', '5'),
(2, 'Newa Shrestha', '1', '4'),
(3, 'Granger Rai', '2', '5'),
(5, 'Ram Aryal', '4', '4'),
(6, 'Ram Shretha', '5', '6'),
(7, 'Ram Paudel', '6', '4'),
(8, 'Pushpa Paudel', '6', '5'),
(9, 'Pushpa kamal', '6', '5'),
(10, 'Ram Karki', '6', '5'),
(11, 'Shyam Rai', '5', '4'),
(12, 'Hemant maharjan', '5', '6'),
(13, 'Hemant Shretha', '5', '6'),
(14, 'Luitel Ram', '4', '6'),
(15, 'Lothbroke karki', '4', '6'),
(16, 'Ragnar Rai', '4', '5'),
(17, 'Pushpa Bahadur', '5', '6'),
(18, 'Naima Kamal', '4', '5');

-- --------------------------------------------------------

--
-- Table structure for table `submitted_assignments`
--

CREATE TABLE `submitted_assignments` (
  `Id` int(11) NOT NULL,
  `Assignment_One` varchar(40) NOT NULL,
  `Assignment_Two` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `submitted_assignments`
--

INSERT INTO `submitted_assignments` (`Id`, `Assignment_One`, `Assignment_Two`) VALUES
(1, 'file1', 'file2'),
(2, 'file1', 'file1');

-- --------------------------------------------------------

--
-- Table structure for table `tutor`
--

CREATE TABLE `tutor` (
  `Id` int(11) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Phone_Number` bigint(20) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `Sex` varchar(10) NOT NULL,
  `Module_Assigned` varchar(20) NOT NULL,
  `Date_Of_Birth` date NOT NULL,
  `Full_Time` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tutor`
--

INSERT INTO `tutor` (`Id`, `Name`, `Phone_Number`, `Address`, `Sex`, `Module_Assigned`, `Date_Of_Birth`, `Full_Time`) VALUES
(13, 'kailash parajuli', 768782734, 'bhaktapur', 'Male ', 'ISA', '1988-01-23', 'YES'),
(15, 'Rakesh Paudel', 98347834, 'kathmandu', 'Male ', 'ISA', '1981-01-10', 'YES'),
(17, 'Jerry Lawyer', 987572632, 'Pokhara', 'Male ', 'OODP', '1999-01-10', 'YES'),
(18, 'Raj Pradhan', 9854365453, 'pokahara', 'Male ', 'Concepts AI', '1990-12-21', 'YES'),
(19, 'Raj Shretha', 9854334651, 'pokahara', 'Male ', 'OODP', '1990-12-21', 'YES'),
(20, 'Raju Aryal', 9853712643, 'Ktm', 'Male ', 'ISA', '1990-12-21', 'YES'),
(21, 'Krishna Ram', 9846577543, 'Ktm', 'Male ', 'NMC', '1990-12-21', 'YES'),
(22, 'Hari lal', 984623412, 'Ktm', 'Male ', 'Concepts AI', '1990-12-21', 'YES'),
(23, 'Sita Aryal', 9846252342, 'Ktm', 'Female', 'OODP', '1990-12-21', 'NO'),
(24, 'Sita Rai', 984629843, 'Ktm', 'Female', 'ISA', '1990-12-21', 'NO'),
(25, 'Pravash Karki', 9874326111, 'Ktm', 'Male ', 'NMC', '1990-12-21', 'NO'),
(26, 'Yamu Paudel', 9864519078, 'Ktm', 'Male ', 'ISA', '1990-12-21', 'NO'),
(27, 'Krishna Paudel', 985767845, 'ktm', 'Male ', 'OODP', '1990-01-12', 'YES'),
(29, 'Newa Nepali', 9785467545, 'Pokhara', 'Male ', 'ISA', '1998-12-21', 'YES'),
(30, 'Jeremy Christon', 9877762323, 'Biratnagar', 'Male ', 'OODP', '1912-11-01', 'YES');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `User_Id` int(11) NOT NULL,
  `Full_Name` varchar(50) NOT NULL,
  `Gender` varchar(20) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `Course` varchar(20) NOT NULL,
  `User_Type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`User_Id`, `Full_Name`, `Gender`, `Username`, `Password`, `Course`, `User_Type`) VALUES
(2, 'Jerry Prichet', 'Male', 'Jerry', 'Jerry', 'BIBM', 'Student'),
(4, 'Yami Paudel', 'Female', 'Yami', 'Yami', 'BIT', 'Student'),
(5, 'hemal pant', 'Female', 'hemal', 'hemal', 'BIT', 'Student'),
(6, 'Kailash Parajuli', 'Male', 'parajuli', 'parajuli', 'BIT', 'Teacher'),
(7, 'subash Shrestha', 'Male', 'Subash', 'Subash', 'BIT', 'Admin'),
(8, 'Sumedha Paudel', 'Female', 'Sumedha', 'Sumedha', 'BIT', 'Student'),
(10, 'Prabin Sapkota', 'Male', 'Prabin', 'Prabin', 'BIBM', 'Teacher'),
(11, 'Tom Hanks', 'Male', 'Tom', 'Tom', 'BIBM', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `assignment`
--
ALTER TABLE `assignment`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `bds_optional_modules`
--
ALTER TABLE `bds_optional_modules`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `bibm_optional_modules`
--
ALTER TABLE `bibm_optional_modules`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `bit_optional_modules`
--
ALTER TABLE `bit_optional_modules`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`Course_Id`);

--
-- Indexes for table `marks`
--
ALTER TABLE `marks`
  ADD PRIMARY KEY (`student_Id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `submitted_assignments`
--
ALTER TABLE `submitted_assignments`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `tutor`
--
ALTER TABLE `tutor`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`User_Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `assignment`
--
ALTER TABLE `assignment`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `bds_optional_modules`
--
ALTER TABLE `bds_optional_modules`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `bibm_optional_modules`
--
ALTER TABLE `bibm_optional_modules`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `bit_optional_modules`
--
ALTER TABLE `bit_optional_modules`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `Course_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `marks`
--
ALTER TABLE `marks`
  MODIFY `student_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `submitted_assignments`
--
ALTER TABLE `submitted_assignments`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tutor`
--
ALTER TABLE `tutor`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `User_Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
