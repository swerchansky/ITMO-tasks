;; -------------------------------------------- HW10 --------------------------------------------

(def constant constantly)


(defn variable
  [var]
  (fn [nums] (nums var)))


(defn binaryOperations
  [fun]
  (fn [& args] (fn [nums] (apply fun (mapv (fn [arg] (arg nums)) args)))))


(def _divide
  (fn
    ([f] (/ (double 1) (double f)))
    ([f & r] (reduce (fn [x y] (/ (double x) (double y))) f r))))


(def add (binaryOperations +))
(def subtract (binaryOperations -))
(def multiply (binaryOperations *))
(def divide (binaryOperations _divide))
(def negate subtract)

(def exp (binaryOperations (fn [x] (Math/exp x))))
(def ln (binaryOperations (fn [x] (Math/log x))))


(def functionalMap
  {'+      add,
   '-      subtract,
   '*      multiply,
   '/      divide,
   'negate negate,
   'exp    exp,
   'ln     ln})


(defn parse
  [arg parseMap constFun varFun]
  (cond
    (number? arg) (constFun arg)
    (list? arg) (apply (parseMap (first arg)) (map (fn [x] (parse x parseMap constFun varFun)) (rest arg)))
    (symbol? arg) (varFun (str arg))))


(defn parseFunction
  [expr]
  (parse (read-string expr) functionalMap constant variable))


;; -------------------------------------------- HW11 --------------------------------------------

;; -------------------------------------------- Lecture Code --------------------------------------------

(defn proto-get
  "Returns object property respecting the prototype chain"
  ([obj key] (proto-get obj key nil))
  ([obj key default]
   (cond
     (contains? obj key) (obj key)
     (contains? obj :prototype) (proto-get (obj :prototype) key default)
     :else default)))


(defn proto-call
  "Calls object method respecting the prototype chain"
  [this key & args]
  (apply (proto-get this key) this args))


(defn field
  [key]
  "Creates field"
  (fn
    ([this] (proto-get this key))
    ([this def] (proto-get this key def))))


(defn method
  "Creates method"
  [key]
  (fn [this & args] (apply proto-call this key args)))


(defn constructor
  "Defines constructor"
  [ctor prototype]
  (fn [& args] (apply ctor {:prototype prototype} args)))


;; -------------------------------------------- End of lecture Code --------------------------------------------

(def evaluate (method :evaluate))
(def toString (method :toString))
(def toStringSuffix (method :toStringSuffix))
(def diff (method :diff))
(def _x (field :x))
(def _var (field :var))
(def _full (field :full))
(def _op (field :op))
(def _symb (field :symb))
(def _args (field :args))
(def _myDiff (field :myDiff))


(defn defaultConstructor
  [evaluate toString diff toStringSuffix]
  {:evaluate       evaluate
   :toString       toString
   :diff           diff
   :toStringSuffix toStringSuffix})


(def Constant
  (constructor
    (fn [this x]
      (assoc this :x x))
    (defaultConstructor
      (fn [this x] (_x this))
      (fn [this] (str (_x this)))
      (fn [this x] (Constant 0))
      toString)))


(def Variable
  (constructor
    (fn [this var]
      (assoc this
             :var (str (Character/toLowerCase (first var)))
             :full (str var)))
    (defaultConstructor
      (fn [this vars] (get vars (_var this)))
      (fn [this] (_full this))
      (fn [this diffVar] (if (= diffVar (_var this)) (Constant 1) (Constant 0)))
      toString)))


(defn createOperation
  [symb op myDiff]
  (constructor
    (fn [this & args] (assoc this :args args))
    ((constructor
       (fn [this symb op myDiff]
         (assoc this
                :symb symb
                :op op
                :myDiff myDiff))
       (defaultConstructor
         (fn [this vars] (apply (_op this) (mapv (fn [x] (evaluate x vars)) (_args this))))
         (fn [this]
           (if (= (count (_args this)) 0)
             (str "(" (_symb this) " )")
             (str "(" (_symb this) (reduce str (mapv (fn [x] (str " " x)) (mapv toString (_args this)))) ")")))
         (fn [this diffVar] ((_myDiff this) (_args this) (mapv (fn [x] (diff x diffVar)) (_args this)) diffVar))
         (fn [this]
           (if (= (count (_args this)) 0)
             (str "(" (_symb this) " )")
             (str "(" (reduce str (mapv (fn [x] (str x " ")) (mapv toStringSuffix (_args this)))) (_symb this) ")")))))
     symb op myDiff)))


(def Add
  (createOperation
    "+"
    +
    (fn [args args' diffVar] (apply Add args'))))


(def Subtract
  (createOperation
    "-"
    -
    (fn [args args' diffVar] (apply Subtract args'))))


(def Negate
  (createOperation
    "negate"
    -
    (fn [args args' diffVar] (apply Negate args'))))


(def Multiply
  (createOperation
    "*"
    *
    (fn [args args' diffVar]
      (second (reduce
                (fn [[x x'] [y y']] [(Multiply x y) (Add (Multiply x' y) (Multiply x y'))])
                (mapv vector args args'))))))


(def Divide
  (createOperation
    "/"
    _divide
    (fn [args args' diffVar]
      (if (= (count args) 1)
        (Divide
          (Subtract
            (Multiply (Constant 0) (first args))
            (Multiply (Constant 1) (first args')))
          (Multiply (first args) (first args)))
        (second (reduce
                  (fn [[x x'] [y y']]
                    [(Divide x y)
                     (Divide (Subtract (Multiply x' y) (Multiply x y')) (Multiply y y))])
                  (mapv vector args args')))))))


(def Mean
  (createOperation
    "mean"
    (fn [& args]
      (/ (reduce + args) (count args)))
    (fn [args args' diffVar]
      (apply Mean args'))))


(def Varn
  (createOperation
    "varn"
    (fn [& args]
      (-
        (/ (reduce + (mapv (fn [x] (* x x)) args)) (count args))
        (Math/pow (/ (reduce + args) (count args)) 2)))
    (fn [args args' diffVar]
      (Subtract
        (Divide
          (apply Add (mapv (fn [x] (diff (Multiply x x) diffVar)) args))
          (Constant (count args)))
        (diff (Multiply (apply Mean args) (apply Mean args)) diffVar)))))


(def Exp
  (createOperation
    "exp"
    (fn [args]
      (Math/exp args))
    (fn [args args' diffVar]
      (apply Multiply (apply Exp args) args'))))


(def Ln
  (createOperation
    "ln"
    (fn [args]
      (Math/log args))
    (fn [args args' diffVar]
      (apply Multiply (apply Divide (Constant 1) args) args'))))


(def objectMap
  {'+      Add,
   '-      Subtract,
   '*      Multiply,
   '/      Divide,
   'negate Negate,
   'mean   Mean,
   'varn   Varn,
   'exp    Exp,
   'ln     Ln})


(defn parseObject
  [expr]
  (parse (read-string expr) objectMap Constant Variable))


;; (println (toStringSuffix (Add (Multiply (Constant 1) (Constant 4)) (Constant 2))))


;; -------------------------------------------- HW12 --------------------------------------------


;; -------------------------------------------- Lecture Code --------------------------------------------

(defn -return [value tail] {:value value :tail tail})
(def -valid? boolean)
(def -value :value)
(def -tail :tail)


(defn _empty
  [value]
  (partial -return value))


(defn _char
  [p]
  (fn [[c & cs]]
    (if (and c (p c)) (-return c cs))))


(defn _map
  [f]
  (fn [result]
    (if (-valid? result)
      (-return (f (-value result)) (-tail result)))))


(defn _combine
  [f a b]
  (fn [str]
    (let [ar ((force a) str)]
      (if (-valid? ar)
        ((_map (partial f (-value ar)))
         ((force b) (-tail ar)))))))


(defn _either
  [a b]
  (fn [str]
    (let [ar ((force a) str)]
      (if (-valid? ar) ar ((force b) str)))))


(defn _parser
  [p]
  (fn [input]
    (-value ((_combine (fn [v _] v) p (_char #{\u0001})) (str input \u0001)))))


(mapv (_parser (_combine str (_char #{\a \b}) (_char #{\x}))) ["ax" "ax~" "bx" "bx~" "" "a" "x" "xa"])


(defn +char
  [chars]
  (_char (set chars)))


(defn +char-not
  [chars]
  (_char (comp not (set chars))))


(defn +map
  [f parser]
  (comp (_map f) parser))


(def +ignore (partial +map (constantly 'ignore)))


(defn iconj
  [coll value]
  (if (= value 'ignore) coll (conj coll value)))


(defn +seq
  [& ps]
  (reduce (partial _combine iconj) (_empty []) ps))


(defn +seqf
  [f & ps]
  (+map (partial apply f) (apply +seq ps)))


(defn +seqn
  [n & ps]
  (apply +seqf (fn [& vs] (nth vs n)) ps))


(defn +or
  [p & ps]
  (reduce (partial _either) p ps))

; :NOTE: you should've used the library
(defn +opt
  [p]
  (+or p (_empty ())))


(defn +star
  [p]
  (letfn [(rec [] (+or (+seqf cons p (delay (rec))) (_empty ())))] (rec)))


(defn +plus
  [p]
  (+seqf cons p (+star p)))


(defn +str
  [p]
  (+map (partial apply str) p))


(def +parser _parser)


;; -------------------------------------------- End of lecture Code --------------------------------------------

(def *all-chars (mapv char (range 32 127)))

(def *ws (+ignore (+star (+char (apply str (filter #(Character/isWhitespace %) *all-chars))))))
(def *digits (_char #(Character/isDigit %))) ; :NOTE:/2 use simpler combinators


(defn word
  [word]
  (apply +seqf (constantly word) (mapv #(+char (str %)) word)))


(def *number
  (+map
    #(Constant (Double/parseDouble %))
    (+str (+seqf
            #(flatten %&)
            (+opt (+char "-")) (+plus *digits) (+opt (+char ".")) (+opt (+plus *digits))))))


(def *variable
  (+map
    #(Variable (str %))
    (+str (+plus (+char "XYZxyz")))))


(def parseMap
  {"+"      Add,
   "-"      Subtract,
   "*"      Multiply,
   "/"      Divide,
   "negate" Negate})


(def operation (+or (+char "+-/*") (word "negate")))


(def parseObjectSuffix
  (letfn [(*operation
            []
            (+map
              #(apply (parseMap (str (last %))) (butlast %))
              (+seqn 1 *ws (+char "(") (*args) (+char ")") *ws)))
          (*args
            []
            (+seqf #(flatten %&)
                   *ws
                   (delay (*argument))
                   *ws
                   (+opt (delay (*argument)))
                   *ws
                   operation
                   *ws))
          (*argument [] (+or *number *variable (*operation)))]
    (+parser (+seqn 0 *ws (*argument) *ws))))


;; (println (evaluate (parseObjectSuffix "xyYZXz") {"x" 2 "y" 0 "z" 15}))

