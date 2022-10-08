(ns oci.auth-test
  (:require [clojure.test :refer :all] 
            [oci.auth :refer :all]))

(deftest config-not-nil-test
  (testing "config should not be nil or false"
    (is (get-config "DEFAULT"))))