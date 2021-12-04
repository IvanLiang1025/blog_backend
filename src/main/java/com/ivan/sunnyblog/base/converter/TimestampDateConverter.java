package com.ivan.sunnyblog.base.converter;

import org.jooq.Converter;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Author: jinghaoliang
 * Date: 2021-10-01 10:16 a.m.
 **/

public class TimestampDateConverter implements Converter<Timestamp, Date> {

    @Override
    public Date from(Timestamp timestamp) {
        return timestamp == null ? null : new Date(timestamp.getTime());
    }

    @Override
    public Timestamp to(Date date) {
        return date == null ? null :  new Timestamp(date.getTime());
    }

    @Override
    public Class<Timestamp> fromType() {
        return Timestamp.class;
    }

    @Override
    public Class<Date> toType() {
        return Date.class;
    }
}
