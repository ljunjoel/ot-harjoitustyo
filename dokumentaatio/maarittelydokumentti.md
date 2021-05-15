# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen tarkoitus on mahdollistaa keräilyesineiden, lähtökohtaisesti keräilykorttien, kirjanpito. Sovellukseen voi rekisteröidä erillisiä käyttäjiä, jolloin niillä on omat eriliset listat.

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
  - Artikkelin nimi ja määrä
  - Lisätyt artikkelit näkyvät vain käyttäjälle itselleen
  - Usealla käyttäjällä voi olla samannimisiä artikkeleita
- Mahdollisuus muuttaa artikkelien tietoja
  - Artikkelin määrää voi lisätä ja vähentää
  - Kun artikkelin määrä laskee nollaan, järjestelmä tuhoaa artikkelin automaattisesti
- Mahdollisuus tulostaa kaikki tallennetut artikkelit
  - Koskee vain käyttäjän omia artikkeleita, ei muiden käyttäjien artikkeleita
  - Tulostus tapahtuu konsoliin
- Käyttäjä pystyy tekemään tarkennettuja hakuja tallentamiensa artikkelien joukosta
  - Hakutulokset tulostuvat konsoliin

## Jatkokehitysideoita
- Tulostukset tiedostoon
- Käyttäjä pystyy vertaamaan omaa kokoelmaansa valmiiseen listaan, jolloin selviää, paljonko hänen vielä tarvitsee kerätä
  - Vertailun idea on lähtökohtaisesti ns. [decklistien](https://magic.gg/decklists) vertaaminen omaan kokoelmaan, jolloin saa selville, mitä pakan korteista jo omistaa
