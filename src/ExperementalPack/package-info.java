
@javax.xml.bind.annotation.XmlSchema(
        //namespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd",
        namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl",
        xmlns = {
              // @XmlNs(prefix = "wsu",namespaceURI = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl")
                @XmlNs(prefix = "wsu",namespaceURI = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd")
               ,@XmlNs(prefix = "fil",namespaceURI = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl")
        },
        elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)

/*
@javax.xml.bind.annotation.XmlSchema
        (  namespace = "http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl",
                elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)*/
package ExperementalPack;

import javax.xml.bind.annotation.XmlNs;