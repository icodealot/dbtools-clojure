(ns oci.dbtools
  (:require [oci.auth :as auth])
  (:import (com.oracle.bmc.databasetools DatabaseToolsClient)
           (com.oracle.bmc.databasetools.requests ListDatabaseToolsConnectionsRequest)))

;; Sign OCI requests using the ~/.oci/config profile provided.
(defn get-client 
  "Returns a `DatabaseToolsClient` object using a OCI authentication
   details provider. In this example we are using the provider that 
   assumes there exists a ~/.oci/config file or an environment that
   resolves to the location of this config accordingly. For more
   information see: `ConfigFileAuthenticationDetailsProvider`."
  ([] 
   (get-client auth/default))
  ([profile]
   (new DatabaseToolsClient (auth/get-provider profile))))

(defn list-connections-request
  ([]
   (list-connections-request (auth/root-compartment)))
  ([compartment] 
   (.build
    (.compartmentId 
     (ListDatabaseToolsConnectionsRequest/builder) 
     compartment))))

;; DatabaseToolsClient/listDatabaseToolsConnections
;; https://docs.oracle.com/en-us/iaas/tools/java/latest/com/oracle/bmc/databasetools/DatabaseTools.html
;; https://docs.oracle.com/en-us/iaas/tools/java-sdk-examples/latest/databasetools/ListDatabaseToolsConnectionsExample.java.html
(defn list-connections-response
  ([request]
   ;; no client specified so attempt to use the DEFAULT
   (list-connections-response (get-client) request))
  ([client request] 
   (.listDatabaseToolsConnections client request)))

(defn list-connections
  "Lists connections in an OCI compartment using the database tools client.
   - Passing no arguments will search the root compartment of the DEFAULT
   profile defined in ~/.oci/config.
   - Passing `compartment` will search for connections in that compartment
   using the DEFAULT profile.
   - Passing `client` `compartment` will search for connections in that
   compartment using the specified `DatabaseToolsClient`.
   
   List requests depend on the user having appropriate IAM policies applied 
   that authorize requests for Database Tools resources in the specified 
   compartment. For example:
   - `allow <group> to read database-tools-connections in <compartment>`"
  ([]
   (.getItems
    (.getDatabaseToolsConnectionCollection
     (list-connections-response (list-connections-request)))))
  ([compartment]
   (.getItems 
    (.getDatabaseToolsConnectionCollection 
     (list-connections-response (list-connections-request compartment)))))
  ([client compartment]
   (.getItems
    (.getDatabaseToolsConnectionCollection
     (list-connections-response client (list-connections-request compartment))))))