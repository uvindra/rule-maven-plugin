
/**
 * GetBuildRuleArtifactIDsServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class GetBuildRuleArtifactIDsServiceGovernanceException extends Exception{

    private static final long serialVersionUID = 1393060593594L;
    
    private BuildRuleStub.GetBuildRuleArtifactIDsServiceGovernanceException faultMessage;

    
        public GetBuildRuleArtifactIDsServiceGovernanceException() {
            super("GetBuildRuleArtifactIDsServiceGovernanceException");
        }

        public GetBuildRuleArtifactIDsServiceGovernanceException(String s) {
           super(s);
        }

        public GetBuildRuleArtifactIDsServiceGovernanceException(String s, Throwable ex) {
          super(s, ex);
        }

        public GetBuildRuleArtifactIDsServiceGovernanceException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(BuildRuleStub.GetBuildRuleArtifactIDsServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public BuildRuleStub.GetBuildRuleArtifactIDsServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    