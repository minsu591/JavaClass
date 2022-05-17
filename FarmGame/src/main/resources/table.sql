
  CREATE TABLE "HOMEFARM"."ACCOUNTS" 
   (	"ACC_ID" VARCHAR2(50 BYTE), 
	"ACC_PW" VARCHAR2(50 BYTE) NOT NULL ENABLE, 
	"ACC_PHONE" VARCHAR2(14 BYTE) NOT NULL ENABLE, 
	 CONSTRAINT "ACC_ID_PK" PRIMARY KEY ("ACC_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;


  
  CREATE TABLE "HOMEFARM"."ALL_PRODUCTS" 
   (	"ITEM_ID" NUMBER(10,0), 
	"ITEM_NAME" VARCHAR2(50 BYTE) NOT NULL ENABLE, 
	"A_COST" NUMBER(6,0) DEFAULT 1, 
	"A_LEVEL" NUMBER(5,0) NOT NULL ENABLE, 
	"A_EXPLAIN" VARCHAR2(50 BYTE), 
	"C_TIME" NUMBER(10,0), 
	"C_EXP" NUMBER(20,0), 
	"C_HP" NUMBER(5,0) DEFAULT 5, 
	 PRIMARY KEY ("ITEM_ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE, 
	 UNIQUE ("ITEM_NAME")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;


  CREATE TABLE "HOMEFARM"."CHARACTERS" 
   (	"ACC_ID" VARCHAR2(50 BYTE), 
	"FARM_NAME" VARCHAR2(50 BYTE) NOT NULL ENABLE, 
	"USER_NICKNAME" VARCHAR2(50 BYTE) NOT NULL ENABLE, 
	"USER_HP" NUMBER(5,0) DEFAULT 50, 
	"USER_LEVEL" NUMBER(5,0) DEFAULT 1 NOT NULL ENABLE, 
	"USER_EXP" NUMBER(10,0) DEFAULT 0, 
	"USER_MONEY" NUMBER(15,0) DEFAULT 100, 
	"USER_CHARACTER" VARCHAR2(50 BYTE) DEFAULT '୧(๑•̀⌄•́๑)૭', 
	 CONSTRAINT "ACC_CH_FK" FOREIGN KEY ("ACC_ID")
	  REFERENCES "HOMEFARM"."ACCOUNTS" ("ACC_ID") ON DELETE CASCADE ENABLE, 
	 CONSTRAINT "CH_LEV_FK" FOREIGN KEY ("USER_LEVEL")
	  REFERENCES "HOMEFARM"."LEVELS" ("USER_LEVEL") ON DELETE CASCADE ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;


  CREATE TABLE "HOMEFARM"."IN_FIELD" 
   (	"ACC_ID" VARCHAR2(50 BYTE), 
	"FIELD_X" NUMBER(5,0) NOT NULL ENABLE, 
	"FIELD_Y" NUMBER(5,0) NOT NULL ENABLE, 
	"ITEM_ID" NUMBER(10,0), 
	 CONSTRAINT "FI_ACC_FK" FOREIGN KEY ("ACC_ID")
	  REFERENCES "HOMEFARM"."ACCOUNTS" ("ACC_ID") ON DELETE CASCADE ENABLE, 
	 CONSTRAINT "FI_ITEM_ID_FK" FOREIGN KEY ("ITEM_ID")
	  REFERENCES "HOMEFARM"."ALL_PRODUCTS" ("ITEM_ID") ON DELETE CASCADE ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;


  CREATE TABLE "HOMEFARM"."ITEMS" 
   (	"ACC_ID" VARCHAR2(50 BYTE), 
	"ITEM_ID" NUMBER(10,0), 
	"ITEM_CNT" NUMBER(10,0) DEFAULT 1, 
	 CONSTRAINT "ITEM_ID_FK" FOREIGN KEY ("ITEM_ID")
	  REFERENCES "HOMEFARM"."ALL_PRODUCTS" ("ITEM_ID") ON DELETE CASCADE ENABLE, 
	 CONSTRAINT "ITEM_ACC_ID_FK" FOREIGN KEY ("ACC_ID")
	  REFERENCES "HOMEFARM"."ACCOUNTS" ("ACC_ID") ON DELETE CASCADE ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;


  CREATE TABLE "HOMEFARM"."LEVELS" 
   (	"USER_LEVEL" NUMBER(5,0), 
	"LEVEL_NAME" VARCHAR2(100 BYTE) DEFAULT '왕초보 농부', 
	"MAX_EXP" NUMBER(20,0) NOT NULL ENABLE, 
	"MAX_HP" NUMBER(20,0) NOT NULL ENABLE, 
	 PRIMARY KEY ("USER_LEVEL")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
