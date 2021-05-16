## Sovelluksen tarkoitus

Sovelluksen tarkoituksena on laskea yleisilmailukoneen weight&balance kaavake automaattisesti käyytäjän antamilla massoilla. 
Tuloksena käyttäjä saa taulukon, josta selviää koneen massakeskipisteet ja painot eri lennon vaiheille.

## Käyttäjät

Sovelluksessa on tavallinen käyttäjä, joka voi tehdä uuden laskelman, tallentaa sen sekä selata vanhoja kaavakkeita.
Perjaatteessa käyttäjän on mahdollista luoda uusia koneita muuttamalla referenceValues.txt tiedoston arvoja, tämä ei ole kuitenkaan kovin suositeltava tapa.
Tarkoituksena olisi laajentaa sovellusta myöhemmin siten, että sovellukseen lisätään admin käyttäjä jolla on oikeudet koneiden lisäämiseen ja muokkaamiseen suoraan sovelluksen kautta.

## Sovelluksen toiminnallisuudet

### Ennen kirjautumista
* Käyttäjä pystyy luomaan uuden käyttäjätunnuksen
* Käyttäjä pystyy kirjautumaan sovellukseen omalla tunnuksella

### Sisäänkirjautumisen jälkeen
* Käyttäjä näkee tyhjän kaavakkeen valmiinaa täytettäväksi
* Kaavake tarkistaa käyttäjän syötteet ja antaa varoituksen virheellisistä arvoista (liian suuret ja pienet luvut sekä muut kuin lukuarvot)
* Käyttäjä voi vaihtaa konetta valikosta
* Käyttäjä voi tallentaa täytetyn kaavakkeen
* Käyttäjä voi selata vanhoja kaavakkeita  

## Jatkokehitys
* Ylläpitäjän profiili koneiden lisäämiseen ja arvojen päivityksiin
* Lisää ominauuksia lennonsuunnitteluun
* Erillinen lomake lennonsuunnitteluun
* Koneiden suoritusarvojen tallentaminen
* Mahdollisuus lomakkeiden tulostamiseen

