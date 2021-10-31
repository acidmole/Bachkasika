# Toteutusdokumentti


## Yleisrakenne
Ohjelma ottaa syötteeksi yhden tai useamman MIDI-tiedoston. Yksittäiset nuotit käsitellään ja syötetään listaksi.

Lista ajetaan Trie-rakenteeseen niin, että halutun Markovin ketjun asteen määrän mukaisesti saadaan yhdestä taulukosta m\*n sävelkulkua, missä m = Markovin ketjun aste ja n = taulukon koko. Valmiista listasta arvotaan sävelkorkeuksia m-1:lle alkiolle sopiva lapsi Trie-puusta. Tämä tehdään käyttäjän valitseman määrän kertaa. Sävelkorkeustaulukko sovitetaan nuottikehikkoon, joka katsoo arvotun sävelkorkeuden ja kehikko-triestä katsoo, toimiiko annettu nuotti basso- vai melodianuottina ja sävelkorkeuksia vastaavalla tavalla etsii sille sopivan lapsen.

Valmis sävelkorkeudet ja -kestot sisältävä lista lopuksi kirjoitetaan MIDI:ksi.

## Pakkausrakenne
Ohjelmalla on graafinen käyttöliittymä, jolle injektoidaan Service-tason luokka BachkasikaService riippuvuudeksi. Midi-pakkaus vastaa MIDI:n käsittelystä ja tiedostoon kirjoittamisesta. Trien rakentamisesta, etsimisestä, sekvenssien ja nuottikehikon, Markovin ketjujen muodostamisesta vastaa Trie-niminen pakkaus. Io hoitaa tiedoston käsittelyn.

## Aikavaativuudet
MIDI:n parseroinnin aikavaativuus on periaatteessa O(n<sup>2</sup>), sillä teknisesti ottaen tällainen tilanne olisi mahdollinen, jos MIDI:ssä soisi samanaikaisesti n nuottia. Tämä tilanne on kuitenkin syötteen kannalta täysin absurdi ja voidaan siksi sivuuttaa.

Trien aikavaativuus on O(m\*n), sillä kaikissa vaiheissa n-pituinen taulukko käydään läpi m kertaa. Eli pseudokoodina:
```
for nuotti in lista:
  for i in ketjunAste:
    sekvenssi[i] = lista[i]
  tallennaTriehen(sekvenssi)
```

Samanlainen rakenne koostuu läpi kaikissa trien vaiheissa. Ketjua muodostettaessa käytetään hyväksi rekursiota.

## Tilavaativuudet
Kaikki listat ovat tilavaativuudeltaan luokkaa O(n).

Trien tilavaativuus on luokkaa 128<sup>m</sup>, sillä mahdollisia MIDI-säveliä on 128 ja lapsi jakaantuu 128 lapseen aina lehtisolmuihin asti. Tämä rajoittaa ketjun asteiden kasvattamista tilavaativuuden kasvaessa eksponentiaalisesti.

## Puutteet ja jatkokehitys
Ohjelman musiikki ei täytä länsimaisen musiikin teoriaa. Musiikki pitäisi pystyä ajamaan jonkinlaiseen tahtilajiin ja jotta musiikkiin saisi lisää harmoniaa, sen tulisi pystyä erittelemään samaan aikaan soivat nuotit ja toistamaan niitä. Tällaisella rakenteella sellaisen toistaminen on hyvin hankalaa.

Tällaisessa muodossaan ohjelmaa voisi kehittää vielä keräämään MIDI:stä myös alin soiva nuotti ja muodostaa nuottikehikkoa suuntaan, jossa kaksi nuottia joko soi tai ei soi samaan aikaan.
