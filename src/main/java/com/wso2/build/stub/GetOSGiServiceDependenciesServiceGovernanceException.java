
/**
 * GetOSGiServiceDependenciesServiceGovernanceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

package com.wso2.build.stub;

public class GetOSGiServiceDependenciesServiceGovernanceException extends Exception{

    private static final long serialVersionUID = 1396948661806L;
    
    private com.wso2.build.stub.OSGiServiceStub.GetOSGiServiceDependenciesServiceGovernanceException faultMessage;

    
        public GetOSGiServiceDependenciesServiceGovernanceException() {
            super("GetOSGiServiceDependenciesServiceGovernanceException");
        }

        public GetOSGiServiceDependenciesServiceGovernanceException(String s) {
           super(s);
        }

        public GetOSGiServiceDependenciesServiceGovernanceException(String s, Throwable ex) {
          super(s, ex);
        }

        public GetOSGiServiceDependenciesServiceGovernanceException(Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.wso2.build.stub.OSGiServiceStub.GetOSGiServiceDependenciesServiceGovernanceException msg){
       faultMessage = msg;
    }
    
    public com.wso2.build.stub.OSGiServiceStub.GetOSGiServiceDependenciesServiceGovernanceException getFaultMessage(){
       return faultMessage;
    }
}
    