
/**
 * DeleteValidationServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class DeleteValidationServiceGovernanceException extends java.lang.Exception{

    private static final long serialVersionUID = 1391873740673L;
    
    private com.wso2.build.stub.ValidationStub.DeleteValidationServiceGovernanceException faultMessage;

    
        public DeleteValidationServiceGovernanceException() {
            super("DeleteValidationServiceGovernanceException");
        }

        public DeleteValidationServiceGovernanceException(java.lang.String s) {
           super(s);
        }

        public DeleteValidationServiceGovernanceException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public DeleteValidationServiceGovernanceException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.ValidationStub.DeleteValidationServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.ValidationStub.DeleteValidationServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    