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

INSERT INTO `categories` (`id`, `name`, `alias`, `enabled`, `parent_id`)
VALUES ('21', 'Asian', 'Asian', '1', '6'),
       ('22', 'Baking', 'Baking', '1', '6'),
       ('23', 'BBQ', 'BBQ', '1', '6'),
       ('24', 'Desserts', 'Desserts', '1', '6'),
       ('25', 'Ghosts', 'Ghosts', '1', '10'),
       ('26', 'Paranormal', 'Paranormal', '1', '10'),
       ('27', 'Supernatural', 'Supernatural', '1', '10'),
       ('28', 'Vampires', 'Vampires', '1', '10'),
       ('29', 'Zombies', 'Zombies', '1', '10'),
       ('30', 'Anatomy', 'Anatomy', '1', '15'),
       ('31', 'Animals', 'Animals', '1', '15'),
       ('32', 'Astronomy', 'Astronomy', '1', '15'),
       ('33', 'Biology', 'Biology', '1', '15'),
       ('34', 'Mathematics', 'Mathematics', '1', '15');

INSERT INTO `products`
VALUES (1, 'Being Britney: Pieces of a Modern Icon', 'Jennifer Otter Bickerdike',
        'being-britney-pieces-of-a-modern-icon',
        'Drawing from a wealth of sources including interviews and fan experiences, Bickerdike paints a vivid picture of Britney Spears’ path to fame, shedding sympathetic but candid light on the life, talent, career and character of the pop icon.',
        NULL, NULL, 1, 1, 30, 37, 0, 0.5, 2),
       (2, 'Art Deco: 50 Works Of Art You Should Know', 'Lynn Federle Orr', 'art-deco-50-works-of-art-you-should-know',
        'This new addition to Prestel\'s successful \"50s\" series focuses on Art Deco, an artistic movement that originated in France after World War I and spread throughout Europe and America. Presented chronologically in full-page illustrations accompanied by explanatory texts, these fifty iconic examples demonstrate the variety of ways Art Deco was expressed.',
        NULL, NULL, 1, 1, 20, 25, 0, 0.5, 1),
       (3, 'The Decision Book: Fifty models for strategic thinking', 'Mikael Krogerus, Roman Tschappeler',
        'The-Decision-Book-Fifty-models-for-strategic-thinking',
        'Most of us face the same questions every day: What do I want? And how can I get it? How can I live more happily and work more efficiently? This updated edition of the international bestseller distils into a single volume the fifty best decision-making models used on MBA courses, and elsewhere, that will help you tackle these important questions - from the well known (the Eisenhower matrix for time management) to the less familiar but equally useful (the Swiss Cheese model).',
        NULL, NULL, 0, 0, 0, 0, 0, 0.5, 3),
       (4, 'Doctor Zhivago', 'Boris Pasternak', 'doctor-zhivago',
        'Dr. Yury Zhivago, Pasternak’s alter ego, is a poet, philosopher, and physician whose life is disrupted by the war and by his love for Lara, the wife of a revolutionary. His artistic nature makes him vulnerable to the brutality and harshness of the Bolsheviks; wandering throughout Russia, he is unable to take control of his fate, and dies in utter poverty. The poems he leaves behind constitute some of the most beautiful writing in the novel.',
        '2021-11-16 12:03:17', '2021-11-16 12:03:17', 1, 1, 34, 34, 0, 0.5, 12),
       (5, 'The Incorrigible Optimists Club', 'Jean-Michel Guenassia', 'the-incorrigible-optimists-club',
        'Paris, 1959. As dusk settles over the immigrant quarter, 12-year-old Michel Marini—amateur photographer and compulsive reader—is drawn to the hum of the local bistro. From his usual position at the football table, he has a vantage point on a grown-up world—of rock \'n\' roll and of the Algerian War. But as the sun sinks and the plastic players spin, Michel\'s concentration is not on the game, but on the huddle of men gathered in the shadows of a back room. Past the bar, behind a partly drawn curtain, a group of eastern European men gather, where under a cirrus of smoke and over the squares of chess boards, they tell of their lives before France—of lovers and wives, children and ambitions.',
        '2021-11-16 12:03:32', '2021-11-16 12:03:32', 1, 0, 30, 30, 0, 0.5, 12),
       (6, 'Dumplings and Noodles: Bao, Gyoza, Biang Biang, Ramen - and Everything in Between', 'Pippa Middlehurst ',
        'Dumplings-and-Noodles-Bao-Gyoza-Biang-Biang-Ramen-and-Everything-in-Between',
        'The winner of Britain’s Best Home Cook delivers a fun, fresh approach to Asian cuisine, boasting seventy easy-to-prepare dishes from pork bao to miso ramen.',
        NULL, NULL, 1, 0, 17, 17, 0, 0.5, 21),
       (7, 'Little Women', 'Louisa May Alcott', 'Little-Women',
        'Brimming with life and each sharply delineated, Little Women’s titular sisters continue to influence and inspire a century and a half after Louisa May Alcott created them. A compelling family drama set in the shadow of the American Civil War, Little Women is a triumph of characterisation and deft storytelling.',
        NULL, NULL, 1, 1, 20, 20, 0, 0.5, 12);

INSERT INTO `bookstore_db`.`product_details` (`id`, `name`, `value`, `product_id`)
VALUES ('1', 'Cover', 'Paperback', '7'),
       ('2', 'Pages', '576', '7'),
       ('3', 'Publisher', 'Penguin Books Ltd', '7');





