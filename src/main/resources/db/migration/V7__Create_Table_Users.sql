CREATE TABLE IF NOT EXISTS `users` (
   `id` bigint(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
   `user_name` varchar(255) UNIQUE DEFAULT NULL,
   `password` varchar(255) DEFAULT NULL,
   `role` varchar(50) NOT NULL,
   CONSTRAINT check_role CHECK (`role` IN ('USER','ADMIN'))
  );