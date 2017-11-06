--
-- JBoss, Home of Professional Open Source
-- Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements
-- Since the database doesn't know to increase the Sequence to match what is manually loaded here it starts at 1 and tries
--  to enter a record with the same PK and create an error.  If we use a high we don't interfere with the sequencing (at least until later).
-- NOTE: this file should be removed for production systems. 
<!--
insert into Customer (name,email,phoneNumber) values ('john','a13521871@gmail.com','01234567891')
insert into Customer (name,email,phoneNumber) values ('john1','a13521872@gmail.com','01234567892')
insert into Customer (name,email,phoneNumber) values ('john2','a13521873@gmail.com','01234567893')
insert into Customer (name,email,phoneNumber) values ('john3','a13521874@gmail.com','01234567894')
insert into taxi (registrationNumber,numberOfSeats) values ('123456a',3)
insert into taxi (registrationNumber,numberOfSeats) values ('v23456a',2)
insert into taxi (registrationNumber,numberOfSeats) values ('a23456a',20)
insert into taxi (registrationNumber,numberOfSeats) values ('123456b',10)
insert into booking (customerId,taxiId,bookingdate) values (1,1,'2018-10-01')
insert into booking (customerId,taxiId,bookingdate) values (1,2,'2018-11-01')
insert into booking (customerId,taxiId,bookingdate) values (2,3,'2018-12-01')

-->
