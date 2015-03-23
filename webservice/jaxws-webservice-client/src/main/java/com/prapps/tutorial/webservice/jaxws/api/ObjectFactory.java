
package com.prapps.tutorial.webservice.jaxws.api;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.prapps.tutorial.webservice.jaxws.api package. 
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

    private final static QName _FindPerson_QNAME = new QName("http://api.jaxws.webservice.tutorial.prapps.com/", "findPerson");
    private final static QName _FindPersonResponse_QNAME = new QName("http://api.jaxws.webservice.tutorial.prapps.com/", "findPersonResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.prapps.tutorial.webservice.jaxws.api
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindPerson }
     * 
     */
    public FindPerson createFindPerson() {
        return new FindPerson();
    }

    /**
     * Create an instance of {@link FindPersonResponse }
     * 
     */
    public FindPersonResponse createFindPersonResponse() {
        return new FindPersonResponse();
    }

    /**
     * Create an instance of {@link Employee }
     * 
     */
    public Employee createEmployee() {
        return new Employee();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.jaxws.webservice.tutorial.prapps.com/", name = "findPerson")
    public JAXBElement<FindPerson> createFindPerson(FindPerson value) {
        return new JAXBElement<FindPerson>(_FindPerson_QNAME, FindPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindPersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api.jaxws.webservice.tutorial.prapps.com/", name = "findPersonResponse")
    public JAXBElement<FindPersonResponse> createFindPersonResponse(FindPersonResponse value) {
        return new JAXBElement<FindPersonResponse>(_FindPersonResponse_QNAME, FindPersonResponse.class, null, value);
    }

}
