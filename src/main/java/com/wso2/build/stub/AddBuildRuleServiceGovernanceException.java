
/**
 * AddBuildRuleServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class AddBuildRuleServiceGovernanceException extends Exception{

    private static final long serialVersionUID = 1393060593615L;
    
    private com.wso2.build.stub.BuildRuleStub.AddBuildRuleServiceGovernanceException faultMessage;

    
        public AddBuildRuleServiceGovernanceException() {
            super("AddBuildRuleServiceGovernanceException");
        }

        public AddBuildRuleServiceGovernanceException(String s) {
           super(s);
        }

        public AddBuildRuleServiceGovernanceException(String s, Throwable ex) {
          super(s, ex);
        }

        public AddBuildRuleServiceGovernanceException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.BuildRuleStub.AddBuildRuleServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.BuildRuleStub.AddBuildRuleServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    