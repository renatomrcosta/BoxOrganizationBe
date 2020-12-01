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

create table box
(
	id uuid
		constraint box_pk
			primary key,
	owner_id UUID NOT NULL
        constraint box_user_id_fk
            references "user"(id),
	name TEXT,
	qr_code text
);

create table item
(
	id uuid
		constraint item_pk
			primary key,
    owner_id UUID NOT NULL
        constraint box_user_id_fk
            references "user"(id),
    box_id uuid
		constraint item_box_id_fk
			references box (id),
	name text
);

create index item_id_index on item (id);
create index item_name_index on item (name);

create index box_id_index on box (id);
create index box_name_index on box (name);

create table image
(
	id uuid
		constraint image_pk
			primary key,
	url TEXT
);

create table box_image
(
    box_id uuid
        constraint box_image_box_id_fk
            references box (id),
    image_id uuid
        constraint box_image_image_id_fk
            references image (id)
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

create index box_image_box_id_index on box_image    (box_id);
create index item_image_item_id_index on item_image    (item_id);

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
