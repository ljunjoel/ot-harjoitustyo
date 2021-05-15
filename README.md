# README

# CollectionHelper
CollectionHelper -sovelluksen avulla käyttäjät voivat pitää kutia omista kokoelmistaan. Olivatpa ne sitten mitä tahansa! (in English below)

## Miten pääsen alkuun?
Tällä hetkellä ohjelman voi ajaa komentoriviltä komennolla java -jar CollectionHelper.jar, kun releasesta on ladannut tiedostot. Jos haluat ajaa ohjelman ennemmin NetBeans:illa, joutunet käyttämään Clean and Build -toimintoa pariin kertaan ennen kuin ohjelma suostuu suorittamaan.

Ohjelma aukeaa kirjautumisikkunaan. Luo itsellesi kuitenkin ensin käyttäjä, ennen kuin yrität kirjautua sisään. Paina käyttäjätunnus ja salasana mieleesi. Kun käyttäjätili on luotu, siirtyy ohjelma automaattisesti takaisin kirjautumisikkunaan. Kirjaudu nyt sisään bränikällä käyttäjälläsi. Eteesi avautuu nyt liuta työkaluja, joilla voit pitää kirjaa kokoelmistasi, olivatpa ne sitten mitä tahansa!

Pari huomiota:

Haku-nappi käyttää samoja tekstikenttiä kuin lisääminen ja vähentäminen.

Haku- sekä Tulosta kaikki -toimintojen tulostukset tulevat konsoliin.

# CollectionHelper (in English)
CollectionHelper is a software designed to aid with keeping tabs on your collection. Whatever it might be!

## How do I get started?
At present the software works by running the java -jar CollectionHelper.jar command from the command prompt after downloading a release. If you would prefer running it from NetBeans you might have to Build and Clean a couple of times before NetBeans decides to cooperate.

When CollectionHelper starts, you will be faced with a login screen. Before trying to login though, you should create a user. Do not forget these credentials! After your new user is created the system will automatically bring you back to the login screen. Now, login with your brand new user. You now have access to all the functionalities of CollectionHelper! Inside you will find tools for keeping up with your collection.

A couple of other remarks: 

The search button uses the same name field as adding and reducing amounts.

The search and print all functions will print to console.


## Dokumentaatio
[Vaatimusmäärittely](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/dokumentaatio/maarittelydokumentti.md)

[Työaikakirjanpito](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)

[Käyttöohje](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

## Releaset
[Viikko 5](https://github.com/ljunjoel/ot-harjoitustyo/tree/viikko5)

[Loppupalautus](https://github.com/ljunjoel/ot-harjoitustyo/releases/tag/loppupalautus)

## Komentorivikomennot
Ohjelman voi ajaa komentoriviltä kommennolla
```
mvn compile exec:java -Dexec.mainClass=collectionhelper.ui.Main
```
### Testaus
Testit voi suorittaa komennolla
```
mvn test
```
Testikattavuusraportti luodaan komennolla
```
mvn jacoco:report
```
Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_
### Checkstyle
Tiedosto [checkstyle.xml](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/checkstyle.xml) määrittelee tyylisäännöt. Raportin virheistä voi generoida loitsulla
```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
### Suoritettavan jarin generointi

Generoi suoritettava jar-tiedosto!
```
mvn package
```
jar-tiedoston _CollectionHelper-1.0-SNAPSHOT.jar_ löydät hakemistosta _target_. Toimenpide saattaa vaatia, että projekti Build and Clean:ataan ensin.
