init(N) :-
	assert(not_primes(0)),
	assert(not_primes(1)),
	\+ for_i(2, N).

for_i(I, N) :-
	I * I =< N,
	J is I * I,
	\+ for_j(J, I, N),
	I1 is I + 1,
	check_prime(I1, R),
	for_i(R, N).

for_j(J, I, N) :-
	J =< N,
	(not_primes(J) -> !; assert(not_primes(J))),
	J1 is J + I,
	for_j(J1, I, N).

check_prime(I, R) :-
	(\+ not_primes(I) ->
	R is I;
	R is I + 1).

prime(N) :- \+ not_primes(N).
composite(N) :- N =\= 0, N =\= 1, not_primes(N).

prime_divisors(1, []) :- !.
prime_divisors(N, [N]) :- prime(N), !.

prime_divisors(N, [H | T]) :-
	number(N), !,
	find_divisor(N, 2, N1, H),
	prime_divisors(N1, T),
	check_sorted([H | T]).

prime_divisors(N, [H | T]) :-
	N1 is 1,
	mult(N1, R, [H | T]),
	check_sorted([H | T]),
	N is R.


find_divisor(N, DIV, R, H) :-
	N > 1, DIV * DIV =< N,
	(0 =:= (N mod DIV) ->
	R is N // DIV, H is DIV;
	DIV1 is DIV + 1,
	find_divisor(N, DIV1, R, H)).

mult(N, R, [H | T]) :-
	N1 is N * H,
	mult(N1, R, T).

mult(N, R, [H]) :- R is N * H, !.

check_sorted([H]) :- !.
check_sorted([H, H1 | T]) :-
	number(H1),
	H =< H1,
	check_sorted([H1 | T]), !.
check_sorted([H, T]) :- H =< T, !.

lcm(A, B, R) :-
	prime_divisors(A, PA),
	prime_divisors(B, PB),
	(PA = [] -> NEW_PA = [1]; NEW_PA = PA),
	(PB = [] -> NEW_PB = [1]; NEW_PB = PB),
	concat(NEW_PA, NEW_PB, R1),
	N1 is 1,
	mult(N1, R, R1).

concat([], T, T) :- !.
concat(T, [], T) :- !.
concat([H | T], [H | T1], [H | T2]) :- !, concat(T, T1, T2).
concat([H | T], [H1 | T1], [H | T2]) :- H < H1, !, concat(T, [H1 | T1], T2).
concat([H1 | T], [H | T1], [H | T2]) :- H < H1, !, concat([H1 | T], T1, T2).

unique_prime_divisors(1, []) :- !.
unique_prime_divisors(N, Divisors) :-
	prime_divisors(N, [H | T]),
	find_unique([H | T], Divisors).


find_unique([], []) :- !.
find_unique([H], [H]) :- !.
find_unique([H, H1 | T], Divisors) :-
	(H =:= H1 ->
	find_unique([H1 | T], R), Divisors = R);
	find_unique([H1 | T], R), Divisors = [H | R].

nth_prime(N, P) :- found_prime(N, P, 2, 1).

found_prime(N, P, PR, CNT) :-
	number(N), !,
    	(N =:= CNT ->
    	P = PR;
    	next_prime(PR, R),
    	CNT1 is CNT + 1,
    	found_prime(N, P, R, CNT1)).

found_prime(N, P, PR, CNT) :-
    	(P =:= PR ->
    	N = CNT;
    	next_prime(PR, R),
    	CNT1 is CNT + 1,
    	found_prime(N, P, R, CNT1)).

next_prime(N, R) :-
    R1 is N + 1,
    (prime(R1) ->
    R = R1;
    next_prime(R1, R)).
