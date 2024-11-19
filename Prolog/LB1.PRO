DOMAINS
person = symbol

PREDICATES

man(person)
woman(person)

married(person, person)
husband(person, person)
wife(person, person)

parent(person, person)
father(person, person)
mother(person, person)

son(person, person)
dauther(person, person)

sibling(person, person)
brother(person, person)
sister(person, person)

grmother(person, person)
grfather(person, person)

grson(person, person)
grdauther(person, person)

aunt(person, person)
uncle(person, person)

nephew(person, person)
niece(person, person)

svyokr(person, person)
svekrov(person, person)

tyosha(person, person)
test(person, person)

nevestka(person, person)

CLAUSES
man("Boris").
man("Valeryi").
man("Alexander").
man("Maxim").
man("Lev").

woman("Valentina").
woman("Tamara").
woman("Darya").
woman("Tatyana").
woman("Alyona").

married("Boris", "Valentina").
married("Lev", "Tamara").
married("Alexander", "Darya").

father("Valeryi", "Boris").
father("Alexander", "Boris").
father("Darya", "Lev").
father("Tatyana", "Lev").
father("Alyona", "Alexander").
father("Maxim", "Alexander").

mother("Valeryi", "Valentina").
mother("Alexander", "Valentina").
mother("Darya", "Tamara").
mother("Tatyana", "Tamara").
mother("Alyona", "Darya").
mother("Maxim", "Darya").

husband(WIFE, HUSBAND):- married(HUSBAND, WIFE).
wife(HUSBAND, WIFE):- married(HUSBAND, WIFE).

parent(PERSON, PARENT):- father(PERSON, PARENT); mother(PERSON, PARENT).

sibling(PERSON, SIBLING):- father(PERSON, PARENT), father(SIBLING, PARENT), PERSON >< SIBLING.
brother(PERSON, BROTHER):- man(BROTHER), sibling(PERSON, BROTHER).
sister(PERSON, SISTER):- woman(SISTER), sibling(PERSON, SISTER).

son(PARENT, SON):- man(SON), parent(SON, PARENT).
dauther(PARENT, DAUTHER):- woman(DAUTHER), parent(DAUTHER, PARENT).

grmother(PERSON, GRMOTHER):- parent(PERSON, PARENT), mother(PARENT, GRMOTHER).
grfather(PERSON, GRFATHER):- parent(PERSON, PARENT), father(PARENT, GRFATHER).

grson(PERSON, GRSON):- son(PARENT, GRSON), parent(PARENT, PERSON).
grdauther(PERSON, GRDAUTHER):- dauther(PARENT, GRDAUTHER), parent(PARENT, PERSON). 

aunt(PERSON, AUNT):- parent(PERSON, PARENT), sister(PARENT, AUNT).
uncle(PERSON, UNCLE):- parent(PERSON, PARENT), brother(PARENT, UNCLE).

nephew(PERSON, NEPHEW):- son(PARENT, NEPHEW), sibling(PARENT, PERSON).
niece(PERSON, NIECE):- dauther(PARENT, NIECE), sibling(PARENT, PERSON).

svyokr(WIFE, SVYOKR):- husband(WIFE, HUSBAND), father(HUSBAND, SVYOKR).
svekrov(WIFE, SVEKROV):- husband(WIFE, HUSBAND), mother(HUSBAND, SVEKROV).

tyosha(HUSBAND, TYOSHA):- wife(HUSBAND, WIFE), mother(WIFE, TYOSHA).
test(HUSBAND, TEST):- wife(HUSBAND, WIFE), father(WIFE, TEST).

nevestka(PARENT, WIFE) :- wife(HUSBAND, WIFE), son(PARENT, HUSBAND).
