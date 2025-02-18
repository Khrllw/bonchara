CODE = 6000
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

 qEquationCalc(real, real, real, real)
 qEquation(real, real, real, real, real, real, real)
 findSolutionsWithX(real, real, real, real, real, real)
 findSolutionsWithY(real, real, real, real, real, real)

 getLenth(real, real, real)
 getLenth2D(realList, realList, real)
 differentPoints(realList, realList)
 sumPoints(realList, realList, realList)

 checkRhombus(realList2D)
 checkRhombusSidesCombinations(realList2D, realList2D, integer)

/* 
diskriminantPositive(real, real, real, real, real, real)
 diskriminantZero(real, real, real, real, real, real)
 diskriminant(real, real, real, real, real, real)
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
	checkRhombusSidesCombinations([Point1, Point2, Point3, Point4], NiceRhombus, CheckExitCode), 
	CheckExitCode = 0,
	ExitCode = 0, !;
	
	write("[ ERROR ] It's not a rhombus"),
	ExitCode = 1,
	Point1 = [0, 0],
	Point2 = [0, 0],
	Point3 = [0, 0],
	Point4 = [0, 0], NiceRhombus = [Point1, Point2, Point3, Point4].
	

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

% Упорядочивание точек ромба
 checkRhombusSidesCombinations([Point1, Point2, Point3, Point4], NiceRhombus, ExitCode):-
	checkRhombus([Point1, Point2, Point3, Point4]), 
	NiceRhombus = [Point1, Point2, Point3, Point4],
	ExitCode = 0, !;
	
	checkRhombus([Point1, Point3, Point2, Point4]),
	NiceRhombus = [Point1, Point3, Point2, Point4],
	ExitCode = 0, !;

	checkRhombus([Point1, Point2, Point4, Point3]),
	NiceRhombus = [Point1, Point2, Point4, Point3],
	ExitCode = 0, !;
	
	ExitCode = 1.	


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

% Поиск уравнения прямой по двум известным точкам
% kX + gY + b = 0 
 findLine([LineX1, LineY1], [LineX2, LineY2], K, G, B):-

	% Наклонная прямая
	LineX1 <> LineX2, LineY1 <> LineY2, 
	G = -1,
	K = (LineY2 - LineY1) / (LineX2 - LineX1),
	B = LineY1 - K * LineX1, !;

	% Вертикальная прямая
	LineX1 = LineX2, LineY1 <> LineY2,
	G = 0,
	K = -1,
	B = LineX1, !;
	
	% Горизонтальная прямая
	LineX1 <> LineX2, LineY1 = LineY2,
	G = -1,
	K = 0,
	B = LineY1.

qEquationCalc(DiskrSqrt, A, B, X):-
	X = (DiskrSqrt - B) / (2 * A).


qEquation(Diskr, A, B, C, X1, X2, N):-
	Diskr > 0, N = 2,
	DiskrSqrt = sqrt(Diskr),
	NegDiskrSqrt = -DiskrSqrt,	
	qEquationCalc(NegDiskrSqrt, A, B, X1),
	qEquationCalc(DiskrSqrt, A, B, X2);

	Diskr = 0, N = 1, X2 = 0,
	DiskrSqrt = sqrt(Diskr),
	qEquationCalc(DiskrSqrt, A, B, X1), !;

	N = 0, X1 = 0, X2 = 0.


findSolutionsWithX(X1, X2, N, K, G, B):-
	N = 1,
	Y = (-K * X1 - B)/G,
	write("Xint = "), write(X1), nl,
	write("Yint = "), write(Y), nl, nl;

	N = 2,
	Y1 = (-K * X1 - B)/G,
	write("Xint = "), write(X1), nl, 
	write("Yint = "), write(Y1), nl, nl,
	Y2 = (-K * X2 - B)/G,
	write("Xint = "), write(X2), nl, 
	write("Yint = "), write(Y2), nl, nl.

findSolutionsWithY(Y1, Y2, N, K, G, B):-
	N = 1,
	write("Xint = "), write(B), nl, 
	write("Yint = "), write(Y1), nl, nl;

	N = 2,
	write("Xint = "), write(B), nl, 
	write("Yint = "), write(Y1), nl, nl,

	write("Xint = "), write(B), nl, 
	write("Yint = "), write(Y2), nl, nl.

% Поиск точек перечения прямой и окружности
% Общий вид уравнения: 
%	R^2 = (X - x_c) ^ 2 + (-(kX + b)/g - y_c) ^ 2

% При G = 0:
%	R^2 = (B - x_c)^2 + (Y - y_c)^2
%	R^2 = (B - x_c)^2 + Y^2 - 2Yy_c + y_c^2
%	(Y)^2 - 2y_c * (Y) + y_c^2 + (B - x_c)^2 - R^2
 
 findIntersection([CentreX, CentreY], Radius, K, G, B):-
	G <> 0,
	% Решение относительно X
	A_coef = ( K * K ) / ( G * G ) + 1, 
	B_coef = (2 * K * (B + CentreY * G) / (G * G)) - 2 * CentreX,
	C_coef = (B / G + CentreY) * (B / G + CentreY) - Radius * Radius + (CentreX * CentreX),
	Diskr = B_coef * B_coef - 4 * A_coef* C_coef,
	qEquation(Diskr, A_coef, B_coef, C_coef, X1, X2, N),
	findSolutionsWithX(X1, X2, N, K, G, B);
	
	G = 0,
	% Решение относительно Y
	A_coef = 1, 
	B_coef = -2 * CentreY,
	C_coef = (B - CentreX) * (B - CentreX) - Radius * Radius + CentreY * CentreY,
	Diskr = B_coef * B_coef - 4 * A_coef * C_coef,
	qEquation(Diskr, A_coef, B_coef, C_coef, Y1, Y2, N),
	findSolutionsWithY(Y1, Y2, N, K, G, B).

% Поиск точек перечечения окружности со сторонами квадрата
 findIntersections([Point1, Point2, Point3, Point4], CircleCentre, Radius):-
	findLine(Point1, Point2, K, G, B), findIntersection(CircleCentre, Radius, K, G, B);
	findLine(Point2, Point3, K, G, B), findIntersection(CircleCentre, Radius, K, G, B);
	findLine(Point3, Point4, K, G, B), findIntersection(CircleCentre, Radius, K, G, B);
	findLine(Point4, Point1, K, G, B), findIntersection(CircleCentre, Radius, K, G, B).


% Сложение координат двух точек
 sumPoints([Point1X, Point1Y], [Point2X, Point2Y], [PointX, PointY]):-
	PointX = Point1X + Point2X,
	PointY = Point1Y + Point2Y.
