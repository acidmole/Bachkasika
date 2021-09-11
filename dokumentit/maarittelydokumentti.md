# Määrittelydokumentti
Heikki Venhola, tietojenkäsittelytieteen kandidaattiohjelma

Ohjelman tarkoituksena on luoda [Markovin ketjulla](https://en.wikipedia.org/wiki/Markov_chain) Johann Sebastian Bachin (1685-1750) tuotannon pohjalta generoitua urkumusiikkia. Käyttäjä valitsee halumansa teokset, joiden pohjalta tuotantoa ruvetaan muodostamaan. Valmiita kirjastoita käytetään MIDI-tiedostojen käsittelyyn.

Ohjelma käyttää hyväkseen Bachin [MIDI](https://en.wikipedia.org/wiki/MIDI)-muotoon [tallennettuja urkusävellyksiä](http://www.jsbach.net/midi/midi_organ.html), jotka nuotinnetaan ja syötetään trie-tietorakenteeseen, josta Markovin ketjulla toistetaan satunnaisesti seuraava nuotti. Valmis tuotos tallennetaan uudeksi MIDI-tiedostoksi, johon käyttäjä voi valita haluamansa tempon sekä pituuden kappaleelle.

Aika- ja tilavaativuudeltaan pyritään O(n)-vaativuuteen.

Ohjelman kielenä on Java. Vertaisarviointia voidaan myös tehdä Python-töille. Kaikki koodin sisä- ja ulkopuolinen dokumentointi tehdään suomen kielellä.
