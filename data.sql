/*==============================================================*/
/* Table: ADMIN                                                 */
/*==============================================================*/
create table ADMIN (
   ID int not null AUTO_INCREMENT,
   USERNAME varchar(50),
   PASSWORD varchar(50),
   NAME varchar(50),
   primary key (ID)
);
create table ROLE (
   ID int not null AUTO_INCREMENT,
   NAME varchar(50),
   primary key (ID)
);
create table USERS_ROLES
(
   USE_ID               int not null,
   ROLE_ID              int not null,
   primary key (USE_ID, ROLE_ID)
);
/*==============================================================*/
/* Table: BRAND                                                 */
/*==============================================================*/
create table BRAND (
   ID int not null AUTO_INCREMENT,
   NAME varchar(50),
   primary key (ID)
);
/*==============================================================*/
/* Table: CATEGORY                                              */
/*==============================================================*/
create table CATEGORY (
   ID int not null AUTO_INCREMENT,
   NAME varchar(50),
   primary key (ID)
);
/*==============================================================*/
/* Table: COLOR                                                 */
/*==============================================================*/
create table COLOR (
   ID int not null AUTO_INCREMENT,
   NAME varchar(50),
   primary key (ID)
);
/*==============================================================*/
/* Table: `ORDER`                                               */
/*==============================================================*/
create table `ORDER` (
   ID int not null AUTO_INCREMENT,
   TRANSACTION_ID int not null,
   PRODUCT_ID int not null,
   QUANTITY int,
   PICE float,
   primary key (ID)
);
/*==============================================================*/
/* Table: PRODUCT                                               */
/*==============================================================*/
create table PRODUCT (
   ID int not null AUTO_INCREMENT,
   BRAND_ID int not null,
   CATEGORY_ID int not null,
   NAME varchar(50),
   PRICE varchar(50),
   CONTENT varchar(50),
   IMG_LINKS varchar(50),
   CREATED int,
   primary key (ID)
);
/*==============================================================*/
/* Table: PRODUCT_COLOR                                         */
/*==============================================================*/
create table PRODUCT_COLOR (
   COLOR_ID int not null ,
   PRODUCT_ID int not null,
   primary key (COLOR_ID, PRODUCT_ID)
);
/*==============================================================*/
/* Table: TRANSACTION                                           */
/*==============================================================*/
create table TRANSACTION (
   ID int not null AUTO_INCREMENT,
   USER_ID int not null,
   STATUS int,
   AMOUNT float(50),
   PAYMENT varchar(50),
   MESSAGE varchar(50),
   CREATED varchar(50),
   primary key (ID)
);
/*==============================================================*/
/* Table: USER                                                  */
/*==============================================================*/
create table `USER` (
   ID int not null AUTO_INCREMENT,
   NAME varchar(50),
   EMAIL varchar(50),
   ADDRESS varchar(50),
   PASSWORD varchar(50),
   CREATED datetime,
   primary key (ID)
);
alter table `ORDER`
add constraint FK_PRODUCT_ORDER foreign key (PRODUCT_ID) references PRODUCT (ID) ON DELETE CASCADE ON UPDATE CASCADE;
alter table `ORDER`
add constraint FK_TRANSACTION_ORDER foreign key (TRANSACTION_ID) references TRANSACTION (ID) ON DELETE CASCADE ON UPDATE CASCADE;
alter table PRODUCT
add constraint FK_CATEGORY_PRODUCT foreign key (CATEGORY_ID) references CATEGORY(ID) ON DELETE CASCADE ON UPDATE CASCADE;
alter table PRODUCT
add constraint FK_PRODUCT_BRAND foreign key (BRAND_ID) references BRAND (ID) ON DELETE CASCADE ON UPDATE CASCADE;
alter table PRODUCT_COLOR
add constraint FK_COLOR_PRODUCT foreign key (COLOR_ID) references COLOR (ID) ON DELETE CASCADE ON UPDATE CASCADE;
alter table PRODUCT_COLOR
add constraint FK_PRODUCT_COLOR foreign key (PRODUCT_ID) references PRODUCT (ID) ON DELETE CASCADE ON UPDATE CASCADE;
alter table TRANSACTION
add constraint FK_USER_TRANSACTION foreign key (USER_ID) references USER (ID) ON DELETE CASCADE ON UPDATE CASCADE;

alter table USERS_ROLES add constraint FK_ROLES_USERS foreign key(role_ID)
      references ROLE (ID) on delete CASCADE on update CASCADE;

alter table USERS_ROLES add constraint FK_USERS_ROLES foreign key (USE_ID)
      references USER (ID) on delete CASCADE on update CASCADE;