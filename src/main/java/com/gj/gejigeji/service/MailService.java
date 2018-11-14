package com.gj.gejigeji.service;

import com.gj.gejigeji.exception.BaseRuntimeException;
import com.gj.gejigeji.model.Mail;
import com.gj.gejigeji.model.MailContent;
import com.gj.gejigeji.repository.MailContentRepository;
import com.gj.gejigeji.repository.MailRepository;
import com.gj.gejigeji.util.ConstUtil;
import com.gj.gejigeji.vo.MailDetailParam;
import com.gj.gejigeji.vo.MailDetailVo;
import com.gj.gejigeji.vo.MailReadParam;
import com.gj.gejigeji.vo.OkResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MailService {

    @Autowired
    MailContentRepository mailContentRepository;

    @Autowired
    MailRepository mailRepository;


    public OkResult mailTest(String userId) {
        //=====登录添加添加邮件====
        // 奖励邮件
        Mail entity = new Mail();
        entity.setUserId(userId);
        entity.setTitle("测试邮件包含奖励");
        entity.setMailTime(new Date());
        entity.setLogo("testlogo");
        entity.setMailType(ConstUtil.MAIL_TYPE_JL);
        entity.setRead(false);
        entity.setMessage("message消息");
        Mail save = mailRepository.save(entity);

        MailContent entity1 = new MailContent();
        entity1.setItemCount(10);
        entity1.setMailId(save.getId());
        entity1.setItemId(1);
        mailContentRepository.save(entity1);
        // 通知邮件
        Mail entity2 = new Mail();
        entity2.setUserId(userId);
        entity2.setTitle("测试邮件包含奖励");
        entity2.setMailTime(new Date());
        entity2.setLogo("testlogo");
        entity2.setMailType(ConstUtil.MAIL_TYPE_TZ);
        entity2.setRead(false);
        entity2.setMessage("message消息");
        mailRepository.save(entity2);

        return new OkResult(true);
    }

    public MailDetailVo mailDetail(MailDetailParam mailDetailParam) {
        Mail mailEx = new Mail();
        mailEx.setId(mailDetailParam.getMailId());
        mailEx.setUserId(mailDetailParam.getAccountID());

        Mail mail = mailRepository.findOne(Example.of(mailEx)).orElse(null);

        if (mail == null) {
            throw new BaseRuntimeException();
        }
        //设置已读
        if (!mail.getRead()) {
            mail.setRead(true);
            mailRepository.save(mail);
        }
        MailDetailVo mailDetailVo = new MailDetailVo();
        mailDetailVo.setMessage(mail.getMessage());
        //判断邮件的类型
        if (mail.getMailType() == ConstUtil.MAIL_TYPE_TZ) {
            // 通知类型

        } else {
            // 奖励类型
            MailContent mailContentEx = new MailContent();
            mailContentEx.setMailId(mailDetailParam.getMailId());

            List<MailContent> all = mailContentRepository.findAll(Example.of(mailContentEx));
            mailDetailVo.setItemInfos(all);
        }

        return mailDetailVo;
    }

    public OkResult mailRead(MailReadParam mailReadParam) {

        String accountID = mailReadParam.getAccountID();
        Mail mailEx = new Mail();
        mailEx.setUserId(accountID);
        List<Mail> all = mailRepository.findAll(Example.of(mailEx));
        for (Mail mail : all) {
            if (!mail.getRead()) {
                mail.setRead(true);
                mailRepository.save(mail);
            }
        }

        return new OkResult(true);

    }

    public List<Mail> mailAll(MailReadParam mailReadParam) {
        String accountID = mailReadParam.getAccountID();
        Mail mailEx = new Mail();
        mailEx.setUserId(accountID);
        List<Mail> all = mailRepository.findAll(Example.of(mailEx));
        return all;
    }

    /**
     * 获取邮件奖励
     *
     * @param mailDetailParam
     * @return
     */
    public OkResult mailGet(MailDetailParam mailDetailParam) {
        Mail mailEx = new Mail();
        mailEx.setUserId(mailDetailParam.getAccountID());
        mailEx.setId(mailDetailParam.getMailId());

        Mail mail = mailRepository.findOne(Example.of(mailEx)).orElse(null);
        if (mail == null) {
            return new OkResult(false);
        }
        mail.setGet(true);
        mailRepository.save(mail);
        return new OkResult(true);
    }
}
