# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen tarkoitus on mahdollistaa keräilyesineiden, lähtökohtaisesti keräilykorttien, kirjanpito. Sovellukseen voi rekisteröidä erillisiä käyttäjiä, jolloin niillä on omat listat.

## Perusversion tarjoamat toiminnallisuudet

### Ennen kirjautumista
- Mahdollisuus rekisteröidä käyttäjä, johon liittyy käyttäjänimi ja salasana
  - Käyttäjänimen tulee olla uniikki ja vähintään kolme merkkiä pitkä
  - salasanan tulee olla vähintään 3 merkkiä pitkä
- Mahdollisuus kirjautua sisään
  - onnistuu antamalla käyttäjänimi ja salasana
  - jos käyttäjänimeä ei ole rekisteröity, järjestelmä ilmoittaa tästä
 
### Sisäänkirjautumisen jälkeen
- Mahdollisuus lisätä artikkeleita omaan listaan
  - Kortin/esineen nimi ja määrä
  - Lisätyt artikkelit näkyvät vain käyttäjälle itselleen
- Mahdollisuus tulostaa kaikki tallennetut artikkelit
  - Koskee vain käyttäjän omia artikkeleita, ei muiden käyttäjien artikkeleita

## Jatkokehitysideoita
Mikäli aikaa riittää, pyritään saamaan seuraavia ominaisuuksia mukaan järjestelmään:
- Käyttäjä pystyy tekemään tarkennettuja hakuja tallentamiensa artikkelien joukosta
- Käyttäjä pystyy vertaamaan omaa kokoelmaansa valmiiseen listaan, jolloin selviää, paljonko hänen vielä tarvitsee kerätä
  - Vertailun idea on lähtökohtaisesti ns. decklistien vertaaminen omaan kokoelmaan, jolloin saa selville, mitä pakan korteista jo omistaa
