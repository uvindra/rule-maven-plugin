
/**
 * GetOSGiServiceServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class GetOSGiServiceServiceGovernanceException extends Exception{

    private static final long serialVersionUID = 1396948661836L;
    
    private com.wso2.build.stub.OSGiServiceStub.GetOSGiServiceServiceGovernanceException faultMessage;

    
        public GetOSGiServiceServiceGovernanceException() {
            super("GetOSGiServiceServiceGovernanceException");
        }

        public GetOSGiServiceServiceGovernanceException(String s) {
           super(s);
        }

        public GetOSGiServiceServiceGovernanceException(String s, Throwable ex) {
          super(s, ex);
        }

        public GetOSGiServiceServiceGovernanceException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.OSGiServiceStub.GetOSGiServiceServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.OSGiServiceStub.GetOSGiServiceServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    