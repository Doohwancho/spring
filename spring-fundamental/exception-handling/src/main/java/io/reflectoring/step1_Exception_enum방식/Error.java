package io.reflectoring.step1_Exception_enum방식;


import org.apache.logging.log4j.util.Strings;

/*
   Q. 없는 url에 요청 보내면, response에 어떻게 에러메시지가 담겨서 옴?

   A. curl --request GET 'http://localhost:8080/api/v1/carts/1'
--header 'Accept: application/xml'

    <Error>
      <errorCode>PACKT-0001</errorCode>
      <message>The system is unable to complete the request. Contact system support.</message>
      <status>500</status>
      <url>http://localhost:8080/api/v1/carts/1</url>
      <reqMethod>GET</reqMethod>
    </Error>

 */
public class Error {

  private static final long serialVersionUID = 1L;
  /**
   * Application error code, which is different from HTTP error code.
   */
  private String errorCode;

  /**
   * Short, human-readable summary of the problem.
   */
  private String message;

  /**
   * HTTP status code for this occurrence of the problem, set by the origin server.
   */
  private Integer status;

  /**
   * Url of request that produced the error.
   */
  private String url = "Not available";

  /**
   * Method of request that produced the error.
   */
  private String reqMethod = "Not available";

  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getUrl() {
    return url;
  }

  public Error setUrl(String url) {
    if (Strings.isNotBlank(url)) {
      this.url = url;
    }
    return this;
  }

  public String getReqMethod() {
    return reqMethod;
  }

  public Error setReqMethod(String method) {
    if (Strings.isNotBlank(method)) {
      this.reqMethod = method;
    }
    return this;
  }
}
