package com.ujiuye.pro.serviceimpl;

import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.bean.AttachmentExample;
import com.ujiuye.pro.bean.Function;
import com.ujiuye.pro.mapper.AttachmentMapper;
import com.ujiuye.pro.mapper.FunctionMapper;
import com.ujiuye.pro.service.AttachmentService;
import com.ujiuye.pro.service.FunctionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Resource
    private AttachmentMapper attachmentMapper;

    @Override
    public boolean saveInfo(Attachment attachment) {
        int insert = attachmentMapper.insert(attachment);
        if(insert>0){
            return true;
        }
        return false;
    }

    @Override
    public List<Attachment> showAll(AttachmentExample attachmentExample) {
        return attachmentMapper.selectByExample(attachmentExample);

}

    @Override
    public List<Attachment> getAddAndPro() {
        return attachmentMapper.getAttAndPro();
    }
}
