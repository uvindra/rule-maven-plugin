
/**
 * UpdateBuildRuleServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class UpdateBuildRuleServiceGovernanceException extends Exception{

    private static final long serialVersionUID = 1393060593584L;
    
    private BuildRuleStub.UpdateBuildRuleServiceGovernanceException faultMessage;

    
        public UpdateBuildRuleServiceGovernanceException() {
            super("UpdateBuildRuleServiceGovernanceException");
        }

        public UpdateBuildRuleServiceGovernanceException(String s) {
           super(s);
        }

        public UpdateBuildRuleServiceGovernanceException(String s, Throwable ex) {
          super(s, ex);
        }

        public UpdateBuildRuleServiceGovernanceException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(BuildRuleStub.UpdateBuildRuleServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public BuildRuleStub.UpdateBuildRuleServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    