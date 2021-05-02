package com.moodright.blurryworld.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author moodright
 * @date 2021/5/2
 */

@Component
public class ChineseSegmentUtil {
    /**
     * 最大分词长度
     */
    private static int maxLength = 5;
    /**
     * 中文词典
     */
    private Set<String> chineseWordDictionary;
    /**
     * 停用词词典
     */
    private Set<String> stopWordDictionary;

    /**
     * 修改最大分词长度
     * @param maxLength 最大分词长度
     */
    public static void setMaxLength(int maxLength) {
        ChineseSegmentUtil.maxLength = maxLength;
    }

    public ChineseSegmentUtil() throws IOException {
        this.chineseWordDictionary = initialDictionary(new ClassPathResource("static/corpus/ChineseWordDic.txt").getInputStream(), "utf8");
        this.stopWordDictionary = initialDictionary(new ClassPathResource("static/corpus/SichuanUniversityStopWordDic.txt").getInputStream(), "utf8");
    }

    /**
     * 词典初始化
     * @param dictionaryPath 词典文件路径
     * @param charset 词典文件字符编码
     * @return 词典Set
     */
    public Set<String> initialDictionary(InputStream dictionaryPath, String charset) {
        Set<String> dictionary = new HashSet<String>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(dictionaryPath, charset));
            String lineInText;
            while((lineInText = br.readLine()) != null) {
                // 读取每行的词
                dictionary.add(lineInText.split(" ")[0]);
            }
            br.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    /**
     * 正向最大匹配算法：文章内容分词
     * @param postId 文章编号
     * @param cleaningPostContent 文章内容
     * @return 文章索引map
     *         TypeArguments:
     *         0 -> String: 词索引名称
     *         1 -> Integer: 文章编号
     *         2 -> Integer: 出现频率
     */
    public Map<String, Map<Integer, Integer>> segment(Integer postId, String cleaningPostContent) {
        Map<String, Map<Integer, Integer>> postIndexMap = new HashMap<>();

        int begin = 0, end;
        int len = cleaningPostContent.length();
        while(begin < len) {
            end = begin + maxLength;
            if(end > len) end = len;
            // 不匹配指针前移
            while(begin < end && !chineseWordDictionary.contains(cleaningPostContent.substring(begin,end))) {
                end--;
            }
            // 一个字做省略处理
            if(begin == end) {
                end++;
            }
            // 命中两个字及以上的词
            if(end - begin >= 2) {
                String str = cleaningPostContent.substring(begin, end);
                Map<Integer, Integer> map = new HashMap<>();
                // 计算出现频率
                if(!postIndexMap.containsKey(str)) {
                    map.put(postId, 1);
                }else {
                    int i = postIndexMap.get(str).get(postId);
                    map.put(postId, ++i);
                }
                // 存储该词信息
                postIndexMap.put(str, map);
            }
            begin = end;
        }
        return postIndexMap;
    }

    /**
     * 原始文章数据清洗
     * 去除分词时标点符号、换行符、停用词
     * @param originalPostContent 原始的文章字符串
     * @return 清洗后的文章字符串
     */
    public String originalPostContentCleaning(String originalPostContent) {
        // 去除字母、数字、汉字以外的字符
        String cleaningPostContent = originalPostContent.replaceAll("[^A-Za-z0-9\\u4e00-\\u9fa5]", "");
        // 去除停用词
        for ( String stopWord : stopWordDictionary) {
            if(cleaningPostContent.contains(stopWord)) {
                cleaningPostContent = cleaningPostContent.replaceAll(stopWord, "");
            }
        }
        return cleaningPostContent;
    }


}
