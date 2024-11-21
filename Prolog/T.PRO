DOMAINS
 floatList = real*
 floatList2D = floatList*

CONSTANTS
 CURRENCY = 3
	N = 3
 
PREDICATES
 start()

 %cutValue(real, real)
 roundValue(real, real, integer)
 inputValue(real)
 pow(real, real, integer)
 getInteger(real, integer)

GOAL
 start.

CLAUSES
 start() :- 
	nl, write("===== PROGRAM STARTED ====="), nl,
	inputValue(Input),
	write(Input), nl.

 %cutValue(Input, Cutted):-.

 pow(Value, Result, Degree):- Degree = 1, Result = Value, !.
 pow(Value, Result, Degree):-
	Degree > 1,
	LowDegree = Degree - 1,
	pow(Value, LowResult, LowDegree),
	Result = LowResult * Value.

 getInteger(Real, Integer):- 
	Real = Integer.

 roundValue(Input, Rounded, Currency):- 
	Input >= 0, 
	
	getInteger(Input, IntegerInput),
	FractionalInput = Input - IntegerInput,

	pow(10, ValueSize, Currency),
	NumeratorReal = ValueSize*FractionalInput,

	getInteger(NumeratorReal, NumeratorInteger),
	Rounded = IntegerInput + NumeratorInteger/ ValueSize, !.

 inputValue(Rounded):-
	readreal(InputValue),
%	getInteger(InputValue, Rounded),
	roundValue(InputValue, Rounded, 3).