(ns oci.auth
  (:import (com.oracle.bmc ConfigFileReader)))

(defn get-config 
  ([]
   ;; no configuration profile specified calling get-config
   ;; i.e. `(get-config)` so try to get the DEFAULT profile
   (get-config "DEFAULT"))
  ([profile]
   ;; configuration profile specified so attempt to use that
   ;; unless it is falsy in which case, look for DEFAULT
  (if profile
    (ConfigFileReader/parseDefault profile)
    (ConfigFileReader/parseDefault))))