package com.rbs.assignment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * @author Joydeep Paul
 */

public class PrimeNumberResponse implements Serializable {
    @JsonProperty("Initials")
    private Integer initials;

    @JsonProperty("Primes")
    @JacksonXmlElementWrapper(localName = "Primes")
    @JacksonXmlProperty(localName ="Number")
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
