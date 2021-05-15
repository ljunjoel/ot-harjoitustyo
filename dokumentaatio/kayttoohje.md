# Käyttöohje
Ohjelma ei vaadi minkäänlaista mustaa magiaa. Lataa vain tiedosto [CollectionHelper.jar](https://github.com/ljunjoel/ot-harjoitustyo/releases/tag/loppupalautus).

## Alkuaskeleet
Ohjelman saa käyntiin komentoriviltä komennolla
```
java -jar CollectionHelper.jar
```
Ohjelma aukeaa kirjautumisikkunaan. Painikkeesta "Create User!" pääset luomaan itsellesi ikioman käyttäjän! Aseta haluamasi käyttäjätunnus "Username"-kenttään ja haluamasi salasana "Password"-kenttään ja paina "Create User!". Mikäli olet jo luonut käyttäjän, ja tulet erehdyksissäsi painaneeksi itsesi tähän ikkunaan, pääsee siitä pois painamalla "Cancel"-painiketta.

## Kirjautuminen
Nyt kun olet luonut käyttäjätunnukset, on aika kirjautua sisään. Jos juuri loit ne, vie ohjelma sinut automaattisesti takaisin kirjautumisikkunaan. Sisäänkirjautuminen onnistuu käyttämällä luomiasi käyttäjätunnuksia.

## Käyttö
Kirjauduttuasi sisään aukeaa eteesi käyttönäkymä. Tässä näkymässä voit luoda uusia artikkeleita, lisätä ja vähentää niiden määrää kokoelmassa sekä tehdä erilaisia tulostuksia.
### Add-painikkeen toiminnallisuus
Add-painikkeella on kaksi toimintoa. Sillä voi lisätä kokonaan uusia esineitä, ja toisaalta sillä voi lisätä artikkelin lukumäärää kokoelmassa. Molemmat toiminnot suoritetaan täysin samalla tavallaa: Aseta ensin ylempään kenttään artikkelin nimi ja tämän jälkeen määrä alempaan kenttään. Kun molemmat ovat kuten pitää, paina Add-painiketta.
### Reduce-painikkeen toiminnallisuus
Reduce-painikkeella on myös kaksi toimintoa. Sillä voi vähentää artikkelin lukumäärää kokoelmassa ja, vähentämällä jonkin artikkelin nollaan (tai alle), poistaa kyseisen artikkelin.
