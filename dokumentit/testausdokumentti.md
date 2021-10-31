# Testausdokumentti

## Yksikkötestaus
Ohjelman lähdekoodia voi testata ja testikattavuutta seurata komennolla
```
mvn test jacoco:report
```
Tarkempi testiraportti löytyy hakemistosta _target/site/jacoco/index.html_ testien ajamisen jälkeen.

![](https://github.com/acidmole/Bachkasika/blob/master/dokumentit/testauskattavuus.png)

Yksikkötestausta on tehty _src/test/midis/Toccata-and-Fugue-Dm.mid_-tiedostolla silloin, kun siihen on ollut tarvetta. Testauksella on varmistettu, että annetuilla syötteillä metodit toimivat halutusti. Yksikkötestaus on tehty JUnit-kirjastoja apuna käyttäen.

## Suorituskykytestit
Ohjelmalle löytyvät suorituskykytestit testikansiosta löytyvästä BachkasikaPerformanceTest-nimisestä suoritettavasta java-pääohjelmasta.

Suorituskykytestejä on ajettu kahdella Markovin asteella 4:llä ja 8:lla. Suorituskykytestit toimivat tästä syystä ohjelmointiin käytettävällä koneella 8 asteen ketjulla n. 300 000 syötteeseen asti, jonka jälkeen javalle varattu kekomuisti ylittyy ja aiheuttaa poikkeuksen. Tämä johtuu Trien suuresta tilavaativuudesta.

Suorituskykytestissä arvottiin 4. asteen ketjuun 3 kertaa 1 000 000 ja 8. asteen ketjuun 3 kertaa 300 000 (MIDI-)sävelkorkeudeltaan 35-95 vakiokestoista, ei-päällekkäistä nuottia ja tehtiin listaksi.

### 4. asteen testit, 10<sup>6</sup> syötettä
|  Ohjelman osa |Testi 1 (s) |Testi 2 (s) | Testi 3 (s)  |
|---|---|---|---|
| MIDI:n kirjoitus | 2.458  |  1.888 | 1.950  |
| MIDI:n parserointi  | 1.943  |  1.812 | 1.842  |
| Trien rakentaminen  |  2.101 | 2.176  | 2.209  |
| Markovin ketjun rakentaminen  | 0.805  | 0.749  | 0.751  |

### 8. asteen testit 3\*10<sup>5</sup> syötettä
|  Ohjelman osa |Testi 1 (s) |Testi 2 (s) | Testi 3 (s)  |
|---|---|---|---|
| MIDI:n kirjoitus | 0.659  |  0.667 | 0.649  |
| MIDI:n parserointi  | 0.725  |  0.650 | 0.641  |
| Trien rakentaminen  |  3.025 | 3.064  | 2.987  |
| Markovin ketjun rakentaminen  | 0.334  | 0.291  | 0.322  |

Keskimääräisessä MIDI-tiedostossa on tyypillisesti kertaluokkaa 10<sup>3</sup> nuottia, joten tällainen testi on suorituskyvyltään aivan yläpäästä, eikä sitä tilavaativuuden vuoksi merkittävästi pystytä tällaisenaan enää nostamaan.

Suorituskykytestit vahvistavat toteutusdokumentissa olevia väittämiä, että ohjelman aikavaativuus on O(n\*m), jossa m on Markovin ketjun aste.

## Oikeellisuustestit

Ohjelman oikeellisuutta testattiin myös BachkasikaPerformanceTest-luokalla, jossa viisi kertaa arvottiin satunnainen ketju jo tuotetuista Markovin ketjuista. Tällainen ketju tulee löytyä alkuperäisestä nuottilistasta ja joka kerta ohjelma sellaisen löytää.
