/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.obi.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.obi.services.util.DateUtil;
import static org.obi.services.util.DateUtil.GMTFromTimeZone;
import org.obi.services.util.Settings;
import org.obi.services.util.Util;

/**
 *
 * @author r.hendrick
 */
public class TestDadte {

    public static void main(String argc[]) {

//        Util.out(Util.errLine() + " >> timezone : " + DateUtil.localDateTimeFormattedFromZoneId(55));

//        Object o = Settings.read(Settings.CONFIG, Settings.GMT);
//        String timeZone = Settings.read(Settings.CONFIG, Settings.GMT).toString();
//        Util.out(Util.errLine() + " >> timezone : " + 55
//                + "\nGMT : " + DateUtil.GMTFromTimeZone(55)
//                + "\nFormat dtf : " + DateUtil.localDateTimeFormatted(55));

        
        for(int i = 1; i < 70; i++){
            LocalDateTime dt = LocalDateTime.now(ZoneId.of(DateUtil.zoneIdOf(i)));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS");
            
            Instant instant = dt.atZone(ZoneId.of(DateUtil.zoneIdOf(i))).toInstant();
            
            Util.out(Util.errLine() 
                + " >> [" + i + "] "
                + DateUtil.zoneIdOf(i)
                + " >> LocalDateTime : " + dt.format(dtf)
                + " >> Instant : " + instant);
        }
    }

}
