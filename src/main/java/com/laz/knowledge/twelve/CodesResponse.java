package com.laz.knowledge.twelve;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * ClassName: CodesResponse
 * Description:
 *
 * @author chenmiaomiao
 * @version 1.0
 * @date: 2020/4/20
 */
public class CodesResponse {

    private String id;
    private String type;
    private String content;
    private Date createdDate;
    private String docType;
    private List<History> Historys;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedDate() {
    	return getDateStr(createdDate);
    }

    public void setCreatedDate(Date createdDate) {
    	this.createdDate = createdDate;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public List<History> getHistorys() {
        return Historys;
    }

    public void setHistorys(List<History> historys) {
        Historys = historys;
    }
    
  	private String getDateStr(Date date) {
  		SimpleDateFormat dateFormat = new SimpleDateFormat(
  				"yyyy-MM-dd HH:mm:ss");
  		dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
  		String dateStr = dateFormat.format(date);
  		return dateStr;
  	}
}
