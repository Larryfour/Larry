package com.xuebaclass.sato.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.xuebaclass.sato.exception.CrmException;
import com.xuebaclass.sato.mapper.crm.ReportMapper;
import com.xuebaclass.sato.mapper.crm.SalesTargetMapper;
import com.xuebaclass.sato.mapper.sato.CourseMapper;
import com.xuebaclass.sato.mapper.sato.OrderMapper;
import com.xuebaclass.sato.mapper.sato.SalesMapper;
import com.xuebaclass.sato.model.Sales;
import com.xuebaclass.sato.model.SalesTarget;
import com.xuebaclass.sato.model.request.SalesDailyMyselfRequest;
import com.xuebaclass.sato.model.request.SalesDailyRequest;
import com.xuebaclass.sato.model.response.SalesDailyMyselfResponse;
import com.xuebaclass.sato.model.response.SalesDailyResponse;
import com.xuebaclass.sato.service.ReportService;
import com.xuebaclass.sato.utils.CurrentUser;
import com.xuebaclass.sato.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.xuebaclass.sato.utils.Utils.parseUtc2Local;
import static java.util.Objects.nonNull;

/**
 * Created by sunhao on 2017-10-13.
 */
@Transactional
@Service
public class ReportServiceImpl implements ReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    // todo: 暂时用0来区分未分组销售
    private static final Integer NO_GROUP = 0;
    private static final Integer NEW_GROUP = 1;
    private static final Integer RENEW_GROUP = 2;

    @Autowired
    SalesMapper salesMapper;

    @Autowired
    ReportMapper reportMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    SalesTargetMapper salesTargetMapper;

    @Override
    public SalesDailyResponse salesDaily(SalesDailyRequest request) throws Exception {

        if (StringUtils.isEmpty(request.getDailyDate())) {
            throw CrmException.newException("查询日期不能为空!");
        }

        List<Sales> sales = salesMapper.getSales();
        sales = sales.stream().filter(s -> s.getGroupId() > NO_GROUP).collect(Collectors.toList());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dailyDate = simpleDateFormat.parse(request.getDailyDate());

        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM");
        String targetMonth = targetFormat.format(dailyDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dailyDate);
        calendar.add(Calendar.DATE, 1);
        String to = Utils.str2utc(simpleDateFormat.format(calendar.getTime()));

        Calendar firstDay = Calendar.getInstance();
        firstDay.setTime(dailyDate);
        firstDay.add(Calendar.MONTH, 0);
        firstDay.set(Calendar.DAY_OF_MONTH, 1);
        String from = Utils.str2utc(simpleDateFormat.format(firstDay.getTime()));

        // 当日个人业务完成状况
        List<SalesDailyResponse.DailyData> dailyDatas = new ArrayList<>();

        //当月个人
        List<SalesDailyResponse.PersonalMonthData> personalMonthDatas = new ArrayList<>();

        Map<String, Integer> salesGroupMap = new HashMap<>();

        sales.forEach(s -> {

            salesGroupMap.put(s.getId(), s.getGroupId());

            SalesDailyMyselfRequest salesDailyMyselfRequest = new SalesDailyMyselfRequest();
            salesDailyMyselfRequest.setSalesId(s.getId());
            salesDailyMyselfRequest.setUserName(s.getUserName());
            salesDailyMyselfRequest.setFrom(from);
            salesDailyMyselfRequest.setTo(to);

            Map<String, Map> distributedCountsMap = new HashMap();
            Map<String, Map> reservedCoursesCountsMap = new HashMap();
            Map<String, Map> completedCoursesCountsMap = new HashMap();
            Map<String, Map> firstOrdersCountsMap = new HashMap();
            Map<String, Map> repeatOrdersCountsMap = new HashMap();

            List<Map> distributedCounts = reportMapper.getDistributedCount(salesDailyMyselfRequest);
            distributedCounts.forEach(d -> {
                distributedCountsMap.put(d.get("DIS_DATE").toString(), d);
            });

            List<Map> reservedCoursesCounts = courseMapper.getReservedCoursesCount(salesDailyMyselfRequest);
            reservedCoursesCounts.forEach(r -> {
                reservedCoursesCountsMap.put(r.get("RESERVE_DATE").toString(), r);
            });

            List<Map> completedCoursesCounts = courseMapper.getCompletedCoursesCount(salesDailyMyselfRequest);
            completedCoursesCounts.forEach(c -> {
                completedCoursesCountsMap.put(c.get("COMPLETED_DATE").toString(), c);
            });

            Integer totalOrders = 0;
            Integer totalAmount = 0;

            List<Map> firstOrdersCounts = orderMapper.getFirstOrdersCount(salesDailyMyselfRequest);


            if (NEW_GROUP.equals(s.getGroupId())) {
                for (Map f : firstOrdersCounts) {
                    firstOrdersCountsMap.put(f.get("PAY_DATE").toString(), f);

                    totalOrders = totalOrders + ((Long) f.get("TOTAL_NUMBER")).intValue();
                    totalAmount = totalAmount + ((Double) f.get("TOTAL_TRADEAMOUNT")).intValue();
                }
            }


            List<Map> repeatOrdersCounts = orderMapper.getRepeatOrdersCount(salesDailyMyselfRequest);

            if (RENEW_GROUP.equals(s.getGroupId())) {
                for (Map r : repeatOrdersCounts) {
                    repeatOrdersCountsMap.put(r.get("PAY_DATE").toString(), r);

                    totalOrders = totalOrders + ((Long) r.get("TOTAL_NUMBER")).intValue();
                    totalAmount = totalAmount + ((Double) r.get("TOTAL_TRADEAMOUNT")).intValue();
                }
            }

            Map options = new HashMap();
            options.put("startDate", from);
            options.put("endDate", to);
            options.put("salesId", s.getId());

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

            SalesDailyResponse.DailyData dailyData = new SalesDailyResponse.DailyData();
            dailyData.setSalesName(s.getName());
            dailyData.setSalesId(s.getId());
            dailyData.setGroupId(s.getGroupId());


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
            String comment = "";

            if (nonNull(distributedCountsMap.get(request.getDailyDate()))) {
                distributedTotalNumber = ((Long) distributedCountsMap.get(request.getDailyDate()).get("TOTAL_NUMBER")).intValue();
            }
            if (nonNull(callDurationsMap.get(request.getDailyDate()))) {
                callDurationsMin = ((BigDecimal) callDurationsMap.get(request.getDailyDate()).get("TOTAL_MIN")).intValue();
            }
            if (nonNull(reservedCoursesCountsMap.get(request.getDailyDate()))) {
                reservedCount = ((Long) reservedCoursesCountsMap.get(request.getDailyDate()).get("RESERVE_NUMBER")).intValue();
            }
            if (nonNull(completedCoursesCountsMap.get(request.getDailyDate()))) {
                completedCount = ((Long) completedCoursesCountsMap.get(request.getDailyDate()).get("COMPLETED_NUMBER")).intValue();
            }
            if (nonNull(offsetsMap.get(request.getDailyDate()))) {
                offset = (Integer) offsetsMap.get(request.getDailyDate()).get("OFFSET");
                rewards = (Integer) offsetsMap.get(request.getDailyDate()).get("REWARDS");
                offsetAfter = (Integer) offsetsMap.get(request.getDailyDate()).get("OFFSET_AFTER");
                comment = (String) Optional.ofNullable(offsetsMap.get(request.getDailyDate()).get("COMMENT")).orElse("");
            }
            if (nonNull(firstOrdersCountsMap.get(request.getDailyDate()))) {
                firstOrderCount = ((Long) firstOrdersCountsMap.get(request.getDailyDate()).get("TOTAL_NUMBER")).intValue();
                firstOrderAmount = ((Double) firstOrdersCountsMap.get(request.getDailyDate()).get("TOTAL_TRADEAMOUNT")).intValue();
            }
            if (nonNull(repeatOrdersCountsMap.get(request.getDailyDate()))) {
                repeatOrderCount = ((Long) repeatOrdersCountsMap.get(request.getDailyDate()).get("TOTAL_NUMBER")).intValue();
                repeatOrderAmount = ((Double) repeatOrdersCountsMap.get(request.getDailyDate()).get("TOTAL_TRADEAMOUNT")).intValue();
            }

            // 当日个人
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
            dailyData.setComment(comment);
            dailyDatas.add(dailyData);

            // 当月个人完成情况
            SalesDailyResponse.PersonalMonthData personalMonthData = new SalesDailyResponse.PersonalMonthData();
            personalMonthData.setSalesId(s.getId());
            personalMonthData.setName(s.getName());
            personalMonthData.setCompletedAmount(totalAmount);
            personalMonthData.setCompletedOrderCount(totalOrders);

            personalMonthDatas.add(personalMonthData);

        });

        // 当日销售概况
        SalesDailyResponse.Daily daily = new SalesDailyResponse.Daily();
        SalesDailyResponse.DailyNew dailyNew = new SalesDailyResponse.DailyNew();
        SalesDailyResponse.DailyReNew dailyReNew = new SalesDailyResponse.DailyReNew();

        Map<Integer, Long> callerCountMap = dailyDatas.stream().collect(Collectors.groupingBy(SalesDailyResponse.DailyData::getGroupId,
                Collectors.counting()));
        dailyNew.setCallerCount(callerCountMap.get(NEW_GROUP).intValue());
        dailyReNew.setCallerCount(callerCountMap.get(RENEW_GROUP).intValue());

        Map<Integer, Integer> distributionCountMap = dailyDatas.stream().collect(
                Collectors.groupingBy(SalesDailyResponse.DailyData::getGroupId,
                        Collectors.reducing(0, SalesDailyResponse.DailyData::getDistributedNumber, Integer::sum)));
        dailyNew.setDistributionCount(distributionCountMap.get(NEW_GROUP).intValue());

        Map<Integer, Integer> completedCountMap = dailyDatas.stream().collect(
                Collectors.groupingBy(SalesDailyResponse.DailyData::getGroupId,
                        Collectors.reducing(0, SalesDailyResponse.DailyData::getCompletedCount, Integer::sum)));
        dailyNew.setCompletedCount(completedCountMap.get(NEW_GROUP).intValue());

        Map<Integer, Integer> firstOrderCountMap = dailyDatas.stream().collect(
                Collectors.groupingBy(SalesDailyResponse.DailyData::getGroupId,
                        Collectors.reducing(0, SalesDailyResponse.DailyData::getFirstOrderCount, Integer::sum)));

        Map<Integer, Integer> firstOrderAmountMap = dailyDatas.stream().collect(
                Collectors.groupingBy(SalesDailyResponse.DailyData::getGroupId,
                        Collectors.reducing(0, SalesDailyResponse.DailyData::getFirstOrderAmount, Integer::sum)));

        Map<Integer, Integer> repeatOrderCountMap = dailyDatas.stream().collect(
                Collectors.groupingBy(SalesDailyResponse.DailyData::getGroupId,
                        Collectors.reducing(0, SalesDailyResponse.DailyData::getRepeatOrderCount, Integer::sum)));

        Map<Integer, Integer> repeatOrderAmountMap = dailyDatas.stream().collect(
                Collectors.groupingBy(SalesDailyResponse.DailyData::getGroupId,
                        Collectors.reducing(0, SalesDailyResponse.DailyData::getRepeatOrderAmount, Integer::sum)));

        // 当日销售概况
        dailyNew.setOrderCount(firstOrderCountMap.get(NEW_GROUP).intValue() + repeatOrderCountMap.get(NEW_GROUP).intValue());
        dailyNew.setOrderAmount(firstOrderAmountMap.get(NEW_GROUP).intValue() + repeatOrderAmountMap.get(NEW_GROUP).intValue());
        dailyReNew.setOrderCount(firstOrderCountMap.get(RENEW_GROUP).intValue() + repeatOrderCountMap.get(RENEW_GROUP).intValue());
        dailyReNew.setOrderAmount(firstOrderAmountMap.get(RENEW_GROUP).intValue() + repeatOrderAmountMap.get(RENEW_GROUP).intValue());

        daily.setDailyNew(dailyNew);
        daily.setDailyReNew(dailyReNew);


        List<SalesTarget> targets = salesTargetMapper.getTargets(targetMonth);
        Map targetsMap = new HashMap();
        targets.forEach(t -> {
            t.setGroupId(salesGroupMap.get(t.getSalesId()));
            targetsMap.put(t.getSalesId(), t);
        });

        //格式化小数
        DecimalFormat df = new DecimalFormat("0.00");
        personalMonthDatas.forEach(p -> {
            SalesTarget target = (SalesTarget) targetsMap.get(p.getSalesId());
            if (nonNull(target)) {
                p.setAmountCompletedPercent(df.format(((float) p.getCompletedAmount() / target.getTargetAmount()) * 100));
                p.setOrderCompletedPercent(df.format(((float) p.getCompletedOrderCount() / target.getTargetOrders()) * 100));
                p.setTargetAmount(Optional.of(target.getTargetAmount()).orElse(0));
                p.setTargetOrderCount(target.getTargetOrders());
                p.setGroupId(target.getGroupId());
            } else {
                p.setAmountCompletedPercent("0.00");
                p.setOrderCompletedPercent("0.00");
                p.setTargetAmount(0);
                p.setTargetOrderCount(0);
                p.setGroupId(salesGroupMap.get(p.getSalesId()));
            }
        });

        Map<Integer, Integer> amountTargetMonthMap = targets.stream().collect(
                Collectors.groupingBy(SalesTarget::getGroupId,
                        Collectors.reducing(0, SalesTarget::getTargetAmount, Integer::sum)));

        Map<Integer, Integer> ordersTargetMonthMap = targets.stream().collect(
                Collectors.groupingBy(SalesTarget::getGroupId,
                        Collectors.reducing(0, SalesTarget::getTargetOrders, Integer::sum)));

        Map<Integer, Integer> amountCompletedMonthMap = personalMonthDatas.stream().collect(
                Collectors.groupingBy(SalesDailyResponse.PersonalMonthData::getGroupId,
                        Collectors.reducing(0, SalesDailyResponse.PersonalMonthData::getCompletedAmount, Integer::sum)));

        Map<Integer, Integer> ordersCompletedMonthMap = personalMonthDatas.stream().collect(
                Collectors.groupingBy(SalesDailyResponse.PersonalMonthData::getGroupId,
                        Collectors.reducing(0, SalesDailyResponse.PersonalMonthData::getCompletedOrderCount, Integer::sum)));

        SalesDailyResponse.MonthData newMonth = new SalesDailyResponse.MonthData();
        SalesDailyResponse.MonthData reNewMonth = new SalesDailyResponse.MonthData();

        if (!amountTargetMonthMap.isEmpty()) {
            newMonth.setTargetAmount(amountTargetMonthMap.get(NEW_GROUP).intValue());
            reNewMonth.setTargetAmount(amountTargetMonthMap.get(RENEW_GROUP).intValue());
        } else {
            newMonth.setTargetAmount(0);
            reNewMonth.setTargetAmount(0);
        }

        if (!ordersTargetMonthMap.isEmpty()) {
            newMonth.setTargetOrderCount(ordersTargetMonthMap.get(NEW_GROUP).intValue());
            reNewMonth.setTargetOrderCount(ordersTargetMonthMap.get(RENEW_GROUP).intValue());
        } else {
            newMonth.setTargetOrderCount(0);
            reNewMonth.setTargetOrderCount(0);
        }

        if (!amountCompletedMonthMap.isEmpty()) {
            newMonth.setCompletedAmount(amountCompletedMonthMap.get(NEW_GROUP).intValue());
            reNewMonth.setCompletedAmount(amountCompletedMonthMap.get(RENEW_GROUP).intValue());
        } else {
            newMonth.setCompletedAmount(0);
            reNewMonth.setCompletedAmount(0);
        }
        if (!ordersCompletedMonthMap.isEmpty()) {
            newMonth.setCompletedOrderCount(ordersCompletedMonthMap.get(NEW_GROUP).intValue());
            reNewMonth.setCompletedOrderCount(ordersCompletedMonthMap.get(RENEW_GROUP).intValue());
        } else {
            newMonth.setCompletedOrderCount(0);
            reNewMonth.setCompletedOrderCount(0);
        }

        if (newMonth.getTargetOrderCount() == 0) {
            newMonth.setOrderCompletedPercent("0.00");
        } else {
            newMonth.setOrderCompletedPercent(df.format(((float) newMonth.getCompletedOrderCount() / newMonth.getTargetOrderCount()) * 100));
        }
        if (newMonth.getTargetAmount() == 0) {
            newMonth.setAmountCompletedPercent("0.00");
        } else {
            newMonth.setAmountCompletedPercent(df.format(((float) newMonth.getCompletedAmount() / newMonth.getTargetAmount()) * 100));
        }
        if (reNewMonth.getTargetOrderCount() == 0) {
            reNewMonth.setOrderCompletedPercent("0.00");
        } else {
            reNewMonth.setOrderCompletedPercent(df.format(((float) reNewMonth.getCompletedOrderCount() / reNewMonth.getTargetOrderCount()) * 100));
        }
        if (reNewMonth.getTargetAmount() == 0) {
            reNewMonth.setAmountCompletedPercent("0.00");
        } else {
            reNewMonth.setAmountCompletedPercent(df.format(((float) reNewMonth.getCompletedAmount() / reNewMonth.getTargetAmount()) * 100));
        }

        List<Map> orders = orderMapper.getRepeatOrderForTeacher(from, to);
        SalesDailyResponse.TeacherRepeatOrder teacherRepeatOrder = new SalesDailyResponse.TeacherRepeatOrder();
        teacherRepeatOrder.setOrderCount(orders.size());
        teacherRepeatOrder.setOrderAmount(new Double(orders.stream().mapToDouble(o -> (Double) o.get("TRADEAMOUNT")).sum()).intValue());
        teacherRepeatOrder.setTotalAmount(reNewMonth.getCompletedAmount() + teacherRepeatOrder.getOrderAmount());

        SalesDailyResponse.Month month = new SalesDailyResponse.Month();
        month.setMonthNews(newMonth);
        month.setMonthReNews(reNewMonth);
        month.setTeacherRepeatOrder(teacherRepeatOrder);

        SalesDailyResponse response = new SalesDailyResponse();
        response.setPersonalDaily(dailyDatas);
        response.setDaily(daily);
        response.setPersonalMonth(personalMonthDatas);
        response.setMonth(month);

        return response;
    }


    @Override
    public SalesDailyMyselfResponse salesMyselfDaily(SalesDailyMyselfRequest request) throws Exception {

        if (StringUtils.isEmpty(request.getFrom()) || StringUtils.isEmpty(request.getTo())) {
            throw CrmException.newException("查询区间不能为空!");
        }

        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(parseUtc2Local(request.getFrom()));
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(parseUtc2Local(request.getTo()));

        logger.info("startDate:" + startDate + "endDate:" + endDate);

        Sales sales = salesMapper.getSalesByUserName(CurrentUser.getInstance().getCurrentAuditorName());
        request.setSalesId(sales.getId());
        request.setUserName(sales.getUserName());

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

        List<SalesDailyMyselfResponse.SalesDailyData> data = new ArrayList<>();

        while (calendar.getTime().compareTo(endDate) < 0) {

            String dailyDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());

            logger.info("dailyDate:" + dailyDate);

            SalesDailyMyselfResponse.SalesDailyData dailyData = new SalesDailyMyselfResponse.SalesDailyData();

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
            String comment = "";

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
                comment =  (String) Optional.ofNullable(offsetsMap.get(dailyDate).get("COMMENT")).orElse("");
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
            dailyData.setComment(comment);

            data.add(dailyData);

            calendar.add(Calendar.DATE, 1);
        }

        SalesDailyMyselfResponse response = new SalesDailyMyselfResponse();
        response.setData(data);

        return response;
    }

}
