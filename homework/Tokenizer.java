package Lesson5_03_10_2015.homework;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {

    private Matcher[] matchers;
    private int curIndex;
    private int border;
    private Token curToken;

    public static class Token {
        private int type;
        private String value;

        public Token(int type, String value) {
            this.type = type;
            this.value = value;
        }

        public int getType() {
            return type;
        }

        public String getValue() {
            return value;
        }
    }

    public Tokenizer(String input, Pattern... patterns) {
        setPattern(input, patterns);
    }

    public void setInput(String input) {
        this.curIndex = 0;
        this.border = input == null ? 0 : input.length();
        for (Matcher m : matchers) {
            m.reset(input);
        }
    }

    public void setPattern(String input, Pattern... patterns) {
        this.curIndex = 0;
        this.border = input == null ? 0 : input.length();
        fillMatchers(input, patterns);
    }

    private void fillMatchers(String inputSeq, Pattern[] patterns) {
        if (inputSeq == null || patterns == null) {
            return;
        }
        int count = 0;
        matchers = new Matcher[patterns.length];
        for (Pattern p : patterns) {
            matchers[count++] = p.matcher(inputSeq);
        }
    }

    public Token currentToken(){
        return curToken;
    }

    public Token nextToken() {
        int nearestIndex, nearestMatcher = 0;
        String value;
        nearestIndex = border;
        for (int i = 0; i < matchers.length; i++) {
            if (matchers[i].find(curIndex) && matchers[i].start() < nearestIndex) {
                nearestMatcher = i;
                nearestIndex = matchers[i].start();
            }
        }
        if (nearestIndex == border) {
            curIndex = border;
            return curToken = null;
        }
        curIndex = matchers[nearestMatcher].end();
        value = matchers[nearestMatcher].group();
        return curToken = new Token(nearestMatcher, value);
    }
}