:- load_library('alice.tuprolog.lib.DCGLibrary').

operation_sign(op_add) --> ['+'].
operation_sign(op_subtract) --> ['-'].
operation_sign(op_multiply) --> ['*'].
operation_sign(op_divide) --> ['/'].
operation_sign(op_negate) --> ['n', 'e', 'g', 'a', 't', 'e'].

operation(op_add, First, Second, R) :- R is First + Second.
operation(op_subtract, First, Second, R) :- R is First - Second.
operation(op_multiply, First, Second, R) :- R is First * Second.
operation(op_divide, First, Second, R) :- R is First / Second.
operation(op_negate, X, R) :- R is X * -1.

evaluate(const(R), _, R).
evaluate(variable(Name), Args, R) :-
    atom_chars(Name, [Name1 | _]),
    find_var(Name1, Args, R).

evaluate(operation(Op, First, Second), Args, R) :-
    evaluate(First, Args, F1),
    evaluate(Second, Args, S1),
    operation(Op, F1, S1, R).

evaluate(operation(Op, X), Args, R) :-
    evaluate(X, Args, X1),
    operation(Op, X1, R).

find_var(Name, [(Name, R) | _], R).
find_var(Name, [_ | T], R) :- find_var(Name, T, R).

nonvar(V, _) :- var(V).
nonvar(V, T) :- nonvar(V), call(T).

check_minus(Chars) --> digits_p(Chars).
check_minus(['-' | T]) --> ['-'], digits_p(T).

digits_p([]) --> [].
digits_p([H | T]) -->
  { member(H, ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.']) },
  [H],
  digits_p(T).

one_var_p(Var) --> { member(Var, ['x', 'y', 'z', 'X', 'Y', 'Z']) }, [Var].
var_p([H]) --> one_var_p(H).
var_p([H | T]) --> one_var_p(H), var_p(T).

expr_p(const(Value)) -->
    { nonvar(Value, number_chars(Value, Chars)) },
    skip_ws, {write(Chars)},
    digits_p(Chars),
    skip_ws, {write(Chars)},
    { number_chars(Value, Chars) }.

expr_p(variable(Var)) -->
    { nonvar(Var, atom_chars(Var, Chars)) },
    var_p(Chars),
    { atom_chars(Var, Chars) }.

expr_p(operation(Op, First, Second)) -->
    { check_ws(Op, WhiteSpace) },
    ['('], expr_p(First), WhiteSpace, operation_sign(Op), WhiteSpace, expr_p(Second), [')'].

expr_p(operation(Op, X)) --> operation_sign(Op), ['('], expr_p(X), [')'].

check_ws(Op, R) :- var(Op), !, R = [].
check_ws(_, R) :- R = [' '].

infix_str(Expr, Atom) :- ground(Expr), phrase(expr_p(Expr), Chars), atom_chars(Atom, Chars).
infix_str(Expr, Atom) :- atom(Atom), atom_chars(Atom, Chars), phrase(expr_p(Expr), C1).

ws --> [' '].
skip_ws --> [].
skip_ws --> ws, skip_ws.