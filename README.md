# Calculator-cu-numere-romane


Primim la intrare 2 liste: una ne restrictioneaza operatorii pe care ii putem
folosi, iar intr-una se afla ecuatiile al caror rezultat trebuie sa il calculam.

Dupa ce stabilim ce putem folosi, transpunem fiecare element din ecuatiile
date intr-o lista in functie de natura sa. Transmitem lista mai departe pentru
a putea calcula ce e de calculat cu ajutorul formei poloneze de calcul.
Rezultatul este aproximat, convertit in forma romana si apoi intors ca rezultat
al ecuatiei calculate.

Detalii privind fiecare clasa si metode folosite, se pot gasi in continuarea
acestui readme.

pachetul operands:

-contine 2 clase: ArabOperand si RomanOperand ce implementeaza IOperand pentru 
a putea folosi factory pattern
- clasa OperandsFactory:

	-implementarea Singleton
	
	-createOperand se foloeseste de un string ca parametru pentru a putea crea
	elemente de tip Operand. In functie de tipul de string, daca este format
	din cifre sau litere, returnez ArabOperand sau RomanOperand
	
	-convertToRomanNumber reprezinta conversia unui numar arab in numar roman
	
		-scad din numar repetat valori cunoscute pana cand acesta este 0 si
		ii schimb simbolul numarului in functie de numarul scazut
		
	-convertToArabNumber este conversia unui numar roman in numar arab


pachetul operators:

-clasa Operator implementeaza interfata IOperator

-clasa OperatorsFactory:

	-implementarea Singleton
	
	-createOperator se foloeseste de un string ca parametru pentru a putea crea
	elemente de tip Operator. In functie de tipul de string, returneaza adunare,
	scadere sau alt operator
	
	-isOperator verifica daca un string este un operator. Stringul trece printr-o
	serie de if-uri, daca nu este egal cu vreun operator cunoscut, returneaza 
	false
	
	-isBynaryOperator verifica daca un string este un operator. Stringul trece printr-o
	serie de if-uri, daca nu este egal cu vreun operator cunoscut, returneaza 
	false
	
	-isUnaryOperator verifica daca un string este un operator. Stringul trece printr-o
	serie de if-uri, daca nu este egal cu vreun operator cunoscut, returneaza 
	false


pachetul binaryOperators:

-contine 5 clase ce implementeaza IBinaryOperator pentru a putea crea un obiect convenabil
prin metoda createOperator. Clasele au metoda calculate completata in functie de 
calculul pe care trebuie sa-l faca si returneaza un ArabOperand


pachetul unaryOperators:

-contine 2 clase ce implementeaza IUnaryOperator pentru a putea crea un obiect convenabil
prin metoda createOperator. Clasele au metoda calculate completata in functie de 
calculul pe care trebuie sa-l faca si returneaza un ArabOperand

pachetul brackets:

-contine 2 clase ce implementeaza IBracket ce ne vor ajuta in crearea unor
obiecte de tip Bracket prin createBracket

-clasa BreacketsFactory:

	-implementarea Singleton
	
	-createBracket primeste un String ca parametru si intoarce un obiect de tip
	Bracket in functie de deschiderea parantezei
	
	-isBracket verifica daca un token este o paranteza. Stringul este trecut
	printr-o serie de if-uri pana returneaza true
	
	-isClosedBracket si isOpenBracket primesc un parametru de tip IBracket si
	e verificat daca Bracket-ul este deschis sau inchis
	
	-isPairBracket verifica daca 2 obiecte de tip IBracket sunt paranteze
	complementare


pachetul coreClasses:

-clasa Token implementeaza IToken

-clasa Server:


	-publish verifica daca se pot efectua operatiile date si introduce in lista
	de rezultate rezultatul operatiei
	
	-canPublish verifica daca se pot efectua operatiile date, comparand lista
	in care sunt bagati operatorii acceptati si operatorii pe care ii avem noi
	
	-subscribe introduce operatorii valizi intr-o lista
	
	-getResults intoarce lista cu rezultate
	
	-postfixExpression primeste un array de stringuri. Este iterata si este creat un
	token cu care verificam natura stringului
	
		-daca e operand, este adaugat intr-o lista pentru forma postfixata
		
		-daca este paranteza:
		
			-daca este paranteza deschisa, este adaugata intr-o stiva cu operanzi
			daca este paranteza inchisa, se scot operanzi din stiva pana se gaseste
			o paranteza deschisa
			
		-daca este operand, se adauga in stiva
		
	-postfixCalculate primeste o lista de stringuri. Verifica natura fiecarui string
	si aplica algoritmul de la [1].
	

Bibliografie:
[1] http://ocw.cs.pub.ro/courses/sd-ca/2015/laboratoare/laborator-04#forma_poloneza_inversa_forma_postfixata

http://stackoverflow.com/questions/20313254/roman-numeral-to-number-conversion

http://stackoverflow.com/questions/12967896/converting-integers-to-roman-numerals-java

https://www.tutorialspoint.com/design_pattern/factory_pattern.html

https://www.youtube.com/watch?v=uh7fD8WiT28

http://elf.cs.pub.ro/poo/laboratoare/design-patterns




