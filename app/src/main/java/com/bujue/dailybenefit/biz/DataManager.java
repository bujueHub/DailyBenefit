package com.bujue.dailybenefit.biz;

import android.content.Context;

import com.bujue.dailybenefit.callback.Callback;
import com.bujue.dailybenefit.callback.MainThreadCallback;
import com.bujue.dailybenefit.entity.ContentItem;
import com.bujue.dailybenefit.utils.Logger;
import com.bujue.dailybenefit.utils.http.HttpUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * @author bujue
 * @date 16/12/13
 */
public class DataManager extends BaseManager {

    private static DataManager mInstance;

    private DataManager() {
    }

    //单例模式
    public static DataManager getInstance() {
        if (mInstance == null) {
            synchronized (DataManager.class) {
                if (mInstance == null) {
                    mInstance = new DataManager();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void init(Context context) {
        HttpUtil.getInstance().init();
    }

    @Override
    public void destroy() {
        HttpUtil.getInstance().destroy();
    }

    /**
     * 获取妹子系列列表
     * @param url
     * @param callback
     */
    public void getMeiziContentList(final String url,final Callback<List<ContentItem>> callback){
        Logger.d(TAG,"url = "+url);
        HttpUtil.getInstance().get(url, new Callback<String>() {
            @Override
            public void onSuccess(String result) {
                List<ContentItem> list = parserMainBean(result);
                MainThreadCallback.onSuccess(callback, list);
            }

            @Override
            public void onException(int code, String reason) {

            }
        });
    }

    private List<ContentItem> parserMainBean(String html) {
        List<ContentItem> contentBeanList = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements links = doc.select("li");//.select("a[target]");

        Element aelement;
        Element imgelement;
        for (int i = 7; i < links.size(); i++) {
            imgelement = links.get(i).select("img").first();
            aelement = links.get(i).select("a").first();
            ContentItem bean = new ContentItem();
            bean.setOrder(i);

            bean.setTitle(imgelement.attr("alt").toString());
            bean.setType("");
            bean.setHeight(354);//element.attr("height")
            bean.setWidth(236);
            bean.setImageurl(imgelement.attr("data-original"));
            bean.setUrl(aelement.attr("href"));
            int groupId = 1;
            bean.setGroupid(groupId);//首页的这个是从大到小排序的 可以当做排序依据
//            list.add(bean);
//            List<MeiziItem> block = getContent(task, bean.getUrl(), groupId, tag);
//            if (firstList.size() > 0) {
//                block.removeAll(firstList);
//            }
//            if(isFirst){
//            }
            contentBeanList.add(bean);
        }
        return contentBeanList;
    }
//
//    private List<ContentBean> getContent(Task task, String url, int groupid, Object tag) {
//        LogUtils.i("getcontent url=" + url);
//        List<ContentBean> list = new ArrayList<>();
//        HttpResponse httpResponse = HttpSender.instance().getSync(url, null, null, tag);
//        String html = httpResponse.string();
//        if (html != null) {
//            int mcount = getCount(html);
//            for (int i = 1; i < mcount + 1; i++) {
//                ContentBean content = null;
//                content = fetchContent(url + "/" + i, tag);
//                if (content != null) {
//                    content.setOrder(groupid + i);
//                    content.setGroupid(groupid);
//                    list.add(content);
//                }
//                if (list.size() >= 20 && isFirst) {
//                    isFirst = false;
//                    firstList.addAll(list);
//                    task.notifyLoading(list);
//                }
//            }
//        }
//        return list;
//    }
//
//    private int getCount(String html) {
//        Document doc = Jsoup.parse(html);
//        Elements pages = doc.select("span");
//        Element page = pages.get(10);
//
//        Pattern p = Pattern.compile("[\\d*]");
//        Matcher m = p.matcher(page.toString());
//        StringBuffer stringBuffer = new StringBuffer();
//        while (m.find()) {
//            stringBuffer.append(m.group());
//        }
//        return Integer.parseInt(stringBuffer.toString());
//    }
//
//    private MeiziItem fetchContent(String url, Object tag) {
//        String html;
//        HttpResponse httpResponse = HttpSender.instance().getSync(url, null, null, tag);
//        if (httpResponse != null) {
//            html = httpResponse.string();
//            if (html != null) {
//                ContentBean content = ParserContent(html);//这里解析获取的HTML文本
//                return content;
//            }
//        }
////
////        //其实这里不用再去解析bitmap，从HTML可以解析到的。。。至于为什么不去解析，我也不知道我当时怎么想的。。
////        Response response = client.newCall(new Request.Builder().url(content.getUrl()).build()).execute();
////        BitmapFactory.Options options = new BitmapFactory.Options();
////        options.inJustDecodeBounds = true;
////        BitmapFactory.decodeStream(response.body().byteStream(), null, options);
////        content.setImagewidth(options.outWidth);
////        content.setImageheight(options.outHeight);
//
//        return null;
//    }
//
//    private ContentBean ParserContent(String html) {
//        ContentBean content = new ContentBean();
//        Document doc = Jsoup.parse(html);
//        Elements links = doc.select("img[src~=(?i)\\.(png|jpe?g)]");
//        if (links.size() == 0) {
//            return null;
//        }
//        Element element = links.get(0).getElementsByTag("img").first();
//        content.setUrl(element.attr("src"));
//        content.setTitle(element.attr("alt"));
//        return content;
//    }
}
