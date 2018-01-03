package com.xuebaclass.sato.service.impl;

import com.xuebaclass.sato.exception.CrmException;
import com.xuebaclass.sato.mapper.crm.SalesTargetMapper;
import com.xuebaclass.sato.mapper.sato.SalesMapper;
import com.xuebaclass.sato.model.SalesTarget;
import com.xuebaclass.sato.model.request.SalesTargetRequest;
import com.xuebaclass.sato.model.response.SalesTargetResponse;
import com.xuebaclass.sato.service.SalesTargetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Objects.nonNull;


@Transactional
@Service
public class SalesTargetServiceImpl implements SalesTargetService {
    private static final Logger logger = LoggerFactory.getLogger(SalesTargetServiceImpl.class);

    @Resource
    private SalesTargetMapper salesTargetMapper;

    @Resource
    private SalesMapper salesMapper;

    @Override
    public List<SalesTargetResponse> getSalesMonthTarget(String targetMonth) {
        logger.info(String.format("get sales month[%s] target", targetMonth));

        List<SalesTargetResponse> responses = new ArrayList<>();

        List<SalesTarget> targets = salesTargetMapper.getTargets(targetMonth);

        targets.forEach((t) -> {
            SalesTargetResponse response = new SalesTargetResponse();
            response.setId(t.getId());
            response.setSalesId(t.getSalesId());
            response.setTargetAmount(t.getTargetAmount());
            response.setTargetOrders(t.getTargetOrders());
            response.setTargetMonth(t.getTargetMonth());
            response.setSalesName(salesMapper.get(t.getSalesId()).getName());
            responses.add(response);
        });

        return responses;
    }

    @Override
    public void setSalesMonthTarget(SalesTargetRequest request) throws Exception {
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM");
        String targetMonth = targetFormat.format(new Date());
        if (request.getTargetMonth().compareTo(targetMonth) <= 0
                && (request.getUpdateTargets().size() > 0 || request.getDeleteTargetIds().size() > 0)) {
            throw CrmException.newException("禁止对本月目标进行变更!");
        }

        List<Integer> deleteTargetIds = request.getDeleteTargetIds();
        if (nonNull(deleteTargetIds)) {
            deleteTargetIds.forEach((id) -> {
                salesTargetMapper.deleteTarget(id);
            });
        }


        List<SalesTarget> updateTargets = request.getUpdateTargets();
        if (nonNull(updateTargets)) {
            updateTargets.forEach((u) -> {
                salesTargetMapper.update(u);
            });
        }

        List<SalesTargetRequest.CreateSalesTarget> createTargets = request.getCreateTargets();
        if (nonNull(createTargets)) {
            createTargets.forEach((c) -> {
                SalesTarget existTarget = salesTargetMapper.getBySalesIdAndMonth(c.getSalesId(), request.getTargetMonth());
                if (nonNull(existTarget)) {
                    throw new CrmException("增加销售已经存在销售目标");
                }
                c.setTargetMonth(request.getTargetMonth());
                salesTargetMapper.create(c);
            });
        }
    }
}
