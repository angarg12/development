/*******************************************************************************
 *  Copyright FUJITSU LIMITED 2017
 *******************************************************************************/

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.10 at 04:11:11 PM CEST 
//

package org.oscm.billingservice.business.model.billingresult;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * A single subscription.
 * 
 * <p>
 * Java class for SubscriptionType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="SubscriptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrganizationalUnit" type="{}OrganizationalUnitType" minOccurs="0"/>
 *         &lt;element name="PriceModels" type="{}PriceModelsType"/>
 *         &lt;element name="Udas" type="{}UdasType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="purchaseOrderNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscriptionType", propOrder = { "organizationalUnit",
        "priceModels", "udas" })
public class SubscriptionType {

    @XmlElement(name = "OrganizationalUnit")
    protected OrganizationalUnitType organizationalUnit;
    @XmlElement(name = "PriceModels", required = true)
    protected PriceModelsType priceModels;
    @XmlElement(name = "Udas")
    protected UdasType udas;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "purchaseOrderNumber")
    protected String purchaseOrderNumber;

    /**
     * Gets the value of the organizationalUnit property.
     * 
     * @return possible object is {@link OrganizationalUnitType }
     * 
     */
    public OrganizationalUnitType getOrganizationalUnit() {
        return organizationalUnit;
    }

    /**
     * Sets the value of the organizationalUnit property.
     * 
     * @param value
     *            allowed object is {@link OrganizationalUnitType }
     * 
     */
    public void setOrganizationalUnit(OrganizationalUnitType value) {
        this.organizationalUnit = value;
    }

    /**
     * Gets the value of the priceModels property.
     * 
     * @return possible object is {@link PriceModelsType }
     * 
     */
    public PriceModelsType getPriceModels() {
        return priceModels;
    }

    /**
     * Sets the value of the priceModels property.
     * 
     * @param value
     *            allowed object is {@link PriceModelsType }
     * 
     */
    public void setPriceModels(PriceModelsType value) {
        this.priceModels = value;
    }

    /**
     * Gets the value of the udas property.
     * 
     * @return possible object is {@link UdasType }
     * 
     */
    public UdasType getUdas() {
        return udas;
    }

    /**
     * Sets the value of the udas property.
     * 
     * @param value
     *            allowed object is {@link UdasType }
     * 
     */
    public void setUdas(UdasType value) {
        this.udas = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the purchaseOrderNumber property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    /**
     * Sets the value of the purchaseOrderNumber property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setPurchaseOrderNumber(String value) {
        this.purchaseOrderNumber = value;
    }

}
