# Viikkoraportti 3

## Tehdyt asiat
Väijytty trie-rakenteen toimintaperiaatteita ja toteutuksia. Huomattu, että nykyisellä rakenteella parseroidun MIDI-listan lisääminen on aikavaativuus on luokkaa O(n²). Todennäköisillä syötemäärillä se ei tosin ole merkityksellistä.

## Ohjelman edistyminen
Ohjelmoitu verkko mahdollistamaan Markovin ketju sekä tehty tälle yksikkötestaus ja dokumentointi. Lisätty transponointimahdollisuus MIDI:en parserointiin, jotta eri sävellajien teokset voi syöttää samaan rakenteeseen.

## Oppiminen
Opittu trie-rakenteesta hieman lisää. Opittu myös kantapään kautta ArrayListin alkioiden lisäämistä ja poistoa.

## Epäselvyydet ja vaikeudet
Koko viikon kestäneeseen sairasteluun nähden ohjelma on edistynyt erittäin hyvin, tosin vähemmän kuin olisin toivonut. Yhteenvedon sisältävä testausdokumentti ei valmistunut, onneksi viime viikolla oli jo yksikkötestauskuvaajan sekä checkstyle-raportin mahdollisus jo olemassa. Alusta asti vaivanneet epäilyt trie-rakenteen sopivuudesta tähän ohjelmaan ovat vahvistuneet koko ajan. Tämänhetkinen toteutus muistuttaa lähinnä suunnattua verkkoa, mikä tietysti trie myös on.

## Seuraavaksi
Ensi viikolla on tarkoitus muodostaa Markovin ketjuja ja selvittää kuinka järkeviä sellaisia rakenne tuottaa sekä korjata pari pientä bugia MIDI:n parseroinnissa.
