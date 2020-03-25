package com.ule.demo.common.entity;
import com.ule.demo.common.springside.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Table(name = "CERTIFICATE_INFO")
@Getter
@Setter
public class CertificateInfo extends AbstractEntity<Long> {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "select SEQ_CERTIFICATE_INFO.nextval from dual")
	public Long id;
	public String busiName;
	public String sysName;
	public Integer cerSendType;
	public Integer cerType;
	public String channelCode;
	public String downPassword;
	@Transient
	public String cerPassword;
	public String expirationDate;
	public String email;
	public String cerContent;
	public Integer cerOutType;
	public String cerOutFilename;
	public Integer cerEnv;
	public String cerName;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date editTime;
}
