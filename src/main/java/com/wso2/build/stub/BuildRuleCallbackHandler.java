
/**
 * BuildRuleCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.wso2.build.stub;

    /**
     *  BuildRuleCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class BuildRuleCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public BuildRuleCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public BuildRuleCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for updateBuildRule method
            * override this method for handling normal response from updateBuildRule operation
            */
           public void receiveResultupdateBuildRule(
                    com.wso2.build.stub.BuildRuleStub.UpdateBuildRuleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateBuildRule operation
           */
            public void receiveErrorupdateBuildRule(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addBuildRule method
            * override this method for handling normal response from addBuildRule operation
            */
           public void receiveResultaddBuildRule(
                    com.wso2.build.stub.BuildRuleStub.AddBuildRuleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addBuildRule operation
           */
            public void receiveErroraddBuildRule(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for deleteBuildRule method
            * override this method for handling normal response from deleteBuildRule operation
            */
           public void receiveResultdeleteBuildRule(
                    com.wso2.build.stub.BuildRuleStub.DeleteBuildRuleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteBuildRule operation
           */
            public void receiveErrordeleteBuildRule(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getBuildRuleDependencies method
            * override this method for handling normal response from getBuildRuleDependencies operation
            */
           public void receiveResultgetBuildRuleDependencies(
                    com.wso2.build.stub.BuildRuleStub.GetBuildRuleDependenciesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getBuildRuleDependencies operation
           */
            public void receiveErrorgetBuildRuleDependencies(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getBuildRuleArtifactIDs method
            * override this method for handling normal response from getBuildRuleArtifactIDs operation
            */
           public void receiveResultgetBuildRuleArtifactIDs(
                    com.wso2.build.stub.BuildRuleStub.GetBuildRuleArtifactIDsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getBuildRuleArtifactIDs operation
           */
            public void receiveErrorgetBuildRuleArtifactIDs(Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getBuildRule method
            * override this method for handling normal response from getBuildRule operation
            */
           public void receiveResultgetBuildRule(
                    com.wso2.build.stub.BuildRuleStub.GetBuildRuleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getBuildRule operation
           */
            public void receiveErrorgetBuildRule(Exception e) {
            }
                


    }
    