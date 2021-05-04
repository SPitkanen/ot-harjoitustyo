CREATE TABLE aircraft (id SERIAL PRIMARY KEY, type TEXT, register TEXT);
CREATE TABLE users (id SERIAL PRIMARY KEY, name TEXT, password TEXT, role INTEGER, visible INTEGER);
CREATE TABLE acData (id SERIAL PRIMARY KEY, acid INTEGER REFERENCES aircraft (id), item TEXT, arm DECIMAL, weight DECIMAL, moment DECIMAL, maxweight DECIMAL);
CREATE TABLE acDataDepend (id SERIAL PRIMARY KEY, acid INTEGER REFERENCES aircraft (id), item TEXT, arm DECIMAL, weight DECIMAL, moment DECIMAL, maxweight DECIMAL, section INTEGER, operation INTEGER); 
CREATE TABLE wbtemplate (id SERIAL PRIMARY KEY, planeId INTEGER REFERENCES aircraft(id), xstart DECIMAL, xstop DECIMAL, ystart DECIMAL, ystop DECIMAL, xspacing DECIMAL, yspacing DECIMAL);
CREATE TABLE wbenvelope (id SERIAL PRIMARY KEY, planeId INTEGER REFERENCES aircraft(id), x DECIMAL, y DECIMAL);
CREATE TABLE results (id SERIAL PRIMARY KEY, acid INTEGER REFERENCES aircraft (id), userid INTEGER REFERENCES users (id), item TEXT, arm DECIMAL, weight DECIMAL, moment DECIMAL, visible INTEGER, date TEXT);

