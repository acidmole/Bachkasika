# Testausdokumentti

## Yksikkötestaus
Ohjelman lähdekoodia voi testata ja testikattavuutta seurata komennolla
```
mvn test jacoco:report
```
Tarkempi testiraportti löytyy hakemistosta _target/site/jacoco/index.html_
Kuva testiraportin yhteenvedosta löytyy [tästä](https://github.com/acidmole/Bachkasika/blob/master/dokumentit/testauskattavuus.png)
![](https://github.com/acidmole/Bachkasika/blob/master/dokumentit/testauskattavuus.png)

## Testauksen laatu
Testausta on tehty itse generoidulla MIDI-tiedostolla. Käytännössä tällainen syöte on ainoa, jolla voi varmasti todeta ohjelman toimivan halutusti, mutta samalla saattaa piilottaa isompia ongelmia taakseen.

## Suorituskykytestit
Ohjelmalle ei ole pystytty tässä vaiheessa vielä tekemään suurempaa suoritustestausta. Kahden MIDI-tiedoston parseroiminen ja rakenteeseen lisääminen (muutama tuhat syötettä) vie aikaa muutaman sadasosasekunnin. Tarkempi suorityskyvyn testaus pystytään tekemään vasta, kun ohjelma on valmiimpi.
