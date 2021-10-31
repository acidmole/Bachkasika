# Käyttöohje

## Käynnistys
Ohjelman voi käynnistää komennolla
```
mvn compile exec:java -Dexec.mainClass=bachkasika.ui.BachkasikaUi
```
Varmista, että olet samassa hakemistossa kuin _pom.xml_ eli _Bachkasika/Bachkasika_. 

Ohjelmasta on tehty myös [release](https://github.com/acidmole/Bachkasika/releases/tag/1.0), josta löydät valmiin jar:n ja muutama käyttökelpoinen MIDI-tiedosto syötteeksi. MIDI:en tulee olla _midis/_-hakemistossa.

## Omat MIDI-syötteet
Ohjelmassa voi myös käyttää omia MIDI-tiedostoja. Sijoita ne myös _midis/_-hakemistoon. Todennäköisesti rumpuraitoja sisältävät MIDI:t eivät toimi ohjelmassa. Tätä ei ole testattu.

## Käyttölittymä
Näin se toimii:
1) Valitse halutut syötteet vasemmalta valikosta ja lisää yksi kerrallaan tai valitse useampi shift- ja ctrl-näppäiden avulla.
2) Klikkaa "Siirrä valinnat". Tämä siirtää ne valituiksi tiedostoiksi.
3) Jos haluat tyhjentää valinnat, klikkaa "Tyhjennä".
4) Rakenna haluamasi syvyinen trie ennen suorittamista. Kun olet valinnut oikean syvyyden, paina "Rakenna trie".
5) Valitse haluamasi määrä nuotteja kappaleeseen.
6) Jos haluat transponoida kappaletta, valitse sävelaskeleet.
7) Suorittamisen jälkeen _Bachkasika/Bachkasika_-hakemistoon ilmestyy tiedosto _markoved_bach.mid_. Tämä on generoitu midi.
![](https://github.com/acidmole/Bachkasika/blob/master/dokumentit/bs_ui.png)
