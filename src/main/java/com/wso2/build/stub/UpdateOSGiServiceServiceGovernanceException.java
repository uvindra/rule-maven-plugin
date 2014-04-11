
/**
 * UpdateOSGiServiceServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class UpdateOSGiServiceServiceGovernanceException extends java.lang.Exception{

    private static final long serialVersionUID = 1396948661826L;
    
    private com.wso2.build.stub.OSGiServiceStub.UpdateOSGiServiceServiceGovernanceException faultMessage;

    
        public UpdateOSGiServiceServiceGovernanceException() {
            super("UpdateOSGiServiceServiceGovernanceException");
        }

        public UpdateOSGiServiceServiceGovernanceException(java.lang.String s) {
           super(s);
        }

        public UpdateOSGiServiceServiceGovernanceException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public UpdateOSGiServiceServiceGovernanceException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.OSGiServiceStub.UpdateOSGiServiceServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.OSGiServiceStub.UpdateOSGiServiceServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    