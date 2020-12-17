INSERT INTO "user" (id, firebase_uid, name, email)
VALUES ('2fbd2774-06ec-4de7-b657-9617bd9971c5', 1, 'User 1', 'email@emai.com'),
       ('dca4885d-a9ee-41b3-a69a-842b6d527afd', 2, 'User 2', 'email@email.com');

INSERT INTO item(id, name, type, user_id, qr_code, container_item_id)
VALUES ('8cd85844-f63c-4a5d-8e38-3ec158189cf4', 'Box 1, User1', 'Box', '2fbd2774-06ec-4de7-b657-9617bd9971c5', null, null),
       ('69d200af-ccc3-4623-9fd0-4f34f1c8862c', 'Box 2, User1', 'Box', '2fbd2774-06ec-4de7-b657-9617bd9971c5', 'qrhaha',null),
       ('4a075ec8-6d2d-4105-b075-2bf64953a625', 'Box 1, User2', 'Box', 'dca4885d-a9ee-41b3-a69a-842b6d527afd', null, null),
       ('643d0d83-c6b4-4d38-a74b-461f61c23968', 'Item1, Box1, User1', 'Object', '2fbd2774-06ec-4de7-b657-9617bd9971c5',null, '8cd85844-f63c-4a5d-8e38-3ec158189cf4'),
       ('79be2538-801f-4ec9-bddf-222212ad341f', 'Item2, Box1, User1', 'Object', '2fbd2774-06ec-4de7-b657-9617bd9971c5',null, '8cd85844-f63c-4a5d-8e38-3ec158189cf4'),
       ('0cf25970-d9ae-44e4-b132-0638dbc0439d', 'Item1, Box2, User1', 'Object', '2fbd2774-06ec-4de7-b657-9617bd9971c5',null, '69d200af-ccc3-4623-9fd0-4f34f1c8862c'),
       ('47ad7cca-9600-47d6-8087-7cca6a0d906d', 'Item3, no box, User1', 'Object','2fbd2774-06ec-4de7-b657-9617bd9971c5', null, null),
       ('82c7986d-75b2-42ff-a9ba-24ed3fa19aa0', 'Item1, Box1, User2', 'Object','dca4885d-a9ee-41b3-a69a-842b6d527afd', null, '4a075ec8-6d2d-4105-b075-2bf64953a625'),
       ('9c6d70c7-bb7b-472e-97c6-64637f5ab41d', 'Item2, Box1, User2', 'Object','dca4885d-a9ee-41b3-a69a-842b6d527afd', null, '4a075ec8-6d2d-4105-b075-2bf64953a625');

INSERT INTO item_tag(id, item_id, tag)
VALUES ('b6e63c43-c0f3-4452-8f14-79d044a69d67', '643d0d83-c6b4-4d38-a74b-461f61c23968', 'Banana'),
       ('6fe11ae8-3673-44a1-be4e-6e7d58cb8e9d', '643d0d83-c6b4-4d38-a74b-461f61c23968', 'Nanica'),
       ('f86ddbf7-bc16-480b-9437-a8b493ce9175', '9c6d70c7-bb7b-472e-97c6-64637f5ab41d', 'box2thing'),
       ('015e70ea-6c2e-43d8-a4d0-0b485c006b14', '47ad7cca-9600-47d6-8087-7cca6a0d906d', 'Alone');


INSERT INTO image(id, url)
VALUES  ('15026e5e-6fd2-480a-82b8-7383bdb53b4e', 'url1'),
        ('44af065c-bcce-49fd-b1cc-815712c4addb', 'url2'),
        ('271e0d34-54fb-478e-90f6-3644f673e6f3', 'url3'),
        ('74aa48cd-c891-4b09-9fd1-41de64af49df', 'url4');

INSERT INTO item_image(item_id, image_id)
VALUES ('643d0d83-c6b4-4d38-a74b-461f61c23968', '15026e5e-6fd2-480a-82b8-7383bdb53b4e'),
       ('643d0d83-c6b4-4d38-a74b-461f61c23968', '44af065c-bcce-49fd-b1cc-815712c4addb'),
       ('9c6d70c7-bb7b-472e-97c6-64637f5ab41d', '271e0d34-54fb-478e-90f6-3644f673e6f3'),
       ('47ad7cca-9600-47d6-8087-7cca6a0d906d', '74aa48cd-c891-4b09-9fd1-41de64af49df');
