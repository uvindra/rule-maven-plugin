
/**
 * GetBuildRuleDependenciesServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class GetBuildRuleDependenciesServiceGovernanceException extends Exception{

    private static final long serialVersionUID = 1393060593570L;
    
    private BuildRuleStub.GetBuildRuleDependenciesServiceGovernanceException faultMessage;

    
        public GetBuildRuleDependenciesServiceGovernanceException() {
            super("GetBuildRuleDependenciesServiceGovernanceException");
        }

        public GetBuildRuleDependenciesServiceGovernanceException(String s) {
           super(s);
        }

        public GetBuildRuleDependenciesServiceGovernanceException(String s, Throwable ex) {
          super(s, ex);
        }

        public GetBuildRuleDependenciesServiceGovernanceException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(BuildRuleStub.GetBuildRuleDependenciesServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public BuildRuleStub.GetBuildRuleDependenciesServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    