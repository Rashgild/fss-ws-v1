package ExperementalPack;

import HelpersMethods.Doc;
import HelpersMethods.GlobalVariables;
import HelpersMethods.SQLConnect;
import HelpersMethods.SQLStoreQuer;
import WS_ClientToFss.SignAndEncrypt.Encrypt;
import WS_ClientToFss.SignAndEncrypt.Sign;
import org.w3c.dom.Document;
import ru.CryptoPro.JCPxml.xmldsig.JCPXMLDSigInit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.*;
import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rkurbanov on 09.12.2016.
 */
public class Temp_main {

    public static void main(String[] args) throws Exception{

        GlobalVariables.GetConfiguration();

        System.setProperty("javax.net.ssl.trustStore",GlobalVariables.PathToSSLcert[1]);//КОНФ
        System.setProperty("javax.net.ssl.trustStorePassword", "123456");
        //SOAPMessage message = XmlFileLnLpu.CreateMessage();//XmlFileLnLpu.StartSetxmlFileLn();
        JCPXMLDSigInit.init();
        setUp();
        InsertSkel0ton();
    }


    private static Parser parser;
    private static File file;

    public static void setUp() throws Exception {
        parser = new JaxbParser();
        file = new File("person.xml");
    }

    public static void InsertSkel0ton() throws Exception {

        GlobalVariables.GetConfiguration();
        ResultSet rs = SQLConnect.SQL_Select(SQLStoreQuer.Query_SkeletonSelect());

        ROWSET rowset = new ROWSET();
        rowset.setAuthor("R.Kurbanov");
        rowset.setEmail("Rashgild@gmai;.com");
        rowset.setPhone("89608634440");
        rowset.setSoftware("SignAndcypt");
        rowset.setVersion("01");
        rowset.setVersionSoftware("2.0");

        ROW row = new ROW();
        List<ROW> rows = new ArrayList<ROW>();


        ResultSet rs2 = SQLConnect.SQL_Select(SQLStoreQuer.Query_Treats());

        TREAT_FULL_PERIOD treat_full_period = new TREAT_FULL_PERIOD();
        List<TREAT_FULL_PERIOD> treat_full_periods = new ArrayList<>();

        TREAT_PERIOD treat_period = new TREAT_PERIOD();
        List<TREAT_PERIOD> treat_periods = new ArrayList<>();



        while(rs.next())
        {
            String ELN = rs.getString("LN_CODE");
            //int vk =3;
            int per =3;
            row  = new ROW(); //новый экземпляр row
            //Заполняем
            row.setAttribId("ELN_"+ELN);
            row.setSnils(rs.getString("SNILS"));
            row.setSurname(rs.getString("SURNAME"));
            row.setName(rs.getString("NAME"));
            row.setPatronimic(rs.getString("PATRONIMIC"));
            row.setBozflag(rs.getInt("BOZ_FLAG"));
            row.setLpuemployer(rs.getString("LPU_EMPLOYER"));
            row.setLpuemplflag(rs.getInt("LPU_EMPL_FLAG"));
            row.setLncode(rs.getString("LN_CODE"));
            // row.setPrevlncode(rs.getString());
            row.setPrimaryflag(rs.getInt("PRIMARY_FLAG"));
            row.setDuplicateflag(rs.getInt("DUPLICATE_FLAG"));
            row.setLndate(rs.getString("LN_DATE"));
            row.setLpuname(rs.getString("LPU_NAME"));
            row.setLpuaddress(rs.getString("LPU_ADDRESS"));
            row.setLpuogrn(rs.getString("LPU_OGRN"));
            row.setBirthday(rs.getString("BIRTHDAY"));
            row.setGender(rs.getInt("GENDER"));
            row.setReason1(rs.getString("REASON1"));
            row.setReason2(rs.getString("REASON2"));
            row.setReason3(rs.getString("REASON3"));
            row.setDiagnos(rs.getString("DIAGNOS"));
            row.setParentcode(rs.getString("PARENT_CODE"));
            row.setDate1(rs.getString("DATE1"));
            row.setDate2(rs.getString("DATE2"));
            row.setVoucherno(rs.getString("VOUCHER_NO"));
            row.setVoucherogrn(rs.getString("VOUCHER_OGRN"));
            row.setServ1AGE(rs.getString("SERV1_AGE"));
            row.setServ1RELATIONCODE(rs.getString("SERV1_RELATION_CODE"));
            row.setServ1FIO(rs.getString("SERV1_FIO"));
            row.setServ2AGE(rs.getString("SERV2_AGE"));
            row.setServ2RELATIONCODE(rs.getString("SERV2_RELATION_CODE"));
            row.setServ2FIO(rs.getString("SERV2_FIO"));
            row.setPregn12WFLAG(rs.getString("PREGN12W_FLAG"));
            row.setHospitaldt1(rs.getString("HOSPITAL_DT1"));
            row.setHospitaldt2(rs.getString("HOSPITAL_DT2"));
            //row.setclosereason(rs.getString(""));
            row.setMsedt1(rs.getString("MSE_DT1"));
            row.setMsedt2(rs.getString("MSE_DT2"));
            row.setMsedt3(rs.getString("MSE_DT3"));
            row.setLnstate(rs.getString("LN_STATE"));


            int DDID_1 = rs.getInt("DDID");

            treat_full_periods=new ArrayList<>(); //Новый экземпляр fullperiod
            while (rs2.next())
            {
                int DDID_2 = rs2.getInt("DDID");
                //System.out.println("1: "+DDID_1+" j: "+DDID_2);
                if(DDID_1==DDID_2)
                {
                    treat_full_period = new TREAT_FULL_PERIOD();
                    treat_period = new TREAT_PERIOD();

                    treat_full_period.setTreatchairmanrole(rs2.getString("TREAT_CHAIRMAN_ROLE"));
                    treat_full_period.setTreatchairman(rs2.getString("TREAT_CHAIRMAN"));

                    //System.out.println(treat_full_period.getTreatchairmanrole());
                    if(treat_full_period.getTreatchairmanrole()!=null) {
                        treat_full_period.setAttributevk("ELN_" + ELN + "_" + per + "_vk");
                        // vk++;
                    }

                    treat_period.setTreatdt1(rs2.getString("TREAT_DT1"));
                    treat_period.setTreatdt2(rs2.getString("TREAT_DT2"));
                    treat_period.setTreatdoctorrole(rs2.getString("TREAT_DOCTOR_ROLE"));
                    treat_period.setTreatdoctor(rs2.getString("TREAT_DOCTOR"));
                    treat_period.setAttribId("ELN_" + ELN + "_" + per + "_doc");


                    treat_periods =new ArrayList<>();
                    treat_periods.add(treat_period);
                    treat_full_period.setTreat_period(treat_periods);
                    treat_full_periods.add(treat_full_period);


                    per++;
                }else{
                    rs2.previous();
                    break;}
            }

            HOSPITAL_BREACH hospital_breach = new HOSPITAL_BREACH();
            hospital_breach.setHospitalbreachcode(rs.getString("HOSPITAL_BREACH_CODE"));
            hospital_breach.setHospitalbreachdt(rs.getString("HOSPITAL_BREACH_DT"));
            if(hospital_breach.getHospitalbreachcode()!=null) {
                hospital_breach.setAttributeId("ELN_"+ELN+"_1_doc");
            }
            List<HOSPITAL_BREACH> hospital_breaches = new ArrayList<>();
            hospital_breaches.add(hospital_breach);

            LN_RESULT ln_result = new LN_RESULT();
            ln_result.setMseresult(rs.getString("MSE_RESULT"));
            ln_result.setOtherstatedt(rs.getString("other_state_dt"));
            ln_result.setNextlncode(rs.getString("NEXT_LN_CODE"));

            if(ln_result.getMseresult()!=null && !ln_result.getMseresult().equals("")) {
                ln_result.setAttribId("ELN=" + ELN + "_2_doc");
            }

            List<LN_RESULT>ln_results = new ArrayList<>();
            ln_results.add(ln_result);

            row.setLnresult(ln_results);
            row.setHospitalbreach(hospital_breaches);
            row.setTREAT_PERIODS(treat_full_periods);
            rows.add(row);

        }



        rowset.setRow(rows);

        List<ROWSET>rowsets = new ArrayList<>();
        rowsets.add(rowset);
        pXmlFile pXmlFile = new pXmlFile();
        pXmlFile.setRowset(rowsets);

        Request request = new Request();
        request.setOgrn("1037726008110"); //TODO огрн
        List<pXmlFile>pXmlFiles= new ArrayList<>();
        pXmlFiles.add(pXmlFile);
        request.setpXmlFiles(pXmlFiles);

        PrParseFilelnlpu prParseFilelnlpu = new PrParseFilelnlpu();



        //prParseFilelnlpu.setDs("http://www.w3.org/2000/09/xmldsig#");
        // prParseFilelnlpu.setWsu("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
        prParseFilelnlpu.setFil("http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl");
       /*prParseFilelnlpu.setDs("http://www.w3.org/2000/09/xmldsig#");
        prParseFilelnlpu.setAttrib3("http://schemas.xmlsoap.org/soap/envelope/");
        prParseFilelnlpu.setAttrib4("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        prParseFilelnlpu.setWsu("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
        prParseFilelnlpu.setAttrib6("http://www.w3.org/XML/1998/namespace");
        prParseFilelnlpu.setAttrib7("http://www.w3.org/2001/XMLSchema");
        prParseFilelnlpu.setAttrib8("http://www.w3.org/2001/XMLSchema-instance");
        */
        List<Request> requests = new ArrayList<>();
        requests.add(request);
        prParseFilelnlpu.setRequests(requests);

        pack.Body body = new pack.Body();

        List<PrParseFilelnlpu>prParseFilelnlpus =new ArrayList<>();
        prParseFilelnlpus.add(prParseFilelnlpu);
        body.setPrParseFilelnlpus(prParseFilelnlpus);
        List<pack.Body>bodies = new ArrayList<>();
        bodies.add(body);
        pack Pack = new pack();
        Pack.setBodies(bodies);

        //ref = createObject("java", "org.apache.xml.security.Init");

        org.apache.xml.security.Init.init();
        parser.saveObject(file,prParseFilelnlpu);


        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();
        document= parser.ObjToSoap(prParseFilelnlpu);

        //System.out.println("\n");
        //Doc.writeDoc(document, System.out);
        MessageFactory mf = MessageFactory.newInstance();
        SOAPMessage message = mf.createMessage();
        // message.writeTo(System.out);
        // System.out.println("-------\n");
        message= Doc.DocToSOAP(document);
        //String s = Doc.SoapMessageToString(message);

        SOAPMessage message2 = mf.createMessage();

        SOAPPart soapPart = message2.getSOAPPart();
        SOAPEnvelope soapEnv = message2.getSOAPPart().getEnvelope();
        SOAPHeader soapHeader = soapEnv.getHeader();
        SOAPBody soapBody = soapEnv.getBody();
        soapBody.addDocument(document);

        soapEnv.addNamespaceDeclaration("ds","http://www.w3.org/2000/09/xmldsig#");
        soapEnv.addNamespaceDeclaration("wsse","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
        soapEnv.addNamespaceDeclaration("wsu","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
        soapEnv.addNamespaceDeclaration("xsd","http://www.w3.org/2001/XMLSchema");
        soapEnv.addNamespaceDeclaration("xsi","http://www.w3.org/2001/XMLSchema-instance");
        soapEnv.addNamespaceDeclaration("fil","http://ru/ibs/fss/ln/ws/FileOperationsLn.wsdl");




//Signation
        HOSPITAL_BREACH hospital_breach = new HOSPITAL_BREACH();
        LN_RESULT ln_result = new LN_RESULT();
        Doc.SaveSOAPToXML("my.xml",message2);
        for(int i =0;i<rows.size();i++){

            //System.out.println(rows.get(i).getLncode()+"|"+hospital_breach.getAttributeId());

            GlobalVariables.eln=rows.get(i).getLncode();
            //Общая подись, всего ЛН
            message2= Sign.SignationByParametrs2("http://eln.fss.ru/actor/mo/"+GlobalVariables.ogrnMo[1]+"/"+rows.get(i).getAttribId(),
                    "#"+rows.get(i).getAttribId(),message2);
            Doc.SaveSOAPToXML("my.xml",message2);
            List<HOSPITAL_BREACH>hospital_breaches= rows.get(i).getHospitalbreach();
            List<LN_RESULT>ln_results = rows.get(i).getLnresult();
            hospital_breach = hospital_breaches.get(0);
            ln_result = ln_results.get(0);

            //Подпись "Результатов"
            if(ln_result.getAttribId()!=null)
            {
                message2= Sign.SignationByParametrs2("http://eln.fss.ru/actor/mo/"+GlobalVariables.ogrnMo[1]+"/"+ln_result.getAttribId()+"/",
                        "#"+ln_result.getAttribId(),message2);
                Doc.SaveSOAPToXML("my.xml",message2);
            }

            //Подпись "Нарушений"
            if(hospital_breach.getAttributeId()!=null)
            {
                message2= Sign.SignationByParametrs2("http://eln.fss.ru/actor/mo/"+GlobalVariables.ogrnMo[1]+"/"+hospital_breach.getAttributeId()+"/",
                        "#"+hospital_breach.getAttributeId(),message2);
                Doc.SaveSOAPToXML("my.xml",message2);
            }

            TREAT_FULL_PERIOD treat_full_period1 = new TREAT_FULL_PERIOD();
            List<TREAT_FULL_PERIOD>treat_full_periods1 = rows.get(i).getTREAT_PERIODS();

            TREAT_PERIOD treat_period1 = new TREAT_PERIOD();
            //Подпись ВК
            for(int j=0;j<treat_full_periods1.size();j++) {
                treat_full_period1 = treat_full_periods1.get(j);
                if(treat_full_period1.getAttributevk()!=null) {
                    message2= Sign.SignationByParametrs2("http://eln.fss.ru/actor/mo/"+GlobalVariables.ogrnMo[1]+"/"+treat_full_period1.getAttributevk()+"/",
                            "#"+treat_full_period1.getAttributevk(),message2);
                    Doc.SaveSOAPToXML("my.xml",message2);
                }

                List<TREAT_PERIOD> treat_periods1 = treat_full_periods1.get(j).getTreat_period();
                //Подпись доктора
                for (int k=0;k<treat_periods1.size();k++) {
                    treat_period1 = treat_periods1.get(k);
                    if(treat_period1.getAttribId()!=null) {
                        message2= Sign.SignationByParametrs2("http://eln.fss.ru/actor/mo/"+GlobalVariables.ogrnMo[1]+"/"+treat_period1.getAttribId()+"/",
                                "#"+treat_period1.getAttribId(),message2);
                        Doc.SaveSOAPToXML("my.xml",message2);
                    }
                }
            }
        }

        mf = MessageFactory.newInstance();
        SOAPMessage NewMessg = mf.createMessage();
        NewMessg= Encrypt.CreateXMLAndEncrypt(NewMessg, "my.xml");
        Doc.SaveSOAPToXML("LNCrypted.xml",NewMessg);

    }
}
