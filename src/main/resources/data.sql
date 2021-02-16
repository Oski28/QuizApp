INSERT INTO user_role(description,role) VALUES ("Konto studenta upoważnia użytkownika do wykonywania testów doszkalających
z przedmiotu.","ROLE_STUDENT");
INSERT INTO user_role(description,role) VALUES ("Konto nauczyciela upoważnia użytkownika do dodawania, edycji, usuwania
oraz przeglądania testów i kont studentów w ramach przedmiotu do którego przypisany jest nauczyciel. Ponadto umożliwia dodawanie,
edycję, usuwanie oraz przęglądanie pytań w ramach całej aplikacji. Nauczyciel może podobnie jak student wykonywać testy.","ROLE_TEACHER");
INSERT INTO user_role(description,role) VALUES ("Konto administratora upoważnia użytkownika do wykonywania wszystkich operacji
jakie mogą wykonywać pozostałe typy użytkowników. Ponadto umożliwia dodawanie, edycję, usuwanie i przeglądanie przedmiotów
oraz kont nauczycieli. Administrator ma dostęp do wszystkich zasobów w aplikacji.","ROLE_ADMIN");
INSERT INTO subject(name) VALUES ("Technologie WEB i JAVA");
INSERT INTO subject(name) VALUES ("Programowanie w języku Java");
INSERT INTO subject(name) VALUES ("Technologie sieci Web");
INSERT INTO subject(name) VALUES ("Modelowanie Elektromechanicznych Systemów Napędowych");
INSERT INTO subject(name) VALUES ("Modelowanie Układów Energetyki Odnawialnej");
INSERT INTO subject(name) VALUES ("Seminarium dyplomowe");
INSERT INTO quiz(time,number_of_questions,subject_id) VALUES ("15","30","1");

INSERT INTO question(text) VALUES ("Jak ma na imię prowadzący przedmiot?");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE,"Jan","1");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"Tomasz","1");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"Krzysztof","1");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"Adam","1");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","1");

INSERT INTO question(text) VALUES ("Aktualna wersja Javy to:");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE,"15","2");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"1.8","2");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"11","2");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"8","2");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","2");

INSERT INTO question(text) VALUES ("Który z wymienionych typów danych nie jest typem języka Java?");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"String","3");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE,"Number","3");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"int","3");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"undefined","3");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"double","3");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","3");

INSERT INTO question(text) VALUES ("Który z wymienionych typów danych są typami opakowującymi typy proste języka Java?");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE,"Byte","4");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE,"Short","4");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"Character","4");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"Integer","4");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"Boolean","4");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","4");

INSERT INTO question(text) VALUES ("Które zdania są prawdziwe?");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"W javie klasa może dziedziczyć z wielu klas.","5");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE,"W javie klasa może dziedziczyć tylko z jednej klasy.","5");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"W javie klasa może implementować tylko jeden interfejs.","5");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"W javie klasa może implementować wiele interfejsów","5");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"W javie po jednej klasie może dziedziczyć wiele klas.","5");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","5");

INSERT INTO question(text) VALUES ("Aktualna wersja HTML to:");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"HTML 6","6");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"HTML 21","6");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"HTML 4","6");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"HTML 5","6");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"HTML 1.5","6");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","6");

INSERT INTO question(text) VALUES ("Poszczególne litery w skrócie CRUD oznaczają:");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE,"DELETE","7");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE,"CREATE","7");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"DOWNLOAD","7");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"UPDATE","7");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"READ","7");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","7");

INSERT INTO question(text) VALUES ("Znacznikiem HTML nie jest:");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"<div>","8");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"<body>","8");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"<aside>","8");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"<h7>","8");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"<addres>","8");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","8");

INSERT INTO question(text) VALUES ("Nowe znaczniki semantyczne w HTML 5 to:");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"<navigate>","9");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"<header>","9");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"<figure>","9");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"<img>","9");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"<a>","9");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","9");

INSERT INTO question(text) VALUES ("Głównym elementem pliku Android.Manifest.xml jest:");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"application","10");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"manifest","10");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"action","10");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"ResourcesManifest","10");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","10");

INSERT INTO question(text) VALUES ("Dokument HTML5 powinien posiadać DOCTYPE o strukturze:");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"<html>","11");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"<!DOCTYPE html>","11");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"</html>","11");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","11");

INSERT INTO question(text) VALUES ("Które zdania są prawdziwe?");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"Wiadomość SOAP MUSI posiadać nagłówek SOAP","12");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"Wiadomość SOAP MUSI posiadać kopertę SOAP","12");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE,"Wiadomość SOAP MUSI posiadać ciało SOAP","12");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"Wiadomość SOAP NIE MUSI używać SOAP Envelope namespaces","12");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","12");

INSERT INTO question(text) VALUES ("Jeśli deklaracja zmiennej w języku XSLT odbywa się za pomocą
wyrażenia <xsl:variable name=\"nazwa_zmiennej\" select=\"...\"/> to
odwołanie do tej zmiennej ma postać:");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"<xsl:value-of select=\"@nazwa_zmiennej\" />","13");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"<xsl:value-of select=\"$nazwa_zmiennej\" />","13");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"<xsl:attribute select=\"$nazwa_zmiennej\" />","13");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"<xsl:attribute select=\"@nazwa_zmiennej\" />","13");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","13");

INSERT INTO question(text) VALUES ("W jęzuku XML określenie znacznika myTag z przestrzeni nazw myNs
ma postać:");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"<myTag:myNs> Zawartość </myTag:myNs>","14");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"<myNs:myTag> Zawartość </myNs:myTag>","14");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"<xmlns:myTag> Zawartość </ xmlns:myTag>","14");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"<myNs:xmlns> Zawartość </myNs:xmlns >","14");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","14");

INSERT INTO question(text) VALUES ("Która zawartość struktury pliku *.xml jest zgodna ze składnią
języka XML?");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"<?xml version=\"1.0\"?><z1>Tekst</z1><z2>Tekst2</z2>","15");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"<?xml version=\"1.0\"?><z1>Tekst<z2>Tekst</z2></z1>","15");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"<xml version=\"1.0\"><z1>Tekst<z2>Tekst</z2></z1>","15");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"<xml version=\"1.0\"><z1>Tekst</z1><z2>Tekst2</z2>","15");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","15");

INSERT INTO question(text) VALUES ("Dynamiczną zmianę obrazka o identyfikatorze id=\"myImg\" za pomocą
HTML DOM realizuje kod:");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"document.getElementByTagName(\"myImg\").src = \"Image.gif\";","16");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"document.getElementById(\"myImg\").src = \"Image.gif\";","16");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"document.getElementById(\"myImg\").innerHTML = \"Image.gif\";","16");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"document.getElementByTagName(\"myImg\").innerHTML = \"Image.gif\";","16");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","16");

INSERT INTO question(text) VALUES ("Które stwierdzenia są poprawne?");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE,"Metoda window.print() pozwala na druk bieżącej strony","17");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"Właściwość document.cookie pozwala na obsługię ciasteczek.","17");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"Metoda document.read() pozwala na czytanie z pliku","17");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"Metoda document.writel() pozwala na zapis do pliku","17");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","17");

INSERT INTO question(text) VALUES ("Które definicje stylu dla nagłówka h1 przy pozycjonowaniu
względnym są poprawne pod względem składni?");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE,"h1.position_left { position: relative; left: 100px; }","18");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"h1.position_left { position: relative; left: -100px; }","18");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"h1.position_left { position: absolute; top: 100px; }","18");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"h1.position_top { position: relative; top: -30px; }","18");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"h1.position_left { position: absolute; top: -100px; }","18");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","18");

INSERT INTO question(text) VALUES ("Interfejs SimpleXML w języku PHP");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"Jest standardem organizacji W3C","19");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"Obsługuje przestrzenie nazw","19");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"Nie ma takiego interfejsu w języku PHP","19");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"Umożliwia zapis do pliku XML","19");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","19");

INSERT INTO question(text) VALUES ("Który zapis w języku PHP jest zgodny ze składnią tego języka,
jeśli pierwsza litera I to interfejs, a C to klasa?");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"class CC extends IA, IB {}","20");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"interface IC extends IA, IB {}","20");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"interface IB extends CA {}","20");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"class CC implements IA, IB {}","20");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","20");

INSERT INTO question(text) VALUES ("Jak powinny być ustawione prawa dostępu do katalogu w którym
umieszczone są pliki serwisu WWW?");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"drwxrwxrwx","21");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"drwxrw-rw-","21");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE,"drwx-w- - w-","21");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"drwx-wx-wx","21");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","21");

INSERT INTO question(text) VALUES ("Który kod w JS jest poprawny");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"document.write( \"<p>\" + date() + \"</p\");","22");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"document.write( \"<p>\" + new date() + \"</p\");","22");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"document.write( \"<p>\" + Date() + \"</p\");","22");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"document.write( \"<p>\" + new Date + \"</p\");","22");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","22");

INSERT INTO question(text) VALUES ("Ustawienie stylu dla elementu body:");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"<body background-color: red>","23");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"body <background-color: red>","23");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"body { background-color: red}","23");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"{body background-color: red}","23");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","23");

INSERT INTO question(text) VALUES ("Które z poniższych są pseudoklasami CSS?");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"visited","24");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"focus","24");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"empty","24");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"hover","24");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"valid","24");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","24");

INSERT INTO question(text) VALUES ("JavaScript - Które sposoby tworzenia obiektu w tym języku są
poprawne?");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"var obj = new Object();","25");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"var myArray = new Array();","25");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"var obj = {};","25");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"var myArray = [];","25");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","25");

INSERT INTO question(text) VALUES ("JavaScript - Która składnia porównania zwraca false?");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"document.writeln(undefined === null);","26");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"document.writeln(![] === []);","26");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"document.writeln(0 === \"0\");","26");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"document.writeln(0 == \"0\");","26");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","26");

INSERT INTO question(text) VALUES ("Które stwierdzenia są prawdziwe?");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"JSON jest formatem danych w postaci tekstowej","27");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"JSON jest formatem dokumentu","27");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"JSON jest językiem znaczników","27");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"JSON jest formatem serializacji danych","27");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","27");

INSERT INTO question(text) VALUES ("Które wyrażenia są poprawne w CSS?");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"@media (orientation: portrait)","28");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"media @import....","28");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"@import media.......","28");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"@media screen and (min-width:liczba) and (max-height:liczba)","28");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","28");

INSERT INTO question(text) VALUES ("Otwarcie okna dialogowego z napisem „Alert window” realizuje składnia języka
JavaScript:");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"window.alert(\"Alert window\");","29");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"page.alert(\"Alert window\");","29");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"document.alert(\"Alert window\");","29");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"alert(\"Alert window\");","29");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"self.alert(\"alert window\");","29");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","29");

INSERT INTO question(text) VALUES ("Jaka jest poprawna składnia odwołania w dokumencie XHTML do zewnętrznego
arkusza stylów?");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"<head rel=\"stylesheet\" type=\"text/css\" href=\"plik_stylow.css\" />","30");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"<link href=\"stylesheet\" type=\"text/css\" link=\"plik_stylow.css\" />","30");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"<link rel=\"stylesheet\" type=\"text/css\" href=\"plik_stylow.css\" />","30");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"<style rel=\"stylesheet\" type=\"text/css\" href=\"plik_stylow.css\" />","30");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","30");

INSERT INTO question(text) VALUES ("Jaka jest poprawna składnia odwołania do zewnętrznego skryptu języka JavaScript?");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"<script name=\"kodJavaScript.js\"></script>","31");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"<script href=\"kodJavaScript.js\"></script>","31");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"<script src=\"kodJavaScript.js\"></script>","31");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"<script rel=\"kodJavaScript.js\"></script>","31");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","31");

INSERT INTO question(text) VALUES ("Prawidłowe rozszerzenie pliku z szablonem transformacji xslt to:");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"*.xslt","32");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"*.xml","32");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"*.xsl","32");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"*.xhtml","32");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","32");

INSERT INTO question(text) VALUES ("Hiperłącze: <a href=\"javascript:history.back()\">Powrót</a>");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"to nie jest poprawne składniowo","33");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"przejdzie do poprzedniej strony w hierarchii historii","33");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"przejdzie do następnej strony w hierarchii historii","33");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"wróci do strony domowej","33");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","33");

INSERT INTO question(text) VALUES ("REST - Które stwierdzenie dotyczące architektury REST są prawdziwe?");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"REST jest protokołem wymiany danych klient-serwer","34");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"REST jest standardem organizacji W3C","34");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"REST jest protokołem bazującym na formacie JSON","34");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"REST bazuje na znacznikach języka HTML","34");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","34");

INSERT INTO question(text) VALUES ("Zaznacz prawdziwą odpowiedź:");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"REST jest standardem W3C","35");
INSERT INTO answer(correct,text,question_id) VALUES (FALSE ,"Soap może używać resta","35");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"Soap nie może używać resta","35");
INSERT INTO answer(correct,text,question_id) VALUES (TRUE ,"Rest może używać soapa","35");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("1","35");

INSERT INTO quiz(time,number_of_questions,subject_id) VALUES ("3","6","1");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("2","1");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("2","2");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("2","3");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("2","4");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("2","5");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("2","6");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("2","7");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("2","8");
INSERT INTO quiz(time,number_of_questions,subject_id) VALUES ("3","6","1");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("3","11");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("3","12");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("3","13");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("3","14");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("3","15");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("3","16");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("3","17");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("3","18");
INSERT INTO quiz(time,number_of_questions,subject_id) VALUES ("3","6","1");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("4","1");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("4","12");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("4","3");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("4","14");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("4","5");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("4","16");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("4","7");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("4","18");
INSERT INTO quiz(time,number_of_questions,subject_id) VALUES ("3","6","1");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("5","11");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("5","2");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("5","13");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("5","4");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("5","15");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("5","6");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("5","17");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("5","8");
INSERT INTO quiz(time,number_of_questions,subject_id) VALUES ("3","6","1");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("6","21");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("6","22");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("6","23");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("6","24");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("6","25");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("6","26");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("6","27");
INSERT INTO quiz_questions (quiz_id,questions_id) VALUES ("6","28");