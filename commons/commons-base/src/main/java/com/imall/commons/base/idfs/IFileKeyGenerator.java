package com.imall.commons.base.idfs;

import java.io.File;

public interface IFileKeyGenerator {
    String create(String name);
    String create(File file, Long orgId);
}
