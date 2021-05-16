# Arkkitehtuurikuvaus

## Pakkausrakenne

Pakkausrakenne on alla olevan kuvan mukainen ja noudattelee kerrosrakennetta.

![](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/dokumentaatio/Kuvat/onlypackages.png)

Pakkaus collectionhelper.ui sisältää kaiken JavaFX:llä toteutetun graaffisesta käyttöliittymästä vastaavan. Lisäksi pakkauksessa on toinen Main-luokka, joka alustaa tarvittaessa tietokannan, ja käynnistää ohjelman. Pakkaus collectionhelper.domain sisältää kaiken sovelluslogiikasta vastaavan ja pakkaus collectionhelper.dao kaiken, mikä hoitaa tietokannan kanssa keskustelun.

## Käyttöliittymä
Käyttöliittymässä on kolme erillistä näkymää, jotka kaikki on toteutettu omina Scene-olioinaan, jotka näkyvät yksi kerrallaan Stagessa. Ohjelmallisesti käyttöliittymä on rakennettu luokkaan [collectionhelper.ui.MainUI](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/src/main/java/collectionhelper/ui/MainUI.java). Se on pyritty irtauttamaan sovelluslogiikasta, ja näin, se vain kutsuu sovelluslogiikasta vastaavan luokan [collectionhelper.domain.CollectionHelperService](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/src/main/java/collectionhelper/domain/CollectionHelperService.java) metodeja.

Käyttöliittymän tulostukset tapahtuvat konsoliin.

## Sovelluslogiikka
Sovelluslogiikan keskiössä ovat luokat [Collectible](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/src/main/java/collectionhelper/domain/Collectible.java) ja [User](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/src/main/java/collectionhelper/domain/User.java), joiden välistä suhdetta on kuvattu alla.

![](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/dokumentaatio/Kuvat/classdiagram.png)

Toiminnallisuudesta vastaa luokka [CollectionHelperService](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/src/main/java/collectionhelper/domain/CollectionHelperService.java). Luokka tarjoaa konstruktorin, jolla se yhdistetään UserDao:n ja CollectibleDao:n toteutukseen. Luokasta luotu olio tarjoaa käyttöliittymälle kaikki metodit, joiden avulla se voi suorittaa tehtävänsä. Näitä ovat esimerkiksi

- boolean login(String username, String password)
- List<String> getNames()
- List<Collectible> searchItems(String search)

Daojen toteutuksien kytkeminen on kriittistä, sillä muuten [CollectionHelperService](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/src/main/java/collectionhelper/domain/CollectionHelperService.java) ei pääsisi käsiksi tietokantaan, mikä on collectionhelper.dao-paketin tehtävä.

Ohessa [palveluntarjoajan](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/src/main/java/collectionhelper/domain/CollectionHelperService.java) ja ohjelman muiden osien välinen pakkauskaavio, jossa on kuitenkin vedetty yhteydet luokkienkin välille:

![](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/dokumentaatio/Kuvat/packages.png)

## Tietojen pysyväistalletus
