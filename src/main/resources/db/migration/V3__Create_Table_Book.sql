CREATE TABLE IF NOT EXISTS `books` (
 `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
 `author` varchar(255) NOT NULL,
 `launch_date` DATE NOT NULL,
 `price` DOUBLE NOT NULL,
 `title` varchar(255) NOT NULL
)