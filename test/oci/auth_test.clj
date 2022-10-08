(ns oci.auth-test
  (:require [clojure.test :refer :all] 
            [oci.auth :refer :all]))

(def profile "DEFAULT")

(deftest config-not-nil-test
  (testing "config should not be nil or false"
    (is (get-config "DEFAULT"))
    (is (get-config))))

(deftest implicit-default-matches-default-test
  (testing "implicit default config should equal explicit" 
    (is (= (.get (get-config) "user")
           (.get (get-config profile) "user")))
    (is (= (.get (get-config) "fingerprint")
           (.get (get-config profile) "fingerprint")))
    (is (= (.get (get-config) "tenancy")
           (.get (get-config profile) "tenancy")))
    (is (= (.get (get-config) "region")
           (.get (get-config profile) "region")))
    (is (= (.get (get-config) "key_file")
           (.get (get-config profile) "key_file")))))