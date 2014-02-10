
/**
 * GetValidationArtifactIDsServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class GetValidationArtifactIDsServiceGovernanceException extends java.lang.Exception{

    private static final long serialVersionUID = 1391873740693L;
    
    private com.wso2.build.stub.ValidationStub.GetValidationArtifactIDsServiceGovernanceException faultMessage;

    
        public GetValidationArtifactIDsServiceGovernanceException() {
            super("GetValidationArtifactIDsServiceGovernanceException");
        }

        public GetValidationArtifactIDsServiceGovernanceException(java.lang.String s) {
           super(s);
        }

        public GetValidationArtifactIDsServiceGovernanceException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public GetValidationArtifactIDsServiceGovernanceException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.ValidationStub.GetValidationArtifactIDsServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.ValidationStub.GetValidationArtifactIDsServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    