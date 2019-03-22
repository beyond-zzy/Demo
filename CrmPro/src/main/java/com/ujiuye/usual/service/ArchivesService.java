package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.Archives;
import org.omg.CORBA.ARG_IN;

import java.util.List;

public interface ArchivesService {

    public int BatchAdd(List<Archives> list);

}
