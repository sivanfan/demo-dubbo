package com.ule.demo.common.constants;

public enum CommonErrors implements BaseCode {

	//========= 通用 =========
		SYSTEM_ERROR("9000001", "小伙伴太热情，挤爆啦，请稍候再试！"),
		
		GATEWAY_ERROR("9000002", "网关异常"),

		PARAM_IS_EMPTY("9000003", "参数为空"),

		PARAM_LENGTH_ERROR("9000004", "参数长度不合法"),

		PARAMS_INVALID("9000005", "缺少参数"),

		PARAMS_ERROR("9000006", "参数错误"),

		MERCHANT_NOT_AUTHORIZED("9000007", "商户无此接口权限"),

		MERCHANT_NOT_EXIST("9000008", "商户不存在"),

		MERCHANT_WRITTEN_OFF("9000009", "商户号已被注销"),

		MERCHANT_INVALID("90000010", "商户无效"),

		SIGN_FAILED("9000011", "验签失败"),

		EMPTY_RESULT("9000012", "查询结果集为空"),

		MULTIPLE_RESULT ("9000013", "查询结果多条"),
		
		QUERY_TIME_ERROR("9000014", "查询时间参数错误或超过限制"),
		
		USRONLYID_INVALID("9000015", "订单用户与支付用户不匹配"),
		
		LIMIT_PAY_FORMAT_ERROR("9000016", "支付方式格式错误"),
		
		PSBC_QUICK_BIND_ERROR("9000017","该卡已绑定邮乐账户：%s，请您解绑后再进行操作。"),
		
		CARD_RULE_ERROR("9000018","暂不支持专享卡和兑换卡，不能支付！"),
		
		UMPDIRECT_PAY_ERROR("9000019","请稍等1分钟再次提现，谢谢！"),
		
		DIRECT_PAY_ERROR("9000020","提现频繁，请稍后在试，谢谢！"),
		
		//========= 支付 =========
		
		PAY_REQNO_DUPLICATE("9001001", "商户支付请求号重复"),

		PAY_REQNO_INVALID("9001002", "商户支付请求号无效"),//?

		PAY_REQNO_ERROR("9001003", "商户支付请求号错误"),

		GATEWAY_SIGN_FAILED("9001004", "网关验签失败"),

		GATEWAY_GENERATE_SIGN_FAILED("9001005", "网关生成签名错误"),

		PAY_PARAM_INVALID("9001006", "支付信息数据非法"),

		PAY_SUCCESS_RECODE_NOT_FOUND("9001007", "无支付成功交易"),

		PAY_REQUEST_RECODE_NOT_FOUND("9001008", "无支付请求交易"),

		PAY_AMOUNT_OUT_OF_RANGE("9001009", "支付金额超出限制"),

		PAY_AMOUNT_BIGGER_THAN_ORDER_AMOUNT("9001010", "支付金额大于订单所需支付金额"),

		PAY_PASSWORD_ERROR("9001011", "您好，您输入的密码有误！当日输入密码次数剩余%s次，请确认后重新输入"),

		PAY_PASSWORD_NOT_FOUND("9001012", "帐户支付密码未设置"),

		ACCOUNT_INVALID("9001013", "帐户状态非法"),

		PAY_NEED_CONFIRM("9001014", "支付等待确认"),
		
		GET_USER_INFO_ERROR("9001015", "获取用户信息失败"),

		ACCOUNT_NOT_EXIST("9001016", "余额帐户不存在"),
		
		ACCOUNT_FORZEN("9001017", "亲，您的账户可能存在风险已被冻结。请联系客服：11185"),
		
		PAY_NOT_COMPLETED("9001018", "订单未支付，请继续完成支付！"),
		
		PAY_CHINAPOST_BEYONDEXIST("9001019", "支付失败，该积分账号, 超出规定支付范围！"),
		
		PAY_CHINAPOST_BALANCE_BEYONDEXIST("9001020", "支付失败，%s，不在支付范围内！"),
		
		PAY_AMOUNT_ZERO("9001021", "支付金额为 0 ，不能支付！"),
		
		PAY_DETAIL_ERROR("9001022","邮政积分支付数据非法！"),
		
		NOT_SUPPORT_POST_POINT_PAY("9001023","不支持邮政积分支付！"),
		
		PAY_CHINAPOST_FLOOR_LIMIT("9001024", "对不起，您的积分余额未达到积分支付最低限制，请使用其它支付方式进行支付！"),
		
		PAY_CHINAPOST_UPPER_LIMIT("9001025", "对不起，您的本次订单已达到积分使用最高限制，请使用其它支付方式继续支付！"),
		
		PAY_TIME_OVERDUE("9001026", "订单超时未支付已取消，感谢您的参与！"),

		WECHAT_PAY_VERSION_PROMPT("9001027", "您的APP版本过低，无法使用微信支付，建议尽快更新。"),
		
		PAY_PSBC_NO_CARD_INFO("9001028","无相关绑定卡信息"),
		
		PAY_PSBC_ALREADY_BIND("9001029","快捷已开通,不能重复开通"),
		
		JSPOINT_CAR_NO_ACTIVATE("9001030","卡未激活，不能支付！"),
		
		JSPOINT_CAR_ALREADY_AUTHORIZATION("9001031", "支付卡号不能重复！"),
		
		CARD_PAY_AMOUNT_ZERO("9001032", "卡余额为: 0 ，不能支付！"),
		
		JSPOINT_CARD_RULE_EXIST("9001033", "卡规则已存在!"),
		
		PAY_COMPLETED("9001034", "订单支付完成！"),
		
		PSBC_SCAN_FAILURE("9001035", "二维码失效,请刷新二维码重试"),
		
		PRE_PAYID_IDENTICAL_REMINDER("9001036", "该支付方式已使用，请勿重复操作！"),

		JSPOINT_PAYMENT_NUMBER("9001037", "单笔订单最多支持20张邮乐江苏卡支付"),

	    ACCOUNT_WILL_FREEZE("9001038", "您好，您输入的密码有误！您的账户将冻结一日"),

	    ACCOUNT_ALREADY_FREEZE("9001039", "您好，您的账户已冻结，请明天再试"),

	    ACCOUNT_PASSWORD_EMPTY("9001040", "请输入密码"),

	    LIMIT_PAY_EMPTY("9001041", "该订单无法在当前环境中支付"),

	    INSUFFICIENT_BALANCE("9001042", "账户余额不足"),
	    
	    ACCOUNTDI_GETPOINT_ERROR("9001043", "此账户邮政积分支付受限"),
	    
	    SYSTEM_SCAN_ERROR("9001044", "请使用支付宝或微信APP扫码"),

		//========= 退款 =========
		REFUND_RECORD_DUPLICATE("9002001", "退款记录存在，不能重复申请退款"),

		REFUND_AMOUNT_TOO_LARGE("9002003", "总退款金额不能大于总支付金额"),

		REFUND_ULECARD_AMOUNT_TOO_LARGE("9002004", "邮乐卡退款金额大于总退款金额"),
		
		ORG_PAY_FAILED("9002005", "原交易未支付成功"),

		REFUND_AMOUNT_LESS_THAN_ZERO("9002006", "退款金额必须大于0"),
		
		REFUND_ULECARD_AMOUNT_GREATER_THAN_ULECARD_AMOUNT("9002007", "邮乐卡退款金额大于邮乐卡支付金额"),
		
		REFUND_REFUNDREQNO_FORMAT_ERROR ("9002008","退款请求流水号格式错误"),
		
		REFUND_PAYREQNO_FORMAT_ERROR ("9002009","原支付请求流水号格式错误"),
		
		REFUND_RULE_FORMAT_ERROR ("9002010","退款规则格式错误"),
		
		REFUND_RULE_REFUND_AMOUNT_TOO_LARGE ("9002011","退款规则对应的退款金额大于最大可退款金额"),
		
		REFUND_RULE_REFUND_AMOUNT_ERROR ("9002012","退款规则对应的退款金额与申请退款总金额不等"),
		
		REFUND_ULECARD_AMOUNT_ERROR("9002013", "邮乐卡退款金额错误"),
		
		REFUND_RULE_PAY_ID_REPEAT_ERROR("9002014", "退款规则对应的原交易流水号重复"),
		
		REFUND_RULE_DETAIL_NOT_RULE("9002015","退款规则明细没有对应的退款规则"),
		
		REFUND_RULE_DETAIL_NOT_FOUND_RULE("9002016","退款规则明细未找到对应的退款规则定义"),
		
		REFUND_RULE_DETAIL_AMOUNT_ERROR("9002017","退款规则明细退款金额大于对应规则金额"),
		
		REFUND_RULE_DETAIL_AMOUNT_LESS_THAN_ZERO("9002018","退款规则明细退款金额或积分必须大于0"),

		REFUND_POINT_TOO_LARGE("9002019", "总退款积分不能大于总支付积分"),

		REFUND_9002020("9002020","江苏积分专享卡支付退款，请求参数attach必填"),
		REFUND_9002021("9002021","江苏积分专享卡支付退款，卡号错误"),
		REFUND_9002022("9002022","江苏积分专享卡支付退款，退款金额超出限制"),
		REFUND_9002023("9002023","江苏积分专享卡支付退款，请求参数非法，退款金额只能为数字"),
		
		//========= 优惠券=========
		/*
		COUPON_NOT_EXIST("9003001", "券不存在"),
		COUPON_BEEN_USED("9003002", "券已被使用"),
		COUPON_NOT_EFFECT("9003003", "券未生效"),
		COUPON_EXPIRED("9003004", "券已过期"),
		COUPON_NO_VALID_GOODS("9003005", "无有效对应产品"),
		COUPON_NO_VALID_SHOP("9003006", "无有效对应店铺"),
		COUPON_ORDER_AMOUNT_INVALID("9003007", "订单金额不满足条件"),
		COUPON_USER_MISMATCH_OWNER("9003008", "使用用户与绑定用户不匹配"),
		COUPON_CHANNELCODE_NOT_EXIST("9003009", "渠道码不存在"),
		COUPON_NOT_FOUNT_WITH_CHANNELCODE("9003010", "渠道码找不到对应优惠券"),
		COUPON_CHANNELCODE_USED_BY_MOBILE("9003011", "手机已使用此渠道码"),
		COUPON_NOT_ALLOWED_IN_DENOMINATIONS("9003012", "该面额不能使用优惠券"),
		COUPON_2614("9003013", "无有效对应店铺产品限制"),
		COUPON_2615("9003014", "店铺产品总额没达到限额"),
		COUPON_2616("9003015", "产品金额没达到限额"),
		COUPON_2617("9003016", "优惠券类型不匹配"),
		COUPON_2620("9003017", "退款金额必要参数为空  "),
		COUPON_2621("9003018", "退款金额必须是数字    "),
		COUPON_2622("9003019", "退款金额必须小于Amount"),
		*/

		//========= 邮乐卡 =========
		/*
		ULE_CARD_NO_ERROR("9004001", "卡号错误"),
		ULE_CARD_PASSWORD_ERROR("9004002", "密码错误"),
		ULE_CARD_INSUFFICIENT_BALANCE("9004003", "卡余额不足"),
		ULE_CARD_HAS_BEEN_BOUND("9004004", "卡号已经被绑定"),
		ULE_CARD_PASSWORD_ERROR_OR_LIMIT("9004005", "密码错误超过当日最大错误次数限制"),
		ULE_CARD_NOT_ACTIVE("9004006", "卡未激活"),
		ULE_CARD_EXPIRED("9004007", "卡已过有效期"),
		ULE_CARD_NOT_SUITABLE_GOODS("9004008", "卡不能兑换此产品"),
		ULE_CARD_TYPE_ERROR("9004009", "卡类型不正确"),
		ULE_CARD_BEEN_USED("9004010", "卡已被兑换"),
		ULE_CARD_CANNOT_PAY("9004011", "卡类型不能进行支付"),
		ULE_CARD_NO_ACTIVE_FAILED("9004012", "号段激活失败"),
		ULE_CARD_NO_INVALID("9004013", "无效的邮乐卡号"),
		ULE_CARD_NO_NOT_EXIST("9004014", "卡号不存在"),
		ULE_CARD_PART_NO_NOT_EXIST("9004015", "部分卡号不存在"),
		ULE_CARD_EXIST_ERROR_TYPE_CARD("9004016", "存在卡类型不正确的卡"),
		ULE_CARD_EXIST_EFFECT_CARD("9004017", "存在已激活的卡"),
		ULE_CARD_INFO_MISMATCH_ORDER_INFO("9004018", "指定的卡号信息与订单明细不匹配"),
		ULE_CARD_PASSWORD_INCORRECT("9004019", "卡密码不正确"),
		ULE_CARD_ORDER_CANNOT_ACTIVE("9004020", "当前状态的订单不能激活"),
		ULE_CARD_ORDER_NOT_EXIST("9004021", "订单不存在"),
		ULE_CARD_0811("9004022", "测试订单、测试机构、测试卡不匹配"),
		ULE_CARD_0701("9004023", "销售员返利取现余额限制错误"),
		ULE_CARD_0702("9004024", "销售员返利取现金额大于卡余额"),
		ULE_CARD_0710("9004025", "有销售返利正在处理中"),
		ULE_CARD_0687("9004026", "订单金额不等于卡总金额"),
		ULE_CARD_0712("9004027", "预授权卡号重复"),
		ULE_CARD_ORDER_AMOUNT_INVALID("900428", "订单金额不满足条件"), 
		ULE_CARD_ACCOUNT_NOT_BOUND("900428", "当前账号上没有绑定任何卡"),
		ULE_CARD_0713("900430", "预授权号重复支付或不存在"),
		ULE_CARD_0676("900431", "订单金额与已保存的订单金额不相等"),
		*/

		//========= 对账单 =========
		BILL_TYPE_ERROR("9005001", "账单类型错误"),

		BILL_DATE_IS_EMPTY("9005002", "对账日期不能为空"),

		BILL_DATE_FORMAT_ERROR("9005003", "对账日期格式错误"),

		BILL_NOT_CREATE("9005004", "该日期对账单未生成"),

		//========= 商户支付 =========
		ACCESS_MODE_ERROR("9006001", "接入方式为空"),

		GATEWAY_CODE_ERROR("9006002", "网关渠道为空"),

		GATEWAY_TYPE_ERROR("9006003", "网关类型为空"),

		MERCHANT_NOT_ASSOCIATE_BANK("9006004", "订单不支持该支付方式"),

		ERROR_PAY_MODE("9001000","支付方式错误"),
		ERROR_SMS_SEND_FAILURE("9001001","短信发送失败"),
		ERROR_GET_VALIDATE_CODE_FAILURE("9001002","获取验证码失败"),
		ERROR_VALIDATE_CODE_CHECK_FAILURE("9001003","验证码校验失败"),
		ERROR_PAY_FAILURE("9001004","支付失败"),
		ERROR_ACTIVATE_FAILURE("9001005","激活失败"),
		ERROR_PWD_EMPTY("9001007","密码不能为空！"),
		ERROR_PWD_SET_FAILURE("9001008","设置密码失败！"),
		ERROR_MOBILE_EMPTY("9001009","手机号不能为空！"),
		ERROR_MOBILE_FORMAT("9001010","手机号格式有误！"),
		ERROR_VALIDATE_CODE("9001011","验证码错误！"),
		ERROR_DECRYPT("9001012","解密错误！"),

		//江苏积分卡相关错误码信息
		ERROR_JS_POINT_CARD_9003001("9003001", "卡号不能为空"),
		ERROR_JS_POINT_CARD_9003003("9003003", "日期格式有误,请按以下日期格式:YYYYMMDD"),
		ERROR_JS_POINT_CARD_9003004("9003004", "没有找到该手机号[%s]对应的绑卡信息"),
		ERROR_JS_POINT_CARD_9003005("9003005", "交易类型不能为空"),

		;
		
		private String code;
		
		private String desc;
		
		CommonErrors(String code, String desc) {
			this.code = code;
			this.desc = desc;
		}
		
		public String code(){
			return this.code;
		}
		
		public String desc(){
			return this.desc;
		}
		
	    @Override
	    public String getCode() {
	        return code;
	    }

	    @Override
	    public String getDesc() {
	        return desc;
	    }
}