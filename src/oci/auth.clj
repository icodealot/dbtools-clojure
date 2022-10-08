(ns oci.auth
  (:import (com.oracle.bmc ConfigFileReader)))

(defn get-config 
  [profile]
  (if profile
    (ConfigFileReader/parseDefault profile)
    (ConfigFileReader/parseDefault)))