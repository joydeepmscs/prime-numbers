package com.rbs.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;
/**
 *  @author Joydeep Paul
 */
@XmlRootElement
public class PrimeNumberResponse implements Serializable {
    @JsonProperty("Initials")
    @XmlElement(name = "Initials")
    private Integer initials;

    @JsonProperty("Primes")
    @JacksonXmlElementWrapper(localName = "list")
    @XmlElement(name = "Primes")
    private List<Integer> primes;

    public Integer getInitials() {
        return initials;
    }

    public void setInitials(Integer initials) {
        this.initials = initials;
    }

    public List<Integer> getPrimes() {
        return primes;
    }

    public void setPrimes(List<Integer> primes) {
        this.primes = primes;
    }

    @Override
    public boolean equals(Object obj) {
        return new EqualsBuilder().append(this, obj).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(this).hashCode();
    }


}
