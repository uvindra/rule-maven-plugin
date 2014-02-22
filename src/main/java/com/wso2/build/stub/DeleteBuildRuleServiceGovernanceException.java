
/**
 * DeleteBuildRuleServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class DeleteBuildRuleServiceGovernanceException extends Exception{

    private static final long serialVersionUID = 1393060593577L;
    
    private BuildRuleStub.DeleteBuildRuleServiceGovernanceException faultMessage;

    
        public DeleteBuildRuleServiceGovernanceException() {
            super("DeleteBuildRuleServiceGovernanceException");
        }

        public DeleteBuildRuleServiceGovernanceException(String s) {
           super(s);
        }

        public DeleteBuildRuleServiceGovernanceException(String s, Throwable ex) {
          super(s, ex);
        }

        public DeleteBuildRuleServiceGovernanceException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(BuildRuleStub.DeleteBuildRuleServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public BuildRuleStub.DeleteBuildRuleServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    