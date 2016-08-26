package me.wh.stock.admin.Const;

import java.util.HashMap;
import java.util.Map;

public class TicketConstant {

    public static Map<String, String> domainMap = new HashMap<String, String>();
    public static Map<String, String> pageMap = new HashMap<String, String>();

    static {
        pageMap.put("fd", "index.phtml");
        pageMap.put("dl", "downxls.php");
        pageMap.put("jv", "json_v2.php");
        pageMap.put("cpt", "newFLJK.php");
        pageMap.put("ids", "newSinaHy.php");
        pageMap.put("lnews", "rollnews_ch_out_interface.php");
        pageMap.put("ntinfo", "vCB_BulletinGather.php");
        pageMap.put("hs300b", "000300cons.xls");
        pageMap.put("hs300w", "000300closeweight.xls");
        pageMap.put("sz50b", "000016cons.xls");
        pageMap.put("dp", "all_fpya.php");
        pageMap.put("163dp", "fpyg.html");
        pageMap.put("emxsg", "JS.aspx");
        pageMap.put("163fh", "jjcgph.php");
        pageMap.put("newstock", "vRPD_NewStockIssue.php");
        pageMap.put("zz500b", "000905cons.xls");
        pageMap.put("zz500wt", "000905closeweight.xls");
        pageMap.put("t_ticks", "vMS_tradedetail.php");
        pageMap.put("dw", "downLoad.html");
        pageMap.put("qmd", "queryMargin.do");
        pageMap.put("szsefc", "ShowReport.szse");
        pageMap.put("ssecq", "commonQuery.do");
        pageMap.put("sinadd", "cn_bill_download.php");
        pageMap.put("ids_sw", "SwHy.php");

        domainMap.put("sina", "sina.com.cn");
        domainMap.put("sinahq", "sinajs.cn");
        domainMap.put("ifeng", "ifeng.com");
        domainMap.put("sf", "finance.sina.com.cn");
        domainMap.put("vsf", "vip.stock.finance.sina.com.cn");
        domainMap.put("idx", "www.csindex.com.cn");
        domainMap.put("163", "money.163.com");
        domainMap.put("em", "eastmoney.com");
        domainMap.put("sseq", "query.sse.com.cn");
        domainMap.put("sse", "www.sse.com.cn");
        domainMap.put("szse", "www.szse.cn");
        domainMap.put("oss", "www.shibor.org");
        domainMap.put("idxip", "115.29.204.48");
        domainMap.put("shibor", "www.shibor.org");
        domainMap.put("mbox", "www.cbooo.cn");
    }

    public static String TICK_PRICE_URL = "%smarket.%s/%s?date=%s&symbol=%s";
    public static String TODAY_TICKS_PAGE_URL = "%s%s/quotes_service/api/%s/CN_Transactions.getAllPageTime?date=%s&symbol=%s";
    public static String TODAY_TICKS_URL = "%s%s/quotes_service/view/%s?symbol=%s&date=%s&page=%s";
    public static String DAY_PRICE_URL = "%sapi.finance.%s/%s/?code=%s&type=last";
    public static String LIVE_DATA_URL = "%shq.%s/rn=%s&list=%s";
    public static String DAY_PRICE_MIN_URL = "%sapi.finance.%s/akmin?scode=%s&type=%s";
    public static String SINA_DAY_PRICE_URL = "%s%s/quotes_service/api/%s/Market_Center.getHQNodeData?num=80&sort=changepercent&asc=0&node=hs_a&symbol=&_s_r_a=page&page=%s";
    public static String REPORT_URL = "%s%s/q/go.php/vFinanceAnalyze/kind/mainindex/%s?s_i=&s_a=&s_c=&reportdate=%s&quarter=%s&p=%s&num=%s";
    public static String FORECAST_URL = "%s%s/q/go.php/vFinanceAnalyze/kind/performance/%s?s_i=&s_a=&s_c=&s_type=&reportdate=%s&quarter=%s&p=%s&num=%s";
    public static String PROFIT_URL = "%s%s/q/go.php/vFinanceAnalyze/kind/profit/%s?s_i=&s_a=&s_c=&reportdate=%s&quarter=%s&p=%s&num=%s";
    public static String OPERATION_URL = "%s%s/q/go.php/vFinanceAnalyze/kind/operation/%s?s_i=&s_a=&s_c=&reportdate=%s&quarter=%s&p=%s&num=%s";
    public static String GROWTH_URL = "%s%s/q/go.php/vFinanceAnalyze/kind/grow/%s?s_i=&s_a=&s_c=&reportdate=%s&quarter=%s&p=%s&num=%s";
    public static String DEBTPAYING_URL = "%s%s/q/go.php/vFinanceAnalyze/kind/debtpaying/%s?s_i=&s_a=&s_c=&reportdate=%s&quarter=%s&p=%s&num=%s";
    public static String CASHFLOW_URL = "%s%s/q/go.php/vFinanceAnalyze/kind/cashflow/%s?s_i=&s_a=&s_c=&reportdate=%s&quarter=%s&p=%s&num=%s";

    public static String SHIBOR_DATA_URL = "%s%s/shibor/web/html/%s?nameNew=Historical_%s_Data_%s.xls&downLoadPath=data&nameOld=%s%s.xls&shiborSrc=http://www.shibor.org/shibor/";
    public static String SINA_CONCEPTS_INDEX_URL = "%smoney.%s/q/view/%s?param=class";
    public static String SINA_INDUSTRY_INDEX_URL = "%s%s/q/view/%s";
    public static String SINA_DATA_DETAIL_URL = "%s%s/quotes_service/api/%s/Market_Center.getHQNodeData?page=1&num=1000&sort=symbol&asc=1&node=%s&symbol=&_s_r_a=page";
    public static String INDEX_C_COMM = "sseportal/ps/zhs/hqjt/csi";
    public static String HS300_CLASSIFY_URL_FTP = "%s%s/webdata/%s";
    public static String HS300_CLASSIFY_URL_HTTP = "%s%s/%s/%s";
    public static String HIST_FQ_URL = "%s%s/corp/go.php/vMS_FuQuanMarketHistory/stockid/%s.phtml?year=%s&jidu=%s";
    public static String HIST_INDEX_URL = "%s%s/corp/go.php/vMS_MarketHistory/stockid/%s/type/S.phtml?year=%s&jidu=%s";
    public static String HIST_FQ_FACTOR_URL = "%s%s/api/json.php/BasicStockSrv.getStockFuQuanData?symbol=%s&type=hfq";
    public static String INDEX_HQ_URL = "%shq.%s/rn=xppzh&list=sh000001,sh000002,sh000003,sh000008,sh000009,sh000010,sh000011,sh000012,sh000016,sh000017,sh000300,sh000905,sz399001,sz399002,sz399003,sz399004,sz399005,sz399006,sz399008,sz399100,sz399101,sz399106,sz399107,sz399108,sz399333,sz399606";
    public static String SSEQ_CQ_REF_URL = "%s%s/assortment/stock/list/name";
    public static String ALL_STK_URL = "%s%s/all.csv";
    public static String SINA_DD = "%s%s/quotes_service/view/%s?symbol=%s&num=60&page=1&sort=ticktime&asc=0&volume=%s&amount=0&type=0&day=%s";
    public static String BOX = "boxOffice";
    public static String MOVIE_BOX = "%s%s/%s/GetHourBoxOffice?d=%s";
    public static String BOXOFFICE_DAY = "%s%s/%s/GetDayBoxOffice?num=%s&d=%s";
    public static String BOXOFFICE_MONTH = "%s%s/%s/getMonthBox?sdate=%s";

}
