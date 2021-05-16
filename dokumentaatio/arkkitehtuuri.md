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

Toiminnallisuudesta vastaa luokka [CollectionHelperService](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/src/main/java/collectionhelper/domain/CollectionHelperService.java). Luokka tarjoaa konstruktorin, jolla se yhdistetään rajapintojen [UserDao](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/src/main/java/collectionhelper/dao/UserDao.java) ja [CollectibleDao](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/src/main/java/collectionhelper/dao/CollectibleDao.java) toteutukseen. Luokasta luotu olio tarjoaa käyttöliittymälle kaikki metodit, joiden avulla se voi suorittaa tehtävänsä. Näitä ovat esimerkiksi

- boolean login(String username, String password)
- List<String> getNames()
- List<Collectible> searchItems(String search)

Daojen toteutuksien kytkeminen on kriittistä, sillä muuten [CollectionHelperService](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/src/main/java/collectionhelper/domain/CollectionHelperService.java) ei pääsisi käsiksi tietokantaan, mikä on collectionhelper.dao-paketin tehtävä.

Ohessa [palveluntarjoajan](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/src/main/java/collectionhelper/domain/CollectionHelperService.java) ja ohjelman muiden osien välinen pakkauskaavio, jossa on kuitenkin vedetty yhteydet lisäksi luokkien ja rajapintojen välille:

![](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/dokumentaatio/Kuvat/packages.png)

## Tietojen pysyväistalletus
Sekä keräilyesineet että käyttäjät lisätään SQLite:llä toteutettuun tietokantaan omiin tauluihinsa. Mikäli tätä tietokantaa ei vielä ohjelman kansiossa ole, alustaa [Main](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/src/main/java/collectionhelper/ui/Main.java)-metodi kyseisen tietokannan. Tietoihin päästään käsiksi yleisten rajapintojen toteutuksilla [DatabaseCollectibleDao](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/src/main/java/collectionhelper/dao/DatabaseCollectibleDao.java) ja [DatabaseUserDao](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/src/main/java/collectionhelper/dao/DatabaseUserDao.java) avulla. Tehty valinta käyttää rajapintoja, kuten on tyypillistä Data Access Object -suunnittelumallissa, suo sen, että jatkossa muuttuva toteutus toimii yhä suhteellisen moitteetta.

### Tietokannan taulut
Tietokannassa on kaksi taulua, joista toinen on nimeltään Users ja toinen Collection.

Taulu Users sisältää kaksi saraketta, ja ne on nimetty, järjestyksessä, Username ja UserPassword. Username on nimetty taulussa uniikiksi sekä taulun primary key:ksi. Tähän tallentuvat kaikki käyttäjät, ja heidän salasanansa.

Taulu Collection sisältää kolme saraketta, ja ne on nimetty, järjestyksessä, CollectibleName, CollectibleQuantity ja CollectibleUser. CollectibleUser on taulussa nimetty foreign key:ksi, joka viittaa taulun Users sarakkeeseen Username. Tähän tallentuvat kaikki esineet, ja ne yksilöidään kuuluvaksi jollekin käyttäjälle.

## Päätoiminnallisuudet
Muodostetaan sekvenssikaavioiden avulla käsitys siitä, miten ohjelma toimii.
### Kirjautuminen
Käyttäjä kirjoittaa kenttiin käyttäjätunnuksensa ja salasanansa. Tämän jälkeen hän painaa login-painiketta. Sovelluksen sisällä kontrolli etenee seuraavasti:

![](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/dokumentaatio/Kuvat/sequencediagram-login.png)


Käyttöliittymän login-painikkeen tapahtumakäsittelijä ottaa tehtävän vastaan ja aloittaa ottamalla talteen kenttiin syötetyt tiedot, minkä jälkeen se kutsuu [palveluntarjoajan](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/src/main/java/collectionhelper/domain/CollectionHelperService.java) kaikki nimet hakevaa metodia. Tämä taasen kutsuu [käyttäjätotetuksen](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/src/main/java/collectionhelper/dao/DatabaseUserDao.java) kaikki nimet hakevaa metodia, joka hakee tietokannasta nimet, ja laittaa ne listaan. Tämä lista kulkeutuu takaisin käyttöliittymälle vastauksia pitkin. Tapahtumakäsittelijä tsekkaa, löytyykö käyttäjänimi listasta. Kun se löytyy, se kutsuu palveluntarjoajan sisäänkirjautumismetodia, jolloin käyttäjätoteutus käy etsimässä kyseisen käyttäjän, palauttaa sen palveluntarjoajalle, joka tarkistaa salasanan ja vastaa käyttöliittymälle true onnistuneeseen kirjautumiseen. Käyttöliittymä putsaa täytetyt kentät ja vaihtaa näkymän käyttönäkymään, ja jää odottamaan seuraavaa syötettä käyttäjältä.

Samankaltaisilla mekanismeilla toimivat myös sekä lisääminen, vähentäminen että hakeminen. Viesti kulkeutuu käyttöliittymältä palveluntarjoajalle, joka kutsuu toteutuksen metodia, mikä aiheuttaa SQL-kyselyn tietokantaan, ja ketjua pitkin palataan takaisin aina käyttöliittymälle asti.
