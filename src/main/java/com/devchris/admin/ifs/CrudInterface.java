package com.devchris.admin.ifs;

import com.devchris.admin.model.network.Header;

public interface CrudInterface<UserApiRequest, UserApiResponse> {
    Header<UserApiResponse> create(Header<UserApiRequest> userApiResponse);
    Header<UserApiResponse> read(Long id);
    Header<UserApiResponse> update(Header<UserApiRequest> userApiResponse);
    Header<UserApiResponse> delete(Long id);
}
