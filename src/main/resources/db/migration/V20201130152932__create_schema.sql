create table "user"
(
	id UUID
		constraint user_pk
			primary key,
	firebase_uid TEXT,
	name TEXT,
	email TEXT
);

create unique index user_firebase_uid_uindex
	on "user" (firebase_uid);

create type ItemType as enum ('Box', 'Object');

create table item
(
	id uuid
		constraint item_pk
			primary key,
    name TEXT,
    type ItemType NOT NULL,
	user_id UUID NOT NULL
        constraint item_user_id_fk
            references "user"(id),
	qr_code text NULL,
	container_item_id uuid null
        constraint item_container_id_fk
            references item(id)
);


create index item_id_index on item (id);
create index item_name_index on item (name);

create table image
(
	id uuid
		constraint image_pk
			primary key,
	url TEXT
);


create table item_image
(
    item_id uuid
        constraint item_image_item_id_fk
            references item (id),
    image_id uuid
        constraint item_image_image_id_fk
            references image (id)
);

create index item_image_item_id_index on item_image(item_id);

create table item_tag
(
    item_id uuid
        constraint item_tag_item_id_fk
            references item (id),
    tag text
);

create index item_tag_item_id_index
    on item_tag (item_id);
create index item_tag_item_tag_index
    on item_tag (tag);
