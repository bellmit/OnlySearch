package application.service.wechat.message;

import application.filter.SysContext;
import application.model.wechat.InMsgEntity;
import org.springframework.stereotype.Component;

/**
 * @author: wtl
 * @License: (C) Copyright 2020
 * @Contact: 1050100468@qq.com
 * @Date: 2020/8/5 5:23
 * @Version: 1.0
 * @Description:
 */
@Component
public class NewsMessage implements Message{

    private final static String CONTENT = "app";

    @Override
    public String dealMessage(InMsgEntity inMsgEntity) {
        String content = inMsgEntity.getContent();
        String weChatNewsItemsStrings = SysContext.WE_CHAT_NEWS_ITEMS_STRINGS;
        StringBuilder stringBuilder = new StringBuilder();
        switch (inMsgEntity.getContent()) {
            case SysContext.TYPE_TV:
                return SysContext.WE_CHAT_NEWS_TEMPLATE
                        .replaceAll("TO_USER", inMsgEntity.getFromUserName())
                        .replaceAll("FROM_USER", inMsgEntity.getToUserName())
                        .replaceAll("CREATE_TIME", String.valueOf(System.currentTimeMillis()))
                        .replaceAll("ITEMS", weChatNewsItemsStrings.
                                replaceAll("TITLE",SysContext.TYPE_TV)
                                .replaceAll("DESCRIPTION","千度电视剧")
                                .replaceAll("PIC_URL","http://localhost/index_logo.png")
                                .replaceAll("URL","https://ljxwtl.cn/index.html#/tvResult/1"))
                        .replaceAll("COUNT","1");
            case SysContext.TYPE_MOVIE:
                return SysContext.WE_CHAT_NEWS_TEMPLATE
                        .replaceAll("TO_USER", inMsgEntity.getFromUserName())
                        .replaceAll("FROM_USER", inMsgEntity.getToUserName())
                        .replaceAll("CREATE_TIME", String.valueOf(System.currentTimeMillis()))
                        .replaceAll("ITEMS", weChatNewsItemsStrings.
                                replaceAll("TITLE",SysContext.TYPE_MOVIE)
                                .replaceAll("DESCRIPTION","千度电影")
                                .replaceAll("PIC_URL","https://ljxwtl.cn/index_logo.png")
                                .replaceAll("URL","https://localhost/index.html#/movieResult/1"))
                        .replaceAll("COUNT","1");
            case SysContext.TYPE_PICTURE:
                return SysContext.WE_CHAT_NEWS_TEMPLATE
                        .replaceAll("TO_USER", inMsgEntity.getFromUserName())
                        .replaceAll("FROM_USER", inMsgEntity.getToUserName())
                        .replaceAll("CREATE_TIME", String.valueOf(System.currentTimeMillis()))
                        .replaceAll("ITEMS", weChatNewsItemsStrings.
                                replaceAll("TITLE",SysContext.TYPE_PICTURE)
                                .replaceAll("DESCRIPTION","千度图片")
                                .replaceAll("PIC_URL","http://ljxwtl.cn/index_logo.png")
                                .replaceAll("URL","https://ljxwtl.cn/index.html#/imageResult/%E7%BE%8E%E5%A5%B3/1/60"))
                        .replaceAll("COUNT","1");

            case SysContext.TYPE_APP:
            return SysContext.WE_CHAT_NEWS_TEMPLATE
                    .replaceAll("TO_USER", inMsgEntity.getFromUserName())
                    .replaceAll("FROM_USER", inMsgEntity.getToUserName())
                    .replaceAll("CREATE_TIME", String.valueOf(System.currentTimeMillis()))
                    .replaceAll("ITEMS", weChatNewsItemsStrings.
                            replaceAll("TITLE",SysContext.TYPE_APP)
                            .replaceAll("DESCRIPTION","千度APP")
                            .replaceAll("PIC_URL","http://ljxwtl.cn/index_logo.png")
                            .replaceAll("URL","https://ljxwtl.cn/index.html#/appResult/-10/60/1"))
                    .replaceAll("COUNT","1");
            case SysContext.TYPE_MUSIC:
                return SysContext.WE_CHAT_NEWS_TEMPLATE
                        .replaceAll("TO_USER", inMsgEntity.getFromUserName())
                        .replaceAll("FROM_USER", inMsgEntity.getToUserName())
                        .replaceAll("CREATE_TIME", String.valueOf(System.currentTimeMillis()))
                        .replaceAll("ITEMS", weChatNewsItemsStrings.
                                replaceAll("TITLE",SysContext.TYPE_MUSIC)
                                .replaceAll("DESCRIPTION","千度音乐")
                                .replaceAll("PIC_URL","http://ljxwtl.cn/index_logo.png")
                                .replaceAll("URL","https://ljxwtl.cn/index.html#/musicResult/%E5%91%A8%E6%9D%B0%E4%BC%A6/1/60"))
                        .replaceAll("COUNT","1");
            case SysContext.TYPE_CARTOON:
                return SysContext.WE_CHAT_NEWS_TEMPLATE
                        .replaceAll("TO_USER", inMsgEntity.getFromUserName())
                        .replaceAll("FROM_USER", inMsgEntity.getToUserName())
                        .replaceAll("CREATE_TIME", String.valueOf(System.currentTimeMillis()))
                        .replaceAll("ITEMS", weChatNewsItemsStrings.
                                replaceAll("TITLE",SysContext.TYPE_CARTOON)
                                .replaceAll("DESCRIPTION","千度动漫")
                                .replaceAll("PIC_URL","http://ljxwtl.cn/index_logo.png")
                                .replaceAll("URL","https://ljxwtl.cn/index.html#/cartoonResult/list%2F/1/%E5%85%A8%E9%83%A8"))
                        .replaceAll("COUNT","1");
            case SysContext.TYPE_FICTION:
                return SysContext.WE_CHAT_NEWS_TEMPLATE
                        .replaceAll("TO_USER", inMsgEntity.getFromUserName())
                        .replaceAll("FROM_USER", inMsgEntity.getToUserName())
                        .replaceAll("CREATE_TIME", String.valueOf(System.currentTimeMillis()))
                        .replaceAll("ITEMS", weChatNewsItemsStrings.
                                replaceAll("TITLE",SysContext.TYPE_FICTION)
                                .replaceAll("DESCRIPTION","千度小说")
                                .replaceAll("PIC_URL","http://ljxwtl.cn/index_logo.png")
                                .replaceAll("URL","https://ljxwtl.cn/index.html#/fiction/fiction"))
                        .replaceAll("COUNT","1");
            default:
                break;
        }
        return null;
    }
}
