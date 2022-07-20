## A. Дерево отрезков на сумму

В этой задаче вам нужно написать обычное дерево отрезков на сумму.

Входные данные  
Первая строка содержит два числа n и m (1≤n,m≤100000) — размер массива и число операций. Следующая строка содержит n чисел ai — начальное состояние массива (0≤ai≤109). Далее следует описание операций. Описание каждой операции имеет следущий вид:  

1 i v — присвоить элементу с индексом i значение v (0≤i<n, 0≤v≤109).  
2 l r — вычислить сумму элементов с индексами от l до r−1 (0≤l<r≤n).  
Выходные данные  
Для каждой операции второго типа выведите соответствующую сумму.

## B. Число минимумов на отрезке

Теперь измените код дерева отрезков, чтобы кроме минимума на отрезке считалось также и число элементов, равных минимуму.  

Входные данные  
Первая строка содержит два числа n и m (1≤n,m≤100000) — размер массива и число операций. Следующая строка содержит n чисел ai — начальное состояние массива (0≤ai≤109). Далее следует описание операций. Описание каждой операции имеет следущий вид:  

1 i v — присвоить элементу с индексом i значение v (0≤i<n, 0≤v≤109).  
2 l r — найти минимум и число элементов, равных минимуму, среди элементов с индексами от l до r−1 (0≤l<r≤n).  
Выходные данные  
Для каждой операции второго типа выведите два числа — минимум на заданном отрезке и число элементов, равных этому минимуму.  

## C. Отрезок с максимальной суммой

В этой задаче вам нужно написать дерево отрезков для нахождения подотрезка с максимальной суммой.  

Входные данные  
Первая строка содержит два числа n и m (1≤n,m≤100000) — размер массива и число операций. Следующая строка содержит n чисел ai — начальное состояние массива (−109≤ai≤109). Далее следует описание операций. Описание каждой операции имеет следующий вид: i v — присвоить элементу с индексом i значения v (0≤i<n, −109≤v≤109).  

Выходные данные  
Выведите m+1 строку: максимальную сумму чисел на отрезке до всех операций и после каждой операции. Обратите внимание, что этот отрезок может быть пустым (при этом сумма на нем будет равна 0)  

## D. K-я единица

В этой задаче вам нужно добавить в дерево отрезков операцию нахождения k-й единицы.  

Входные данные  
Первая строка содержит два числа n и m (1≤n,m≤100000) — размер массива и число операций. Следующая строка содержит n чисел ai — начальное состояние массива (ai∈{0,1} ). Далее следует описание операций. Описание каждой операции имеет следущий вид:  

1 i — изменить элемент с индексом i на противоположный.  
2 k — найти k-ю единицу (единицы нумеруются с 0, гарантируется, что в массиве достаточное количество единиц).  
Выходные данные  
Для каждой операции второго типа выведите индекс соответствующей единицы (все индексы в этой задаче от 0).  

## E. Первый элемент не меньше X - 2

В этой задаче вам нужно добавить в дерево отрезков операцию нахождения по данным x и l минимального индекса j, для которого j≥l и a[j]≥x.  

Входные данные  
Первая строка содержит два числа n и m (1≤n,m≤100000) — размер массива и число операций. Следующая строка содержит n чисел ai — начальное состояние массива (0≤ai≤109 ). Далее следует описание операций. Описание каждой операции имеет следущий вид:  

1 i v — изменить элемент с индексом i на v (0≤i<n, 0≤v≤109).  
2 x l — найти минимальный индекс j, для j≥l и a[j]≥x (0≤x≤109, 0≤l<n). Если такого элемента нет, выведите −1. Индексы начинаются с 0.  
Выходные данные  
Для каждой операции второго типа выведите ответ на запрос.  

## F. Присваивание и минимум

Есть массив из n элементов, изначально заполненный нулями. Вам нужно написать структуру данных, которая обрабатывает два вида запросов:  

присвоить всем элементам на отрезке от l до r−1 значение v,  
узнать минимум на отрезке от l до r−1.  
Входные данные  
Первая строка содержит два числа n и m (1≤n,m≤100000) — размер массива и число операций. Далее следует описание операций. Описание каждой операции имеет следущий вид:  

1 l r v — присвоить всем элементам на отрезке от l до r−1 значение v (0≤l<r≤n, 0≤v≤109).  
2 l r — узнать минимум на отрезке от l до r−1 (0≤l<r≤n).  
Выходные данные  
Для каждой операции второго типа выведите соответствующее значение.  

## H. Землетрясения

Город представляет собой последовательность из n клеток, занумерованных числами от 0 до n−1. Изначально все клетки пустые. Далее последовательно происходят m событий одного из двух типов:  

в клетке i строится здание с прочностью h (если в этой клетке уже было здание, оно сносится и заменяется на новое),  
на отрезке от l до r−1 случается землятресение мощностью p, оно разрушает все здания, прочность которых не больше p.  
Ваша задача — для каждого землятресения сказать, сколько зданий оно разрушит.  

Входные данные  
Первая строка содержит числа n и m — число клеток и число событий (1≤n,m≤105). Следующие m строк содержат описание событий. Описание каждого события имеет следующий вид:  

1 i h — в клетке i строится здание с прочностью h (0≤i<n, 1≤h≤109).  
2 l r p — на отрезке от l до r−1 происходит землятресение с мощностью p (0≤l<r≤n, 0≤p≤109).  
Выходные данные  
Для каждого события второго типа выведите, сколько зданий было разрушено.  

## K. Разреженные таблицы

Дан массив из n чисел. Требуется написать программу, которая будет отвечать на запросы следующего вида: найти минимум на отрезке между u и v включительно.  

Входные данные  
В первой строке зданы три натуральных числа n, m (1⩽n⩽105, 1⩽m⩽107) и a1 (0⩽a1<16714589) — количество элементов в массиве, количество запросов и первый элемент массива соответственно. Вторая строка содержит два натуральных числа u1 и v1 (1⩽u1,v1⩽n) — первый запрос.  

Для того, чтобы размер ввода был небольшой, массив и запросы генерируются.  

Элементы a2,a3,…,an задаются следующей формулой:  
ai+1=(23⋅ai+21563)mod16714589.  
Например, при n=10, a1=12345 получается следующий массив: a = (12345, 305498, 7048017, 11694653, 1565158, 2591019, 9471233, 570265, 13137658, 1325095).  

Запросы генерируются следующим образом:  

ui+1=((17⋅ui+751+ri+2i)modn)+1, vi+1=((13⋅vi+593+ri+5i)modn)+1,  
где ri — ответ на запрос номер i.  
Обратите внимание, что ui может быть больше, чем vi.  

Выходные данные  
В выходной файл выведите um, vm и rm (последний запрос и ответ на него).  

## M. Точки и отрезки

Дано n отрезков на числовой прямой и m точек на этой же прямой. Для каждой из данных точек определите, скольким отрезкам они принадлежат. Точка x считается принадлежащей отрезку с концами a и b, если выполняется двойное неравенство min(a, b) ≤ x ≤ max(a, b).  

Входные данные  
Первая строка содержит два целых числа n (1 ≤ n ≤ 105) — число отрезков и m (1 ≤ m ≤ 105) — число точек. В следующих n строках по два целых числи ai и bi — координаты концов соответствующего отрезка. В последней строке m целых чисел — координаты точек. Все числа по абсолютной величине не превосходят 109.  

Выходные данные  
В выходной файл выведите m чисел — для каждой точки количество отрезков, в которых она содержится.  

## N. Кассы

На одном из московских вокзалов билеты продают n касс. Каждая касса работает без перерыва определенный промежуток времени по фиксированному расписанию (одному и тому же каждый день). Требуется определить, на протяжении какого времени в течение суток работают все кассы одновременно.

Входные данные  
Сначала вводится одно целое число n (0 < n ≤ 100 000).  

В каждой из следующих n строк через пробел расположены шесть целых чисел, первые три из которых обозначают время открытия кассы в часах, минутах и секундах (часы — целое число от 0 до 23, минуты и секунды — целые числа от 0 до 59), оставшиеся три — время закрытия в том же формате. Числа разделены пробелами.  

Время открытия означает, что в соответствующую ему секунду касса уже работает, а время закрытия — что в соответствующую секунду касса уже не работает. Например, касса, открытая с 10 ч 30 мин 30 с до 10 ч 35 мин 30 с, ежесуточно работает 300 секунд.  

Если время открытия совпадает с временем закрытия, то касса работает круглосуточно. Если первое время больше второго, то касса начинает работу до полуночи, а заканчивает — на следующий день.  

Выходные данные  
Требуется вывести одно число — суммарное время за сутки (в секундах), на протяжении которого работают все n касс.  

## O.py. Операционные системы

Васин жесткий диск состоит из M секторов. Вася последовательно устанавливал на него различные операционные системы следующим методом: он создавал новый раздел диска из последовательных секторов, начиная с сектора номер ai и до сектора bi включительно, и устанавливал на него очередную систему. При этом если очередной раздел хотя бы по одному сектору пересекается с каким-то ранее созданным разделом, то ранее созданный раздел «затирается», и операционная система, которая на него была установлена, больше не может быть загружена.  

Напишите программу, которая по информации о том, какие разделы на диске создавал Вася, определит, сколько в итоге работающих операционных систем установлено и в настоящий момент работает на Васином компьютере.  

Входные данные  
Сначала вводятся натуральное число M — количество секторов на жестком диске (1 ≤ M ≤ 109) и целое число N — количество разделов, которое последовательно создавал Вася (0 ≤ N ≤ 21000). Далее идут N пар чисел ai и bi, задающих номера начального и конечного секторов раздела (1 ≤ ai ≤ bi ≤ M).  

Выходные данные  
Выведите одно число — количество работающих операционных систем на Васином компьютере.  