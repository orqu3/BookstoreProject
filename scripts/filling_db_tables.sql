INSERT INTO `roles` (`name`, `description`)
VALUES ('Admin', 'manage everything');
INSERT INTO `roles` (`name`, `description`)
VALUES ('Salesperson', 'manage products, categories, orders');

INSERT INTO `users` (`email`, `first_name`, `last_name`, `enabled`, `password`)
VALUES ('admin@mail.com', 'Glen', 'Doe', '1', '$2a$10$Op29XN5Se3.XsCcWzBYpuuJhZqDYG0/MZof5PIN5RpeC9cR13hXw2');
INSERT INTO `users` (`email`, `first_name`, `last_name`, `enabled`, `password`)
VALUES ('salesperson@mail.com', 'Ann', 'Watertown', '1',
        '$2a$10$kVrVmIORCh52d0Nmn/bCLOp9Gxb7gz/OjlKCpzPqNFUfhaHu624cK');


INSERT INTO `roles_users` (`role_id`, `user_id`)
VALUES ('1', '1');
INSERT INTO `roles_users` (`role_id`, `user_id`)
VALUES ('2', '2');

INSERT INTO `categories` (`id`, `name`, `alias`, `enabled`)
VALUES ('1', 'Arts', 'Arts', '1'),
       ('2', 'Biographies', 'Biographies', '1'),
       ('3', 'Business', 'Business', '1'),
       ('4', 'Comics', 'Comics', '1'),
       ('5', 'Computers', 'Computers', '1'),
       ('6', 'Cooking', 'Cooking', '1'),
       ('7', 'Education', 'Education', '1'),
       ('8', 'Health', 'Health', '1'),
       ('9', 'History', 'History', '1'),
       ('10', 'Horror', 'Horror', '1'),
       ('11', 'Kids', 'Kids', '1'),
       ('12', 'Fiction', 'Fiction', '1'),
       ('13', 'Parenting', 'Parenting', '1'),
       ('14', 'Fantasy', 'Fantasy', '1'),
       ('15', 'Science', 'Science', '1'),
       ('16', 'Teen', 'Teen', '1'),
       ('17', 'Travel', 'Travel', '1'),
       ('18', 'Westerns', 'Westerns', '1'),
       ('19', 'Home', 'Home', '1'),
       ('20', 'Mystery', 'Mystery', '1');

INSERT INTO `categories` (`name`, `alias`, `enabled`, `parent_id`)
VALUES ('Asian', 'Asian', '1', '6'),
       ('Baking', 'Baking', '1', '6'),
       ('BBQ', 'BBQ', '1', '6'),
       ('Desserts', 'Desserts', '1', '6'),
       ('Ghosts', 'Ghosts', '1', '10'),
       ('Paranormal', 'Paranormal', '1', '10'),
       ('Supernatural', 'Supernatural', '1', '10'),
       ('Vampires', 'Vampires', '1', '10'),
       ('Zombies', 'Zombies', '1', '10'),
       ('Anatomy', 'Anatomy', '1', '15'),
       ('Animals', 'Animals', '1', '15'),
       ('Astronomy', 'Astronomy', '1', '15'),
       ('Biology', 'Biology', '1', '15'),
       ('Mathematics', 'Mathematics', '1', '15');

