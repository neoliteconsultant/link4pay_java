package io.link4pay.model;

public class HostedPayment {
    private Response response;

    public class Response{
        private String action;
        private Data data;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }
    }

    public class Data{
        private String apiKey;
        private String lang;
        private String payLoad;

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getPayLoad() {
            return payLoad;
        }

        public void setPayLoad(String payLoad) {
            this.payLoad = payLoad;
        }
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
