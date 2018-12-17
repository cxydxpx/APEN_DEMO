package com.apen.simple.activity;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * 作者 Y_MS
 * Created by ${APEN} on 2018-07-06.
 * GitHub：https://github.com/cxydxpx
 */
public class TestActivityTest {

    private TestActivity mActivity;

    @BeforeClass
    public static void beforeAll(){
        System.out.println("所有方法执行前");
    }

    @AfterClass
    public static void afterAll(){
        System.out.println("所有方法执行后");
    }


    @Before
    public void setup(){
        mActivity = new TestActivity();
                }

@Test
public void addTest(){
        int sum = mActivity.add(1, 3);
        assertEquals(6,sum);
        }

@Test
public void reduceTest(){
        int sum = mActivity.reduce(6, 2);
        assertEquals(6,sum);
        }

        }