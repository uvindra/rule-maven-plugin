
/**
 * GetValidationDependenciesServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class GetValidationDependenciesServiceGovernanceException extends java.lang.Exception{

    private static final long serialVersionUID = 1391873740679L;
    
    private com.wso2.build.stub.ValidationStub.GetValidationDependenciesServiceGovernanceException faultMessage;

    
        public GetValidationDependenciesServiceGovernanceException() {
            super("GetValidationDependenciesServiceGovernanceException");
        }

        public GetValidationDependenciesServiceGovernanceException(java.lang.String s) {
           super(s);
        }

        public GetValidationDependenciesServiceGovernanceException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public GetValidationDependenciesServiceGovernanceException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.ValidationStub.GetValidationDependenciesServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.ValidationStub.GetValidationDependenciesServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    