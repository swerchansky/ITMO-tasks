%node(Left, Right, Key, Val, H)

height(nil, 0) :- !.
height(node(_, _, _, _, H), H) :- !.

max(A, B, R) :- (A > B -> R = A; R = B).

get_balance(node(Left, Right, _, _, _), R) :-
    height(Left, LH),
    height(Right, RH),
    R is LH - RH.

update_h(node(Left, Right, Key, Val, _), node(Left, Right, Key, Val, NH)) :-
    height(Left, LH),
    height(Right, RH),
    max(LH, RH, R),
    NH is 1 + R.

right_rotate(node(node(LeftL, RightL, KeyL, ValL, _), Right, Key, Val, _), R) :-
    update_h(node(RightL, Right, Key, Val, _), TmpR),
    update_h(node(LeftL, TmpR, KeyL, ValL, _), R).

left_rotate(node(Left, node(LeftR, RightR, KeyR, ValR, _), Key, Val, _), R) :-
    update_h(node(Left, LeftR, Key, Val, _), TmpL),
    update_h(node(TmpL, RightR, KeyR, ValR, _), R).

rotate(node(Left, Right, Key, Val, H), R) :-
    get_balance(node(Left, Right, _, _, _), Balance),
    Balance > 1,
    get_balance(Left, LBalance),
    (LBalance >= 1 ->
    right_rotate(node(Left, Right, Key, Val, H), R);
    left_rotate(Left, TmpL),
    right_rotate(node(TmpL, Right, Key, Val, H), R)), !.

rotate(node(Left, Right, Key, Val, H), R) :-
    get_balance(node(Left, Right, _, _, _), Balance),
    Balance < -1,
    get_balance(Right, RBalance),
    (RBalance =< 1 ->
    left_rotate(node(Left, Right, Key, Val, H), R);
    right_rotate(Right, TmpR),
    left_rotate(node(Left, TmpR, Key, Val, H), R)), !.

rotate(Node, Node) :- !.

map_build([], nil) :- !.

map_build([(K, V) | T], TreeMap) :-
    map_build(T, Root),
    map_put(Root, K, V, TmpMap),
    update_h(TmpMap, Tmp),
    rotate(Tmp, TreeMap).

map_put(nil, K, V, R) :- R = node(nil, nil, K, V, 1).

map_put(node(Left, Right, Key, _, H), K, V, R) :-
    Key = K, !,
    R = node(Left, Right, Key, V, H).

map_put(node(Left, Right, Key, Val, H), K, V, R) :-
    Key > K, !,
    map_put(Left, K, V, Ans),
    update_h(node(Ans, Right, Key, Val, H), Tmp),
    rotate(Tmp, R).

map_put(node(Left, Right, Key, Val, H), K, V, R) :-
    Key =< K, !,
    map_put(Right, K, V, Ans),
    update_h(node(Left, Ans, Key, Val, H), Tmp),
    rotate(Tmp, R).

map_get(node(_, _, Key, Val, _), K, V) :-
    Key = K, !,
    Val = V.

map_get(node(_, Right, Key, _, _), K, V) :-
    Key < K, !,
    map_get(Right, K, V).

map_get(node(Left, _, Key, _, _), K, V) :-
    Key > K, !,
    map_get(Left, K, V).

map_remove(nil, _, nil) :- !.

map_remove(node(Left, Right, Key, Val, H), K, R) :-
    K < Key, !,
    map_remove(Left, K, Res),
	update_h(node(Res, Right, Key, Val, H), Tmp),
    rotate(Tmp, R).

map_remove(node(Left, Right, Key, Val, H), K, R) :-
    K > Key, !,
    map_remove(Right, K, Res),
	update_h(node(Left, Res, Key, Val, H), Tmp),
    rotate(Tmp, R).

map_remove(node(nil, nil, Key, _, _), K, nil) :- Key = K, !.
map_remove(node(Left, nil, Key, _, _), K, Left) :- Key = K, !.
map_remove(node(nil, Right, Key, _, _), K, Right) :- Key = K, !.
map_remove(node(Left, Right, Key, _, H), K, R) :-
    Key = K, !,
    find_min(Right, node(_, _, KeyTmp, ValTmp, _)),
    map_remove(Right, KeyTmp, RTmp),
    update_h(node(Left, RTmp, KeyTmp, ValTmp, H), Tmp),
    rotate(Tmp, R).

find_min(node(Left, _, _, _, _), R) :-
    \+ Left = nil, !,
    find_min(Left, R).

find_min(Node, Node) :- !.

find_max(node(_, Right, _, _, _), R) :-
    \+ Right = nil, !,
    find_min(Right, R).

find_max(Node, Node) :- !.

map_floorKey(node(Left, Right, Key, _, _), K, R) :- map_floorKey(node(Left, Right, Key, _, _), K, R, nil).
map_floorKey(nil, _, Key, node(_, _, Key, _, _)) :- !.
map_floorKey(node(_, _, Key, _, _), Key, Key, _) :- !.

map_floorKey(node(Left, Right, Key, _, _), K, R, Last) :-
	(Key < K ->
    map_floorKey(Right, K, R, node(Left, Right, Key, _, _));
    map_floorKey(Left, K, R, Last)).


map_replace(nil, _, _, nil) :- !.

map_replace(node(Left, Right, Key, _, H), K, V, R) :-
    Key = K, !,
    R = node(Left, Right, Key, V, H).

map_replace(node(Left, Right, Key, Val, H), K, V, R) :-
    Key > K, !,
    map_replace(Left, K, V, R1),
    R = node(R1, Right, Key, Val, H).

map_replace(node(Left, Right, Key, Val, H), K, V, R) :-
    Key < K, !,
    map_replace(Right, K, V, R1),
    R = node(Left, R1, Key, Val, H).