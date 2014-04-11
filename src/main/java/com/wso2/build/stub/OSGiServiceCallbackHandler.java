
/**
 * OSGiServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.wso2.build.stub;

    /**
     *  OSGiServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class OSGiServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public OSGiServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public OSGiServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for deleteOSGiService method
            * override this method for handling normal response from deleteOSGiService operation
            */
           public void receiveResultdeleteOSGiService(
                    com.wso2.build.stub.OSGiServiceStub.DeleteOSGiServiceResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteOSGiService operation
           */
            public void receiveErrordeleteOSGiService(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getOSGiServiceDependencies method
            * override this method for handling normal response from getOSGiServiceDependencies operation
            */
           public void receiveResultgetOSGiServiceDependencies(
                    com.wso2.build.stub.OSGiServiceStub.GetOSGiServiceDependenciesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getOSGiServiceDependencies operation
           */
            public void receiveErrorgetOSGiServiceDependencies(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getOSGiServiceArtifactIDs method
            * override this method for handling normal response from getOSGiServiceArtifactIDs operation
            */
           public void receiveResultgetOSGiServiceArtifactIDs(
                    com.wso2.build.stub.OSGiServiceStub.GetOSGiServiceArtifactIDsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getOSGiServiceArtifactIDs operation
           */
            public void receiveErrorgetOSGiServiceArtifactIDs(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addOSGiService method
            * override this method for handling normal response from addOSGiService operation
            */
           public void receiveResultaddOSGiService(
                    com.wso2.build.stub.OSGiServiceStub.AddOSGiServiceResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addOSGiService operation
           */
            public void receiveErroraddOSGiService(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getOSGiService method
            * override this method for handling normal response from getOSGiService operation
            */
           public void receiveResultgetOSGiService(
                    com.wso2.build.stub.OSGiServiceStub.GetOSGiServiceResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getOSGiService operation
           */
            public void receiveErrorgetOSGiService(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for updateOSGiService method
            * override this method for handling normal response from updateOSGiService operation
            */
           public void receiveResultupdateOSGiService(
                    com.wso2.build.stub.OSGiServiceStub.UpdateOSGiServiceResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateOSGiService operation
           */
            public void receiveErrorupdateOSGiService(Exception e) {
            }
                


    }
    