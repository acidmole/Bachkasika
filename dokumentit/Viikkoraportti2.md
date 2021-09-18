# Viikkoraportti 2

## Tehdyt asiat
Otettu selvää MIDI-tiedoston parseroinnista, johon löytyi kätevä koodinpätkä [StackOverflow'sta](https://stackoverflow.com/questions/3850688/reading-midi-files-in-java#comment4093620_3850885). Tätä muokkaamalla pääsin nopeasti alkuun. Tutustuin myös javax.midi-kirjaston toimintaan, jotta myöhemmässä vaihesssa olisi nopeampaa muokata Markovin ketjusta MIDI-tiedosto. Muistuttelin myös mieleen JavaDocin, JUnit-testien ja Checkstylen perusasiat.

## Ohjelman edistyminen
Ohjelmoitu luokat, joilla pystyy nyt parseroimaan MIDI-tiedostoja listarakenteeseen, jotta ne voidaan syötää myöhemmin trie-rakenteeseen. Kirjoitettu kaksi testiluokkaa testaamaan näiden toimintaa.

## Oppiminen
Opin MIDI-tiedostojen käsittelystä Javalla. 

## Epäselvyydet ja vaikeudet
Oma ja jälkikasvun sairastelu keskellä viikkoa toi paineita aikatauluun enkä edennyt niin paljon kuin olisin halunnut. Vähän epäselväksi on jäänyt, että tuleeko työn kaikissa tietorakenteissa käyttää itse koodattuja ratkaisuja vai voiko ArrayListiä ja ArrayDequeta käyttää normaalisti työssä.

## Seuraavaksi
Ensi viikolla tarkoituksena on rakentaa Trie-rakenne, johon voin nuotit tallettaa. Lisäksi MIDI-parserin tulee pystyä käsittelemään muutama epäselvyys parseroinnissa, jotka tällä hetkellä aiheuttavat pieniä bugeja.
