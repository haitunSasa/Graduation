/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.graduation.api;

public class ApiConstants {
    public static final String URL="http://192.168.1.107:8333/BHGD/";

    public static final String USER_LOGIN="user_login";

    //http://localhost:8333/BHGD/user_register
    public static final String USER_REGISTER="user_register";

    //http://localhost:8333/BHGD/user_getByUserName?userName=hh
    public static final String GET_BY_USER_NAME="user_getByUserName";

    //http://localhost:8333/BHGD/answer_getAnswer?questionId=10&userId=3
    public static final String GET_ANSWER="answer_getAnswer";

    //http://localhost:8333/BHGD/question_getQuestionDetail?questionId=14
    public static final String GET_QUESTION_DETAIL="question_getQuestionDetail";

    //提问
    // http://localhost:8333/BHGD/question_askQuestion
    public static final String ASK_QUESTION="question_askQuestion";

    //回答问题
    // http://localhost:8333/BHGD/answer_answerQuestion
    public static final String ANSWER_QUESTION="answer_answerQuestion";

    //获取最新问题列表
    // http://localhost:8333/BHGD/question_getLastQuestions
    public static final String GET_LAST_QUESTION="question_getLastQuestions";

    //申请认证
    // http://localhost:8333/BHGD/user_applyAuthenticate?userId=3&typeId=1
    public static final String USER_APPLY_AUTHENTICATE="user_applyAuthenticate";



    public static final String NETEAST_HOST = "http://c.m.163.com/";
    // 头条TYPE
    public static final String HEADLINE_TYPE = "headline";
    // 房产TYPE
    public static final String HOUSE_TYPE = "house";
    // 其他TYPE
    public static final String OTHER_TYPE = "list";


    /**
     * 新浪图片新闻
     * http://gank.io/api/data/福利/{size}/{page}
     */
    public static final String SINA_PHOTO_HOST = "http://gank.io/api/";



    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    public static String getHost(int hostType) {
        String host;
        switch (hostType) {
            case HostType.URL_TEST:
                host = URL;
                break;
            case HostType.URL:
                host = SINA_PHOTO_HOST;
                break;
            default:
                host = "";
                break;
        }
        return host;
    }
}
