;; checkers
(defn is-vector?
  [v]
  (and ; :NOTE: not quite up to the spec
    (every? number? v)
    (not (number? v))))


(defn is-matrix?
  [m]
  (and
    (not (empty? (filter vector? m)))
    (not (number? m))))

;; vectors
(defn coordinates
  [op]
  (fn fun
    [& args]
    {:pre [(and
             (every? vector? args)
             (or
               (every? empty? args)
               (apply = (mapv (fn [x] (mapv number? x)) args))))]}
    (apply mapv op args)))


(def v+ (coordinates +))
(def v- (coordinates -))
(def v* (coordinates *))
(def vd (coordinates /))


(defn scalar
  [& b]
  (apply + (apply v* b)))


(defn v*s
  [v & s]
  {:pre [(every? number? s)]}
  (mapv (partial * (reduce * s)) v))


;; matrix
(def m+ (coordinates v+))
(def m- (coordinates v-))
(def m* (coordinates v*))
(def md (coordinates vd))


(defn m*s
  [m & s]
  {:pre [(and
           (is-matrix? m)
           (every? number? s))]}
  (mapv (fn [v] (v*s v (reduce * s))) m))


(defn m*v
  [m v]
  {:pre [(is-matrix? m)]}
  (mapv (fn [a] (scalar a v)) m))


(defn transpose
  [m]
  (apply mapv vector m))


(defn m*m
  ([& matrix]
   {:pre [(if (<= (count matrix) 1)
            (or
              (every? vector? matrix)
              (every? empty? matrix))

            (and
              (every? vector? matrix)
              (if (is-vector? (second matrix))
                (or (every? empty? matrix) (== (count (first matrix)) (count (second matrix))))
                (or (every? empty? matrix) (== (count (first (first matrix))) (count (second matrix)))))))]}
   (reduce
     (fn [a b]
       (mapv (fn [v] (m*v (transpose b) v)) a))
     matrix)))


(defn vect
  [& v]
  {:pre [(and
           (every? vector? v)
           (or
             (every? empty? v)
             (apply = (mapv (fn [x] (mapv number? x)) v))))]}
  (reduce
    (fn [a b]
      (let [[x y z] a matrix [[0 (- z) y] [z 0 (- x)] [(- y) x 0]]]
        (m*v matrix b)))
    v))


(def c+ (coordinates m+))
(def c- (coordinates m-))
(def c* (coordinates m*))
(def cd (coordinates md))
