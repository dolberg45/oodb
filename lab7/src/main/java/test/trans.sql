create table dealer(id serial, name varchar(255),
                    Primary key(id));

create table agent(id serial, client_id bigint, dealer_id bigint, Primary key(id),
                   Foreign key(client_id) references client(id) on Delete Cascade,
                   Foreign key(dealer_id) references dealer(id) on Delete Cascade);

create table car(id serial, model varchar(255), price integer, dealer_id bigint, purchase_id bigint,
                 Primary key(id),
                 Foreign key(dealer_id) references dealer(id) on Delete Cascade,
                 Foreign key(purchase_id) references purchase(id) on Delete Cascade);


create table client(id serial, balance integer, agent_id bigint,
                   Primary key(id),
                   Foreign key(agent_id) references agent(id) on Delete Cascade);

create table purchase(id serial, date date, car_id bigint, client_id bigint, dealer_id bigint,
                  Primary key(id),
                  Foreign key(car_id) references car(id) on Delete Cascade on Update Cascade,
                  Foreign key(client_id) references client(id) on Delete Cascade on Update Cascade,
                  Foreign key(dealer_id) references dealer(id) on Delete Cascade on Update Cascade);


SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = 'client';
