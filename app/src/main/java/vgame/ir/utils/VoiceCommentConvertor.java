package vgame.ir.utils;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vgame.ir.data.model.Paragraph;

public class VoiceCommentConvertor {

    private Map<String, String> map;

    public static List<Paragraph> convertVoiceComment2(String comment_str) throws Exception {
        List<Paragraph> paragraphs = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(comment_str);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject(jsonArray.get(i).toString());
                Paragraph paragraph = new Paragraph();
                paragraph.setText(jsonObject.get("text").toString());
                paragraph.setTime(jsonObject.get("time").toString());
                paragraph.setTimeSec(UtilMethods.getConvertedTime(jsonObject.get("time").toString()));

                paragraphs.add(paragraph);
            }
        } catch (Exception e) {

        }

        return paragraphs;
    }

    public static List<Paragraph> convertVoiceComment(String comment_str) throws Exception {
        if (comment_str == null) {
            return null;
        }
        List<Paragraph> paragraphs = new ArrayList<>();

        comment_str += "<time>";

        String[] time = StringUtils.substringsBetween(comment_str, "<time>", "</time>");
        String[] text = StringUtils.substringsBetween(comment_str, "</time>", "<time>");
        String[] h = StringUtils.substringsBetween(comment_str, "<h>", "</h>");
        String[] p = StringUtils.substringsBetween(comment_str, "<p>", "</p>");

        if (time == null || time.length == 0) {
            Paragraph paragraph = new Paragraph();
            paragraph.setTime("0");
            comment_str.replace("<time>", "");
            paragraph.setText(comment_str);
            paragraph.setTimeSec(UtilMethods.getConvertedTime("00:00:00"));

            paragraphs.add(paragraph);
        } else {
            for (int i = 0; i < time.length; i++) {
                Paragraph paragraph = new Paragraph();
                paragraph.setTime(time[i]);
                paragraph.setText(text[i]);
                paragraph.setTimeSec(UtilMethods.getConvertedTime(time[i]));

                paragraphs.add(paragraph);
            }
        }

        return paragraphs;
    }
}
