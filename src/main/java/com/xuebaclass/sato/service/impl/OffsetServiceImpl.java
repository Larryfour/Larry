package com.xuebaclass.sato.service.impl;

import com.xuebaclass.sato.exception.CrmException;
import com.xuebaclass.sato.mapper.crm.OffsetMapper;
import com.xuebaclass.sato.model.Offset;
import com.xuebaclass.sato.service.OffsetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

@Transactional
@Service
public class OffsetServiceImpl implements OffsetService {
    private static final Logger logger = LoggerFactory.getLogger(OffsetServiceImpl.class);


    @Autowired
    private OffsetMapper offsetMapper;


    @Override
    public void create(Offset offset) throws Exception {
        try {
            Optional.ofNullable(offset.getSalesId()).orElseThrow(() -> CrmException.newException("销售id为必须参数，不能为空！"));
            Optional.ofNullable(offset.getOffset()).orElseThrow(() -> CrmException.newException("抵消时长为必须参数，不能为空！"));
            Optional.ofNullable(offset.getOffsetDate()).orElseThrow(() -> CrmException.newException("抵消日期为必须参数，不能为空！"));

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(offset.getOffsetDate());
            calendar.add(Calendar.DATE, -1);

            String yesterday = format.format(calendar.getTime());

            Offset yesterdayOffset = offsetMapper.getByDate(offset.getSalesId(), yesterday);

            Optional.ofNullable(yesterdayOffset).orElseThrow(() -> CrmException.newException("剩余时长不足！"));

            if (yesterdayOffset.getOffsetAfter().compareTo(offset.getOffset()) < 0) {
                throw new CrmException("剩余时长不足！");
            }

            offsetMapper.create(offset);

        } catch (Exception e) {
            throw new CrmException(e.getMessage());
        }
    }

    @Override
    public Offset getByDate(Integer salesId, String offsetDate) {
        logger.info("sales id:[" + salesId + "], offset date[" + offsetDate + "]");

        Offset offset = null;
        try {
            offset = offsetMapper.getByDate(salesId, offsetDate);
        } catch (Exception e) {
            throw CrmException.newException(e.getMessage());
        }

        return offset;
    }

}
