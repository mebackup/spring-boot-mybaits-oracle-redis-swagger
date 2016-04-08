create table Z_TB_TEST_INVENTORY
(
  id         NUMBER not null,
  count      NUMBER not null,
  product_id NUMBER not null
)

create table Z_TB_TEST_ORDER
(
  id          NUMBER not null,
  create_time TIMESTAMP(6) not null,
  user_id     NUMBER not null,
  status      NUMBER not null
)

create table Z_TB_TEST_ORDER_ITEM
(
  id         NUMBER not null,
  product_id NUMBER not null,
  count      NUMBER not null,
  order_id   NUMBER not null
)

create table Z_TB_TEST_PRODUCT
(
  id          NUMBER not null,
  create_time TIMESTAMP(6) not null,
  name        VARCHAR2(100)
)

create table Z_TB_TEST_USER
(
  id          NUMBER not null,
  name        VARCHAR2(100),
  create_time TIMESTAMP(6)
)
create index Z_IDX_TB_TEST_USER_CREATE_TIME on Z_TB_TEST_USER (CREATE_TIME)
create unique index Z_IDX_TB_TEST_USER_ID_INDEX on Z_TB_TEST_USER (ID)
