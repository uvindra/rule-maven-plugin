
/**
 * GetOSGiServiceArtifactIDsServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class GetOSGiServiceArtifactIDsServiceGovernanceException extends Exception{

    private static final long serialVersionUID = 1396948661816L;
    
    private com.wso2.build.stub.OSGiServiceStub.GetOSGiServiceArtifactIDsServiceGovernanceException faultMessage;

    
        public GetOSGiServiceArtifactIDsServiceGovernanceException() {
            super("GetOSGiServiceArtifactIDsServiceGovernanceException");
        }

        public GetOSGiServiceArtifactIDsServiceGovernanceException(String s) {
           super(s);
        }

        public GetOSGiServiceArtifactIDsServiceGovernanceException(String s, Throwable ex) {
          super(s, ex);
        }

        public GetOSGiServiceArtifactIDsServiceGovernanceException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.OSGiServiceStub.GetOSGiServiceArtifactIDsServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.OSGiServiceStub.GetOSGiServiceArtifactIDsServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    