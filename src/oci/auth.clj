(ns oci.auth
  (:import (com.oracle.bmc ConfigFileReader)
           (com.oracle.bmc.auth ConfigFileAuthenticationDetailsProvider)))

(def default "DEFAULT")

(defn get-config 
  ([]
   ;; no configuration profile specified calling get-config
   ;; i.e. `(get-config)` so try to get the DEFAULT profile
   (get-config default))
  ([profile]
   ;; configuration profile specified so attempt to use that
   ;; unless it is falsy in which case, look for DEFAULT
   ;; ConfigFileReader.parseDefault also works with no param
   ;; but adding default here for symmetry
  (if profile
    (ConfigFileReader/parseDefault profile)
    (ConfigFileReader/parseDefault default))))

(defn get-provider
  ([]
   ;; no profile specified so calling get-provider with DEFAULT
   (get-provider default))
  ([profile]
   (if profile
     (new ConfigFileAuthenticationDetailsProvider profile)
     (new ConfigFileAuthenticationDetailsProvider default))))