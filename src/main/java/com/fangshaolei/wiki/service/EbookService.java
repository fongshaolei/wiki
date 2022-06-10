package com.fangshaolei.wiki.service;

import com.fangshaolei.wiki.domain.Ebook;
import com.fangshaolei.wiki.domain.EbookExample;
import com.fangshaolei.wiki.mapper.EbookMapper;
import com.fangshaolei.wiki.req.EbookReq;
import com.fangshaolei.wiki.resp.EbookResp;
import com.fangshaolei.wiki.resp.PageResp;
import com.fangshaolei.wiki.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fangshaolei
 * @version 1.0.0
 * @ClassName EbookService
 * @Description
 * @createTime 2022/06/07 22:32
 **/
@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;
    /**
      * @author: fangshaolei
      * @description: 
      * @Date: 2022/6/7 22:54
      * @params: 
      * @return: 
      **/
    public PageResp<EbookResp> list(EbookReq req){
;
        EbookExample example = new EbookExample();
        EbookExample.Criteria criteria = example.createCriteria();
        if(!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        // 分页插件
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(example);
        // 获取其他信息
        PageInfo<Ebook> pageInfo = new PageInfo(ebookList);

        // 进行转换
        List<EbookResp> respList = CopyUtil.copyList(ebookList, EbookResp.class);
        // 插件对象封装
        PageResp<EbookResp> pageResp = new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(respList);

        return pageResp;
    }
}
