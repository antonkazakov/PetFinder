package com.greencode.petfinder.data.entity.beans;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.net.URL;

/**
 *
 * This is base response from server it contains Header which contains response status which is
 * important and bit strange:
 *
 * 100	PFAPI_OK	No error
 * 200	PFAPI_ERR_INVALID	Invalid request
 * 201	PFAPI_ERR_NOENT	Record does not exist
 * 202	PFAPI_ERR_LIMIT	A limit was exceeded
 * 203	PFAPI_ERR_LOCATION	Invalid geographical location
 * 300	PFAPI_ERR_UNAUTHORIZED	Request is unauthorized
 * 301	PFAPI_ERR_AUTHFAIL	Authentication failure
 * 999	PFAPI_ERR_INTERNAL	Generic internal error
 *
 *  @link {https://www.petfinder.com/developers/api-docs#response}
 *
 * @author Anton Kazakov
 * @date 30.03.17.
 */

@Root(name="petfinder")
public class BaseResponse {

    @Element(name="header", required=false)
    Header header;

    @Attribute(name="noNamespaceSchemaLocation", required=false)
    URL noNamespaceSchemaLocation;

    public Header getHeader() {return this.header;}

    public static class Header {

        @Element(name="version", required=false)
        String version;

        @Element(name="timestamp", required=false)
        String timestamp;

        @Element(name="status", required=false)
        Status status;

        public String getVersion() {return this.version;}
        public void setVersion(String value) {this.version = value;}

        public String getTimestamp() {return this.timestamp;}
        public void setTimestamp(String value) {this.timestamp = value;}

        public Status getStatus() {return this.status;}
        public void setStatus(Status value) {this.status = value;}

    }

    public static class Status {

        @Element(name="code", required=false)
        String code;

        @Element(name="message", required=false)
        String message;

        public String getCode() {return this.code;}
        public void setCode(String value) {this.code = value;}

        public String getMessage() {return this.message;}
        public void setMessage(String value) {this.message = value;}

    }

}
