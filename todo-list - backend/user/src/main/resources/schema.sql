CREATE TABLE IF NOT EXISTS `task` (
  `id` int AUTO_INCREMENT  PRIMARY KEY,
  `TASK_DESCRIPTION` varchar(100) NOT NULL,
  `created_at` date DEFAULT CURRENT_DATE,
  `updated_at` date DEFAULT NULL
);

