package com.ideabox.tests.utils;

import org.hamcrest.Matcher;

/**
 * Created by sperin on 11/23/16.
 */
public class MatchersHelper {
    public static Matcher<Integer> is(Long value) {
        return org.hamcrest.core.Is.is(value.intValue());
    }
}
