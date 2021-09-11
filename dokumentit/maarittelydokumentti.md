# Määrittelydokumentti
Heikki Venhola, tietojenkäsittelytieteen kandidaattiohjelma

## Yleistä
Ohjelman tarkoituksena on saada Johann Sebastian Bachin (1685-1750) tuotannon pohjalta automaattisesti generoitua urkumusiikkia. 

Ohjelman kielenä on Java. Vertaisarviointia voidaan myös tehdä Python-töille. Kaikki koodin sisä- ja ulkopuolinen dokumentointi tehdään suomen kielellä.

## Syötteet
 Ohjelma käyttää hyväkseen [Markovin ketjulla](https://en.wikipedia.org/wiki/Markov_chain) Bachin [MIDI](https://en.wikipedia.org/wiki/MIDI)-muotoon [tallennettuja urkusävellyksiä](http://www.jsbach.net/midi/midi_organ.html). Käyttäjä valitsee haluamansa teokset, joiden pohjalta tuotantoa ruvetaan muodostamaan. 

## Algoritmit ja tietorakenteet
Markovin ketjua varten lähtöaineisto tallennetaan trie-tietorakenteeseen, josta nuotinnuksen jälkeen valitaan Markovin ketjulla satunnaisesti seuraava nuotti eli äänen korkeus ja kesto. Valmis MIDI-tiedosto on lista.

Trie-rakenne joudutaan suunnitelemaan ja toteuttamaan kokonaan itse. Lista- ja mahdollisissa Map-rakenteissa käytetään Javan omia standardikirjastoja.

## Aika- ja tilavaativuudet
Aikavaativuudessa pyritään O(n)-vaativuuteen. Syötteitä voi olla paljonkin, mutta kuitenkin selvästi alle 10⁶, mikä on riittävä O(n):lle (Laaksonen 2020). Tilavaativuus on O(n).

## Lähteet: 
Laaksonen, Antti: Tietorakenteet ja algoritmit (2020)
Markov chain, Wikipedia, luettu 11.9, saatavilla: https://en.wikipedia.org/wiki/Markov_chain
