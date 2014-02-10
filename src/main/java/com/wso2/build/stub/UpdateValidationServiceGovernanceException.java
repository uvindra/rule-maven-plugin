
/**
 * UpdateValidationServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class UpdateValidationServiceGovernanceException extends java.lang.Exception{

    private static final long serialVersionUID = 1391873740686L;
    
    private com.wso2.build.stub.ValidationStub.UpdateValidationServiceGovernanceException faultMessage;

    
        public UpdateValidationServiceGovernanceException() {
            super("UpdateValidationServiceGovernanceException");
        }

        public UpdateValidationServiceGovernanceException(java.lang.String s) {
           super(s);
        }

        public UpdateValidationServiceGovernanceException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public UpdateValidationServiceGovernanceException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.ValidationStub.UpdateValidationServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.ValidationStub.UpdateValidationServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    