# Rakenne

Ohjelmassa on kolmikerroksinen rakenne, joka muodostuu käyttöliittymän näyttävästä wbapp.ui:sta, sovelluslogiikan sisältävästä wbapp.domain:sta sekä tietokantaan yhteydessä olevasta wbapp.dao:sta.

![pakkauskaavio](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/wbappPackageUML.png)

# Käyttöliittymä

Käyttöliittymässä on näkymät Kirjautumiseen, uuden käyttäjän luomiseen, kaavakkeen täyttöön, listaus tallennetuista lennoista sekä mahdollisuus tarkastella tiettyä tallennettua lentoa. Ohjeet käytöstä ja kuvia näkymistä löydät [käyttöohjeesta](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

login ja signup käyttävät yhteistä metodia userInputScene käyttäjätietojen keräämiseen.

Jokaiselle näistä on eriytetty oma metodi luokassa Ui. Näitä olisi ehkä hyvä eriyttää omiksi luokikseen, mutta se jää tehtäväksi sovelluksen jatkokehitykseen. Lisäksi luokka Ui sisältää metodit kaavakkeen komponenttien alustamiseen (initWBSheet), Kaavakkeen uudelleenpiirtämiseen (redrawInputSheet) sekä kuvaajan uudellenpiirtämistä varten (redrawEnvelope).

* initWBSheet luo kaavakkeen komponentit, kaavakkeen ruudukossa käytetyn GridPanen, napit ja niiden toiminnot.
* redrawInputSheet tyhjentää ruudukon ja täyttää sen uusilla halutuilla arvoilla (esim koneen vaihto)
* redrawInputSheet tekee saman kuvaajalle.
redrawWBSheet kutsuu molempia metodeita kun kaavake halutaan uusia.

drawFlightLog toteuttaa käytännössä saman näkymän kuin redrawWBSheet, mutta ilman mahdollisuutta muuttaa arvoja. Nämä saisi varmaan yhdistetyä yhteen metodiin, valitettavasti aika loppui tämän kanssa kesken.

Metodi topBar huolehtii valikon piirtämisestä yhdessä metodissa.

Kaikki metodit siirtävät sovelluslogiikasta huolehtimisen paketin domain luokkien tehtäväksi.

# Sovelluslogiikka

Ohjelman sovelluslogiikka käsitellään pakkauksessa wbapp.domain ja se sisältää luokat lentokoneen datan käsittelyyn, käyttäjän datan käsittelyyn, sekä tuloksen käsittelyyn liittyvän luokan.

* Aircraft vastaa lentokone-olion luomisesta, se saa id:n, tyypin ja rekisteritnnuksen
* AircraftData vastaa lentokoneen datan käsittelystä. Yleisilmailukoneilla on massabalanssikaavakkeessa käytännössä kahdenlaisia rivejä. Rivit, jotka ovat samoja kaikille koneille (BASIC WEIGHT, ZERO FUEL WEIGHT, RAMP WEIGHT, TAKE OFF WEIGHT ja LANDING WEIGHT). Nämä säilyvät koneesta toiseen, tietysti yksittäiset arvot vaihtuvat sarakkeissa. Ne toimivat käytännössä laskennan lähtö -ja välivaiheina joihin koneesta toiseen muuttuvia arvoja lasketaan, näitä ovat esimerkiksi penkkirivien ja erilaisten tavaratilojen määrä (referenssikoneessa esim SEATS 1&2 jne). Sovelluksessa nämä rivit on tallennettu omiin tauluihinsa (acdata ja acdatadepend)

luokkakaavio
![luokkakaavio](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Luokkakaavio.jpg)

sekvenssikaavio sovelluksen käynnistymisestä
![sekvenssikaavio init](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/wbappInit.png)
