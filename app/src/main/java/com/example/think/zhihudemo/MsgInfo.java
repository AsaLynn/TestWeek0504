package com.example.think.zhihudemo;

import java.util.List;

/**
 * Created by think on 2017/12/21.
 */

public class MsgInfo {

    /**
     * date : 20171221
     * stories : [{"ga_prefix":"122116","id":9661794,"images":["https://pic2.zhimg.com/v2-e8235324db202b445aa144f0534d1c11.jpg"],"title":"这玩意常年称霸中老年朋友圈，我猜你也没少听过它的名字","type":0}]
     * top_stories : [{"ga_prefix":"122107","id":9661781,"image":"https://pic3.zhimg.com/v2-f5a26048e89561e0bf46ff0964f40486.jpg","title":"「生男生女几率各一半」，这其实是个错误的认识","type":0}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        @Override
        public String toString() {
            return "StoriesBean{" +
                    "ga_prefix='" + ga_prefix + '\'' +
                    ", id=" + id +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", images=" + images +
                    '}';
        }

        /**
         * ga_prefix : 122116
         * id : 9661794
         * images : ["https://pic2.zhimg.com/v2-e8235324db202b445aa144f0534d1c11.jpg"]
         * title : 这玩意常年称霸中老年朋友圈，我猜你也没少听过它的名字
         * type : 0
         */



        private String ga_prefix;
        private int id;
        private String title;
        private int type;
        private List<String> images;

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        @Override
        public String toString() {
            return "TopStoriesBean{" +
                    "ga_prefix='" + ga_prefix + '\'' +
                    ", id=" + id +
                    ", image='" + image + '\'' +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    '}';
        }

        /**
         * ga_prefix : 122107
         * id : 9661781
         * image : https://pic3.zhimg.com/v2-f5a26048e89561e0bf46ff0964f40486.jpg
         * title : 「生男生女几率各一半」，这其实是个错误的认识
         * type : 0
         */


        private String ga_prefix;
        private int id;
        private String image;
        private String title;
        private int type;

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }

    @Override
    public String toString() {
        return "MsgInfo{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }
}
