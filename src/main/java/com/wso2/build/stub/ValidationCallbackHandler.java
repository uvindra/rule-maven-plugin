
/**
 * ValidationCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.wso2.build.stub;

    /**
     *  ValidationCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class ValidationCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public ValidationCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public ValidationCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for updateValidation method
            * override this method for handling normal response from updateValidation operation
            */
           public void receiveResultupdateValidation(
                    com.wso2.build.stub.ValidationStub.UpdateValidationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateValidation operation
           */
            public void receiveErrorupdateValidation(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addValidation method
            * override this method for handling normal response from addValidation operation
            */
           public void receiveResultaddValidation(
                    com.wso2.build.stub.ValidationStub.AddValidationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addValidation operation
           */
            public void receiveErroraddValidation(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getValidation method
            * override this method for handling normal response from getValidation operation
            */
           public void receiveResultgetValidation(
                    com.wso2.build.stub.ValidationStub.GetValidationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getValidation operation
           */
            public void receiveErrorgetValidation(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getValidationArtifactIDs method
            * override this method for handling normal response from getValidationArtifactIDs operation
            */
           public void receiveResultgetValidationArtifactIDs(
                    com.wso2.build.stub.ValidationStub.GetValidationArtifactIDsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getValidationArtifactIDs operation
           */
            public void receiveErrorgetValidationArtifactIDs(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getValidationDependencies method
            * override this method for handling normal response from getValidationDependencies operation
            */
           public void receiveResultgetValidationDependencies(
                    com.wso2.build.stub.ValidationStub.GetValidationDependenciesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getValidationDependencies operation
           */
            public void receiveErrorgetValidationDependencies(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for deleteValidation method
            * override this method for handling normal response from deleteValidation operation
            */
           public void receiveResultdeleteValidation(
                    com.wso2.build.stub.ValidationStub.DeleteValidationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteValidation operation
           */
            public void receiveErrordeleteValidation(java.lang.Exception e) {
            }
                


    }
    