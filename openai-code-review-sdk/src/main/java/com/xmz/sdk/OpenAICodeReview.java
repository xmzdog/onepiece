package com.xmz.sdk;

import com.alibaba.fastjson2.JSON;
import com.xmz.sdk.infrastructure.git.GitCommand;
import com.xmz.sdk.infrastructure.openai.IOpenai;
import com.xmz.sdk.infrastructure.openai.dto.ChatCompletionRequestDTO;
import com.xmz.sdk.infrastructure.openai.dto.ChatCompletionSyncResponseDTO;
import com.xmz.sdk.infrastructure.openai.impl.ChatGLM;
import com.xmz.sdk.infrastructure.weixin.WeiXin;
import com.xmz.sdk.infrastructure.weixin.dto.TemplateMessageDTO;
import com.xmz.sdk.model.Model;
import com.xmz.sdk.service.impl.OpenAiCodeReviewServiceImpl;
import com.xmz.sdk.utils.ChatGLMToken;
import com.xmz.sdk.utils.WXAccessTokenUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: xiangmz
 * Date: 2025/4/17
 * Time: 22:06
 * Description: No Description
 */
public class OpenAICodeReview {

    private static final Logger logger = LoggerFactory.getLogger(OpenAICodeReview.class);



    public static void main(String[] args) throws Exception {
        GitCommand gitCommand = new GitCommand(
                getEnv("GITHUB_REVIEW_LOG_URI"),
                getEnv("GITHUB_TOKEN"),
                getEnv("COMMIT_PROJECT"),
                getEnv("COMMIT_BRANCH"),
                getEnv("COMMIT_AUTHOR"),
                getEnv("COMMIT_MESSAGE")
        );

        /**
         * 项目：{{repo_name.DATA}} 分支：{{branch_name.DATA}} 作者：{{commit_author.DATA}} 说明：{{commit_message.DATA}}
         */
        WeiXin weiXin = new WeiXin(
                getEnv("WEIXIN_APPID"),
                getEnv("WEIXIN_SECRET"),
                getEnv("WEIXIN_TOUSER"),
                getEnv("WEIXIN_TEMPLATE_ID")
        );



        IOpenai openAI = new ChatGLM(getEnv("CHATGLM_APIHOST"), getEnv("CHATGLM_APIKEYSECRET"));

        OpenAiCodeReviewServiceImpl openAiCodeReviewService = new OpenAiCodeReviewServiceImpl(gitCommand, openAI, weiXin);
        openAiCodeReviewService.exec();

        logger.info("openai-code-review done!");
    }

    private static String getEnv(String key) {
        String value = System.getenv(key);
        if (null == value || value.isEmpty()) {
            throw new RuntimeException("value is null");
        }
        return value;
    }







}
