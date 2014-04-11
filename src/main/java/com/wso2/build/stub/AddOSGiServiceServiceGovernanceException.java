
/**
 * AddOSGiServiceServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class AddOSGiServiceServiceGovernanceException extends Exception{

    private static final long serialVersionUID = 1396948661796L;
    
    private com.wso2.build.stub.OSGiServiceStub.AddOSGiServiceServiceGovernanceException faultMessage;

    
        public AddOSGiServiceServiceGovernanceException() {
            super("AddOSGiServiceServiceGovernanceException");
        }

        public AddOSGiServiceServiceGovernanceException(String s) {
           super(s);
        }

        public AddOSGiServiceServiceGovernanceException(String s, Throwable ex) {
          super(s, ex);
        }

        public AddOSGiServiceServiceGovernanceException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.OSGiServiceStub.AddOSGiServiceServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.OSGiServiceStub.AddOSGiServiceServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    