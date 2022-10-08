(ns oci.auth-test
  (:require [clojure.test :refer :all] 
            [oci.auth :refer :all]))

(deftest config-not-nil-test
  (testing "config should not be nil or false"
    (is (get-config default))
    (is (get-config))))

(deftest implicit-default-matches-explicit-test
  (testing "implicit default config should equal explicit" 
    (is (= (.get (get-config) "user")
           (.get (get-config default) "user")))
    (is (= (.get (get-config) "fingerprint")
           (.get (get-config default) "fingerprint")))
    (is (= (.get (get-config) "tenancy")
           (.get (get-config default) "tenancy")))))

(deftest provider-not-nil-test
  (testing "provider should not be nil or false"
    (is (get-provider default))
    (is (get-provider))))

(deftest provider-region-matches-config-test
  (testing "provider should match default config"
    (is (= (.get (get-config) "user")
           (.getUserId (get-provider))))
    (is (= (.get (get-config) "fingerprint")
           (.getFingerprint (get-provider))))
    (is (= (.get (get-config) "tenancy")
           (.getTenantId (get-provider))))))