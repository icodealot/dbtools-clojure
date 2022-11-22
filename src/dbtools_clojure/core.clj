(ns dbtools-clojure.core
  (:require [oci.dbtools :as dbtools])
  (:gen-class))

(defn -main
  "List database tools connections in the DEFAULT profile using the
   root compartment (i.e. the tenancy). 
   
   Prerequisites:
   - Have an OCI config file with a DEFAULT profile
   - Setup your OCI API key in the profile represented by `[DEFAULT]`
   - Create at least one Database Tools connection the root compartment
   - Follow the readme of this project to setup your Clojure environment
   - All tests should pass when running `lein test`
   
   you should see something similar to the following when you use
   `lein run` to start this program.
   
   ```
   connection-display-name-example
   ocid1.databasetoolsconnection.oc1.phx.exampleocid
   ```"
  [& args]
  (doseq [conn (dbtools/list-connections)]
    (println (.getDisplayName conn))
    (println (.getId conn))))
