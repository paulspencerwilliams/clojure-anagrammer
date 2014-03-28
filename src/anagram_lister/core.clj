(ns anagram-lister.core)

(defn order-letters [word]
  (apply str (sort(seq (char-array word )))))

(defn list-anagrams []
  (with-open [rdr (clojure.java.io/reader "/Users/will/src/anagram-lister/resources/wordlist.txt")]
    (loop [remaining-lines (line-seq rdr) anagram-sets {}]
      (if (seq remaining-lines) 
        (let [current-word (first remaining-lines)
              ordered-letters (order-letters current-word)]
          (if (contains? anagram-sets ordered-letters)
            (let [current-anagram-set (get anagram-sets ordered-letters)]
              (recur (rest remaining-lines)
                     (assoc anagram-sets 
                            [ordered-letters] 
                            (conj current-anagram-set current-word)))) 
            (recur (rest remaining-lines)
                   (assoc anagram-sets
                          ordered-letters
                          [current-word]))))
        (clojure.string/join "\n" 
                             (map (fn [a-set] (clojure.string/join " " a-set))
                                  (filter (fn [a-set] (> (count a-set) 1))
                                          (map anagram-sets 
                                               (keys anagram-sets)))))))))
