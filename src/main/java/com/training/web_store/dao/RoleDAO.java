package com.training.web_store.dao;

import com.training.web_store.bean.account.Role;
import com.training.web_store.dao.exception.DAOException;
import com.training.web_store.util.exception.StorageException;

import java.util.List;

public interface RoleDAO {
    List<Role> getRoles() throws DAOException, StorageException;
}
