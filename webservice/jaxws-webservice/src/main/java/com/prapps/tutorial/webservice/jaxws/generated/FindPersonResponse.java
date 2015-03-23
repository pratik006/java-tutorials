
package com.prapps.tutorial.webservice.jaxws.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "findPersonResponse", namespace = "http://api.jaxws.webservice.tutorial.prapps.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findPersonResponse", namespace = "http://api.jaxws.webservice.tutorial.prapps.com/")
public class FindPersonResponse {

    @XmlElement(name = "return", namespace = "")
    private com.prapps.tutorial.webservice.jaxws.vo.Employee _return;

    /**
     * 
     * @return
     *     returns Employee
     */
    public com.prapps.tutorial.webservice.jaxws.vo.Employee getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.prapps.tutorial.webservice.jaxws.vo.Employee _return) {
        this._return = _return;
    }

}
