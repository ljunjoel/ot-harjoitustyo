# Testausdokumentti

Ohjelman yksikkö- ja integraatiotestaus on toteutettu automatisoidusti JUnitilla ja järjestelmätason testaus manuaalisesti.

## Yksikkö- ja integraatiotestaus
Automatisoitu testaus keskittyy lähinnä collectionhelper.domain -pakkauksen sisältämiin luokkiin, jotka hoitavat sovelluslogiikkaa. Dao-luokkia ei ole erikseen testattu, mutta niistä tehdyt kopiot FakeCollectibleDao ja FakeUserDao ovat avainasemassa näissä testeissä ja tullevat täten tosiasiallisesti testatuiksi, vaikkei tätä Jacocon raportissa näykään. Raportti alla.
![](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/dokumentaatio/Kuvat/jacocoreport.png)
Raportin mukaan rivikattavuus olisi 24% ja haarautumakattavuus 16% koko ohjelman ei-GUI-osilta. Rivikattavuus tosin nousee 74%:iin ja haarautumakattavuus 75%:iin, jos huomioidaan vain paketti collectionhelper.domain.

## Järjestelmätestaus
Sovellukselle tehty järestelmätestaus on suoritettu manuaalisesti.

### Asennus
Sovellus on haettu, ja testattu, että se lähtee pelittämään halutulla tavalla käyttöohjeen(https://github.com/ljunjoel/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) mukaisessa käytössä Linux-ympäristössä. Testattu sekä siten, että ohjelma joutuu itse luomaan tietokannan että siten, ettei se joudu.

### Toiminnallisuudet
Vaatimusmäärittelyn(https://github.com/ljunjoel/ot-harjoitustyo/blob/master/dokumentaatio/maarittelydokumentti.md) mukaiset toiminnallisuudet on käyty läpi ja pyritty rikkomaan esimerkiksi viallisilla syötteillä, hakemalla hakuehdolla, jolla ei löydy mitään sekä yrittämällä tulostaa kaikki artikkelit, kun niitä ei vielä ole yhtäkään.
