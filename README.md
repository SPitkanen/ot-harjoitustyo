# Ohjelmistotekniikka, harhoitustyö

Työn tarkoituksena on toteuttaa sovellus, jolla voi laskea yleisilmailukoneen massakeskipisteen.
Sovelluksen käyttäjä pystyy:

* Kirjautumaan sovellukseen tai luomaan uuden tunnuksen
* Täyttämään ja laskemaan kaavakkeet tietokantaan tallennetuille koneille
* Tallentamaan täytetyn kaavakkeen ja tarkastelemaan aikaisempia kaavakkeita

## Dokumentaatio

[Arkkitehtuurikuvaus](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[Vaatimusmäärittely](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmäärittely.md)

[Käyttöohje](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Release 2](https://github.com/SPitkanen/ot-harjoitustyo/releases/tag/Viikko6)

[Release 1](https://github.com/SPitkanen/ot-harjoitustyo/releases/tag/viikko5)

* Testiraportin suoritus: mvn test jacoco:report
* Jarin generointi: mvn package
* Checkstyletarkastus: mvn jxr:jxr checkstyle:checkstyle
* JavaDoc: mvn javadoc:javadoc

## Tehtävät

[Laskarit](https://github.com/SPitkanen/ot-harjoitustyo/tree/master/laskarit)

## Viikko 6

Sovellus on nyt toimintojensa puolesta valmis. Koodi kuitenkin kaipaisi reilusti siistimistä ja selkeyttämistä, ehkä myös jonkin verran rakenteellisia muutoksia. Tällä hetkellä esimerkiksi wbapp.ui:n luokka Ui sisältää jonkin verran turhaa toistoa.

Myös dokumentaatiota ja testausta pitää vielä kasvattaa. Tämänhetkisen käyttöohjeen löydät [täältä](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

## Viikko 5

Sovellukseen pystyy nyt kirjautumaan ja luomaan uuden käyttäjätunnuksen. Kirjautumisen jälkeen käyttäjä näkee kaavakkeen valmiina täytettäväksi, oletusarvona on listan ensimmäinen lentokone, konetta voi vaihtaa valikosta. Mikäli käyttäjä antaa liian suuria tai pieniä arvoja, ohjelma muuttaa yli (tai ali) menevän massan punaiseksi ja ilmoittaa sallitut rajat. Kuvaajaan piirtyvät massakeskipisteen sallitut rajat ja pienempi viiva kuvaa massakeskipisteen muutosta lennon aikana.

Toimiakseen sovellus vaatii postgres-tietokannan nimeltä 'weightandbalance' ja testejä varten tietokannan 'wbtest'. Taulut ja referenssiarvot voi kopioida suoraan [tästä](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/WeightAndBalance/referenceValues.txt).
Ohjelman voi suorittaa komennolla 'java -jar WeightandBalance-1.0-SNAPSHOT.jar
'.

Arkkitehtuurikuvauksesta löytyvä sekvenssikaavio kuvaa sovelluksen käynnistyessä Ui:n suorittamaa init-metodia.


### Viikko 4


Jo aikaisemmin toteutetut toiminnot siirrettiin javafx projektiin ja siihen luotiin alustava graafinen käyttöliittymä. Kirjautuminen toimii ja sovellus luo kirjauduttuaan weight and balance lomakkeen taulukon ja massalaskelman kuvaajan pohjan. Konetta pystyy vaihtamaan valikosta. Asetteluun tai käytettävyyteen ei ole vielä kiinnitetty sen eenempää huomiota. Laskenta toimi hienosti tekstikäyttöliittymällä, mutta sen toteutus graafisesti toi eteensä taas yhden hankalan pulman jota pitänee tulla selvittämään pajaan myöhemmin.

Sovelluksesta puuttuu tällä hetkellä vielä (em. mainitun lisäksi) mahdollisuus uuden käyttäjän luomiseen sekä täytetyn kaavakkeen tallentaminen. 


### Viikko3
[Työaikakirjanpito](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[Laskarit viikko 3](https://github.com/SPitkanen/ot-harjoitustyo/tree/master/laskarit/viikko3)

Sovelluksella on nyt karkea pohja ja massalaskelma onnistuu, tulosten näyttäminen on kuitenkin vielä vaiheessa. 
Alunperin ajatuksena oli tehdä sovellus johon voisi mahdollisesti toteuttaa useita koentyyppejä ilman, että tietokantaan luodaan erillisiä tauluja jokaiselle koneelle. Tämä osoittautuikin hieman hankalaksi ja johti aika monimutkaiseen laskelmaan. On mahdollista että tämä ei ole lopullinen tapa hoitaa massalaskelmaa (vaikka sen saaminen toimivaksi veikin aivan liikaa aikaa...)

Omina havaintoina suurimmat puutteet/parannettavat asiat tällä hetkellä:
* Sekava laskukaava
* Graafisen käyttöliitymän rakentamista ei ole vielä aloitettu
* Testauksen lisääminen helpottamaan laskennan kanssa

Seuraavaksi vuorossa toteutettujen ominaisuuksien parantelua ja uusien luomista.


### Viikko 2
[Työaikakirjanpito](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)


### Viikko 1
[gitlog.txt](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/laskarit/viikko1/gitlog.txt)

[komentorivi.txt](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/laskarit/viikko1/komentorivi.txt)

*Vielä loppuun oma muutos*

jotain tekstiä
