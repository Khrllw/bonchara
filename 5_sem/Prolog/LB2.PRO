DOMAINS
 realList = real*
 realList2D = realList*

CONSTANTS
 currency = 3
 
PREDICATES
 start()
 allRight(integer)

 inputData(realList2D, realList, real, integer) 
 inputRhombus(realList2D, integer) 
 inputPoint(realList, string)
 inputCircle(realList, real, integer)
 readRealValue(real)

 pow(real, real, integer)
 getInteger(real, integer)
 roundReal(real, real, integer)
 equal(real, real)
 equal(real, real, real, real)
 max(real, real, real)
 min(real, real, real)
 positiveValue(real)

 getLenth(real, real, real)
 getLenth2D(realList, realList, real)
 differentPoints(realList, realList)
sumPoints(realList, realList, realList)

 checkRhombus(realList2D)
 checkRhombusSidesCombinations(realList2D, realList2D)

diskriminantPositive(real, real, real, real, real, real)
diskriminantZero(real, real, real, real, real, real)
diskriminant(real, real, real, real, real, real)

/*
 onCircle(realList, real, realList)
 onRhombus(realList2d, realList)
 onSegment(realList, realList, realList)
 onLine(realList, realList, realList)
 */
 findIntersection(realList, real, real, real, real)
 findIntersections(realList2D, realList, real)
 findLine(realList, realList, real,  real, real)
 
GOAL
 start.

CLAUSES

% -------------------- Начало программы --------------------

 start() :- 
	nl, write("===== PROGRAM STARTED ====="), nl,
	inputData(Rhombus, CircleCentre, Radius, 0),
	findIntersections(Rhombus, CircleCentre, Radius),
	write("GOOOD!"), nl.

% -------------------- Логика программы --------------------
% Проверка корректности ввода
 allRight(ExitCode):-
	ExitCode = 0.

% -------------------- Ввод данных ------------------------

 inputData(RhombusCoordinates, CircleCentre, Radius, 0):-
	inputCircle(CircleCentre, Radius, CircleExitCode),
	allRight(CircleExitCode),

	inputRhombus(RhombusCoordinates, RhobmusExitCode),
	allRight(RhobmusExitCode),	
	write("All Right"), nl.

% Ввод параметров окружности 
 inputCircle(CentrePoint, Radius, ExitCode):-
	write("-- ENTER CIRCLE COORDINATES --"), nl,
	inputPoint(CentrePoint, "Centre"),

	write("[ Radius size ]:"), nl,
	write("   Radius = "), readRealValue(Radius),
	positiveValue(Radius),
	ExitCode = 0, !;
	
	write("[ ERROR ] Uncorrect radius value"),
	ExitCode = 1,
	CentrePoint = [0, 0],
	Radius = 0.

% Ввод параметров ромба
 inputRhombus(NiceRhombus, ExitCode):-
	write("-- ENTER RHOMBUS COORDINATES --"), nl,
	inputPoint(Point1, "1"),
	inputPoint(Point2, "2"),
	differentPoints(Point1, Point2), !,
	
	inputPoint(Point3, "3"),
	differentPoints(Point2, Point3),
	differentPoints(Point1, Point3), !,

	inputPoint(Point4, "4"),
	differentPoints(Point1, Point4),
	differentPoints(Point2, Point4),
	differentPoints(Point3, Point4), !,
	write("Lenth norm"), nl,
	checkRhombusSidesCombinations([Point1, Point2, Point3, Point4], NiceRhombus),
	ExitCode = 0, !;
	
	write("[ ERROR ] It's not a rhombus"),
	ExitCode = 1,
	Point1 = [0, 0],
	Point2 = [0, 0],
	Point3 = [0, 0],
	Point4 = [0, 0].
	

% Ввод координат точки
 inputPoint([X, Y], PointDescription):-
	write("[ Point '"), write(PointDescription), write("' ]:"), nl,
	write("   X = "), readRealValue(X),
	write("   Y = "), readRealValue(Y).

% Ввод числа типа "real" с заданной точностью
 readRealValue(RoundedValue):-
	readreal(InputValue),
	roundReal(InputValue, RoundedValue, currency).

% -------------------- Базовые математические операции ------------------------

% Рекурсивное возведение числа в степень 
 pow(Value, Result, Degree):- Degree = 1, Result = Value, !.
 pow(Value, Result, Degree):-
	Degree > 1,
	LowDegree = Degree - 1,
	pow(Value, LowResult, LowDegree),
	Result = LowResult * Value.

% Выделение целой части числа типа "real"
 getInteger(Real, Integer):- 
	Real = Integer.

% Округление числа типа "real" до указанной точности
 roundReal(Input, Rounded, Currency):- 
	
	getInteger(Input, IntegerInput),
	FractionalInput = Input - IntegerInput,

	pow(10, ValueSize, Currency),
	NumeratorReal = ValueSize*FractionalInput,

	getInteger(NumeratorReal, NumeratorInteger),
	Rounded = IntegerInput + NumeratorInteger/ ValueSize, !.

% Сравнение чисел
 equal(Value1, Value2):-
	Value1 = Value2.

 equal(Value1, Value2, Value3, Value4):-
	equal(Value1, Value2),
	equal(Value3, Value4),
	equal(Value1, Value3).

% Нахождение максимума из двух чисел
 max(Value1, Value2, Result):-
	Value1 > Value2, Result = Value1, !;
	Result = Value2.
	
% Нахождение минимума из двух чисел
 min(Value1, Value2, Result):-
	Value1 < Value2, Result = Value1, !;
	Result = Value2.

% Проверка на положительное число
 positiveValue(Value):-
	Value > 0.

% -------------------- Геометрические операции ------------------------	

% Формулы рассчёта точек пересечения
% r^2 = (x - a)^2 + (y - c)^2

% kX + gY + b = 0 
% Y = -(kX + b)/g

% r^2 = (X - a) ^ 2 + (-(kX + b)/g - c) ^ 2
% (X - a) ^ 2 + (-(kX + b)/g - c) ^ 2 - r^2 = 0
% X^2 - (2a)X + (a^2) + ((k/g)X + (bg + c))^2 - r^2 = 0
% X^2 - (2a)X + ((k/g)X + (bg + c))^2 - r^2 + (a^2)= 0

% X^2 - (2a)X + ((k/g)^2)X^2 + (2k(bg + c)/g)X + (bg + c)^2 - r^2 + (a^2)= 0

% (((k/g)^2) + 1) * X^2 + ((2k(bg + c)/g) - 2a) * X + (bg + c)^2 - r^2 + (a^2)= 0
% D = ((2k(bg + c)/g) - 2a) ^2 - 4*(((k/g)^2) + 1) * ((bg + c)^2 - r^2 + (a^2))
% D > 0:
	
% D = 0:
	
% D < 0:
	



% r^2 = (x - x0)^2 + (kx + b -x0)^2
% r^2 = x^2 -2xx0 + x0^2 + kx^2 + 2(b - x0)kx +(b - x0)^2
% X^2 - (2x0)X - 

% Определение длины одномерного отрезка
 getLenth(X1, X2, Lenth):-
	Lenth = abs(X1 - X2).

% Определение длины двумерного отрезка
 getLenth2D([X1, Y1], [X2, Y2], Lenth):-
	getLenth(X1, X2, XLenth),
	getLenth(Y1, Y2, YLenth),
	Lenth = sqrt(XLenth * XLenth + YLenth * YLenth).
	
% Проверка "Точки различны"
 differentPoints([Point1X, Point1Y], [Point2X, Point2Y]):-
	not(equal(Point1X, Point2X));
	not(equal(Point1Y, Point2Y)). 

 checkRhombusSidesCombinations([Point1, Point2, Point3, Point4], NiceRhombus):-
	checkRhombus([Point1, Point2, Point3, Point4]), 
	NiceRombus = [Point1, Point2, Point3, Point4], !;
	
	checkRhombus([Point1, Point3, Point2, Point4]),
	NiceRombus = [Point1, Point3, Point2, Point4], !;

	checkRhombus([Point1, Point2, Point4, Point3]),
	NiceRombus = [Point1, Point2, Point4, Point3].


% Проверка "Введённый четырехугольник - ромб"
 checkRhombus([Point1, Point2, Point3, Point4]):-
	% Все стороны равны
	getLenth2D(Point1, Point2, Lenth12),
	getLenth2D(Point2, Point3, Lenth23),
	getLenth2D(Point3, Point4, Lenth34),
	getLenth2D(Point4, Point1, Lenth41),
	equal(Lenth12, Lenth23, Lenth34, Lenth41),

	% Диагонали пересекаются в их центрах
	sumPoints(Point1, Point3, LeftPoint),
	sumPoints(Point2, Point4, RightPoint),
	
	LeftPoint = RightPoint.

% kX + gY + b = 0 
 findLine([LineX1, LineY1], [LineX2, LineY2], K, G, B):-
	LineX1 <> LineX2, LineY1 <> LineY2, 
	G = -1,
	K = (LineY2 - LineY1) / (LineX2 - LineX1),
	B = LineY1 - K * LineX1, !;
	
	LineX1 = LineX2, LineY1 <> LineY2,
	G = 0,
	K = -1,
	min(LineX1, LineX2, B), !;

	LineX1 <> LineX2, LineY1 = LineY2,
	G = -1,
	K = 0,
	min(LineY1, LineY2, B), !.

diskriminantPositive(D, B, A, K, G, Br):-
	D > 0, 

	Root1 = (-B + D) /(2 * A),
	write(Root1), nl,
	Y1 = (-K * Root1 - Br) / G,
	write(Y1), nl,

	Root2 = (-B - D) /(2 * A),
	write(Root2), nl,
	Y2 = (-K * Root2 - Br) / G,
	write(Y2), nl.

diskriminantZero(D, B, A, K, G, Br):-
	D = 0,
	X = -B / (2 * A),
	write(X), nl,
	Y = (-K * X - Br) / G,
	write(Y), nl.
	
	
diskriminant(Value, B, A, K, G, Br):-
	diskriminantPositive(Value, B, A, K, G, Br),!;
	diskriminantZero(Value, B, A, K, G, Br),!.


% r^2 = (X - a) ^ 2 + (-(kX + b)/g - c) ^ 2
findIntersection([CentreX, CentreY], Radius, K, G, Br):-
	G <> 0,
	A = ( K / G ) * ( K / G ) + 1, 
	B = (2 * K* (Br*G + CentreY)/ G) - 2 * CentreX,
	C = (Br * G + CentreY) * (Br * G + CentreY) - Radius * Radius + (CentreX * CentreX),
	D = sqrt(B * B - 4* A* C),
	CentreX = 0, CentreY = 0,
	diskriminant(D, B, A, K, G, Br).

	%Radius * Radius = (X - CentreX) * (X - CentreX) + ((K * X + B)/G - CentreY) * ((K * X + B)/G - CentreY),
	%write(X), nl,
	%Y = (-K * X - B) / G,
	%write(Y), nl.


findIntersections([Point1, Point2, Point3, Point4], CircleCentre, Radius):-
	findLine(Point1, Point2, K, G, B), findIntersection(CircleCentre, Radius, K, G, B);
	findLine(Point2, Point3, K, G, B), findIntersection(CircleCentre, Radius, K, G, B);
	findLine(Point3, Point4, K, G, B), findIntersection(CircleCentre, Radius, K, G, B);
	findLine(Point4, Point1, K, G, B), findIntersection(CircleCentre, Radius, K, G, B).
	

/*

% Проверка "Точка принадлежит окружности"
 onCircle(CircleCentre, Radius, Point):-
	getLenth2D(Point, CircleCentre, Lenth),
	SqrtLenth = sqrt(Lenth),
	roundReal(SqrtLenth, RoundedLenth, currency),
	Radius = RoundedLenth.

	
% Проверка "Точка принадлежит ромбу и окружности"
 onRhombus([Point1, Point2, Point3, Point4], Point):-
	onSegment(Point1, Point2, Point);
	onSegment(Point2, Point3, Point);
	onSegment(Point3, Point4, Point);
	onSegment(Point4, Point1, Point).



% Проверка "Точка принадлежит стороне ромба"
 onSegment([LineX1, LineY1], [LineX2, LineY2], [PointX, PointY]):-
	onLine([LineX1, LineY1], [LineX2, LineY2], [PointX, PointY]),
	max(LineX1, LineX2, XMax),
	min(LineX1, LineX2, XMin),
	max(LineY1, LineY2, YMax),
	min(LineY1, LineY2, YMin),
	XMin <= PointX,  PointX <= XMax,
	YMin <= PointY,  PointY <= YMax.

% Проверка "Точка принадлежит прямой отрезка ромба"
 onLine([LineX1, LineY1], [LineX2, LineY2], [PointX, PointY]):-
	LineX1 <> LineX2, 
	LineY1 <> LineY2,
	K = (LineY2 - LineY1) / (LineX2 - LineX1),
	B = LineY1 - K * LineX1,
	PointY = K * PointX + B .

% Проверка "Точка принадлежит ромбу"
 onRhombus([Point1, Point2, Point3, Point4], Point):-
	onSegment(Point1, Point2, Point);
	onSegment(Point2, Point3, Point);
	onSegment(Point3, Point4, Point);
	onSegment(Point4, Point1, Point).

% Проверка "Точка принадлежит отрезку"
 onSegment([LineX1, LineY1], [LineX2, LineY2], [PointX, PointY]):-
	onLine([LineX1, LineY1], [LineX2, LineY2], [PointX, PointY]),
	max(LineX1, LineX2, XMax),
	min(LineX1, LineX2, XMin),
	max(LineY1, LineY2, YMax),
	min(LineY1, LineY2, YMin),
	XMin <= PointX,  PointX <= XMax,
	YMin <= PointY,  PointY <= YMax.

% Проверка "Точка принадлежит прямой"
 onLine([LineX1, LineY1], [LineX2, LineY2], [PointX, PointY]):-
	LineX1 <> LineX2, 
	LineY1 <> LineY2,
	K = (LineY2 - LineY1) / (LineX2 - LineX1),
	B = LineY1 - K * LineX1,
	PointY = K * PointX + B .
*/
% Сложение координат двух точек
 sumPoints([Point1X, Point1Y], [Point2X, Point2Y], [PointX, PointY]):-
	PointX = Point1X + Point2X,
	PointY = Point1Y + Point2Y.


	