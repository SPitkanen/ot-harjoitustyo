# Rakenne

Ohjelmassa on kolmikerroksinen rakenne, joka muodostuu käyttöliittymän näyttävästä wbapp.ui:sta, sovelluslogiikan sisältävästä wbapp.domain:sta sekä tietokantaan yhteydessä olevasta wbapp.dao:sta.
![pakkauskaavio](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/wbappPackageUML.png)

# Sovelluslogiikka

Ohjelman sovelluslogiikka käsitellään pakkauksessa wbapp.domain ja se sisältää luokat lentokoneen datan käsittelyyn, käyttäjän datan käsittelyyn, sekä tuloksen käsittelyyn liittyvän luokan.

luokkakaavio
![luokkakaavio](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/Luokkakaavio.jpg)

sekvenssikaavio sovelluksen käynnistymisestä
![sekvenssikaavio init](https://github.com/SPitkanen/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/wbappInit.png)
