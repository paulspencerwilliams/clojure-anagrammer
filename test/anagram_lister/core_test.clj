(ns anagram-lister.core-test
  (:use [midje.sweet]
            [anagram-lister.core :refer :all]))

(facts "It outputs lots of anagrams"
      (fact "It outputs lots of anagrams"
            (let [results (list-anagrams)]
              results => (contains "kinship pinkish"))))
