package com.my.restfulapi.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class AddUserListVo {

    private List<AddUserVo> addUserVoList;
}
