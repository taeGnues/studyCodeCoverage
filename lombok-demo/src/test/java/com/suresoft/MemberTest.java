package com.suresoft;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class MemberTest extends TestCase {

    @Test
    public void test() {
        Member member = new Member();
        member.setName("taeseung");
        Assert.assertEquals("taeseung", member.getName());
    }

}