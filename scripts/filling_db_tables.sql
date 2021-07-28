INSERT INTO `roles` (`name`, `description`) VALUES ('Admin', 'manage everything');
INSERT INTO `roles` (`name`, `description`) VALUES ('Salesperson', 'manage products, categories, orders');

INSERT INTO `users` (`email`, `first_name`, `last_name`, `enabled`, `password`) VALUES ('admin@mail.com', 'Glen', 'Doe', '1', '$2a$10$Op29XN5Se3.XsCcWzBYpuuJhZqDYG0/MZof5PIN5RpeC9cR13hXw2');
INSERT INTO `users` (`email`, `first_name`, `last_name`, `enabled`, `password`) VALUES ('salesperson@mail.com', 'Ann', 'Watertown', '1', '$2a$10$kVrVmIORCh52d0Nmn/bCLOp9Gxb7gz/OjlKCpzPqNFUfhaHu624cK');


INSERT INTO `roles_users` (`role_id`, `user_id`) VALUES ('1', '1');
INSERT INTO `roles_users` (`role_id`, `user_id`) VALUES ('2', '2');