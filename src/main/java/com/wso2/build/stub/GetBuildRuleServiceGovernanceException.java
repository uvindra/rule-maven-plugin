
/**
 * GetBuildRuleServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class GetBuildRuleServiceGovernanceException extends Exception{

    private static final long serialVersionUID = 1393060593604L;
    
    private BuildRuleStub.GetBuildRuleServiceGovernanceException faultMessage;

    
        public GetBuildRuleServiceGovernanceException() {
            super("GetBuildRuleServiceGovernanceException");
        }

        public GetBuildRuleServiceGovernanceException(String s) {
           super(s);
        }

        public GetBuildRuleServiceGovernanceException(String s, Throwable ex) {
          super(s, ex);
        }

        public GetBuildRuleServiceGovernanceException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(BuildRuleStub.GetBuildRuleServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public BuildRuleStub.GetBuildRuleServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    