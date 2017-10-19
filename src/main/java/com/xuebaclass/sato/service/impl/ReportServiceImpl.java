package com.xuebaclass.sato.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.xuebaclass.sato.exception.CrmException;
import com.xuebaclass.sato.mapper.crm.ReportMapper;
import com.xuebaclass.sato.mapper.sato.CourseMapper;
import com.xuebaclass.sato.mapper.sato.OrderMapper;
import com.xuebaclass.sato.mapper.sato.SalesMapper;
import com.xuebaclass.sato.model.Sales;
import com.xuebaclass.sato.model.request.SalesDailyMyselfRequest;
import com.xuebaclass.sato.model.response.SalesDailyResponse;
import com.xuebaclass.sato.service.ReportService;
import com.xuebaclass.sato.utils.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.xuebaclass.sato.utils.Utils.parseUtc2Local;
import static java.util.Objects.nonNull;

/**
 * Created by sunhao on 2017-10-13.
 */
@Transactional
@Service
public class ReportServiceImpl implements ReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    SalesMapper salesMapper;

    @Autowired
    ReportMapper reportMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    OrderMapper orderMapper;

    @Override
    public SalesDailyResponse salesDaily(SalesDailyMyselfRequest request) throws Exception {

        if (StringUtils.isEmpty(request.getFrom()) || StringUtils.isEmpty(request.getTo())) {
            throw CrmException.newException("查询区间不能为空!");
        }

        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(parseUtc2Local(request.getFrom()));
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(parseUtc2Local(request.getTo()));

        logger.info("startDate:" + startDate + "endDate:" + endDate);

        Sales sales = salesMapper.getSalesByUserName(CurrentUser.getInstance().getCurrentAuditorName());
        request.setSalesId(sales.getId());

        Map<String, Map> distributedCountsMap = new HashMap();
        Map<String, Map> reservedCoursesCountsMap = new HashMap();
        Map<String, Map> completedCoursesCountsMap = new HashMap();
        Map<String, Map> firstOrdersCountsMap = new HashMap();
        Map<String, Map> repeatOrdersCountsMap = new HashMap();

        List<Map> distributedCounts = reportMapper.getDistributedCount(request);
        distributedCounts.forEach(d -> {
            distributedCountsMap.put(d.get("DIS_DATE").toString(), d);
        });

        List<Map> reservedCoursesCounts = courseMapper.getReservedCoursesCount(request);
        reservedCoursesCounts.forEach(r -> {
            reservedCoursesCountsMap.put(r.get("RESERVE_DATE").toString(), r);
        });

        List<Map> completedCoursesCounts = courseMapper.getCompletedCoursesCount(request);
        completedCoursesCounts.forEach(c -> {
            completedCoursesCountsMap.put(c.get("COMPLETED_DATE").toString(), c);
        });

        List<Map> firstOrdersCounts = orderMapper.getFirstOrdersCount(request);
        firstOrdersCounts.forEach(f -> {
            firstOrdersCountsMap.put(f.get("PAY_DATE").toString(), f);
        });

        List<Map> repeatOrdersCounts = orderMapper.getRepeatOrdersCount(request);
        repeatOrdersCounts.forEach(r -> {
            repeatOrdersCountsMap.put(r.get("PAY_DATE").toString(), r);
        });

        Map options = new HashMap();
        options.put("startDate", parseUtc2Local(request.getFrom()));
        options.put("endDate", parseUtc2Local(request.getTo()));
        options.put("salesId", request.getSalesId());

        Map<String, Map> callDurationsMap = new HashMap();
        Map<String, Map> offsetsMap = new HashMap();

        List<Map> callDurations = reportMapper.getCallDuration(options);
        callDurations.forEach(c -> {
            callDurationsMap.put(c.get("S_TIME").toString(), c);
        });

        List<Map> offsets = reportMapper.getOffset(options);
        offsets.forEach(o -> {
            offsetsMap.put(o.get("OFFSET_DATE").toString(), o);
        });


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        List<SalesDailyResponse.SalesDailyData> data = new ArrayList<>();

        while (calendar.getTime().compareTo(endDate) < 0) {

            String dailyDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

            logger.info("dailyDate:" + dailyDate);

            SalesDailyResponse.SalesDailyData dailyData = new SalesDailyResponse.SalesDailyData();

            dailyData.setDate(dailyDate);

            Integer distributedTotalNumber = 0;
            Integer callDurationsMin = 0;
            Integer reservedCount = 0;
            Integer completedCount = 0;
            Integer offset = 0;
            Integer rewards = 0;
            Integer offsetAfter = 0;
            Integer firstOrderCount = 0;
            Integer firstOrderAmount = 0;
            Integer repeatOrderCount = 0;
            Integer repeatOrderAmount = 0;

            if (nonNull(distributedCountsMap.get(dailyDate))) {
                distributedTotalNumber = ((Long) distributedCountsMap.get(dailyDate).get("TOTAL_NUMBER")).intValue();
            }
            if (nonNull(callDurationsMap.get(dailyDate))) {
                callDurationsMin = ((BigDecimal) callDurationsMap.get(dailyDate).get("TOTAL_MIN")).intValue();
            }
            if (nonNull(reservedCoursesCountsMap.get(dailyDate))) {
                reservedCount = ((Long) reservedCoursesCountsMap.get(dailyDate).get("RESERVE_NUMBER")).intValue();
            }
            if (nonNull(completedCoursesCountsMap.get(dailyDate))) {
                completedCount = ((Long) completedCoursesCountsMap.get(dailyDate).get("COMPLETED_NUMBER")).intValue();
            }
            if (nonNull(offsetsMap.get(dailyDate))) {
                offset = (Integer) offsetsMap.get(dailyDate).get("OFFSET");
                rewards = (Integer) offsetsMap.get(dailyDate).get("REWARDS");
                offsetAfter = (Integer) offsetsMap.get(dailyDate).get("OFFSET_AFTER");
            }
            if (nonNull(firstOrdersCountsMap.get(dailyDate))) {
                firstOrderCount = ((Long) firstOrdersCountsMap.get(dailyDate).get("TOTAL_NUMBER")).intValue();
                firstOrderAmount = ((Double) firstOrdersCountsMap.get(dailyDate).get("TOTAL_TRADEAMOUNT")).intValue();
            }
            if (nonNull(repeatOrdersCountsMap.get(dailyDate))) {
                repeatOrderCount = ((Long) repeatOrdersCountsMap.get(dailyDate).get("TOTAL_NUMBER")).intValue();
                repeatOrderAmount = ((Double) repeatOrdersCountsMap.get(dailyDate).get("TOTAL_TRADEAMOUNT")).intValue();
            }

            dailyData.setDistributedNumber(distributedTotalNumber);
            dailyData.setBridgeDuration(callDurationsMin);
            dailyData.setOffset(offset);
            dailyData.setRewards(rewards);
            dailyData.setOffsetAfter(offsetAfter);
            dailyData.setReservedCount(reservedCount);
            dailyData.setCompletedCount(completedCount);
            dailyData.setFirstOrderCount(firstOrderCount);
            dailyData.setFirstOrderAmount(firstOrderAmount);
            dailyData.setRepeatOrderCount(repeatOrderCount);
            dailyData.setRepeatOrderAmount(repeatOrderAmount);

            data.add(dailyData);

            calendar.add(Calendar.DATE, 1);
        }

        SalesDailyResponse response = new SalesDailyResponse();
        response.setData(data);

        return response;
    }

}
