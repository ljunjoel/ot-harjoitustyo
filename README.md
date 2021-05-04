# README

# CollectionHelper
CollectionHelper -sovelluksen avulla käyttäjät voivat pitää kutia omista kokoelmistaan. Olivatpa ne sitten mitä tahansa! (in English below)

## Miten ohjelma toimii?
Tällä hetkellä ohjelman voi ajaa komentoriviltä komennolla java -jar CollectionHelper.jar, kun releasesta on ladannut tiedostot. Jos haluat ajaa ohjelman ennemmin NetBeans:illa, joutunet käyttämään Clean and Build -toimintoa pariin kertaan ennen kuin ohjelma suostuu suorittamaan. Perimmäistä syytä tälle en ole vielä keksinyt, enkä siten myöskään ratkaissut ongelmaa.

Kun ohjelma aukeaa, se kysyy salasanaa. Anna sille salasana "salasana". Näin pääset käsiksi kaikkiin CollectionHelper:in toiminnalisuuksiin! Kirjautumisen jälkeen eteesi avautuu liuta ominaisuuksia, joiden avulla voit seurata kokoelmaasi. On hyvä huomata, että tällä hetkellä ohjelma unohtaa kaiken, mitä sille on annettu, sulkemisen jälkeen.

Pari muuta huomiota:

Haku-nappi käyttää samoja tekstikenttiä kuin lisääminen ja vähentäminen. Hakeminen onnistuu vain tarkalla nimellä

Haku- sekä Tulosta kaikki -toimintojen tulostukset tulevat konsoliin.

# CollectionHelper (in English)
CollectionHelper is a software designed to aid with keeping tabs on your collection. Whatever it might be!

## How does it work?
At present the software works by running the java -jar CollectionHelper.jar command from the command prompt after downloading the first release. If you would prefer running it with NetBeans you might have to Build and Clean a couple of times before NetBeans decides to run the software. I have yet to find a solution, or the root cause for that matter, for the problem.

When CollectionHelper starts, you are asked to give a password. Use the password "salasana" to access the functionalities of CollectionHelper! Inside you will find tools for keeping up with your collection. Unfortunately, at present the software will wipe out its records when closed. 

A couple of other remarks: 

The search button uses the same name field as adding and reducing amounts. At present you can only search by exact name.

The search and print all functions will print to console.


## Dokumentaatio
[Vaatimusmäärittely](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/dokumentaatio/maarittelydokumentti.md)

[Työaikakirjanpito](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)


## Releaset
[Viikko 5](https://github.com/ljunjoel/ot-harjoitustyo/tree/viikko5)

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
Tiedostoon [checkstyle.xml](https://github.com/ljunjoel/ot-harjoitustyo/blob/master/CollectionHelper/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla
```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_
### Suoritettavan jarin generointi

Komento
```
mvn package
```
generoi hakemistoon _target_ suoritettavan jar-tiedoston _CollectionHelper-1.0-SNAPSHOT.jar_
