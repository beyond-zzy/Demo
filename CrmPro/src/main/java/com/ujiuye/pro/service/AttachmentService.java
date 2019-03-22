package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.bean.AttachmentExample;
import com.ujiuye.pro.bean.Function;

import java.util.List;

public interface AttachmentService {

    public boolean  saveInfo(Attachment attachment);

    public List<Attachment> showAll(AttachmentExample attachmentExample);

    public List<Attachment> getAddAndPro();
}
