package application.filter;

public class SysContext {
	/**
	 * 全局access_token
	 */
	public static String ACCESS_TOKEN;

	/**
	 * 微信公众号的appId和AppSecret
	 */
	public static final String AppID = "wx3670540bf9bd6c3b";
	public static final String AppSecret = "91607abedaecda24e943f1383500a37a";

	public static final String PREFIX_URL = "https://ljxwtl.cn";

	public static final String BILIBILI_URL_PREFIX = "https://www.bilibili.com/";

	public static final String BLANK_STRING = "";

	/**
	 * bilibili的热门标签的尝试次数
	 */
	public static final int TRY_COUNT = 20;

	/**
	 * 上传文件的路径
	 */
	public static final String UPLOAD_FILE_PATH = "/mine/upload/";

	/**
	 * method : post
	 */
	public static final String METHOD_POST = "POST";

	/**
	 * method : get
	 */
	public static final String METHOD_GET = "GET";

	/**
	 * 编码格式
	 */
	public static final String ENCODING = "utf-8";

	/**
	 * content-type
	 */
	public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";

	/**
	 * 默认的content-type
	 */
	public static final String CONTENT_TYPE_DEFAULT = "application/json";

	/**
	 * 哔哩哔哩app注册使用的appkey
	 */
	public static final String BILIBILI_APPKEY = "1d8b6e7d45233436";

	/**
	 * 哔哩哔哩app注册使用的access_key
	 */
	public static final String BILIBILI_ACCESS_KEY = "c1f8e374c72109de5c5e3db2b8038031";

	public static final String BILIBILI_URL = "https://www.bilibili.com/&aid=AID&cid=CID";

	public static final String IQIYI_URL = "http://www.iqiyi.com/";

	public static final String UNDEFINED_STRING = "Undefined";

	public static final String YOUKU_VIDEO_URL = "https://v.youku.com/v_show/id_YOUKUVIDEO.html";

	public static final String DEFAULT_NUMBER = "0";

	/**
	 * 图灵机器人的api token
	 */
	public static final String TU_LING_API_TOKEN = "0de5b231637b4bfba670fbc82ba9d43c";

	/**
	 * 图灵机器人的userId
	 */
	public static final String TU_LING_USER_ID = "1234567890";

	public static final String TU_LING_REQUEST_CONTENT = "{\n" +
			"\t\t\t\"reqType\":0,\n" +
			"\t\t\t\"perception\": {\n" +
			"\t\t\"inputText\": {\n" +
			"\t\t\t\"text\": \"MESSAGE\"\n" +
			"\t\t}\n" +
			"\t},\n" +
			"\t\t\t\"userInfo\": {\n" +
			"\t\t\"apiKey\": \"API_KEY\",\n" +
			"\t\t\t\t\"userId\": \"USER_ID\"\n" +
			"\t}\n" +
			"}";


	public static final String WE_CHAT_TEXT_MESSAGE_TEMPLATE = "<xml>\n" +
			"  <ToUserName><![CDATA[TO_USER]]></ToUserName>\n" +
			"  <FromUserName><![CDATA[FROM_USER]]></FromUserName>\n" +
			"  <CreateTime>CREATE_TIME</CreateTime>\n" +
			"  <MsgType><![CDATA[text]]></MsgType>\n" +
			"  <Content><![CDATA[CONTENT]]></Content>\n" +
			"</xml>";

	public static final String WE_CHAT_NEWS_TEMPLATE = "<xml>\n" +
			"  <ToUserName><![CDATA[TO_USER]]></ToUserName>\n" +
			"  <FromUserName><![CDATA[FROM_USER]]></FromUserName>\n" +
			"  <CreateTime>CREATE_TIME</CreateTime>\n" +
			"  <MsgType><![CDATA[news]]></MsgType>\n" +
			"  <ArticleCount>COUNT</ArticleCount>\n" +
			"  <Articles>\n" +
			"ITEMS" +
			"  </Articles>\n" +
			"</xml>";

	public static final String WE_CHAT_NEWS_ITEMS_STRINGS = "<item>\n" +
			"      <Title><![CDATA[TITLE]]></Title>\n" +
			"      <Description><![CDATA[DESCRIPTION]]></Description>\n" +
			"      <PicUrl><![CDATA[PIC_URL]]></PicUrl>\n" +
			"      <Url><![CDATA[URL]]></Url>\n" +
			"    </item>\n";

	public static final String TYPE_TV = "电视剧";
	public static final String TYPE_MOVIE = "电影";
	public static final String TYPE_PICTURE = "图片";
	public static final String TYPE_APP = "app";
	public static final String TYPE_MUSIC = "音乐";
	public static final String TYPE_CARTOON = "动漫";
	public static final String TYPE_FICTION = "小说";


}
