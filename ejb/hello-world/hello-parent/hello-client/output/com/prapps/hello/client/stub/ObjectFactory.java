
package com.prapps.hello.client.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.prapps.hello.client.stub package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SayHelloRemoteDetail_QNAME = new QName("http://ejb.hello.prapps.com/", "sayHelloRemoteDetail");
    private final static QName _SayHelloRemoteDetailResponse_QNAME = new QName("http://ejb.hello.prapps.com/", "sayHelloRemoteDetailResponse");
    private final static QName _SayHelloRemoteResponse_QNAME = new QName("http://ejb.hello.prapps.com/", "sayHelloRemoteResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.prapps.hello.client.stub
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link HelloRequest }
     * 
     */
    public HelloRequest createHelloRequest() {
        return new HelloRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb.hello.prapps.com/", name = "sayHelloRemoteDetail")
    public JAXBElement<HelloRequest> createSayHelloRemoteDetail(HelloRequest value) {
        return new JAXBElement<HelloRequest>(_SayHelloRemoteDetail_QNAME, HelloRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb.hello.prapps.com/", name = "sayHelloRemoteDetailResponse")
    public JAXBElement<HelloResponse> createSayHelloRemoteDetailResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_SayHelloRemoteDetailResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ejb.hello.prapps.com/", name = "sayHelloRemoteResponse")
    public JAXBElement<String> createSayHelloRemoteResponse(String value) {
        return new JAXBElement<String>(_SayHelloRemoteResponse_QNAME, String.class, null, value);
    }

}
