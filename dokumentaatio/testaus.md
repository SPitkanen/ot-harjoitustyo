## Sovelluslogiikka

Sovelluslogiikka testataan testiluokassa domain. Domainissa on tavallinen luokka Db, joka muodostaa yhteyden testauksessa käytettävään testitietokantaan. Yhteys aslustetaan jokaisessa testiluokassa testejä varten.
Mikäli testit suoritetaan ensimmäisen kerran, Db-luokka luo tietokantaan tarvittavat taulut ja lisää sinne testeissä käytettävät arvot.

## Testikattavuus

Domain pakettiin keskittyvä haarautumiskattavuus on yhteensä 74%. Käyttöliittymä jätetään testeistä pois. Tietokantaan yhteydessä olevien metodien testaus jää rakenteesta johtuen tekemättä, niiden toimivuus tulee kuitenkin testattua välillisesti nykyisillä testeillä.


## Konfiguraatio

Sovelluksen konfigurointi on testattu manuaalisesti mac-koneella.

Konfigurointi on testattu sekä uusilla että olemassa olevilla tietokannoilla ja vaihtamalla näitä toisiin.
