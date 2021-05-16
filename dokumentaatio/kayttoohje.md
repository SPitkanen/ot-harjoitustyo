## Asennus

Lataa githubista projektin zip ja suorita komento: mvn package

Sovellus Käyttää [Postgresiä](https://www.postgresql.org/download/) tietojen tallentamiseen ja olettaa että se on asennettu käyttäjän koneelle. Postgresin saa ladattua koneelle em. linkistä ja seuraamalla sivun asennusohjeita.
Tämän jälkeen käyttäjän tulee luoda kaksi tietokantaa, oletuksena weightandbalance jota käytetään itse sovelluksen tietojen tallettamiseen ja wbtest testejä varten.
* Postgres aukeaa komentoriviltä komennolla: psql *koneen käyttäjätunnus*
* Tietokannat luodaan komennolla: CREATE DATABASE *tietokannan nimi*;
Huomaathan, että tietokantojen nimien tulee olla samat kuin config kansiosta löytyvien db.txt ja testDb.txt tiedostoissa olevat tietokantojen nimet.
Kun sovellus käynnistyy ensimmäisen kerran, se luo tietokantaan tarvittavat taulut ja luo sovellukseen esimerkkikoneen. Sama toistuu myös kun testit ajetaan ensimmäisen kerran.

Tällä hetkellä sovelluksessa ei ole mahdollista luoda uutta konetta itse sovelluksessa, mutta se on käyttäjälle perjaatteessa mahdollista muuttamalla tiedoton referenceValues.txt arvoja (koneen rekisteri, arvot ja id numero) tämä ei ole kuitenkaan kovin suositeltavaa.
Toinen vaihtoehto on lisätä koneita suoraan tietokannan kautta.

## Käyttö

Sovelluksen käynnistyessä käyttäjälle avautuu kirjautumisnäkymä ja samalla vaihtoehto uuden tunnuksen luomiseen.

![login](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/wbappLogin.png)

Onnistuneen kirjautumisen jälkeen käyttäjälle avautuu massabalanssikaavake valmiina täytettäväksi.
Yläpalkin napeista pääsee liikkumaan jokolomakkeeseen, tai listaan tallennetuista lennoista.
[Referenssiarvoja](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/referenceValues.txt) käyttämällä näkymä on kutakuinkin seuraavanlainen.
Ylempään taulukkoon syötetään halutut massat ja calculate-napilla arvot saa laskettua, alempaan kuvaajaan piirtyy massakeskipisteen vaihtelu lennon aikana.
Käyttäjä voi halutessaan tallentaa lasketun kaavakkeen.

![input scene](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/wbappWBInput.png)
![calculated form](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/wbappFilledForm.png)

Mikäli syöte on liian pieni tai suuri, antaa ohjelma siitä varoituksen

![error](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/wbappIncorrectInput.png)

Yläpalkin flight log-napista pääsee katsomaan listauksen tallennetuista lennoista

![results](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/wbappFlightLog.png)

Haluttua lentoa painamalla pääsee katsomaan täytetyn kaavakkeen

![result](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/wbappFlightLogResult.png)
