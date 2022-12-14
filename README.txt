Constantinescu Andrei Alexandru 324CD
Proiect Etapa 1- POO TV

Pachetul input contine urmatoarele clase, ce m-au ajutat sa intepretez inputul fiecarui test:

	-Action:
		aceasta clasa are field-urile specifice tutror actiunilor ce pot fi efectuate
in cadrul acestei etape. Cu ajutorul acestei clase am putut stoca datele de intrare din
cadrul testelor

	-Contains:
		aceasta clasa are field-urile specifice filtrarii filmelor, in functie de actorii
sau genurile ce pot fi regasite intr-un film

	-Sort:
		aceasta clasa conttine field-urile specifice filtrarii filmelor, intr-o anumita ordine,
din punct de vedere al duratei si a ratingului

	-Credentials:
		aceasta clasa contine fieldurile specifice credentialelor unui user, atat cand este
vorba de credentialele unui user ce vrea sa se autentifice/inregistreze, cat si cele 
specifice unui user deja autentificat(adica si tipul contului, tara, si balance ul)

	-Error:
		aceasta clasa este de tip SINGLETON si instantiaza un obiect ce este pus in output
de fiecare data cand se doreste sa se efectueze o actiune ce nu este permisa. Implementarea
tip SINGLETON a fost posibila intrucat indiferent de motivul pentru care actiunea nu era
permisa, trebuia afisata aceeasi eroare

	-Filter:
		aceasta clasa contine field-urile specifice actiunii de tip filtrare, si anume:
    -sort(instanta a clasei Sort - ce este nul daca filtrarea nu implica si sortarea filmelor 
dupa un anumit criteriu)
    -contains(instanta a clasei Contains - ce este nul daca filtrarea nu implica vederea filmelor
ce se incadreaza intr-un anumit gen sau in care joaca un anumit actor)
Cu ajutorul acestor field-uri citesc din input acest tip de actiune

	-Movie:
		aceasta clasa contine field-urile specifice unui film, dar si unul in plus, si anume
array-ul de int "ratings" ce retine toate rating-urile ce le-a primit un film, pentru
a putea calcula corect rating-ul final al filmului respectiv

	-User:
		aceasta clasa contine field-urole specifice unui utilizator
ce este autentificat in baza de date a site-ului

	-Input:
		aceasta clasa contine un array de utilizatori, unul de filme si unul de actiuni. 
Acestea reprezinta practic, input-ul fiecarui test.

Pachetul visitor contine urmatoarele clase ce m-au ajutat sa interpretez inputul fiecarui test,
folosind o implementare ce utilizeaza design pattern ul VISITOR:

	-Visitor:
		o interfata ce contine metoda visit, ce primeste ca paramtetru o pagina, data de baze
a site-ului, actiunea ce trebuie indeplinita, un arraynode in care retin outputul specific
fiecarui test, si instanta clasei Error, ce e afisata in cazul in care actiunea primita
ca parametru nu poate fi indeplinita

	-Page:
		aceasta clasa contine un field unde este retinuta pagina curenta sub forma unui string
si o metoda accept ce primeste ca paramtru o instanta a clasei Visitor, baza de date a site-ului,
o actiune ce trebuie indeplinita, un arraynode in care retin output-ul pentru fiecare test,
dar si instanta clasei Error, ce e afisata in output in cazul in care actiunea nu poate fi indepli-
nita. Aceasta metoda apeleaza metoda visit a obiectului de tip Visitor primit ca parametru

	-DataBase:
		aceasta clasa contine un field de tip User, ce reprezinta user-ul curent,
un arraylist de useri, ce reprezinta userii inregistrati, un arraylist ed movie, ce contine
filmele de pe platforma, si 2 arraylist de movie ce sunt folosite in rezolvarea temei

	-HomePageAuthenticatedVisitor:
		aceasta clasa implementeaza interfata Visitor, si deci si metoda visit. Aceasta
metoda verifica daca pagina curenta este "movies", "see details", "upgrades", "HomeA"(homepage
autentificat) si in functie de acest lucru si de actiunea transmisa ca parametru ori se
efectueaza actiunea ori este transmisa o eroare la output

	-HomePageNotAuthenticatedVisitor:
		aceasta clasa implementeaza m=interfata visitor, si deci si metoda viist. Aceasta
metoda verifica daca pagina curenta est "HomeNA"(homepage neautentificat), "login" sau
"register" si in functie de acest lucru si de actiunea transmisa ca parametru ori se 
efectueaza actiunea ori este transmisa o eroare la output

In clasa main citesc datele de input din fiecare test, creez data de baze, instantiez clasa
eroare si iterez prin arraylist ul de actiuni prezent in inputul furnizat. In cazul in care
avem un utilizator autentificat, este apelat visitor-ul specific 
(de tip HomePageAuthenticatedVisitor), iar in caz contrar cel specific unui scnariu fara user
autentificat(HomePageNAuVisitor). La final este printat outputul in fisierul ce output.
		
		