# Toteutusdokumentti

## Yleisrakenne
Ohjelma ottaa syötteeksi yhden tai useamman MIDI-tiedoston, jotka se muokkaa taulukoksi. Tämä taulukko järjestetään ja käydään läpi koherenssin datan turvaamiseksi.

Taulukkoja syötetään Trie-rakenteeseen niin, että halutun Markovin ketjun asteen määrän mukaisesti saadaan yhdestä taulukosta m*n sävelkulkua, missä m = Markovin ketjun aste ja n = taulukon koko. Ennen Trie-rakenteeseen syöttöä taulukosta poistetaan samanaikaisia nuotteja.
