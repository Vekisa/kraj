insert into object_type(description,name) values ("Vila","Poveca kuca cesto na obali mora");
insert into object_type(description,name) values ("Hotel","Mnogo dobra stvaar");
insert into object_type(description,name) values ("Motel","Malo losije od hotelaa");
insert into object_type(description,name) values ("Hostel","Uglaavnom je nekkaa babaa gaaazdddaricaaa");

insert into adress(city,latitude,longitude,number,state,street,zip) values ("Novi Sad",45.244575,19.781128,3,"Belgrade","Slobodana Jovanovica",21000);
insert into adress(city,latitude,longitude,number,state,street,zip) values ("Zrenjanin",45.379986,20.383102,27,"Belgrade","Djordjaa Jovaaanovica",20000);
insert into adress(city,latitude,longitude,number,state,street,zip) values ("Sabac",44.755926,19.692607,112,"Belgrade","Nikole Tesle",12000);
insert into adress(city,latitude,longitude,number,state,street,zip) values ("Bor",44.058477,22.101853,91,"Belgrade","Milenko Pavkovic",77000);

insert into object(category,description,name,adress_id,object_type_id) values (4,"Lepa villa u jjoss lllepsse veternikku, tessskkko zaa oddolleti", "Villaa Mosskkkkvaaa",1,1);
insert into object(category,description,name,adress_id,object_type_id) values (5,"Jako lep hotel na jako ocajnom mestu", "Prezident",4,2);

insert into accommodation_type(name,description) values ("Apartman", "Sa kuhinjom");
insert into accommodation_type(name,description) values ("Soba", "Bez kuhinje");
insert into accommodation_type(name,description) values ("BedAndBreakfast", "Svedski sto");

insert into extra_option (name,description,price) values ("Pranje nogu","Sa vrelom vodom",10.3);
insert into extra_option (name,description,price) values ("Jagode sa slagom","Svako jutro",5.4);
insert into extra_option (name,description,price) values ("Cupanje obrva","Sa koncem",2.3);


insert into unit(cancellation,person,beds,accommodation_type_id,object_id) values (10,4,3,1,1);
insert into unit(cancellation,person,beds,accommodation_type_id,object_id) values (20,5,5,2,1);
insert into unit(cancellation,person,beds,accommodation_type_id,object_id) values (30,4,4,1,2);

insert into role(name) values ("ROLE_ADMIN");
insert into role(name) values ("ROLE_REG");
insert into role(name) values ("ROLE_AGENT");

insert into object_extra_option(object_id,extra_option_id) values (1,1);
insert into object_extra_option(object_id,extra_option_id) values (1,2);

INSERT INTO plan(from_date, per_person,price,to_date) VALUES(DATE '2019-06-26', b'0',30,DATE '2019-07-26');
INSERT INTO plan(from_date, per_person,price,to_date) VALUES(DATE '2019-06-26', b'1',40,DATE '2019-07-26');

INSERT INTO price_schedule(made) VALUES(DATE '2019-06-12');

INSERT INTO unit_price_schedule(unit_id,price_schedule_id) VALUES(1,1);

INSERT INTO price_schedule_plan(plan_id,price_schedule_id) VALUES(1,1);
INSERT INTO price_schedule_plan(plan_id,price_schedule_id) VALUES(2,1);

INSERT INTO comment(approved,date_of_publication,text,registered_user_id,unit_id) VALUES (b'1',DATE'2019-06-26',"Skroz koretkno. Lepo smo se proveli!",1,1);
INSERT INTO comment(approved,date_of_publication,text,registered_user_id,unit_id) values(b'1',DATE'2019-06-27','Brate ko bi jos ovde dosao. Ovo je blaga katastrofa!!!!',1,1);

INSERT INTO reservation(confirmed,end,possible_cancellation_date,price,start,registered_user_id,unit_id) values (b'1',DATE'2019-06-10',DATE'2019-06-2',500,DATE'2019-06-20',1,1);