
package com.prapps.tutorial.webservice.jaxws.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "findPerson", namespace = "http://api.jaxws.webservice.tutorial.prapps.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findPerson", namespace = "http://api.jaxws.webservice.tutorial.prapps.com/")
public class FindPerson {

    @XmlElement(name = "arg0", namespace = "")
    private long arg0;

    /**
     * 
     * @return
     *     returns long
     */
    public long getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(long arg0) {
        this.arg0 = arg0;
    }

}
