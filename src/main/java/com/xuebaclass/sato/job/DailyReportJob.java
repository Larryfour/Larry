package com.xuebaclass.sato.job;

import com.xuebaclass.sato.model.request.SalesDailyRequest;
import com.xuebaclass.sato.model.response.SalesDailyResponse;
import com.xuebaclass.sato.service.ReportService;
import com.xuebaclass.sato.utils.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class DailyReportJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(DailyReportJob.class);
    private static final String HTML_NAME = "sales-daily.html";

    @Value("${daily.report.enable}")
    private Boolean enable;

    @Autowired
    ReportService reportService;

    //每天早晨9点发日报 cron配置时间为utc时间，北京时间要+8小时
    @Scheduled(cron = "0 0 1 * * ? ")
    @Override
    public void apply() {

        logger.info("start send daily report.");

        if (!enable) {
            logger.info("enable is closed.");
            return;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        String dailyDate = simpleDateFormat.format(calendar.getTime());

        try {
            SalesDailyRequest request = new SalesDailyRequest();
            request.setDailyDate(dailyDate);

            SalesDailyResponse response = reportService.salesDaily(request);

            String htmlTemplate = EmailUtil.loadRestPwdMailHtml(HTML_NAME);

            StringBuffer dailyNewBuffer = new StringBuffer();
            dailyNewBuffer.append("<tr><td>");
            dailyNewBuffer.append(response.getDaily().getDailyNew().getCallerCount());
            dailyNewBuffer.append("人</td><td>");
            dailyNewBuffer.append(response.getDaily().getDailyNew().getDistributionCount());
            dailyNewBuffer.append("条</td><td>");
            dailyNewBuffer.append(response.getDaily().getDailyNew().getCompletedCount());
            dailyNewBuffer.append("节</td><td>");
            dailyNewBuffer.append(response.getDaily().getDailyNew().getOrderCount());
            dailyNewBuffer.append("</td><td>");
            dailyNewBuffer.append(response.getDaily().getDailyNew().getOrderAmount());
            dailyNewBuffer.append("</td></tr>");
            String dailyNew = dailyNewBuffer.toString();
            logger.info("dailyNew:[" + dailyNew + "]");

            htmlTemplate = htmlTemplate.replace("#{dailyNew}", dailyNew);

            StringBuffer dailyReNewBuffer = new StringBuffer();
            dailyReNewBuffer.append("<tr><td>");
            dailyReNewBuffer.append(response.getDaily().getDailyReNew().getCallerCount());
            dailyReNewBuffer.append("人</td><td>");
            dailyReNewBuffer.append(response.getDaily().getDailyReNew().getOrderCount());
            dailyReNewBuffer.append("</td><td>");
            dailyReNewBuffer.append(response.getDaily().getDailyReNew().getOrderAmount());
            dailyReNewBuffer.append("</td><td></td><td></td></tr>");
            String dailyReNew = dailyReNewBuffer.toString();
            logger.info("dailyReNew:[" + dailyReNew + "]");

            htmlTemplate = htmlTemplate.replace("#{dailyReNew}", dailyReNew);


            StringBuffer personalDailyBuffer = new StringBuffer();

            for (SalesDailyResponse.DailyData dailyData : response.getPersonalDaily()) {
                StringBuffer dailyDataBuffer = new StringBuffer();

                dailyDataBuffer.append("<tr><td>");
                dailyDataBuffer.append(dailyData.getSalesName());
                dailyDataBuffer.append("</td><td>");
                dailyDataBuffer.append(dailyData.getDistributedNumber());
                dailyDataBuffer.append("</td><td>");
                dailyDataBuffer.append(dailyData.getBridgeDuration());
                dailyDataBuffer.append("min</td><td>");
                dailyDataBuffer.append(dailyData.getOffset());
                dailyDataBuffer.append("min</td><td>");
                dailyDataBuffer.append(dailyData.getRewards());
                dailyDataBuffer.append("min</td><td>");
                dailyDataBuffer.append(dailyData.getOffsetAfter());
                dailyDataBuffer.append("min</td><td>");
                dailyDataBuffer.append(dailyData.getComment());
                dailyDataBuffer.append("</td><td>");
                dailyDataBuffer.append(dailyData.getReservedCount());
                dailyDataBuffer.append("</td><td>");
                dailyDataBuffer.append(dailyData.getCompletedCount());
                dailyDataBuffer.append("</td><td>");
                dailyDataBuffer.append(dailyData.getFirstOrderCount());
                dailyDataBuffer.append("</td><td>");
                dailyDataBuffer.append(dailyData.getFirstOrderAmount());
                dailyDataBuffer.append("</td><td>");
                dailyDataBuffer.append(dailyData.getRepeatOrderCount());
                dailyDataBuffer.append("</td><td>");
                dailyDataBuffer.append(dailyData.getRepeatOrderAmount());
                dailyDataBuffer.append("</td></tr>");

                personalDailyBuffer.append(dailyDataBuffer.toString());
            }
            String personalDaily = personalDailyBuffer.toString();
            logger.info("personalDaily:[" + personalDaily + "]");
            htmlTemplate = htmlTemplate.replace("#{personalDaily}", personalDaily);


            StringBuffer monthNewsBuffer = new StringBuffer();
            monthNewsBuffer.append("<tr><td>");
            monthNewsBuffer.append(response.getMonth().getMonthNews().getTargetOrderCount());
            monthNewsBuffer.append("</td><td>");
            monthNewsBuffer.append(response.getMonth().getMonthNews().getCompletedOrderCount());
            monthNewsBuffer.append("</td><td>");
            monthNewsBuffer.append(response.getMonth().getMonthNews().getOrderCompletedPercent());
            monthNewsBuffer.append("%</td><td>");
            monthNewsBuffer.append(response.getMonth().getMonthNews().getTargetAmount());
            monthNewsBuffer.append("</td><td>");
            monthNewsBuffer.append(response.getMonth().getMonthNews().getCompletedAmount());
            monthNewsBuffer.append("</td><td>");
            monthNewsBuffer.append(response.getMonth().getMonthNews().getAmountCompletedPercent());
            monthNewsBuffer.append("%</td></tr>");
            String monthNews = monthNewsBuffer.toString();
            logger.info("monthNews:[" + monthNews + "]");
            htmlTemplate = htmlTemplate.replace("#{monthNews}", monthNews);

            StringBuffer monthReNewsBuffer = new StringBuffer();
            monthReNewsBuffer.append("<tr><td>");
            monthReNewsBuffer.append(response.getMonth().getMonthReNews().getTargetOrderCount());
            monthReNewsBuffer.append("</td><td>");
            monthReNewsBuffer.append(response.getMonth().getMonthReNews().getCompletedOrderCount());
            monthReNewsBuffer.append("</td><td>");
            monthReNewsBuffer.append(response.getMonth().getMonthReNews().getOrderCompletedPercent());
            monthReNewsBuffer.append("%</td><td>");
            monthReNewsBuffer.append(response.getMonth().getMonthReNews().getTargetAmount());
            monthReNewsBuffer.append("</td><td>");
            monthReNewsBuffer.append(response.getMonth().getMonthReNews().getCompletedAmount());
            monthReNewsBuffer.append("</td><td>");
            monthReNewsBuffer.append(response.getMonth().getMonthReNews().getAmountCompletedPercent());
            monthReNewsBuffer.append("%</td></tr>");

            String monthReNews = monthReNewsBuffer.toString();
            logger.info("monthReNews:[" + monthReNews + "]");
            htmlTemplate = htmlTemplate.replace("#{monthReNews}", monthReNews);

            StringBuffer teacherRepeatOrderBuffer = new StringBuffer();
            teacherRepeatOrderBuffer.append("<tr><td>");
            teacherRepeatOrderBuffer.append(response.getMonth().getTeacherRepeatOrder().getOrderCount());
            teacherRepeatOrderBuffer.append("</td><td>");
            teacherRepeatOrderBuffer.append(response.getMonth().getTeacherRepeatOrder().getOrderAmount());
            teacherRepeatOrderBuffer.append("</td><td>");
            teacherRepeatOrderBuffer.append(response.getMonth().getTeacherRepeatOrder().getTotalAmount());
            teacherRepeatOrderBuffer.append("</td><td></td><td></td><td></td></tr>");

            String teacherRepeatOrder = teacherRepeatOrderBuffer.toString();
            logger.info("teacherRepeatOrder:[" + teacherRepeatOrder + "]");
            htmlTemplate = htmlTemplate.replace("#{teacherRepeatOrder}", teacherRepeatOrder);


            StringBuffer personalMonthBuffer = new StringBuffer();

            for (SalesDailyResponse.PersonalMonthData personalMonthData : response.getPersonalMonth()) {
                StringBuffer personalMonthDataBuffer = new StringBuffer();

                personalMonthDataBuffer.append("<tr><td>");
                personalMonthDataBuffer.append(personalMonthData.getName());
                personalMonthDataBuffer.append("</td><td>");
                personalMonthDataBuffer.append(personalMonthData.getTargetAmount());
                personalMonthDataBuffer.append("</td><td>");
                personalMonthDataBuffer.append(personalMonthData.getCompletedAmount());
                personalMonthDataBuffer.append("</td><td>");
                personalMonthDataBuffer.append(personalMonthData.getAmountCompletedPercent());
                personalMonthDataBuffer.append("%</td><td>");
                personalMonthDataBuffer.append(personalMonthData.getTargetOrderCount());
                personalMonthDataBuffer.append("</td><td>");
                personalMonthDataBuffer.append(personalMonthData.getCompletedOrderCount());
                personalMonthDataBuffer.append("</td><td>");
                personalMonthDataBuffer.append(personalMonthData.getOrderCompletedPercent());
                personalMonthDataBuffer.append("%</td></tr>");

                personalMonthBuffer.append(personalMonthDataBuffer.toString());
            }
            String personalMonth = personalMonthBuffer.toString();
            logger.info("personalMonth:[" + personalMonth + "]");

            htmlTemplate = htmlTemplate.replace("#{personalMonth}", personalMonth);

            logger.info("report detail template:[" + htmlTemplate + "]");


            EmailUtil.sendSalesDailyReport(dailyDate, htmlTemplate);

            logger.info("send daily[" + dailyDate + "] report successful.");

        } catch (Exception e) {
            logger.error("send daily report error occurred.", e);
        }

    }
}