
/**
 * DeleteOSGiServiceServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class DeleteOSGiServiceServiceGovernanceException extends Exception{

    private static final long serialVersionUID = 1396948661789L;
    
    private com.wso2.build.stub.OSGiServiceStub.DeleteOSGiServiceServiceGovernanceException faultMessage;

    
        public DeleteOSGiServiceServiceGovernanceException() {
            super("DeleteOSGiServiceServiceGovernanceException");
        }

        public DeleteOSGiServiceServiceGovernanceException(String s) {
           super(s);
        }

        public DeleteOSGiServiceServiceGovernanceException(String s, Throwable ex) {
          super(s, ex);
        }

        public DeleteOSGiServiceServiceGovernanceException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.OSGiServiceStub.DeleteOSGiServiceServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.OSGiServiceStub.DeleteOSGiServiceServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    