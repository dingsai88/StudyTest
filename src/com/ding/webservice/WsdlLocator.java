/**
 * WsdlLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Nov 19, 2006 (02:31:34 GMT+00:00) WSDL2Java emitter.
 */

package com.ding.webservice;

public class WsdlLocator extends org.apache.axis.client.Service implements com.ding.webservice.Wsdl {

    public WsdlLocator() {
    }


    public WsdlLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WsdlLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for wsdlPort
    private java.lang.String wsdlPort_address = "http://10.199.88.161/webservice/index.php";

    public java.lang.String getwsdlPortAddress() {
        return wsdlPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String wsdlPortWSDDServiceName = "wsdlPort";

    public java.lang.String getwsdlPortWSDDServiceName() {
        return wsdlPortWSDDServiceName;
    }

    public void setwsdlPortWSDDServiceName(java.lang.String name) {
        wsdlPortWSDDServiceName = name;
    }

    public com.ding.webservice.WsdlPortType getwsdlPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(wsdlPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getwsdlPort(endpoint);
    }

    public com.ding.webservice.WsdlPortType getwsdlPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ding.webservice.WsdlBindingStub _stub = new com.ding.webservice.WsdlBindingStub(portAddress, this);
            _stub.setPortName(getwsdlPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setwsdlPortEndpointAddress(java.lang.String address) {
        wsdlPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ding.webservice.WsdlPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ding.webservice.WsdlBindingStub _stub = new com.ding.webservice.WsdlBindingStub(new java.net.URL(wsdlPort_address), this);
                _stub.setPortName(getwsdlPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("wsdlPort".equals(inputPortName)) {
            return getwsdlPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("wsdl", "wsdl");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("wsdl", "wsdlPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("wsdlPort".equals(portName)) {
            setwsdlPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
