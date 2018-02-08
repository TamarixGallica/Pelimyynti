# Pelimyynti
Kyseessä on Haaga-Helian Ohjelmistoprojekti 1 -kurssin harjoitustyö.
Harjoitustyön vaatimukset ovat seuraavat:
- Sovelluksen on oltava selainkäyttöinen.
- Sovelluksessa on käytettävä Javaa, JSP:tä ja jQueryä.
- Sovelluksen on käytettävä MariaDB-tietokantaa.
- Tietokannassa on oltava vähintään kaksi taulua ja yksi välitaulu.
- Sovelluksessa on käytettävä vähintään yhdessä toiminnossa AJAXia.

Aihe on vapaa, mutta lueteltujen vähimmäisvaatimusten on täytyttävä.

## Sovelluksen idea
- Sovelluksen avulla pelejä myyvä henkilö voi kirjata myynnissä olevat pelit tietokantaan ja tulostaa niistä listan.
- Sovellus on suunniteltu yhdelle käyttäjälle, joten siihen ei ole sisällytetty autentikointia.
- Tietojen käsittely on toteutettu API:lla ja JSONilla, jotta backend voitaisiin vaihtaa tarvittaessa ja samaa backendiä voitiin käyttää myös Mobiiliohjelmointi-kurssilla (PhoneGap).

## Suunnitellut parannukset harjoitustyön palauttamisen jälkeen
- [x] Julkaista tietokannan rakenne ja esimerkkidata, jotta sovellusta voi kokeilla.
- [ ] Lisätä kentät pelien myyntihinnoille ja myyntiajankohdille.
- [ ] Lisätä käyttöliittymään mahdollisuus näyttää ja piilottaa jo myydyt pelit tietoineen.
- [ ] Parantaa virheiden hallintaa, kun myytävän pelin useampaa tietoa muutetaan kerralla tietokannassa.
- [ ] Kirjoittaa backend uudelleen toisella kielellä, jotta sen saisi vietyä käytettävissä olevaan hosting-palveluun.
- [ ] Sisällyttää virhetieto ja -viesti(t) osaksi API-vastauksia.
