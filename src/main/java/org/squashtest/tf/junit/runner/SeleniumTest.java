/**
 *     This file is part of the Squashtest platform.
 *     Copyright (C) 2018 - 2019 Henix
 *
 *     See the NOTICE file distributed with this work for additional
 *     information regarding copyright ownership.
 *
 *     This is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     this software is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with this software.  If not, see <http://www.gnu.org/licenses />.
 */
package org.squashtest.tf.junit.runner;

import java.io.File;
import java.util.concurrent.TimeUnit;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {
    private static WebDriver driver;
    WebElement element;

    @BeforeClass
    public static void openBrowser(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void canConnectToRedmine(){

        System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println(driver);
        driver = new FirefoxDriver();
        System.out.println(driver);
        driver.get("http://localhost:8000/");

        // Page d'accueil //
        driver.findElement(By.className("login")).click();

        // Page de connexion //
        driver.findElement(By.name("username")).sendKeys("test_admin");
        driver.findElement(By.name("password")).sendKeys("test_admin");
        driver.findElement(By.name("login")).click();

        // Page d'accueil //
        String loggedas = driver.findElement(By.id("loggedas")).getText();
        String user = driver.findElement(By.className("user active")).getText();

        Assert.assertSame("", "Connecté en tant que test_admin", loggedas + user);

        System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @AfterClass
    public static void closeBrowser(){
        driver.quit();
    }
}