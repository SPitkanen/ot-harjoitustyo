CREATE TABLE users (id SERIAL PRIMARY KEY, name TEXT, password TEXT, role TEXT);
CREATE TABLE aircraft (id SERIAL PRIMARY KEY, type TEXT, register, TEXT);
CREATE TABLE acData (id SERIAL PRIMARY KEY, acid INTEGER REFERENCES aircraft (id), item TEXT, arm DECIMAL, weight DECIMAL, moment DECIMAL, maxweight DECIMAL);
CREATE TABLE acDataDepend (id SERIAL PRIMARY KEY, acid INTEGER REFERENCES aircraft (id), item TEXT, arm DECIMAL, weight DECIMAL, moment DECIMAL, maxweight DECIMAL, section INTEGER); 

