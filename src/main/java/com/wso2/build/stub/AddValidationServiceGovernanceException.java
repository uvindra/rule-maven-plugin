
/**
 * AddValidationServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class AddValidationServiceGovernanceException extends java.lang.Exception{

    private static final long serialVersionUID = 1391873740701L;
    
    private com.wso2.build.stub.ValidationStub.AddValidationServiceGovernanceException faultMessage;

    
        public AddValidationServiceGovernanceException() {
            super("AddValidationServiceGovernanceException");
        }

        public AddValidationServiceGovernanceException(java.lang.String s) {
           super(s);
        }

        public AddValidationServiceGovernanceException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public AddValidationServiceGovernanceException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.ValidationStub.AddValidationServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.ValidationStub.AddValidationServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    