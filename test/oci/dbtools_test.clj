(ns oci.dbtools-test
  (:require [clojure.test :refer [deftest is testing]] 
            [oci.dbtools :refer :all]))


(deftest client-not-nil-test
  (testing "client should not be nil or false"
    (is (get-client))))


(deftest list-connections-request-not-nil-test
  (testing "list connections request should not be nil"
    (is (list-connections-request "ocid1.compartment.oc1..example"))))


(deftest list-connections-response-not-nil-test
  ;; This test assumes that the DEFAULT profile has IAM policies
  ;; to list Database Tools connections in the root compartment.
  ;; It does not matter if a connection actually exists there.
  (testing "list connections response should not be nil" 
    (is (list-connections-response (list-connections-request)))))


(deftest list-connections-succeeds-test
  ;; This test assumes that the DEFAULT profile has IAM policies
  ;; to list Database Tools connections in the root compartment.
  ;; It does not matter if a connection actually exists there.
  (testing, "list connections should not fail"
    (is (list-connections))))