## Лабораторная работа №2

[A. BST или нет?]
----
Вам дано какое-то двоичное дерево. Проверьте, является ли оно BST.

Входные данные
В первой строке дано целое число n – количество вершин в дереве (1 ≤ n ≤ 100000).

В следующих n строках даны описания вершин. Каждая строка содержит число x, содержащееся в вершине, затем два числа l и r – номера левого и правого ребёнка, или -1, если ребёнка нет. (1 ≤ x ≤ 109, 1 ≤ l, r ≤ n).

В последней строке дан номер вершины, являющейся корнем.

Вершины нумеруются в порядке, в котором они перечислены во входных данных. Гарантируется, что заданная структура является бинарным деревом.

Выходные данные
Выведите «YES», если данное дерево является BST, иначе «NO»

[B. Постройте BST]
----
Входные данные
Вам даны n чисел. Постройте какое-нибудь BST с этими числами.

Выходные данные
Выведите BST в формате из предыдущей задачи.

[C. Простое двоичное дерево поиска]
----
Реализуйте просто двоичное дерево поиска.

Входные данные
Входной файл содержит описание операций с деревом, их количество не превышает 100. В каждой строке находится одна из следующих операций:

insert x — добавить в дерево ключ x. Если ключ x есть в дереве, то ничего делать не надо;
delete x — удалить из дерева ключ x. Если ключа x в дереве нет, то ничего делать не надо;
exists x — если ключ x есть в дереве выведите «true», если нет «false»;
next x — выведите минимальный элемент в дереве, строго больший x, или «none» если такого нет;
prev x — выведите максимальный элемент в дереве, строго меньший x, или «none» если такого нет.
В дерево помещаются и извлекаются только целые числа, не превышающие по модулю 10^9.
Выходные данные
Выведите последовательно результат выполнения всех операций exists, next, prev. Следуйте формату выходного файла из примера.

[D. Сбалансированное двоичное дерево поиска]
----
Реализуйте сбалансированное двоичное дерево поиска.

Входные данные
Входной файл содержит описание операций с деревом, их количество не превышает 10^5. В каждой строке находится одна из следующих операций:

insert x — добавить в дерево ключ x. Если ключ x есть в дереве, то ничего делать не надо;
delete x — удалить из дерева ключ x. Если ключа x в дереве нет, то ничего делать не надо;
exists x — если ключ x есть в дереве выведите «true», если нет «false»;
next x — выведите минимальный элемент в дереве, строго больший x, или «none» если такого нет;
prev x — выведите максимальный элемент в дереве, строго меньший x, или «none» если такого нет.
В дерево помещаются и извлекаются только целые числа, не превышающие по модулю 10^9.
Выходные данные
Выведите последовательно результат выполнения всех операций exists, next, prev. Следуйте формату выходного файла из примера.

[E. Добавление ключей]
----

Вы работаете в компании Макрохард и вас попросили реализовать структуру данных, которая будет хранить множество целых ключей.

Будем считать, что ключи хранятся в бесконечном массиве A, проиндексированном с 1, исходно все его ячейки пусты. Структура данных должна поддерживать следующую операцию:

Insert(L, K), где L — позиция в массиве, а K — некоторое положительное целое число.

Операция должна выполняться следующим образом:

Если ячейка A[L] пуста, присвоить A[L]←K.
Если A[L] непуста, выполнить Insert(L+1, A[L]) и затем присвоить A[L]←K.
По заданным N целым числам L1,L2,…,LN выведите массив после выполнения последовательности операций:

Insert(L1, 1) Insert(L2, 2) … Insert(LN, N)

Входные данные
Первая строка входного файла содержит числа N — количество операций Insert, которое следует выполнить и M — максимальную позицию, которая используется в операциях Insert (1≤N≤131072, 1≤M≤131072).

Следующая строка содержит N целых чисел Li, которые описывают операции Insert, которые следует выполнить (1≤Li≤M).

Выходные данные
Выведите содержимое массива после выполнения всех сделанных операций Insert. 
На первой строке выведите W — номер максимальной непустой ячейки в массиве. Затем выведите W целых чисел — A[1],A[2],…,A[W]. Выводите нули для пустых ячеек.

[F. И снова сумма]
----
Реализуйте структуру данных, которая поддерживает множество S целых чисел, с котором разрешается производить следующие операции:

 — добавить в множество S число i (если он там уже есть, то множество не меняется);
 — вывести сумму всех элементов x из S, которые удовлетворяют неравенству l ≤ x ≤ r.
Входные данные
Исходно множество S пусто. Первая строка входного файла содержит n — количество операций (1 ≤ n ≤ 300 000).Следующие n строк содержат операции. Каждая операция имеет вид либо «+ i», либо «? l r». Операция «? l r» задает запрос .

Если операция «+ i» идет во входном файле в начале или после другой операции «+», то она задает операцию . Если же она идет после запроса «?», и результат этого запроса был y, то выполняется операция .

Во всех запросах и операциях добавления параметры лежат в интервале от 0 до 109.

Выходные данные
Для каждого запроса выведите одно число — ответ на запрос.

[K -й максимум]
----
Напишите программу, реализующую структуру данных, позволяющую добавлять и удалять элементы, а также находить k-й максимум.

Входные данные
Первая строка входного файла содержит натуральное число n — количество команд (n≤100000). Последующие n строк содержат по одной команде каждая. Команда записывается в виде двух чисел ci и ki — тип и аргумент команды соответственно (|ki|≤109). Поддерживаемые команды:

 1: Добавить элемент с ключом ki.
 0: Найти и вывести ki-й максимум.
-1: Удалить элемент с ключом ki.
Гарантируется, что в процессе работы в структуре не требуется хранить элементы с равными ключами или удалять несуществующие элементы. Также гарантируется, что при запросе ki-го максимума, он существует.

Выходные данные
Для каждой команды нулевого типа в выходной файл должна быть выведена строка, содержащая единственное число — ki-й максимум.

[H. Неявный ключ]
----
Научитесь быстро делать две операции с массивом:  add i x — добавить после i-го элемента x (0 ≤ i ≤ n)  del i — удалить i-й элемент (1 ≤ i ≤ n)

Входные данные
На первой строке n0 и m (1 ≤ n0, m ≤ 105) — длина исходного массива и количество запросов. На второй строке n0 целых чисел от 0 до 109 - 1 — исходный массив. Далее m строк, содержащие запросы. Гарантируется, что запросы корректны: например, если просят удалить i-й элемент, он точно есть.

Выходные данные
Выведите конечное состояние массива. На первой строке количество элементов, на второй строке сам массив.

[I. Переместить в начало]
----
Вам дан массив a1 = 1, a2 = 2, ..., an = n и последовальность операций: переместить элементы с li по ri в начало массива. Например, для массива 2, 3, 6, 1, 5, 4, после операции (2, 4) новый порядок будет 3, 6, 1, 2, 5, 4. А после применения операции (3, 4) порядок элементов в массиве будет 1, 2, 3, 6, 5, 4.

Выведите порядок элементов в массиве после выполнения всех операций.

Входные данные
В первой строке входного файла указаны числа n и m (2 ≤ n ≤ 100 000, 1 ≤ m ≤ 100 000) — число элементов в массиве и число операций. Следующие m строк содержат операции в виде двух целых чисел: li и ri (1 ≤ li ≤ ri ≤ n).

Выходные данные
Выведите n целых чисел — порядок элементов в массиве после применения всех операций.

[J. Развороты]
----
Вам дан массив a1 = 1, a2 = 2, ..., an = n и последовательность операций: переставить элементы с li по ri в обратном порядке. Например, для массива 1, 2, 3, 4, 5, после операции (2, 4) новый порядок будет 1, 4, 3, 2, 5. А после применения операции (3, 5) порядок элементов в массиве будет 1, 4, 5, 2, 3.

Выведите порядок элементов в массиве после выполнения всех операций.

Входные данные
В первой строке входного файла указаны числа n и m (2 ≤ n ≤ 100 000, 1 ≤ m ≤ 100 000) — число элементов в массиве и число операций. Следующие m строк содержат операции в виде двух целых чисел: li и ri (1 ≤ li ≤ ri ≤ n).

Выходные данные
Выведите n целых чисел — порядок элементов в массиве после применения всех операций.

[L. Декартово дерево]
----
Вам даны пары чисел (ai, bi). Необходимо построить декартово дерево, такое что i-я вершина имеет ключи (ai, bi), вершины с ключом ai образуют бинарное дерево поиска, а вершины с ключом bi образуют кучу.

Входные данные
В первой строке записано число N — количество пар. Далее следует N (1 ≤ N ≤ 300 000) пар (ai, bi). Для всех пар |ai|, |bi| ≤ 1 000 000. ai ≠ aj и bi ≠ bj для всех i ≠ j.

Выходные данные
Если декартово дерево с таким набором ключей построить возможно, выведите в первой строке «YES», в противном случае выведите «NO». В случае ответа «YES» выведите N строк, каждая из которых должна описывать вершину. Описание вершины состоит из трёх чисел: номера предка, номера левого сына и номера правого сына. Если у вершины отсутствует предок или какой либо из сыновей, выведите на его месте число 0.

Если подходящих деревьев несколько, выведите любое.